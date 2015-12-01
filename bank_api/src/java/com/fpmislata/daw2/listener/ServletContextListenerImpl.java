
package com.fpmislata.daw2.listener;

import com.fpmislata.daw2.database.DatabaseMigration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServletContextListenerImpl implements ServletContextListener {
    @Autowired
    DatabaseMigration dbmigration;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\nSTARTING...\n");
        
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
        
        dbmigration.migrateDB();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nSTOPPING...\n");
    }

}
