
package com.fpmislata.daw2.business.service.impl;

import com.fpmislata.daw2.business.service.GenericService;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.persistence.dao.GenericDAO;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {
    @Autowired
    protected GenericDAO<T, ID> genericDAO;
    
    @Override
    public T get(ID id) throws BusinessException {
        return genericDAO.get(id);
    }
    
    @Override
    public T insert(T entity) throws BusinessException {
        return genericDAO.insert(entity);
    }
    
    @Override
    public T update(T entity) throws BusinessException {
        return genericDAO.update(entity);
    }
    
    @Override
    public boolean delete(ID id) throws BusinessException {
        return genericDAO.delete(id);
    }
    
    @Override
    public List<T> findAll() throws BusinessException {
        return genericDAO.findAll();
    }
    
}
