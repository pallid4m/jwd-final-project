package by.estore.service;

import by.estore.entity.Category;
import by.estore.entity.Product;
import by.estore.service.exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts() throws ServiceException;
    List<Product> findProductsByCategory(Category category) throws ServiceException;
    List<Product> findProductsByCategory(Category category, int limit, int offset) throws ServiceException;
    List<Product> findLowCostProductsByLimit(int limit) throws ServiceException;
    long findProductCount() throws ServiceException;
    long findProductCountByCategoryName(Category category) throws ServiceException;
    Product findProductById(Long id) throws ServiceException;
}
