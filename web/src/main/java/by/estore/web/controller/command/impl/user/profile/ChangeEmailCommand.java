package by.estore.web.controller.command.impl.user.profile;

import by.estore.dto.ProfileDto;
import by.estore.entity.User;
import by.estore.service.ServiceFactory;
import by.estore.service.UserService;
import by.estore.service.exception.ServiceException;
import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import by.estore.web.validation.Validator;
import by.estore.web.validation.impl.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeEmailCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeEmailCommand.class);

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private static final String EMAIL_PARAM = "email";

    private static final String USER_ATTR = "user";

    private static final String VALIDATION_ERROR = "error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(EMAIL_PARAM);

        Validator<String> emailValidator = new EmailValidator();
        if (!emailValidator.isValid(email)) {
            request.setAttribute(VALIDATION_ERROR, emailValidator.getMessage());
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_UP_PAGE);
            return;
        }

        ProfileDto profileDto = new ProfileDto();
        profileDto.setEmail(email);

        User user = (User) request.getSession().getAttribute(USER_ATTR);

        try {
            boolean ret = userService.updateUserEmail(user, profileDto);
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.USER_PAGE);
    }
}
