package by.estore.web.controller.command.impl.page;

import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(RouteHolder.FORWARD_SIGN_UP_PAGE).forward(request, response);
    }
}
