package by.estore.web.controller.command.impl.user.cart;

import by.estore.entity.Order;
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Order order = null;
        if (session.getAttribute("order") != null) {
            order = (Order) session.getAttribute("order");
        }

        try {
            boolean isSavedOrder = false;
            if (order != null) {
                isSavedOrder = ServiceFactory.getInstance().getOrderService().saveOrder(order);
            }
            if (isSavedOrder) {
                session.removeAttribute("order");
                session.removeAttribute("cartProducts");
            }
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.CATAlOG_PAGE);
    }
}
