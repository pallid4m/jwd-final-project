package by.estore.service;

import by.estore.bean.Product;
import by.estore.service.exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts() throws ServiceException;
    Product getProductById(Long id) throws ServiceException;
}
