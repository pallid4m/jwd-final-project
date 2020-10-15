package by.estore.controller.command.impl;

import by.estore.bean.User;
import by.estore.controller.command.Command;
import by.estore.controller.command.RouteHolder;
import by.estore.controller.dto.UserAuth;
import by.estore.controller.validation.UserAuthValidator;
import by.estore.controller.validation.Validator;
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

    public static final String LOGIN_PARAM = "login";
    public static final String EMAIL_PARAM = "email";
    public static final String PASSWORD_PARAM = "password";
    public static final String REMEMBER_ME_PARAM = "remember-me";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter(EMAIL_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
        String rememberMe = req.getParameter(REMEMBER_ME_PARAM);

        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(email);
        userAuth.setPassword(password);

        Validator<UserAuth> validator = new UserAuthValidator();
        if (!validator.isValid(userAuth)) {
            logger.info("user fields aren't valid");
            resp.sendRedirect(req.getContextPath() + RouteHolder.SIGN_IN_PAGE);
            return;
        }

        User user = null;
        try {
            user = ServiceFactory.getInstance().getUserService().authorize(userAuth);
        } catch (UserNotFoundException | AuthorizationException e) {
            logger.info(e.getMessage());
            resp.sendRedirect(req.getContextPath() + RouteHolder.SIGN_IN_PAGE);
            return;
        } catch (ServiceException e) {
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "pff");
            return;
        }

        if (user != null) {
            req.getSession().setAttribute("user", user);
            if (rememberMe != null) {
                // TODO: 12-Oct-20 remember me
            }
        }

        resp.sendRedirect(req.getContextPath() + RouteHolder.MAIN_PAGE);
    }
}
