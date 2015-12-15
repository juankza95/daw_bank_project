
package com.fpmislata.daw2.persistence.dao;

import com.fpmislata.daw2.business.domain.User;
import com.fpmislata.daw2.core.exception.BusinessException;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Integer> {
    List<User> findByNameEquals(String name) throws BusinessException;
    List<User> findByNameLike(String name) throws BusinessException;
    boolean checkUserLogin(String email, String password) throws BusinessException;
}
