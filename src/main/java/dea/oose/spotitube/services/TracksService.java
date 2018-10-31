package dea.oose.spotitube.services;



import dea.oose.spotitube.dtos.TracksDTO;
import dea.oose.spotitube.dataSource.daos.ITracksDao;
import org.json.simple.JSONObject;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

public class TracksService {





    ITracksDao tracksDao;

    public Response getAllTracksJson(JSONObject jsonPlaylist, int status){


        jsonPlaylist.put("tracks", tracksDao.getAllTracks());


        return Response.accepted(jsonPlaylist).status(status).
                build();
    }


    public Response getTrackOfPlaylist(JSONObject jsonPlaylist, int status,int id){



        jsonPlaylist.put("tracks", tracksDao.getTracksOfPlaylist(id));


        return Response.accepted(jsonPlaylist).status(status).
                build();
    }


    public boolean offlineAvailableIsTheSame(int trackId, Boolean offlineAvailable) {
        return tracksDao.getOneTrack(trackId).isOfflineAvailable() == offlineAvailable;
    }


    public void updateOfflineAvailable(int trackId, Boolean offlineAvailable) {
        tracksDao.updateOfflineAvailable(trackId, offlineAvailable);
    }



    public boolean trackExistsInPlaylist(int playlistId, int trackId){
       List <TracksDTO> tracksOfPlaylist = tracksDao.getTracksOfPlaylist(playlistId);
        for (TracksDTO track : tracksOfPlaylist){
            if (track.getId() == trackId){
                return true;
            }
        }
        return false;
    }


    public boolean isTrackExistsInServer(int trackId){
        return tracksDao.getOneTrack(trackId) != null;
    }


    public void addTrackToPlaylist(int playlistId, int trackId){
        tracksDao.addTracktoPlaylist(playlistId,trackId);

    }



    public void deleteTrackFromPlaylist(int playlistId, int trackId){
        tracksDao.deleteOneTrackFromPlaylist(playlistId, trackId);
    }

    @Inject
    public void setTracksDao(ITracksDao tracksDao) {
        this.tracksDao = tracksDao;
    }
}
