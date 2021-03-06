package com.jacket.order.bo;

import com.jacket.order.bo.exception.BOException;
import com.jacket.order.dao.OrderDAO;
import com.jacket.order.dto.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by feher on 2016. 11. 13..
 */
public class OrderBOImplTest {

    @Mock
    OrderDAO dao;
    private OrderBOImpl bo;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        bo = new OrderBOImpl();
        bo.setDao(dao);
    }

    @Test
    public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {

        Order order = new Order();
        when(dao.create(order)).thenReturn(1);
        boolean result = bo.placeOrder(order);

        assertTrue(result);
        verify(dao).create(order);
    }

    @Test
    public void placeOrder_Should_Not_Create_An_Order() throws SQLException, BOException {

        Order order = new Order();
        when(dao.create(order)).thenReturn(0);
        boolean result = bo.placeOrder(order);

        assertFalse(result);
        verify(dao).create(order);
    }

    @Test(expected = BOException.class)
    public void placeOrder_Should_Throw_BOException() throws SQLException, BOException {

        Order order = new Order();
        when(dao.create(order)).thenThrow(SQLException.class);
        boolean result = bo.placeOrder(order);
    }

    @Test
    public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
        Order order = new Order();
        when(dao.read(123)).thenReturn(order);
        when(dao.update(order)).thenReturn(1);
        boolean result = bo.cancelOrder(123);

        assertTrue(result);

        verify(dao).read(123);
        verify(dao).update(order);
    }

    @Test
    public void cancelOrder_Should_Not_Cancel_The_Order() throws SQLException, BOException {
        Order order = new Order();
        when(dao.read(123)).thenReturn(order);
        when(dao.update(order)).thenReturn(0);
        boolean result = bo.cancelOrder(123);

        assertFalse(result);

        verify(dao).read(123);
        verify(dao).update(order);
    }

    @Test(expected = BOException.class)
    public void cancelOrder_ShouldThrowABOExceptionOnRead() throws SQLException, BOException {
        //noinspection unchecked
        when(dao.read(123)).thenThrow(SQLException.class);
        bo.cancelOrder(123);

    }

    @Test(expected = BOException.class)
    public void cancelOrder_Should_Throw_A_BOExceptionOnUpdate() throws SQLException, BOException {
        Order order = new Order();
        when(dao.read(123)).thenReturn(order);
        //noinspection unchecked
        when(dao.update(order)).thenThrow(SQLException.class);
        bo.cancelOrder(123);
    }

    @Test
    public void deleteOrder() throws Exception {

    }

}