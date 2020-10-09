package by.estore.controller.command.impl;

import by.estore.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignInPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/signInPage.jsp").forward(req, resp);
    }
}
