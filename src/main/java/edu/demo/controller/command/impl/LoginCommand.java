package edu.demo.controller.command.impl;

import edu.demo.bean.User;
import edu.demo.controller.command.Command;
import edu.demo.service.ServiceFactory;
import edu.demo.service.UserService;
import edu.demo.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String USER_ATTRIBUTE = "user";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String INIT_ATTRIBUTE = "init";
    private static final String ERROR_ATTRIBUTE = "error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter(LOGIN_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            User user = userService.authorization(authorizationData);
            System.out.println(user);
            session.setAttribute(ATTRIBUTE_USER, user);
            resp.sendRedirect(USER_PAGE);
        } catch (UserNotFoundServiceException e) {
            session.setAttribute(ATTRIBUTE_AUTHORIZATION_MESSAGE, "wrong_data");
            resp.sendRedirect(LOGIN_PAGE);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_AUTHORIZATION_MESSAGE, "bd_error");
            resp.sendRedirect(LOGIN_PAGE);
        }
    }
}
