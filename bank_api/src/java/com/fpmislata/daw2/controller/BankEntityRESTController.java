
package com.fpmislata.daw2.controller;

import com.fpmislata.daw2.business.domain.BankEntity;
import com.fpmislata.daw2.business.service.BankEntityService;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.json.JSONTransformer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/bankentity")
public class BankEntityRESTController {
    @Autowired
    BankEntityService bankEntityService;
    @Autowired
    JSONTransformer jsonTransformer;
    
    //TODO
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            List<BankEntity> bankEntitys = bankEntityService.findAll();
            if(bankEntitys != null && !bankEntitys.isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bankEntitys));
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
                //httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                //httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
                //httpServletResponse.getWriter().println("[{ \"idEntidadBancaria\" : null }]");
            }
        } catch(BusinessException bex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch(Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
}
