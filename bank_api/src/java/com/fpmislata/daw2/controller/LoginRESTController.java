
package com.fpmislata.daw2.controller;

import com.fpmislata.daw2.business.domain.Role;
import com.fpmislata.daw2.business.domain.User;
import com.fpmislata.daw2.security.WebSession;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginRESTController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            if(!isLogged(httpServletRequest.getSession())) {
                User user = new User(0, "guest", "guest", "surguest", "guest@domain.com", Role.GUEST);
                httpServletRequest.getSession().setAttribute("WEB_SESSION", new WebSession(user, new Date()));
            }
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch(Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            if(isLogged(httpServletRequest.getSession())) {
                httpServletRequest.getSession().setAttribute("WEB_SESSION", null);
            }
            
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch(Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    private boolean isLogged(HttpSession httpSession) {
        WebSession webSession = (WebSession)httpSession.getAttribute("WEB_SESSION");
        if(webSession != null) {
            return (webSession.getUser() != null);
        } else {
            return false;
        }
        
    }
    
}
