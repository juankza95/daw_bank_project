
package com.fpmislata.daw2.business.service;

import com.fpmislata.daw2.core.exception.BusinessException;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, ID extends Serializable> {
    T get(ID id) throws BusinessException;
    T insert(T entity) throws BusinessException;
    T update(T entity) throws BusinessException;
    boolean delete(ID id) throws BusinessException;
    List<T> findAll() throws BusinessException;
}
