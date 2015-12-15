
package com.fpmislata.daw2.security.impl;

import com.fpmislata.daw2.business.domain.Role;
import com.fpmislata.daw2.business.domain.User;
import com.fpmislata.daw2.security.Authorization;
import com.fpmislata.daw2.security.HTTPMethod;

public class AuthorizationImplDummy implements Authorization {

    private final String URL_LOGIN = "/bank_api/api/login";
    
    @Override
    public Boolean isAuthorizedURL(User user, String url, HTTPMethod httpmethod) {
        if(url.equalsIgnoreCase(URL_LOGIN)) {
            return true;
        } else {
//            if(user != null) {
//                if(user.getRole() == Role.ROOT) {
//                    return true;
//                } else if(user.getRole() == Role.ADMIN) {
//                    return (httpmethod == HTTPMethod.GET || httpmethod == HTTPMethod.PUT || httpmethod == HTTPMethod.POST);
//                } else if(user.getRole() == Role.USER) {
//                    return (httpmethod == HTTPMethod.GET || httpmethod == HTTPMethod.PUT);
//                } else if(user.getRole() == Role.GUEST) {
//                    return (httpmethod == HTTPMethod.GET);
//                } else {
//                    return false;
//                }
           // } else {
                return true;
            }
        }
    }


