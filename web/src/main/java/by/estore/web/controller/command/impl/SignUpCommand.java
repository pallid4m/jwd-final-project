package by.estore.web.controller.command.impl;

import by.estore.entity.User;
import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import by.estore.dto.UserAuth;
import by.estore.web.validation.impl.UserAuthValidator;
import by.estore.web.validation.Validator;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import by.estore.service.exception.UserAlreadyExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);

    public static final String LOGIN_PARAM = "login";
    public static final String EMAIL_PARAM = "email";
    public static final String PASSWORD_PARAM = "password";
    public static final String CONFIRM_PASSWORD_PARAM = "confirm-password";
    public static final String PHONE_PARAM = "phone";
    public static final String REMEMBER_ME_PARAM = "remember-me";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_PARAM);
        String rememberMe = request.getParameter(REMEMBER_ME_PARAM);

        // TODO: 12-Oct-20 js
        if (!password.equals(confirmPassword)) {
            logger.info("Passwords don't match");
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_UP_PAGE);
            return;
        }

        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(email);
        userAuth.setPassword(password);

        Validator<UserAuth> validator = new UserAuthValidator();
        if (!validator.isValid(userAuth)) {
            logger.info("user fields aren't valid");
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_UP_PAGE);
            return;
        }

        User user = null;
        try {
            user = ServiceFactory.getInstance().getUserService().register(userAuth);
        } catch (UserAlreadyExistException e) {
            logger.info(e.getMessage());
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_UP_PAGE);
            return;
        } catch (ServiceException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        if (user != null) {
            request.getSession().setAttribute("user", user);
            if (rememberMe != null) {
                // TODO: 12-Oct-20 remember me
            }
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.MAIN_PAGE);
    }
}
