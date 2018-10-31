package dea.oose.spotitube.controllers;

import dea.oose.spotitube.dtos.LoginRequestDTO;
import dea.oose.spotitube.services.LoginService;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/login")
public class LoginController {



    LoginService loginService;


    public static String username;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO login) {

        username = login.getUser();
        String password = login.getPassword();

        if (loginService.checkUsernamePassword(username, password)) {


            loginService.setNewToken(username, loginService.generateNewToken());

            return loginService.returnToken(login);

        }
        return Response.status(401).build();
    }

    @Inject
    public void setUserService(LoginService loginService) {
        this.loginService = loginService;
    }


}
