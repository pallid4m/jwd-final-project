package by.estore.dao;

import by.estore.entity.User;
import by.estore.dao.exception.DAOException;

import java.util.List;

/**
 * User DAO Interface
 * API used to access database user data
 */

public interface UserDAO {

    /**
     * Saving a user to database
     * @param user entity with filled data
     * @return true if success
     * @throws DAOException if occurred problem in database
     */
    boolean saveUser(User user) throws DAOException;

    /**
     * Deleting a user by its identifier in the database
     * @param id
     * @return true if row in database was updated
     * @throws DAOException if occurred problem in database
     */
    boolean deleteUserById(Long id) throws DAOException;

    /**
     * User search by its identifier in the database
     * @param id - identifier in database
     * @return user if it was found or null
     * @throws DAOException if occurred problem in database
     */
    User findUserById(Long id) throws DAOException;

    /**
     * Search user by its email in database
     * @param email
     * @return user if it was found or null
     * @throws DAOException if occurred problem in database
     */
    User findUserByEmail(String email) throws DAOException;

    /**
     * Search user by its order id in database
     * @param orderId
     * @return user if it was found or null
     * @throws DAOException if occurred problem in database
     */
    User findUserByOrderId(Long orderId) throws DAOException;

    /**
     * Search for all users in database
     * @return list of found users
     * @throws DAOException if occurred problem in database
     */
    List<User> findAllUsers() throws DAOException;

    /**
     * Updating user email
     * @param user with changed email
     * @return true if update was success
     * @throws DAOException if occurred problem in database
     */
    boolean updateUserEmail(User user) throws DAOException;

    /**
     * Updating user password
     * @param user with changed password
     * @return true if update was success
     * @throws DAOException if occurred problem in database
     */
    boolean updateUserPassword(User user) throws DAOException;

    /**
     * Updating user data info
     * @param user with changed info
     * @return true if update was success
     * @throws DAOException if occurred problem in database
     */
    boolean updateUserData(User user) throws DAOException;
}
