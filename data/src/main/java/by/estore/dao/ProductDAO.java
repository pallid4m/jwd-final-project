package by.estore.dao;

import by.estore.entity.Category;
import by.estore.entity.Order;
import by.estore.entity.Product;
import by.estore.dao.exception.DAOException;

import java.util.List;
import java.util.Set;

/**
 * Product DAO Interface
 * API used to access database product data
 */

public interface ProductDAO {

    /**
     * Search for all products in database
     * @return list of found products
     * @throws DAOException if occurred problem in database
     */
    List<Product> findAllProducts() throws DAOException;

    /**
     * Search for all products by category in database
     * @param category - entity with filled category data
     * @return list of found products
     * @throws DAOException if occurred problem in database
     */
    List<Product> findProductsByCategory(Category category) throws DAOException;

    /**
     * Search for all products by category in database with limit and offset
     * @param category - entity with filled category data
     * @param limit - count of requesting rows in database
     * @param offset - offset from beginnings search in database
     * @return list of found products
     * @throws DAOException if occurred problem in database
     */
    List<Product> findProductsByCategory(Category category, int limit, int offset) throws DAOException;

    /**
     * Search low cost products with limit in database
     * @param limit - count of requesting rows in database
     * @return list of found products
     * @throws DAOException if occurred problem in database
     */
    List<Product> findLowCostProductsByLimit(int limit) throws DAOException;

    /**
     * Search products by order in database
     * @param order - entity with filled data
     * @return set of products
     * @throws DAOException if occurred problem in database
     */
    Set<Product> findProductsByOrder(Order order) throws DAOException;

    /**
     * Search product count in database
     * @return count of founded rows
     * @throws DAOException if occurred problem in database
     */
    long findProductCount() throws DAOException;

    /**
     * Search product by its id in database
     * @param id - identifier of product
     * @return product entity if founded or null
     * @throws DAOException if occurred problem in database
     */
    Product findProductById(Long id) throws DAOException;

    /**
     * Saving product to database
     * @param product - entity with filled data
     * @return true if saving success
     * @throws DAOException if occurred problem in database
     */
    boolean addProduct(Product product) throws DAOException;
}
