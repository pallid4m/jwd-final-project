package by.estore.dao;

import by.estore.bean.Category;
import by.estore.bean.Order;
import by.estore.bean.Product;
import by.estore.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts() throws DAOException;
    List<Product> getProductsByCategory(Category category) throws DAOException;
    List<Product> getProductsByCategory(Category category, int limit, int offset) throws DAOException;
    List<Product> getProductsByOrder(Order order) throws DAOException;
    Product getProductById(Long id) throws DAOException;
}
