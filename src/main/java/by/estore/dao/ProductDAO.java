package by.estore.dao;

import by.estore.bean.Product;
import by.estore.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts() throws DAOException;
    Product getProductById(Long id) throws DAOException;
}
