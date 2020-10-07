package by.estore.controller.command.impl;

import by.estore.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignOutCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/main?command=main-page");
        return null;
    }
}
