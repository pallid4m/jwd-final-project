package edu.demo.controller.command.impl;

import edu.demo.bean.AuthDetail;
import edu.demo.controller.command.Command;
import edu.demo.service.ServiceFactory;
import edu.demo.service.UserService;
import edu.demo.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationPageCommand implements Command {

    private final static String EMAIL_PARAM = "email";
    private final static String LOGIN_PARAM = "login";
    private final static String PASSWORD_PARAM = "password";

    private final static String ATTRIBUTE_REGISTRATION_MESSAGE = "registration_message";

    private final static String REGISTRATION_PAGE = "mainController?command=registration-page";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        String email = req.getParameter(EMAIL_PARAM);
        String login = req.getParameter(LOGIN_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);

        UserService userService = ServiceFactory.getInstance().getUserService();

        AuthDetail registrationData = new AuthDetail();
        registrationData.setEmail(email);
        registrationData.setLogin(login);
        registrationData.setPassword(password);

        HttpSession session = req.getSession();

        try {
            if (userService.registration(registrationData)) {
                session.setAttribute(ATTRIBUTE_REGISTRATION_MESSAGE, "registration_successful");
            } else {
                session.setAttribute(ATTRIBUTE_REGISTRATION_MESSAGE, "registration_error");
            }
        } catch (ServiceException e) {
        }
        resp.sendRedirect(REGISTRATION_PAGE);
    }
}
