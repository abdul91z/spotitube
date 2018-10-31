
import dea.oose.spotitube.controllers.PlaylistsController;
import dea.oose.spotitube.dtos.PlaylistDTO;
import dea.oose.spotitube.services.LoginService;
import dea.oose.spotitube.services.PlaylistsService;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlaylistControllerTest {


    private final String TOKEN = "1234-1234-1234";
    private final int ID = 1;


    PlaylistsController playlistsController;

    PlaylistsService playlistsService = mock(PlaylistsService.class);
    LoginService loginService = mock(LoginService.class);

    JSONObject jsonPlaylist;
    Response response  = mock(Response.class);;

    PlaylistDTO playlistDTO;


    @BeforeEach
    public void setup() {

        playlistsController = new PlaylistsController();

        playlistsController.setPlaylistsService(playlistsService);
        playlistsController.setLoginService(loginService);

        jsonPlaylist = new JSONObject();

        playlistDTO = new PlaylistDTO();


    }

    @Test
    public void testGetAllPlaylist() {

        when(loginService.isTokenCorrect(TOKEN)).thenReturn(true);
        when(playlistsService.getAllPlaylistsJson(jsonPlaylist, 200)).thenReturn(response);
        Response test = playlistsController.getAllPlaylists(TOKEN);
        assertEquals(200, test.getStatus());

        verify(loginService).isTokenCorrect(TOKEN);
        verify( playlistsService.getAllPlaylistsJson(jsonPlaylist,200));
    }



    @Test
    public void testDeletePlaylist() {

        when(loginService.isTokenCorrect(TOKEN)).thenReturn(true);
        when(playlistsService.deletePlaylist(ID)).thenReturn(true);
        when(playlistsService.getAllPlaylistsJson(jsonPlaylist,200)).thenReturn(response);
        Response test = playlistsController.deletePlaylist(TOKEN,ID);
        assertEquals(200, test.getStatus());

        verify(loginService).isTokenCorrect(TOKEN);
        verify(playlistsService).deletePlaylist( 45);
        verify( playlistsService.getAllPlaylistsJson(jsonPlaylist,200));
    }




    @Test
    public void testAddPlaylist() {

        when(loginService.isTokenCorrect(TOKEN)).thenReturn(true);
        when(playlistsService.getAllPlaylistsJson(jsonPlaylist,200)).thenReturn(response);
        Response test = playlistsController.addPlaylist(TOKEN,playlistDTO);
        assertEquals(200, test.getStatus());

        verify(loginService).isTokenCorrect(TOKEN);
        verify(playlistsService).addPlaylist("Abdul", true);
        verify( playlistsService.getAllPlaylistsJson(jsonPlaylist,200));
    }


    @Test
    public void testEditPlaylistName() {

        when(loginService.isTokenCorrect(TOKEN)).thenReturn(true);
        when(playlistsService.editPlaylistName("Abdul", 45)).thenReturn(true);
        when(playlistsService.getAllPlaylistsJson(jsonPlaylist,200)).thenReturn(response);
        Response test = playlistsController.editPlaylistName(TOKEN,ID,playlistDTO);
        assertEquals(200, test.getStatus());

        verify(loginService).isTokenCorrect(TOKEN);
        verify(playlistsService).editPlaylistName("Abdul", 45);
        verify( playlistsService.getAllPlaylistsJson(jsonPlaylist,200));
    }

}