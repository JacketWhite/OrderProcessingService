package com.jacket.order.bo;

import com.jacket.order.bo.exception.BOException;
import com.jacket.order.dao.OrderDAO;
import com.jacket.order.dto.Order;

import java.sql.SQLException;

/**
 * Created by feher on 2016. 11. 13..
 */
public class OrderBOImpl implements OrderBO {

    OrderDAO dao;

    public OrderDAO getDao() {
        return dao;
    }

    public void setDao(OrderDAO dao) {
        this.dao = dao;
    }


    @Override
    public boolean placeOrder(Order order) throws BOException {
        try {
            int result = dao.create(order);
            if (result == 0) return false;
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws BOException {
        try {
            Order order = dao.read(id);
            order.setStatus("cancelled");
            int result = dao.update(order);
            if (result == 0) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws BOException {
        try {
            int result = dao.delete(id);
            if (result == 0) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
