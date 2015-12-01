
package com.fpmislata.daw2.persistence.dao;

import com.fpmislata.daw2.business.domain.BankEntity;
import com.fpmislata.daw2.core.exception.BusinessException;

import java.util.List;

public interface BankEntityDAO extends GenericDAO<BankEntity, Integer> {
    List<BankEntity> findByNameEquals(String name) throws BusinessException;
    List<BankEntity> findByNameLike(String name) throws BusinessException;
}
