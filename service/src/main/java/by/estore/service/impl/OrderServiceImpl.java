package by.estore.service.impl;

import by.estore.entity.Currency;
import by.estore.entity.Order;
import by.estore.dao.DAOFactory;
import by.estore.dao.exception.DAOException;
import by.estore.entity.OrderStatus;
import by.estore.entity.Product;
import by.estore.service.OrderService;
import by.estore.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getAllOrders() throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderDAO().getAllOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
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

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId((byte) 1);

        order.setOrderStatus(orderStatus);

        try {
            return DAOFactory.getInstance().getOrderDAO().saveOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
