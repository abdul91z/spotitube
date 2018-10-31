package dea.oose.spotitube.dataSource.daos;


import dea.oose.spotitube.dtos.TracksDTO;

import java.util.List;

public interface ITracksDao {

    void deleteOneTrackFromPlaylist(int playlistId, int trackId);
    public void addTracktoPlaylist(int playlistId, int trackId);
    public void updateOfflineAvailable(int trackId, Boolean offlineAvailable);
    public TracksDTO getOneTrack(int idTrack);
    public List<TracksDTO> getTracksOfPlaylist(int playlistId);
    public List<TracksDTO> getAllTracks();
    public int getAllDuration();

}
