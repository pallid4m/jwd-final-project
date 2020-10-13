package by.estore.controller.command.impl.page;

import by.estore.bean.Category;
import by.estore.bean.Product;
import by.estore.controller.command.Command;
import by.estore.controller.dto.Pagination;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CatalogPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CatalogPageCommand.class);

    private final String CATEGORY_PARAM = "category";
    private final String PAGE_PARAM = "page";

    private final int ITEMS_COUNT = 5;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter(CATEGORY_PARAM);
        String pageNumber = req.getParameter(PAGE_PARAM);

        if (categoryName != null) {
            Category category = new Category();
            category.setName(categoryName);

            Pagination pagination;
            int offset = 0;
            if (pageNumber == null) {
                pagination = new Pagination();
            } else {
                pagination = (Pagination) req.getSession().getAttribute("pagination");
                pagination.setCurrentPage((Integer.parseInt(pageNumber)));
                offset = (Integer.parseInt(pageNumber) - 1) * ITEMS_COUNT;
            }

            List<Product> products = null;
            try {
                products = ServiceFactory.getInstance().getProductService().getProductsByCategory(category, ITEMS_COUNT, offset);
            } catch (ServiceException e) {
                logger.error(e);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "pff");
                return;
            }

            if (products == null) {
                // TODO: 10-Oct-20
            }

            req.getSession().setAttribute("category", category);
            req.getSession().setAttribute("products", products);
            req.getSession().setAttribute("pagination", pagination);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/catalogPage.jsp").forward(req, resp);
    }
}
