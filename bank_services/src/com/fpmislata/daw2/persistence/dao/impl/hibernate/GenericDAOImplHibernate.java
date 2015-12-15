package com.fpmislata.daw2.persistence.dao.impl.hibernate;

import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.persistence.dao.GenericDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GenericDAOImplHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {

    // Usar autowired
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public T get(ID id) throws BusinessException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        T t = (T) session.get(getEntityClass(), id);
        session.getTransaction().commit();
        session.close();
        return t;
    }

    @Override
    public T insert(T t) throws BusinessException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(t);
        session.getTransaction().commit();
        session.close();

        return t;
    }

    @Override
    public T update(T t) throws BusinessException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(t);

        session.getTransaction().commit();
        session.close();
        return t;
    }

    @Override
    public boolean delete(ID id) throws BusinessException {
        Session session = sessionFactory.openSession();
        boolean result;

        T t = get(id);
        if (t != null) {
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
            result = true;
        } else {
            result = false;
        }

        session.close();
        return result;
    }

    @Override
    public List<T> findAll() throws BusinessException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<T> t = session.createQuery("SELECT t FROM " + getEntityClass().getName() + " t").list();

        session.getTransaction().commit();
        session.close();
        return t;
    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
