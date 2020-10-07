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
import java.io.IOException;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(email, password);
        boolean ret = false;
        try {
            ret = ServiceFactory.getInstance().getUserService().saveUser(user);
        } catch (ServiceException e) {
            logger.error(e);
        }
        if (ret) {
            if (password.equals(user.getPassword())) {
                req.getSession().setAttribute("user", user);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/main?command=main-page");
        return null;
    }
}
