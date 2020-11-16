package by.estore.web.controller.command.impl.user.profile;

import by.estore.entity.Order;
import by.estore.entity.User;
import by.estore.service.OrderService;
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

public class OrderPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OrderPageCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String USER_ATTR = "user";
    private static final String ORDERS_ATTR = "orders";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute(USER_ATTR);

        try {
            List<Order> orders = orderService.findAllOrdersByUser(user);
            if (orders != null) {
                request.setAttribute(ORDERS_ATTR, orders);
            }
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        request.getRequestDispatcher(RouteHolder.FORWARD_ORDER_PAGE).forward(request, response);
    }
}
