
package com.fpmislata.daw2.security;

import com.fpmislata.daw2.business.domain.User;

public interface Authorization {
    Boolean isAuthorizedURL(User user, String url, HTTPMethod httpmethod);
}
