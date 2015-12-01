
package com.fpmislata.daw2.security;

import com.fpmislata.daw2.business.domain.User;

import java.util.Date;

public class WebSession {
    private User user;
    private Date date;
    
    public WebSession() { }
    
    public WebSession(User user, Date date) {
        this.user = user;
        this.date = date;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
    
}
