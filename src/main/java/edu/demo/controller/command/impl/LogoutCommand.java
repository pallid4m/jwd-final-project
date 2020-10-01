package edu.demo.controller.command.impl;

import edu.demo.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {

    private static final String USER_ATTRIBUTE = "user";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        resp.sendRedirect("/");
    }
}
