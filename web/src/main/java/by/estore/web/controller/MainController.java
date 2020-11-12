package by.estore.web.controller;

import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MainController.class);

    private static final String COMMAND_PARAM = "command";

    private static final CommandProvider commandProvider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(COMMAND_PARAM);
        Command command = commandProvider.getCommand(commandName);
        if (command == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        command.execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
