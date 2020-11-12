package by.estore.web.controller.command.impl.user.profile;

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

    public static final String EMAIL_PARAM = "email";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(EMAIL_PARAM);

        Validator<String> emailValidator = new EmailValidator();
        if (!emailValidator.isValid(email)) {
            logger.debug("email is not valid");
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.USER_PAGE);
    }
}
