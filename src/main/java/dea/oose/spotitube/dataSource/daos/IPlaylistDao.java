package dea.oose.spotitube.dataSource.daos;

import dea.oose.spotitube.dtos.PlaylistDTO;

import java.util.List;

public interface IPlaylistDao {

    public void addPlaylist(String namePlaylist,boolean isOwner);
    public void deletePlaylist(int id);
    public void editPlaylistName(String name,int id);
    public List<PlaylistDTO> getAllPlaylists();
    public PlaylistDTO getPlaylist(int id);
}
