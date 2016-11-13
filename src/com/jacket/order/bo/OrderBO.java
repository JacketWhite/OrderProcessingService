package com.jacket.order.bo;

import com.jacket.order.bo.exception.BOException;
import com.jacket.order.dto.Order;

/**
 * Created by feher on 2016. 11. 13..
 */
public interface OrderBO {

    boolean placeOrder(Order order) throws BOException;

    boolean cancelOrder(int id) throws BOException;

    boolean deleteOrder(int id) throws BOException;
}
