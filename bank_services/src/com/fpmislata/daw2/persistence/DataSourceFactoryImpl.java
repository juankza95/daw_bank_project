
package com.fpmislata.daw2.persistence;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataSourceFactoryImpl implements DataSourceFactory {
    @Override
    public DataSource getDataSource() {
        try {
            InitialContext initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            DataSource dataSource = (DataSource)envCtx.lookup("jdbc/MySQLDS");
            return dataSource;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
