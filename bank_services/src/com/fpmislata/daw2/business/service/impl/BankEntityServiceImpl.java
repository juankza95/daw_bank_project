package com.fpmislata.daw2.business.service.impl;

import com.fpmislata.daw2.business.domain.BankEntity;
import com.fpmislata.daw2.business.service.BankEntityService;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.core.exception.BusinessMessage;
import com.fpmislata.daw2.persistence.dao.BankEntityDAO;
import java.util.ArrayList;

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

    @Override
    public BankEntity insert(BankEntity bankEntity) throws BusinessException {
        List<BusinessMessage> businessMessages = new ArrayList();
        BusinessException businessException;
        if (bankEntity.getName() == null || bankEntity.getName().equals("")) {
            businessMessages.add(new BusinessMessage("Name", "Name cannot be null."));
        }
        if (bankEntity.getAddress() == null || bankEntity.getAddress().equals("")) {
            businessMessages.add(new BusinessMessage("Address", "Address cannot be null."));
        }

        if (String.valueOf(bankEntity.getBankCode()).length() != 5) {
            businessMessages.add(new BusinessMessage("Bank Code", "Bank code must be 5 chars lenght."));
        }
        if (bankEntity.getCreationDate() == null) {
            businessMessages.add(new BusinessMessage("Date", "Date cannot be null."));
        }

        if (!businessMessages.isEmpty()) {
            businessException = new BusinessException(businessMessages);
            throw businessException;
        }
        return bankEntityDAO.insert(bankEntity);
    }

    @Override
    public BankEntity update(BankEntity bankEntity) throws BusinessException {
        List<BusinessMessage> businessMessages = new ArrayList();
        BusinessException businessException;
        if (bankEntity.getName() == null || bankEntity.getName().equals("")) {
            businessMessages.add(new BusinessMessage("Name", "Name cannot be null."));
        }

        if (bankEntity.getAddress().equals("") || bankEntity.getAddress() == null) {
            businessMessages.add(new BusinessMessage("Address", "Address cannot be null."));
        }

        if (String.valueOf(bankEntity.getBankCode()).length() != 5) {
            businessMessages.add(new BusinessMessage("Bank Code", "Bank code must be 5 chars lenght."));
        }
        if (bankEntity.getCreationDate() == null) {
            businessMessages.add(new BusinessMessage("Date", "Date cannot be null."));
        }

        if (!businessMessages.isEmpty()) {
            businessException = new BusinessException(businessMessages);
            throw businessException;
        }
        return bankEntityDAO.update(bankEntity);
    }
}
