package by.estore.web.controller.command.impl.page;

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
import java.io.IOException;
import java.util.List;

public class MainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MainPageCommand.class);

    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private static final int PRODUCT_COUNT = 8;

    private static final String LOW_COST_PRODUCTS = "products";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> products = productService.findLowCostProductsByLimit(PRODUCT_COUNT);
            request.setAttribute(LOW_COST_PRODUCTS, products);
        } catch (ServiceException e) {
            logger.error(e);
        }

        request.getRequestDispatcher(RouteHolder.FORWARD_MAIN_PAGE).forward(request, response);
    }
}
