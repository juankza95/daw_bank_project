
package com.fpmislata.daw2.business.service.impl;

import com.fpmislata.daw2.business.domain.BankEntity;
import com.fpmislata.daw2.business.service.BankEntityService;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.persistence.dao.BankEntityDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class BankEntityServiceImpl extends GenericServiceImpl<BankEntity, Integer> implements BankEntityService {
    @Autowired
    private BankEntityDAO bankEntityDAO;
    
    @Override
    public List<BankEntity> findByNameEquals(String name) throws BusinessException {
        return bankEntityDAO.findByNameEquals(name);
    }

    @Override
    public List<BankEntity> findByNameLike(String name) throws BusinessException {
        return bankEntityDAO.findByNameLike(name);
    }

}
