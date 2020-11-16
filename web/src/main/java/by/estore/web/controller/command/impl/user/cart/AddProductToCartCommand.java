package by.estore.web.controller.command.impl.user.cart;

import by.estore.entity.Product;
import by.estore.service.ProductService;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class AddProductToCartCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddProductToCartCommand.class);

    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private static final String PRODUCT_ID_PARAM = "product-id";

    private static final String USER_ATTR = "user";
    private static final String CART_PRODUCTS_ATTR = "cartProducts";

    private static final String REFERER_HEADER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String referer = request.getHeader(REFERER_HEADER);
        String productId = request.getParameter(PRODUCT_ID_PARAM);

        HttpSession session = request.getSession();

        if (session.getAttribute(USER_ATTR) == null) {
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_IN_PAGE);
            return;
        }

        Product product = null;
        try {
            product = productService.findProductById(Long.parseLong(productId));
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        if (product != null) {
            Set<Product> cartProducts;
            if (session.getAttribute(CART_PRODUCTS_ATTR) == null) {
                cartProducts = new LinkedHashSet<>();
                session.setAttribute(CART_PRODUCTS_ATTR, cartProducts);
            } else {
                cartProducts = (Set<Product>) session.getAttribute(CART_PRODUCTS_ATTR);
            }
            Product existProduct = null;
            for (Product product1 : cartProducts) {
                if (product1.getId().equals(product.getId())) {
                    existProduct = product1;
                    break;
                }
            }
            if (existProduct == null) {
                product.setQuantity(1);
                cartProducts.add(product);
            } else {
                existProduct.setQuantity(existProduct.getQuantity() + 1);
            }
        }

        response.sendRedirect(referer);
    }
}
