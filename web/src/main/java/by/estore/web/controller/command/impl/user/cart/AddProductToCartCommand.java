package by.estore.web.controller.command.impl.user.cart;

import by.estore.entity.Product;
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

    public static final String PRODUCT_ID_PARAM = "product-id";

    public static final String REFERER_HEADER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String referer = request.getHeader(REFERER_HEADER);
        String productId = request.getParameter(PRODUCT_ID_PARAM);

        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_IN_PAGE);
            return;
        }

        Product product = null;
        try {
            product = ServiceFactory.getInstance().getProductService().getProductById(Long.parseLong(productId));
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        if (product != null) {
            Set<Product> cartProducts;
            if (session.getAttribute("cartProducts") == null) {
                cartProducts = new LinkedHashSet<>();
                session.setAttribute("cartProducts", cartProducts);
            } else {
                cartProducts = (Set<Product>) session.getAttribute("cartProducts");
            }
            product.setQuantity(1);
            cartProducts.add(product);
        }

        response.sendRedirect(referer);
    }
}
