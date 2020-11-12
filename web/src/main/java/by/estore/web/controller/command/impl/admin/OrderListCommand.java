package by.estore.web.controller.command.impl.admin;

import by.estore.entity.Order;
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

public class OrderListCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OrderListCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders;
        try {
            orders = ServiceFactory.getInstance().getOrderService().getAllOrders();
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }
        request.setAttribute("orders", orders);
        request.setAttribute("content", RouteHolder.ORDER_LIST);

        request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
    }
}
