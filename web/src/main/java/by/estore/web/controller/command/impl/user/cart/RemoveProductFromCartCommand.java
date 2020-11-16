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

public class RemoveProductFromCartCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RemoveProductFromCartCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String PRODUCT_ID_PARAM = "product-id";

    private static final String ORDER_ATTR = "order";
    private static final String CAR_PRODUCTS_ATTR = "cartProducts";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter(PRODUCT_ID_PARAM));

        HttpSession session = request.getSession();

        Order order = null;
        if (session.getAttribute(ORDER_ATTR) != null) {
            order = (Order) session.getAttribute(ORDER_ATTR);
        }

        try {
            order = orderService.removeProductById(order, productId);
            if (order == null) {
                session.removeAttribute(ORDER_ATTR);
                session.removeAttribute(CAR_PRODUCTS_ATTR);
            } else {
                session.setAttribute(ORDER_ATTR, order);
            }
        } catch (ServiceException e) {
            logger.debug(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.CART_PAGE);
    }
}
