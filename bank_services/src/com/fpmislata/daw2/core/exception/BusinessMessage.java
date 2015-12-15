
package com.fpmislata.daw2.core.exception;

import org.springframework.util.StringUtils;

public class BusinessMessage implements Comparable<BusinessMessage> {
    private String fieldName;
    private String message;

    public BusinessMessage() { }
    
    public BusinessMessage(String fieldName, String message) {
        if(message == null) {
            throw new IllegalArgumentException("message can't be null");
        }
        
        if((fieldName != null) && (fieldName.trim().equals(""))) {
            this.fieldName = null;
        } else {
            this.fieldName = StringUtils.capitalize(fieldName);
        }
        
        this.message = StringUtils.capitalize(message);
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        if (fieldName != null) {
            return "'" + fieldName + "' - " + message;
        } else {
            return message;
        }
    }
    
    @Override
    public int compareTo(BusinessMessage o) {
        if((getFieldName()==null) && (o.getFieldName()==null)) {
            return getMessage().compareTo(o.getMessage());
        } else if((getFieldName()==null) && (o.getFieldName()!=null)) {
            return 1;
        } else if((getFieldName()!=null) && (o.getFieldName()==null)) {
            return -1;
        } else if((getFieldName()!=null) && (o.getFieldName()!=null)) {
            if (getFieldName().equals(o.getFieldName())) {
                return getMessage().compareTo(o.getMessage());
            } else {
                return getFieldName().compareTo(o.getFieldName());
            }
        } else {
            throw new RuntimeException("Logic Error");
        }
    }
    
}
