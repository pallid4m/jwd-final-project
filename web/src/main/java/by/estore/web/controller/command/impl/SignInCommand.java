package by.estore.web.controller.command.impl;

import by.estore.entity.User;
import by.estore.service.UserService;
import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import by.estore.dto.UserAuth;
import by.estore.web.validation.impl.UserAuthValidator;
import by.estore.web.validation.Validator;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.AuthorizationException;
import by.estore.service.exception.ServiceException;
import by.estore.service.exception.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignInCommand.class);

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private static final Validator<UserAuth> validator = new UserAuthValidator();

    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";

    private static final String VALIDATION_ERROR = "error";

    private static final String USER_ATTR = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(email);
        userAuth.setPassword(password);

        if (!validator.isValid(userAuth)) {
            request.setAttribute(VALIDATION_ERROR, validator.getMessage());
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_IN_PAGE);
            return;
        }

        User user = null;
        try {
            user = userService.authorize(userAuth);
        } catch (UserNotFoundException | AuthorizationException e) {
            logger.info(e.getMessage());
            request.setAttribute(VALIDATION_ERROR, e.getMessage());
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_IN_PAGE);
            return;
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        if (user != null) {
            request.getSession().setAttribute(USER_ATTR, user);
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.MAIN_PAGE);
    }
}
