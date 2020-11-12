package by.estore.web.controller.command.impl.admin;

import by.estore.entity.User;
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

public class UserListCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UserListCommand.class);

    private static final String USER_LIST_PARAM = "user-list";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users;
        try {
            users = ServiceFactory.getInstance().getUserService().getAllUsers();
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }
        request.setAttribute("users", users);
        request.setAttribute("content", RouteHolder.USER_LIST);

        request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
    }
}
