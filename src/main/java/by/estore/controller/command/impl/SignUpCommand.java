package by.estore.controller.command.impl;

import by.estore.bean.User;
import by.estore.controller.command.Command;
import by.estore.controller.command.RouteHolder;
import by.estore.controller.validation.UserValidator;
import by.estore.controller.validation.Validator;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import by.estore.service.exception.UserAlreadyExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);

    private final String AUTH_TOKEN = "token";

    public static final String LOGIN_PARAM = "login";
    public static final String EMAIL_PARAM = "email";
    public static final String PASSWORD_PARAM = "password";
    public static final String CONFIRM_PASSWORD_PARAM = "confirm-password";
    public static final String PHONE_PARAM = "phone";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(EMAIL_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
        String confirmPassword = req.getParameter(CONFIRM_PASSWORD_PARAM);

        if (!password.equals(confirmPassword)) {
            logger.info("Passwords don't match");
            resp.sendRedirect(req.getContextPath() + RouteHolder.MAIN_PAGE);
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Validator<User> validator = new UserValidator();
        if (!validator.isValid(user)) {
            logger.info("user fields aren't valid");
            resp.sendRedirect(req.getContextPath() + RouteHolder.MAIN_PAGE);
            return;
        }

        boolean isRegistered = false;
        try {
            isRegistered = ServiceFactory.getInstance().getUserService().register(user);
        } catch (UserAlreadyExistException e) {
            logger.info(e.getMessage());
        } catch (ServiceException e) {
            logger.error(e);
        }

        if (isRegistered) {
            req.getSession().setAttribute("user", user);
            resp.addCookie(new Cookie("auth", AUTH_TOKEN));
        }

        resp.sendRedirect(req.getContextPath() + RouteHolder.MAIN_PAGE);
    }
}
