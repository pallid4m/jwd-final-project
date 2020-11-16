package by.estore.web.controller.command.impl.admin;

import by.estore.entity.User;
import by.estore.service.AdminService;
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

public class ShowUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowUserCommand.class);

    private final AdminService adminService = ServiceFactory.getInstance().getAdminService();

    private static final String ORDER_ID_PARAM = "order-id";

    private static final String USER_ATTR = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter(ORDER_ID_PARAM);

        try {
            long id = Long.parseLong(orderId);
            User user = adminService.findUserByOrderId(id);
            if (user != null) {
                request.setAttribute(USER_ATTR, user);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
        }

        request.getRequestDispatcher(RouteHolder.FORWARD_USER_INFO_PAGE).forward(request, response);
    }
}
