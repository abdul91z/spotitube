import dea.oose.spotitube.dataSource.daos.ITracksDao;
import dea.oose.spotitube.dataSource.daos.TracksDao;
import dea.oose.spotitube.dtos.PlaylistDTO;
import dea.oose.spotitube.dataSource.daos.IPlaylistDao;
import dea.oose.spotitube.dataSource.daos.PlaylistsDao;
import dea.oose.spotitube.services.PlaylistsService;
import dea.oose.spotitube.services.TracksService;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlaylistServiceTest {


PlaylistsService playlistsService;
TracksService tracksService;

ITracksDao tracksDao = mock(TracksDao.class);
IPlaylistDao playlistsDao = mock(PlaylistsDao.class);

    List<PlaylistDTO> list ;
    JSONObject jsonPlaylist;


    @BeforeEach
    public void setup(){

        playlistsService = new PlaylistsService();
        tracksService = new TracksService();

    tracksService.setTracksDao(tracksDao);
        playlistsService.setPlaylistsDao(playlistsDao);

        playlistsService.setTracksDao(tracksDao);

         jsonPlaylist = new JSONObject();

         list = new ArrayList<>();

    }


    @Test
    public void testGetAllPlaylistsJson(){

        when(playlistsDao.getAllPlaylists()).thenReturn(list);
        when(tracksDao.getAllDuration()).thenReturn(150);

        Response test = playlistsService.getAllPlaylistsJson(jsonPlaylist,200);

        assertEquals(200,test.getStatus());

    }


}
