package by.estore.controller.command.impl.page;

import by.estore.bean.Order;
import by.estore.bean.User;
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
import java.util.List;

public class CartPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CartPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            try {
                List<Order> orders = ServiceFactory.getInstance().getUserService().getAllOrders(user);
                session.setAttribute("orders", orders);
            } catch (ServiceException e) {
                logger.error(e);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "pff");
                return;
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/cartPage.jsp").forward(req, resp);
    }
}
