package by.estore.service;

import by.estore.bean.Category;
import by.estore.bean.Product;
import by.estore.service.exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts() throws ServiceException;
    List<Product> getProductsByCategory(Category category) throws ServiceException;
    List<Product> getProductsByCategory(Category category, int limit, int offset) throws ServiceException;
    long getProductCount() throws ServiceException;
    Product getProductById(Long id) throws ServiceException;
}
