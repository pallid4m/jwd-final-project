package edu.demo.controller.command.impl;

import edu.demo.controller.command.Command;
import edu.demo.service.ServiceFactory;
import edu.demo.service.UserService;
import edu.demo.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private final static String PARAMETER_EMAIL = "email";
    private final static String PARAMETER_LOGIN = "login";
    private final static String PARAMETER_PASSWORD = "password";

    private final static String ATTRIBUTE_REGISTRATION_MESSAGE = "registration_message";

    private final static String REGISTRATION_PAGE = "mainController?command=go_to_registration_page";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        String email = req.getParameter(PARAMETER_EMAIL);
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);

        UserService userService = ServiceFactory.getInstance().getUserService();

        RegistrationData registrationData = new RegistrationData();
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
        } catch (UserAlreadyExistsServiceException e) {
            session.setAttribute(ATTRIBUTE_REGISTRATION_MESSAGE, "user_already_exists");
        } catch (ServiceException e) {
        }
        resp.sendRedirect(REGISTRATION_PAGE);
    }
}
