package by.estore.service;

import by.estore.entity.OrderStatus;
import by.estore.entity.Product;
import by.estore.entity.User;
import by.estore.service.exception.ServiceException;

public interface AdminService {
    boolean addProduct(Product product) throws ServiceException;
    boolean addUserToBlackListById(Long userId) throws ServiceException;
    boolean removeUserFromBlackListById(Long userId) throws ServiceException;
    boolean updateOrderStateById(Long orderId, OrderStatus orderStatus) throws ServiceException;
    User findUserByOrderId(Long orderId) throws ServiceException;
}
