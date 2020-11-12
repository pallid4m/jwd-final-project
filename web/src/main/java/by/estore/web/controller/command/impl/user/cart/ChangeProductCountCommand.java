package by.estore.web.controller.command.impl.user.cart;

import by.estore.entity.Order;
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

public class ChangeProductCountCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeProductCountCommand.class);

    public static final String PRODUCT_ID_PARAM = "product-id";
    public static final String PRODUCT_COUNT_PARAM = "count";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productId;
        int productCount;

        try {
            productId = Long.parseLong(request.getParameter(PRODUCT_ID_PARAM));
            productCount = Integer.parseInt(request.getParameter(PRODUCT_COUNT_PARAM));
        } catch (NumberFormatException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");

        try {
            ServiceFactory.getInstance().getOrderService().changeProductCountById(order, productId, productCount);
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.CART_PAGE);
    }
}
