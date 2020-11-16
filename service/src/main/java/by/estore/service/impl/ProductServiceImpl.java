package by.estore.service.impl;

import by.estore.dao.ProductDAO;
import by.estore.entity.Category;
import by.estore.entity.Product;
import by.estore.dao.DAOFactory;
import by.estore.dao.exception.DAOException;
import by.estore.service.ProductService;
import by.estore.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    private final ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();

    @Override
    public List<Product> findAllProducts() throws ServiceException {
        try {
            return productDAO.findAllProducts();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findProductsByCategory(Category category) throws ServiceException {
        try {
            return productDAO.findProductsByCategory(category);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findProductsByCategory(Category category, int limit, int offset) throws ServiceException {
        try {
            return productDAO.findProductsByCategory(category, limit, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findLowCostProductsByLimit(int limit) throws ServiceException {
        try {
            return productDAO.findLowCostProductsByLimit(limit);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long findProductCount() throws ServiceException {
        try {
            return productDAO.findProductCount();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product findProductById(Long id) throws ServiceException {
        try {
            return productDAO.findProductById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
