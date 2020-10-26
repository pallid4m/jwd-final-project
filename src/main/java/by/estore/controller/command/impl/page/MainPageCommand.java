package by.estore.controller.command.impl.page;

import by.estore.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MainPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 12-Oct-20 low cost products
        request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
    }
}
