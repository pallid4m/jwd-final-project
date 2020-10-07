package by.estore.service;

import by.estore.service.impl.ProductServiceImpl;
import by.estore.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public ProductService getProductService() {
        return productService;
    }
}
