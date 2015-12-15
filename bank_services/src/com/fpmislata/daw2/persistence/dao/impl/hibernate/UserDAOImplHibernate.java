package com.fpmislata.daw2.persistence.dao.impl.hibernate;

import com.fpmislata.daw2.business.domain.User;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.persistence.dao.UserDAO;
import java.util.List;
import org.hibernate.Session;

public class UserDAOImplHibernate extends GenericDAOImplHibernate<User, Integer> implements UserDAO {

    @Override
    public List<User> findByNameEquals(String name) throws BusinessException {
        /*
        la HQL está mal.
        List<User> users;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        users = session.createQuery("SELECT User e FROM user u WHERE name = " + name).list();
        
        session.getTransaction().commit();
        session.close();
        return users;*/
        return null;
    }

    @Override
    public List<User> findByNameLike(String name) throws BusinessException {
        return null;
    }

    @Override
    public boolean checkUserLogin(String email, String password) throws BusinessException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // hay algún método para sacar uno y no lista?
        List<User> users = session.createQuery("SELECT u FROM user u WHERE email = " + email).list();

        session.getTransaction().commit();
        session.close();

        return (users.get(0).getPassword().equals(password));
    }
}
