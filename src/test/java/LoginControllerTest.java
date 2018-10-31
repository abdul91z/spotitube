


import dea.oose.spotitube.controllers.LoginController;
import dea.oose.spotitube.dtos.LoginRequestDTO;
import dea.oose.spotitube.services.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoginControllerTest {

    private LoginService loginService = mock(LoginService.class);
    private LoginController loginController;
    private LoginRequestDTO userDTO;

    private Response response = mock(Response.class);

    String username = "meron";
    String password = "MySuperSecretPassword12341";

    @BeforeEach
    public void setup() {
        userDTO = new LoginRequestDTO();
        loginController = new LoginController();


        loginController.setUserService(loginService);


        userDTO.setUser("meron");
        userDTO.setPassword("MySuperSecretPassword12341");
    }

    @Test
    public void checkAuthentication() {
        when(loginService.checkUsernamePassword(username, password)).thenReturn(true);
        when(loginService.generateNewToken()).thenReturn(anyString());
        when(loginService.setNewToken(username, anyString())).thenReturn(true);
        when(loginService.returnToken(userDTO)).thenReturn(response);
        Response test = loginController.login(userDTO);

        assertEquals(201, test.getStatus());

    }


    @Test
    public void checkFalseAuthentication() {
        when(loginService.checkUsernamePassword(anyString(), anyString())).thenReturn(false);

        Response test = loginController.login(userDTO);

        assertEquals(401, test.getStatus());
    }

}
