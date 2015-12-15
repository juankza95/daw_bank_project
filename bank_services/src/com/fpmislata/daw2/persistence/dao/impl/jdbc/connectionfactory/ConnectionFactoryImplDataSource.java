
package com.fpmislata.daw2.persistence.dao.impl.jdbc.connectionfactory;

import com.fpmislata.daw2.persistence.DataSourceFactory;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;

public class ConnectionFactoryImplDataSource implements ConnectionFactory {
    @Autowired
    DataSourceFactory dataSourceFactory;
    
    @Override
    public Connection getConnection() {
        try {
            return dataSourceFactory.getDataSource().getConnection();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void close(Connection connection) {
        try {
            connection.close();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
