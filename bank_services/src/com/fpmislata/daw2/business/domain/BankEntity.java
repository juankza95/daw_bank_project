
package com.fpmislata.daw2.business.domain;

import java.io.Serializable;
import java.util.Date;

public class BankEntity implements Serializable{
    private int bankEntityID;
    private String name;
    private int bankCode;
    private Date creationDate;
    private String address;
    private String ctc; //CompanyTaxCode
    
    public BankEntity() { }
    
    public BankEntity(int bankEntityID, String name, int bankCode, Date creationDate, String address, String ctc) {
        this.bankEntityID = bankEntityID;
        this.name = name;
        this.bankCode = bankCode;
        this.creationDate = creationDate;
        this.address = address;
        this.ctc = ctc;
    }

    public int getBankEntityID() {
        return bankEntityID;
    }
    public void setBankEntityID(int bankEntityID) {
        this.bankEntityID = bankEntityID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getBankCode() {
        return bankCode;
    }
    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCtc() {
        return ctc;
    }
    public void setCtc(String ctc) {
        this.ctc = ctc;
    }
    
}
