package by.estore.controller.command.impl;

import by.estore.bean.User;
import by.estore.controller.command.Command;
import by.estore.controller.command.RequestParameter;
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
import java.util.HashMap;
import java.util.Map;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);

    private final String AUTH_TOKEN = "token";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(RequestParameter.EMAIL_PARAM);
        String password = req.getParameter(RequestParameter.PASSWORD_PARAM);

        Map<String, String> invalidMessages = new HashMap<>();
        boolean requestValidation = true;

        if (!Validator.isEmailValid(email)) {
            requestValidation = false;
            invalidMessages.put("invalid_email", Validator.INVALID_EMAIL_MESSAGE);
        }

        if (!Validator.isPasswordValid(password)) {
            requestValidation = false;
            invalidMessages.put("invalid_password", Validator.INVALID_PASSWORD_MESSAGE);
        }

        if (!requestValidation) {
            req.getSession().setAttribute("invalid_messages", invalidMessages);
            resp.sendRedirect(req.getContextPath() + RequestParameter.MAIN_PAGE);
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

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

        resp.sendRedirect(req.getContextPath() + RequestParameter.MAIN_PAGE);
    }
}
