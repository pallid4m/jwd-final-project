package by.estore.controller.command.impl.page;

import by.estore.bean.Product;
import by.estore.controller.command.Command;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProductPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ProductPageCommand.class);

    private final String PRODUCT_ID_PARAM = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productId;
        try {
            productId = Long.parseLong(request.getParameter(PRODUCT_ID_PARAM));
        } catch (NumberFormatException e) {
            logger.warn(e);
            throw e;
        }

        Product product = null;
        try {
            product = ServiceFactory.getInstance().getProductService().getProductById(productId);
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            return;
        }

        if (product != null) {
            HttpSession session = request.getSession();
            session.setAttribute("product", product);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/productPage.jsp").forward(request, response);
    }
}
