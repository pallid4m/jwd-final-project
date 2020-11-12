package by.estore.web.controller.command.impl.user.profile;

import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    public static final String OLD_PASSWORD_PARAM = "old-password";
    public static final String NEW_PASSWORD_PARAM = "new-password";
    public static final String CONFIRM_NEW_PASSWORD_PARAM = "confirm-new-password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter(OLD_PASSWORD_PARAM);
        String newPassword = request.getParameter(NEW_PASSWORD_PARAM);
        String confirmNewPassword = request.getParameter(CONFIRM_NEW_PASSWORD_PARAM);

        response.sendRedirect(request.getContextPath() + RouteHolder.USER_PAGE);
    }
}
