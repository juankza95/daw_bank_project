package com.fpmislata.daw2.persistence.dao.impl.hibernate;

import com.fpmislata.daw2.business.domain.BankEntity;
import java.sql.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankEntityDAOImplHibernateTest {

    public BankEntityDAOImplHibernateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        HibernateUtil.buildSessionFactory("com/fpmislata/daw2/persistence/dao/impl/hibernate/hibernate.test.cfg.xml");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        int id = 4;
        BankEntityDAOImplHibernate instance = new BankEntityDAOImplHibernate();
        BankEntity result = instance.get(id);
        assertEquals("23213213", result.getName());
    }

    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        BankEntity entity = new BankEntity(10, "Bankia", 122, new java.util.Date(), "Calle falsa", "123");
        
        BankEntityDAOImplHibernate instance = new BankEntityDAOImplHibernate();
        BankEntity result = instance.insert(entity);
        assertEquals(entity, result);
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        BankEntityDAOImplHibernate instance = new BankEntityDAOImplHibernate();
        boolean deleted = instance.delete(11);
        assertEquals(deleted, true);
    }

}
