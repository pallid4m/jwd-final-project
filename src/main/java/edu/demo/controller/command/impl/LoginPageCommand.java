package edu.demo.controller.command.impl;

import edu.demo.controller.command.Command;
import edu.demo.controller.command.CommandName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(CommandName.LOGIN_PAGE).forward(req, resp);
    }
}
