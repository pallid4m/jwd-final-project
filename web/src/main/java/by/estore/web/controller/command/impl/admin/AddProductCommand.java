package by.estore.web.controller.command.impl.admin;

import by.estore.entity.Category;
import by.estore.entity.Currency;
import by.estore.entity.Product;
import by.estore.service.AdminService;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class AddProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddProductCommand.class);

    private final AdminService adminService = ServiceFactory.getInstance().getAdminService();

    private static final String PRODUCT_NAME_PARAM = "name";
    private static final String PRODUCT_DESCRIPTION_PARAM = "description";
    private static final String PRODUCT_PRICE_PARAM = "price";
    private static final String PRODUCT_IMAGE_PARAM = "image";
    private static final String PRODUCT_CURRENCY_PARAM = "currency";
    private static final String PRODUCT_CATEGORY_PARAM = "category";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter(PRODUCT_NAME_PARAM);
        String productDescription = request.getParameter(PRODUCT_DESCRIPTION_PARAM);
        String productPrice = request.getParameter(PRODUCT_PRICE_PARAM);
        String productImage = request.getParameter(PRODUCT_IMAGE_PARAM);
        String productCurrency = request.getParameter(PRODUCT_CURRENCY_PARAM);
        String productCategory = request.getParameter(PRODUCT_CATEGORY_PARAM);

        Currency currency = new Currency();
        currency.setCode(productCurrency);

        Category category = new Category();
        category.setName(productCategory);

        Product product = Product.builder()
                .setName(productName)
                .setDescription(productDescription)
                .setPrice(new BigDecimal(productPrice))
                .setImage(productImage)
                .setCurrency(currency)
                .setCategory(category)
                .build();

        try {
            boolean ret = adminService.addProduct(product);
        } catch (ServiceException e) {
            logger.error(e);
        }

        request.getRequestDispatcher(RouteHolder.FORWARD_ADMIN_PAGE).forward(request, response);
    }
}
