import dea.oose.spotitube.dtos.LoginRequestDTO;
import dea.oose.spotitube.dataSource.daos.ILoginDao;
import dea.oose.spotitube.dataSource.daos.LoginDao;
import dea.oose.spotitube.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;


import static org.mockito.Mockito.*;


import java.sql.ResultSet;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginServiceTest {

    LoginService loginService;
    String  username = "abdul";
    String password = "00000000";
    String token = "1234-1234-1234";

    LoginRequestDTO loginRequestDTO ;
    ILoginDao loginDao = mock(LoginDao.class);
    ResultSet resultSet = mock(ResultSet.class);
    Response response = mock(Response.class);

    @BeforeEach
    public void setup(){
        loginService = new LoginService();
        loginService.setLoginDao(loginDao);


        loginRequestDTO = new LoginRequestDTO();;



    }


    @Test
    public void TestCheckUserPassword() {

        loginRequestDTO.setUser(username);
        loginRequestDTO.setPassword(password);
        when(loginDao.checkUsernamePassword(username,password)).thenReturn(resultSet);

        Boolean checkUsernamePassword = loginService.checkUsernamePassword(username, password);

        assertEquals(true, checkUsernamePassword);

    }



    @Test
    public void TestIsTokenCorrect(){

        when(loginDao.getToken(username)).thenReturn(token);

        boolean isTokenCorrect =  loginService.isTokenCorrect(token);

        assertEquals(true, isTokenCorrect);

        //verify(loginDao).getToken(username);


    }


    @Test
    public void TestReturnTolken(){
        loginRequestDTO.setUser(username);
        when(loginService.returnToken(loginRequestDTO)).thenReturn(response);
       Response test= loginService.returnToken(loginRequestDTO);
       assertEquals(201,test.getStatus());
    }


}
