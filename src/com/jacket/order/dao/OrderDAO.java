package com.jacket.order.dao;

import com.jacket.order.dto.Order;

import java.sql.SQLException;

/**
 * Created by feher on 2016. 11. 13..
 */
public interface OrderDAO {

    int create(Order order) throws SQLException;

    Order read(int id) throws SQLException;

    int update(Order order) throws SQLException;

    int delete(int id) throws SQLException;
}
