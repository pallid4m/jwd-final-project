package by.estore.controller.command.impl.page;

import by.estore.bean.Order;
import by.estore.bean.Product;
import by.estore.bean.User;
import by.estore.controller.command.Command;
import by.estore.controller.command.CommandName;
import by.estore.controller.command.RouteHolder;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminPageCommand.class);

    private static final String VIEW_PARAM = "view";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String view = request.getParameter(VIEW_PARAM);

        HttpSession session = request.getSession();

        if (view != null) {
            if (view.equals(CommandName.USER_LIST)) {
                List<User> users;
                try {
                    users = ServiceFactory.getInstance().getUserService().getAllUsers();
                } catch (ServiceException e) {
                    logger.error(e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
                session.setAttribute("users", users);
                session.setAttribute("content", RouteHolder.USER_LIST);
            } else if (view.equals(CommandName.PRODUCT_LIST)) {
                List<Product> products;
                try {
                    products = ServiceFactory.getInstance().getProductService().getAllProducts();
                } catch (ServiceException e) {
                    logger.error(e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
                session.setAttribute("products", products);
                session.setAttribute("content", RouteHolder.PRODUCT_LIST);
            } else if (view.equals(CommandName.ORDER_LIST)) {
                List<Order> orders;
                try {
                    orders = ServiceFactory.getInstance().getOrderService().getAllOrders();
                } catch (ServiceException e) {
                    logger.error(e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
                session.setAttribute("orders", orders);
                session.setAttribute("content", RouteHolder.ORDER_LIST);
            }
        }

        request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
    }
}
