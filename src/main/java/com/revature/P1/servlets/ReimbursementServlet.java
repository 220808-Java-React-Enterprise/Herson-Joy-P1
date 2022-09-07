package com.revature.P1.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1.dtos.requests.ReimbursementRequest;
import com.revature.P1.dtos.responses.Principal;
import com.revature.P1.models.Reimbursement;
import com.revature.P1.services.ReimbursementService;
import com.revature.P1.services.TokenService;
import com.revature.P1.services.UserService;
import com.revature.P1.utils.custom_exceptions.AuthenticationException;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOError;
import java.io.IOException;

public class ReimbursementServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserService userService;
    private final ReimbursementService reimbursementService;

    public ReimbursementServlet(ObjectMapper mapper, TokenService tokenService, UserService userService, ReimbursementService reimbursementService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
        this.reimbursementService = reimbursementService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequesterDetails(token);
        ReimbursementRequest reimbursementRequest = mapper.readValue(req.getInputStream(), ReimbursementRequest.class);

        try{
            if(principal.getRole().equals("100")){ // 100 = DEFAULT
                reimbursementRequest.setAuthor_id(principal.getId());
                Reimbursement reimbursement = reimbursementService.saveReimbursement(reimbursementRequest);
                resp.setStatus(200);

                System.out.println(reimbursement);

                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(reimbursement.getReimb_id()));

            }




        } catch (InvalidRequestException e){
            resp.setStatus(404);
        } catch (AuthenticationException e){
            resp.setStatus(401);
        } catch (NullPointerException e){
            resp.setStatus(401);
        }
    }


}
