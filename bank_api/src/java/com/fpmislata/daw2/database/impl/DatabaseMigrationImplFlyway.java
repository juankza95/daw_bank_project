
package com.fpmislata.daw2.database.impl;

import com.fpmislata.daw2.database.DatabaseMigration;
import com.fpmislata.daw2.persistence.DataSourceFactory;

import org.flywaydb.core.Flyway;

import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseMigrationImplFlyway implements DatabaseMigration {
    @Autowired
    DataSourceFactory dataSourceFactory;
    
    @Override
    public void migrateDB() {
        try {
            Flyway flyway = new Flyway();
            flyway.setDataSource(dataSourceFactory.getDataSource());
            flyway.setLocations("com.fpmislata.daw2.database");
            flyway.setEncoding("utf-8");
            flyway.migrate();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
