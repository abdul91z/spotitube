import dea.oose.spotitube.dataSource.daos.IPlaylistDao;
import dea.oose.spotitube.dataSource.test.FakePlaylistDAO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFakePlaylist {

IPlaylistDao fakePlaylist;

    @Before
    public void setUp() throws Exception {
        fakePlaylist = new FakePlaylistDAO();
    }

    @Test
    public void testGetallPlaylist()
    {
        assertEquals(0, fakePlaylist.getAllPlaylists().size());
    }


    @Test
    public void testAddPlaylist()
    {
        fakePlaylist.addPlaylist("Abdul", true);
        assertEquals(1, fakePlaylist.getAllPlaylists().size());
    }

    @Test
    public void testDeletePlaylist()
    {
        fakePlaylist.deletePlaylist(1);
        assertEquals(0, fakePlaylist.getAllPlaylists().size());
    }

    @Test
    public void testUpdate()
    {
        fakePlaylist.addPlaylist("Kenny", true);
        fakePlaylist.editPlaylistName( "notMyList",0);
        assertEquals("notMyList", fakePlaylist.getPlaylist(0).getName());
    }

}



