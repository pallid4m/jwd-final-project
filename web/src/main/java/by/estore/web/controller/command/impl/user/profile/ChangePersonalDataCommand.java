package by.estore.web.controller.command.impl.user.profile;

import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePersonalDataCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePersonalDataCommand.class);

    public static final String PHONE_PARAM = "phone";
    public static final String FIRST_NAME_PARAM = "first-name";
    public static final String LAST_NAME_PARAM = "last-name";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter(PHONE_PARAM);
        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);

        response.sendRedirect(request.getContextPath() + RouteHolder.USER_PAGE);
    }
}
