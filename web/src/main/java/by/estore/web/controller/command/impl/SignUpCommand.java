package by.estore.web.controller.command.impl;

import by.estore.entity.User;
import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import by.estore.dto.UserAuth;
import by.estore.web.validation.impl.PasswordValidator;
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

    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String CONFIRM_PASSWORD_PARAM = "confirm-password";
    private static final String PHONE_PARAM = "phone";

    private static final String VALIDATION_ERROR = "error";

    private static final String USER_ATTR = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_PARAM);
        String phone = request.getParameter(PHONE_PARAM);

        if (!password.equals(confirmPassword)) {
            request.setAttribute(VALIDATION_ERROR, PasswordValidator.MESSAGE);
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_UP_PAGE);
            return;
        }

        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(email);
        userAuth.setPassword(password);
        userAuth.setPhone(phone);

        Validator<UserAuth> validator = new UserAuthValidator();
        if (!validator.isValid(userAuth)) {
            request.setAttribute(VALIDATION_ERROR, validator.getMessage());
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_UP_PAGE);
            return;
        }

        User user = null;
        try {
            user = ServiceFactory.getInstance().getUserService().register(userAuth);
        } catch (UserAlreadyExistException e) {
            logger.info(e.getMessage());
            request.setAttribute(VALIDATION_ERROR, e.getMessage());
            response.sendRedirect(request.getContextPath() + RouteHolder.SIGN_UP_PAGE);
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
