package dea.oose.spotitube.services;

import dea.oose.spotitube.controllers.LoginController;
import dea.oose.spotitube.dtos.TokenDTO;
import dea.oose.spotitube.dtos.LoginRequestDTO;
import dea.oose.spotitube.dataSource.daos.ILoginDao;


import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


public class LoginService {



    ILoginDao loginDao;


    public boolean checkUsernamePassword(String username, String password) {


        ResultSet result = loginDao.checkUsernamePassword(username, password);
        try {
            return result.isBeforeFirst();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


    public Response returnToken(LoginRequestDTO login) {
        return Response.accepted(new TokenDTO(loginDao.getFirstName(login.getUser()) + " " + loginDao.getLastName(login.getUser()), loginDao.getToken(login.getUser()))).status(201).build();
    }


    public boolean isTokenCorrect(String token) {

        return token.equals(loginDao.getToken(LoginController.username));


    }


    public String generateNewToken(){

        Random rand = new Random();

        int  n = rand.nextInt(9);
        int  n1 = rand.nextInt(9);
        int  n2 = rand.nextInt(9);
        int  n3 = rand.nextInt(9);
        int  n4 = rand.nextInt(9);
        int  n5 = rand.nextInt(9);
        int  n6 = rand.nextInt(9);
        int  n7 = rand.nextInt(9);
        int  n8 = rand.nextInt(9);
        int  n9 = rand.nextInt(9);
        int  n10 = rand.nextInt(9);
        int  n11 = rand.nextInt(9);

        String token = "" + n+n1+n2+n3+"-"+n4+n5+n6+n7+"-"+n8+n9+n10+n11;
        return token;
    }

    public boolean setNewToken(String userName, String token) {
        loginDao.setNewToken(userName, token);
        return true;
    }

    @Inject
    public void setLoginDao(ILoginDao loginDao) {
        this.loginDao = loginDao;
    }
}
