
package com.fpmislata.daw2.business.service.impl;

import com.fpmislata.daw2.business.domain.User;
import com.fpmislata.daw2.business.service.UserService;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.persistence.dao.UserDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {
    @Autowired
    private UserDAO userDAO;
    
    @Override
    public List<User> findByNameEquals(String name) throws BusinessException {
        return userDAO.findByNameEquals(name);
    }

    @Override
    public List<User> findByNameLike(String name) throws BusinessException {
        return userDAO.findByNameLike(name);
    }
    
}
