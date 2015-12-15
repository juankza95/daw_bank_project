package com.fpmislata.daw2.persistence.dao.impl.hibernate;

import com.fpmislata.daw2.business.domain.BankEntity;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.persistence.dao.BankEntityDAO;
import java.util.List;


public class BankEntityDAOImplHibernate extends GenericDAOImplHibernate<BankEntity, Integer> implements BankEntityDAO {

    @Override
    public List<BankEntity> findByNameEquals(String name) throws BusinessException {
        return null;
    }

    @Override
    public List<BankEntity> findByNameLike(String name) throws BusinessException {
        return null;
    }
}
