package com.jacket.order.bo.exception;

import java.sql.SQLException;

/**
 * Created by feher on 2016. 11. 13..
 */
public class BOException extends Exception {
    public BOException(SQLException e) {
        super(e);
    }
}
