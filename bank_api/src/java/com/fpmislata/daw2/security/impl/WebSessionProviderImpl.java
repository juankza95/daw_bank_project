
package com.fpmislata.daw2.security.impl;

import com.fpmislata.daw2.security.WebSession;
import com.fpmislata.daw2.security.WebSessionProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebSessionProviderImpl implements WebSessionProvider {

    @Override
    public WebSession getWebSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpSession httpSession = httpServletRequest.getSession();
        
        return (WebSession)httpSession.getAttribute("WEB_SESSION");
    }

}
