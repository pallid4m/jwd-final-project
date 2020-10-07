package by.estore.controller.command.impl;

import by.estore.bean.User;
import by.estore.controller.command.Command;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        try {
            users = ServiceFactory.getInstance().getUserService().getAllUsers();
        } catch (ServiceException e) {
            logger.error(e);
        }
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.setAttribute("users", users);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(req, resp);
        return null;
    }
}
