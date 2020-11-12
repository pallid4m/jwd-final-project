package by.estore.web.controller.command.impl.page;

import by.estore.entity.Category;
import by.estore.entity.Product;
import by.estore.web.controller.command.Command;
import by.estore.dto.Pagination;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import by.estore.web.controller.command.RouteHolder;
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter(CATEGORY_PARAM);
        String pageNumber = request.getParameter(PAGE_PARAM);

        if (categoryName != null && pageNumber != null) {
            Category category = new Category();
            category.setName(categoryName);
            Pagination pagination = new Pagination();

            int currentPage;
            try {
                currentPage = Integer.parseInt(pageNumber);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }

            int offset = (currentPage - 1) * ITEMS_COUNT;

            List<Product> products;
            long productCount;
            try {
                products = ServiceFactory.getInstance().getProductService().getProductsByCategory(category, ITEMS_COUNT, offset);
                productCount = ServiceFactory.getInstance().getProductService().getProductCount();
            } catch (ServiceException e) {
                logger.error(e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                return;
            }

            if (products != null) {
                int prevPage = currentPage - 1;
                int nextPage = currentPage + 1;
                int lastPage = (int)(productCount / ITEMS_COUNT);

                pagination.setCurrentPage(currentPage);
                pagination.setPrevPage(prevPage);
                pagination.setNextPage(nextPage);
                pagination.setLastPage(lastPage);

                request.setAttribute("category", category);
                request.setAttribute("products", products);
                request.setAttribute("pagination", pagination);
            }
        }

        request.getRequestDispatcher(RouteHolder.FORWARD_CATALOG_PAGE).forward(request, response);
    }
}
