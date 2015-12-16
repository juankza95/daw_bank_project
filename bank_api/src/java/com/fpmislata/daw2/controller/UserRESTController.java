package com.fpmislata.daw2.controller;

import com.fpmislata.daw2.business.domain.User;
import com.fpmislata.daw2.business.service.UserService;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.core.exception.BusinessMessage;
import com.fpmislata.daw2.json.JSONTransformer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserRESTController {

    @Autowired
    UserService userService;
    @Autowired
    JSONTransformer jsonTransformer;

    @RequestMapping(value = "/{userID}", method = RequestMethod.GET, produces = "application/json")
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            @PathVariable("userID") int userID) {
        try {
            User user = userService.get(userID);
            if (user != null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(user));
            }
        } catch (BusinessException bex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");

                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonRequest) {
        try {
            User user = jsonTransformer.toObject(jsonRequest, User.class);
            User insertedUser = userService.insert(user);
            if (insertedUser != null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(insertedUser));
            } else {
                httpServletResponse.setStatus(SC_INTERNAL_SERVER_ERROR);
            }
        } catch (BusinessException bex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException e) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, e);
            }
        } catch (IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        List<User> users;
        try {
            if (httpServletRequest.getParameter("userName") != null) {
                users = userService.findByNameLike(httpServletRequest.getParameter("userName"));
            } else {
                users = userService.findAll();
            }
            if (users != null && !users.isEmpty()) {
                httpServletResponse.setStatus(SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(users));
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (BusinessException bex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable int userID, @RequestBody String jsonRequest) {
        try {
            User newUser = jsonTransformer.toObject(jsonRequest, User.class);
            newUser.setUserID(userID);
            User updatedUser = userService.update(newUser);
            if (updatedUser != null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(updatedUser));

            } else {
                httpServletResponse.setStatus(SC_INTERNAL_SERVER_ERROR);
            }
        } catch (BusinessException bex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                httpServletResponse.setStatus(SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            httpServletResponse.setStatus(SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable int userID) {
        try {
            boolean isDeleted = userService.delete(userID);
            if (isDeleted) {
                httpServletResponse.setStatus(SC_OK);
            } else {
                throw new BusinessException(new BusinessMessage(null, "Error: BankEntity already deleted."));
            }
        } catch (BusinessException bex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                httpServletResponse.setStatus(SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
