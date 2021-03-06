package by.estore.web.controller.command.impl.user.cart;

import by.estore.entity.Order;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PlaceOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PlaceOrderCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String ORDER_ATTR = "order";
    private static final String CART_PRODUCTS_ATTR = "cartProducts";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Order order = null;
        if (session.getAttribute(ORDER_ATTR) != null) {
            order = (Order) session.getAttribute(ORDER_ATTR);
        }

        try {
            boolean isSavedOrder = false;
            if (order != null) {
                isSavedOrder = orderService.saveOrder(order);
            }
            if (isSavedOrder) {
                session.removeAttribute(ORDER_ATTR);
                session.removeAttribute(CART_PRODUCTS_ATTR);
            }
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.CATAlOG_PAGE);
    }
}
