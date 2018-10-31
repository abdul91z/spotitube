package dea.oose.spotitube.services;





import dea.oose.spotitube.dataSource.daos.IPlaylistDao;
import dea.oose.spotitube.dataSource.daos.ITracksDao;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.core.Response;



public class PlaylistsService {



    IPlaylistDao playlistsDao;


    ITracksDao tracksDao;




    public Response getAllPlaylistsJson(JSONObject jsonPlaylist, int status) {


        jsonPlaylist.put("playlists", playlistsDao.getAllPlaylists());

        jsonPlaylist.put("length", tracksDao.getAllDuration());

        return Response.accepted(jsonPlaylist).status(status).
                build();
    }

    public boolean deletePlaylist(int id) {


        playlistsDao.deletePlaylist(id);

        return true;
    }


    public void addPlaylist(String namePlaylist, boolean isOwner) {

        playlistsDao.addPlaylist(namePlaylist, true);

    }


    public boolean editPlaylistName(String name, int id) {

        playlistsDao.editPlaylistName(name, id);
        return true;

    }


    public Response getplaylist(JSONObject jsonPlaylist, int status, int id) {


        jsonPlaylist.put("playlists", playlistsDao.getPlaylist(id));


        return Response.accepted(jsonPlaylist).status(status).
                build();
    }


    @Inject
    public void setTracksDao(ITracksDao tracksDao) {
        this.tracksDao = tracksDao;
    }
    @Inject
    public void setPlaylistsDao(IPlaylistDao playlistsDao) {
        this.playlistsDao = playlistsDao;
    }
}
