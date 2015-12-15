
package com.fpmislata.daw2.core.exception;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//import javax.validation.ConstraintViolation;

public class BusinessException extends Exception {
    private Set<BusinessMessage> businessMessages = new TreeSet<>();
    
    public BusinessException(List<BusinessMessage> businessMessages) {
        this.businessMessages.addAll(businessMessages);
    }
    
    public BusinessException(BusinessMessage businessMessage) {
        this.businessMessages.add(businessMessage);
    }
    
    public BusinessException(Exception ex) {
        this.businessMessages.add(new BusinessMessage(null, ex.toString()));
    }
    /*
    public BusinessException(javax.validation.ConstraintViolationException cve) {
        for(ConstraintViolation constraintViolation : cve.getConstraintViolations()) {
            String fieldName;
            String message;
            
            fieldName = getCaptions(constraintViolation.getRootBeanClass(), constraintViolation.getPropertyPath());
            message = constraintViolation.getMessage();
            
            businessMessages.add(new BusinessMessage(fieldName, message));
        }
    }
    */
    public Set<BusinessMessage> getBusinessMessages() {
        return businessMessages;
    }
    
}
