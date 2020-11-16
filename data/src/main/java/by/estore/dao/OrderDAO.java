package by.estore.dao;

import by.estore.entity.Order;
import by.estore.dao.exception.DAOException;
import by.estore.entity.OrderStatus;
import by.estore.entity.User;

import java.util.List;

/**
 * Order DAO Interface
 * API used to access database order data
 */

public interface OrderDAO {

    /**
     * Search for all orders in database
     * @return list of found orders
     * @throws DAOException if occurred problem in database
     */
    List<Order> findAllOrders() throws DAOException;

    /**
     * Search for all orders by user entity in database
     * @param user entity
     * @return list of found orders
     * @throws DAOException if occurred problem in database
     */
    List<Order> findAllOrdersByUser(User user) throws DAOException;

    /**
     * Saving a order
     * @param order entity with filled data
     * @return true if saving success
     * @throws DAOException if occurred problem in database
     */
    boolean saveOrder(Order order) throws DAOException;

    /**
     * Updating order state by order identifier in the database
     * @param orderId - identifier
     * @param orderStatus - entity with status state
     * @return true if updating success
     * @throws DAOException if occurred problem in database
     */
    boolean updateOrderStateById(Long orderId, OrderStatus orderStatus) throws DAOException;
}
