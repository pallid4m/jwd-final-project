package by.estore.controller.command.impl;

import by.estore.bean.Good;
import by.estore.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProductPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ProductPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodId = req.getParameter("productId");
        HttpSession session = req.getSession(false);
        if (session != null) {
            // TODO: 06-Oct-20 find good
            Good product = new Good();
            product.setName("product name");
            product.setDescription("product description");
            session.setAttribute("product", product);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/productPage.jsp").forward(req, resp);
    }
}
