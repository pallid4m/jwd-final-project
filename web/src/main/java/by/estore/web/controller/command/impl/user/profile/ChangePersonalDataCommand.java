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

public class ChangePersonalDataCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePersonalDataCommand.class);

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private static final String PHONE_PARAM = "phone";
    private static final String FIRST_NAME_PARAM = "first-name";
    private static final String LAST_NAME_PARAM = "last-name";

    private static final String USER_ATTR = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter(PHONE_PARAM);
        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);

        ProfileDto profileDto = new ProfileDto();
        profileDto.setPhone(phone);
        profileDto.setFirstName(firstName);
        profileDto.setLastName(lastName);

        User user = (User) request.getSession().getAttribute(USER_ATTR);

        try {
            boolean ret = userService.updateUserData(user, profileDto);
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.PROFILE_PAGE);
    }
}
