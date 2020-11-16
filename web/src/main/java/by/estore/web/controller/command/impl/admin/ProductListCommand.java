package by.estore.web.controller.command.impl.admin;

import by.estore.entity.Product;
import by.estore.service.ProductService;
import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductListCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ProductListCommand.class);

    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private static final String PRODUCTS_ATTR = "products";
    private static final String CONTENT_ATTR = "content";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products;

        try {
            products = productService.findAllProducts();
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        request.setAttribute(PRODUCTS_ATTR, products);
        request.setAttribute(CONTENT_ATTR, RouteHolder.PRODUCT_LIST);

        request.getRequestDispatcher(RouteHolder.FORWARD_ADMIN_PAGE).forward(request, response);
    }
}
