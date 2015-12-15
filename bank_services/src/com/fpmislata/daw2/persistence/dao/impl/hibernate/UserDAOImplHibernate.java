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

        // meter en hql la pass?
        User user = (User) session.createQuery("SELECT u FROM user u WHERE email = :email").setParameter("email", email).list().get(0);

        session.getTransaction().commit();
        session.close();

        return (user.getPassword().equals(password));
    }
}
