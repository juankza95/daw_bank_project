
package com.fpmislata.daw2.business.service;

import com.fpmislata.daw2.business.domain.User;
import com.fpmislata.daw2.core.exception.BusinessException;

import java.util.List;

public interface UserService extends GenericService<User, Integer> {
    List<User> findByNameEquals(String name) throws BusinessException;
    List<User> findByNameLike(String name) throws BusinessException;
}
