package by.estore.service;

import by.estore.bean.Good;
import by.estore.service.exception.ServiceException;

import java.util.List;

public interface GoodService {
    List<Good> getAllGoods() throws ServiceException;
    Good getGoodById(Long id) throws ServiceException;
}
