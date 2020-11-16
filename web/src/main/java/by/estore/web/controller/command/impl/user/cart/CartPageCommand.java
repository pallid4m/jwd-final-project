package by.estore.web.controller.command.impl.user.cart;

import by.estore.entity.Order;
import by.estore.entity.Product;
import by.estore.entity.User;
import by.estore.service.OrderService;
import by.estore.web.controller.command.Command;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import by.estore.web.controller.command.RouteHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class CartPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CartPageCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String CART_PRODUCTS_ATTR = "cartProducts";
    private static final String USER_ATTR = "user";
    private static final String ORDER_ATTR = "order";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute(CART_PRODUCTS_ATTR) != null) {
            Set<Product> cartProducts = (Set<Product>) session.getAttribute(CART_PRODUCTS_ATTR);
            User user = (User) session.getAttribute(USER_ATTR);

            Order order = new Order();
            order.setUser(user);
            order.setProducts(cartProducts);

            try {
                order = orderService.generateOrder(order);
            } catch (ServiceException e) {
                logger.error(e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                return;
            }

            session.setAttribute(ORDER_ATTR, order);
        }

        request.getRequestDispatcher(RouteHolder.FORWARD_CART_PAGE).forward(request, response);
    }
}
