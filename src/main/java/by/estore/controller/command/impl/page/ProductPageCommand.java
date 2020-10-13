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
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productId;
        try {
            productId = Long.parseLong(req.getParameter(PRODUCT_ID_PARAM));
        } catch (NumberFormatException e) {
            logger.warn(e);
            throw e;
        }

        Product product = null;
        try {
            product = ServiceFactory.getInstance().getProductService().getProductById(productId);
        } catch (ServiceException e) {
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "pff");
            return;
        }

        if (product != null) {
            HttpSession session = req.getSession();
            session.setAttribute("product", product);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/productPage.jsp").forward(req, resp);
    }
}
