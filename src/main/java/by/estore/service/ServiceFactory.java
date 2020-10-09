package by.estore.service;

import by.estore.service.impl.GoodServiceImpl;
import by.estore.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final GoodService goodService = new GoodServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public GoodService getGoodService() {
        return goodService;
    }
}
