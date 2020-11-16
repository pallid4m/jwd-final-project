package by.estore.web.controller.command.impl.user.profile;

import by.estore.dto.ProfileDto;
import by.estore.entity.User;
import by.estore.service.ServiceFactory;
import by.estore.service.UserService;
import by.estore.service.exception.ServiceException;
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

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private static final String OLD_PASSWORD_PARAM = "old-password";
    private static final String NEW_PASSWORD_PARAM = "new-password";
    private static final String CONFIRM_NEW_PASSWORD_PARAM = "confirm-new-password";

    private static final String USER_ATTR = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter(OLD_PASSWORD_PARAM);
        String newPassword = request.getParameter(NEW_PASSWORD_PARAM);
        String confirmNewPassword = request.getParameter(CONFIRM_NEW_PASSWORD_PARAM);

        ProfileDto profileDto = new ProfileDto();
        profileDto.setOldPassword(oldPassword);
        profileDto.setNewPassword(newPassword);
        profileDto.setConfirmNewPassword(confirmNewPassword);

        User user = (User) request.getSession().getAttribute(USER_ATTR);

        try {
            boolean ret = userService.updateUserPassword(user, profileDto);
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.USER_PAGE);
    }
}
