
package com.fpmislata.daw2.controller;

import com.fpmislata.daw2.business.domain.BankEntity;
import com.fpmislata.daw2.business.service.BankEntityService;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.core.exception.BusinessMessage;
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
    
    @RequestMapping(value = "/{bankEntityID}", method = RequestMethod.GET, produces = "application/json")
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                @PathVariable("bankEntityID") int bankEntityID) {
        try {
            BankEntity bankEntity = bankEntityService.get(bankEntityID);
            if(bankEntity != null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bankEntity));
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch(BusinessException bex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(BankEntityRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch(IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(BankEntityRESTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                @RequestBody String jsonRequest) {
        try {
            BankEntity newBankEntity = jsonTransformer.toObject(jsonRequest, BankEntity.class);
            BankEntity insertedBankEntity = bankEntityService.insert(newBankEntity);
            if(insertedBankEntity != null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(insertedBankEntity));
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch(BusinessException bex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(BankEntityRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch(IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(BankEntityRESTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/{bankEntityID}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                @PathVariable("bankEntityID") int bankEntityID, @RequestBody String jsonRequest) {
        try {
            BankEntity newBankEntity = jsonTransformer.toObject(jsonRequest, BankEntity.class);
            newBankEntity.setBankEntityID(bankEntityID);
            BankEntity updatedBankEntity = bankEntityService.update(newBankEntity);
            if(updatedBankEntity != null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(updatedBankEntity));
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch(BusinessException bex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(BankEntityRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch(IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(BankEntityRESTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/{bankEntityID}", method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                @PathVariable("bankEntityID") int bankEntityID) {
        try {
            boolean isDeleted = bankEntityService.delete(bankEntityID);
            if(isDeleted) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            } else {
                throw new BusinessException(new BusinessMessage(null, "Error: BankEntity already deleted."));
            }
        } catch(BusinessException bex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(BankEntityRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch(Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(BankEntityRESTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            List<BankEntity> bankEntitys;
            
            if(httpServletRequest.getParameter("bankEntityName") != null) {
                bankEntitys = bankEntityService.findByNameLike(httpServletRequest.getParameter("bankEntityName"));
            } else {
                bankEntitys = bankEntityService.findAll();
            }
            
            if(bankEntitys != null && !bankEntitys.isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bankEntitys));
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch(BusinessException bex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonTransformer.toJSON(bex.getBusinessMessages()));
            } catch (IOException ex) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch(IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(UserRESTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
