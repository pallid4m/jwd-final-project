package by.estore.service.impl;

import by.estore.dao.OrderDAO;
import by.estore.dao.ProductDAO;
import by.estore.entity.*;
import by.estore.dao.DAOFactory;
import by.estore.dao.exception.DAOException;
import by.estore.service.OrderService;
import by.estore.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
    private final ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        try {
            return orderDAO.findAllOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAllOrdersByUser(User user) throws ServiceException {
        List<Order> orders = null;

        try {
            orders = orderDAO.findAllOrdersByUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (orders != null) {
            try {
                for (Order order : orders) {
                    Set<Product> products = productDAO.findProductsByOrder(order);
                    order.setProducts(products);
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }

        user.setOrders(orders);

        return orders;
    }

    @Override
    public Order generateOrder(Order order) throws ServiceException {
        BigDecimal amount = calcOrderAmount(order);
        order.setAmount(amount);

        Currency currency = new Currency();
        currency.setId((short) 1);
        currency.setCode("BYN");

        order.setCurrency(currency);

        return order;
    }

    private BigDecimal calcOrderAmount(Order order) {
        Set<Product> products = order.getProducts();

        BigDecimal amount = BigDecimal.ZERO;
        for (Product product : products) {
            BigDecimal price = product.getPrice();
            if (product.getQuantity() > 1) {
                price = price.multiply(BigDecimal.valueOf(product.getQuantity()));
            }
            amount = amount.add(price);
        }

        return amount;
    }

    @Override
    public Order removeProductById(Order order, Long productId) throws ServiceException {
        Set<Product> products = order.getProducts();

        for (Product product : products) {
            if (product.getId().equals(productId)) {
                order.getProducts().remove(product);
                break;
            }
        }

        BigDecimal amount = calcOrderAmount(order);
        order.setAmount(amount);

        return products.size() != 0 ? order : null;
    }

    @Override
    public Order changeProductCountById(Order order, Long productId, int productCount) throws ServiceException {
        for (Product product : order.getProducts()) {
            if (product.getId().equals(productId)) {
                product.setQuantity(productCount);
            }
        }

        BigDecimal amount = calcOrderAmount(order);
        order.setAmount(amount);

        return order;
    }

    @Override
    public boolean saveOrder(Order order) throws ServiceException {

        // TODO: 12-Nov-20 validate order
        // TODO: 16-Nov-20 order status data

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId((byte) 1);

        order.setOrderStatus(orderStatus);

        try {
            return orderDAO.saveOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
