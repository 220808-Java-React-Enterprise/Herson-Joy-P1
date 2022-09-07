package com.revature.P1.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1.dtos.requests.NewUserRequest;
import com.revature.P1.dtos.responses.Principal;
import com.revature.P1.models.User;
import com.revature.P1.services.TokenService;
import com.revature.P1.services.UserService;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;
import com.revature.P1.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserService userService;

    public UserServlet(ObjectMapper mapper, TokenService tokenService, UserService userService) {
        this.mapper = mapper;
        mapper.findAndRegisterModules();
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /* New user request from Postman */
            /* mapper obj convert JSON request and store into into a NewUserRequest.class */
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);

            //System.out.println(request.getId());

            request.setId(UUID.randomUUID().toString());
            User createdUser = userService.register(request);

            resp.setStatus(200); // CREATED
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(createdUser.getUser_id()));
        }
        catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
            e.printStackTrace();
        } catch (Exception e) {
            resp.setStatus(404); // BAD REQUEST
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequesterDetails(token);

        try {
            if (principal.getRole().equals("300")) {
                String username = req.getParameter("username");
                String role_id = req.getParameter("role_id");
                String change_role = req.getParameter("change_role");

                resp.setContentType("application/json");
                if (username != null) {
                    if(change_role != null){
                        userService.setRoleIdForUser(username, change_role);
                    }
                    resp.getWriter().write(mapper.writeValueAsString(userService.getUserByUsername(username)));
                } else if(role_id != null){
                    //boolean is_active_boolean = Boolean.parseBoolean(is_active);
                    //System.out.println(is_active);
                    //System.out.println(is_active_boolean);
                    //int rows = userService.setIsActiveForUser(username, is_active_boolean);
                    //resp.getWriter().write(rows);

                    List<User> userList = userService.getAllUsersByRoleId(role_id);
                    resp.getWriter().write(mapper.writeValueAsString(userList));
                }
                else {
                    List<User> userList = userService.getAllUsers();
                    resp.getWriter().write(mapper.writeValueAsString(userList));
                }
            } else {
                resp.setStatus(403); // FORBIDDEN
            }
        } catch (NullPointerException e) {
            resp.setStatus(401); // UNAUTHORIZED
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        }
    }
}
