package by.estore.dao;

import by.estore.entity.Category;
import by.estore.entity.Order;
import by.estore.entity.Product;
import by.estore.dao.exception.DAOException;

import java.util.List;
import java.util.Set;

public interface ProductDAO {
    List<Product> getAllProducts() throws DAOException;
    List<Product> getProductsByCategory(Category category) throws DAOException;
    List<Product> getProductsByCategory(Category category, int limit, int offset) throws DAOException;
    Set<Product> getProductsByOrder(Order order) throws DAOException;
    long getProductCount() throws DAOException;
    Product getProductById(Long id) throws DAOException;
}
