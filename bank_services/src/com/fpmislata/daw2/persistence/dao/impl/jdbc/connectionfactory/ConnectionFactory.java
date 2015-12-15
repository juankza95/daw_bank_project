
package com.fpmislata.daw2.persistence.dao.impl.jdbc.connectionfactory;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection getConnection();
    void close(Connection connection);
}
