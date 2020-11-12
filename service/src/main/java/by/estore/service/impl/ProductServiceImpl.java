package by.estore.service.impl;

import by.estore.entity.Category;
import by.estore.entity.Product;
import by.estore.dao.DAOFactory;
import by.estore.dao.exception.DAOException;
import by.estore.service.ProductService;
import by.estore.service.exception.ServiceException;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAllProducts() throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductDAO().getAllProducts();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductsByCategory(Category category) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductDAO().getProductsByCategory(category);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductsByCategory(Category category, int limit, int offset) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductDAO().getProductsByCategory(category, limit, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long getProductCount() throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductDAO().getProductCount();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product getProductById(Long id) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductDAO().getProductById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
