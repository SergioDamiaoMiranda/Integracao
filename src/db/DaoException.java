package db;

import java.sql.SQLException;

public class DaoException extends Exception {
    
    public DaoException(Throwable e) {
        super(e);
    }
    
    public DaoException(String msg, SQLException e) {
        super(msg, e);
    }
    
    public DaoException() {
    }
    
}
