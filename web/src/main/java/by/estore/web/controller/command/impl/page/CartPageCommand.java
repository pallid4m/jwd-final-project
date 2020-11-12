package by.estore.web.controller.command.impl.page;

import by.estore.entity.Order;
import by.estore.entity.Product;
import by.estore.entity.User;
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("cartProducts") != null) {
            Set<Product> cartProducts = (Set<Product>) session.getAttribute("cartProducts");
            User user = (User) session.getAttribute("user");

            Order order = new Order();
            order.setUser(user);
            order.setProducts(cartProducts);

            try {
                order = ServiceFactory.getInstance().getOrderService().generateOrder(order);
            } catch (ServiceException e) {
                logger.error(e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                return;
            }

            session.setAttribute("order", order);
        }

        request.getRequestDispatcher(RouteHolder.FORWARD_CART_PAGE).forward(request, response);
    }
}
