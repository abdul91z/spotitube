package dea.oose.spotitube.dataSource.test;

import dea.oose.spotitube.dataSource.daos.IPlaylistDao;
import dea.oose.spotitube.dtos.PlaylistDTO;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class FakePlaylistDAO implements IPlaylistDao {

	private List<PlaylistDTO> playlist;


	public FakePlaylistDAO()

	{
		playlist = new ArrayList<>();

	}

	@Override
	public void addPlaylist(String namePlaylist, boolean isOwner) {

	playlist.add(new PlaylistDTO(1,namePlaylist,isOwner,null ));
	}

	@Override
	public void deletePlaylist(int id) {
		playlist.removeIf(playlist -> playlist.getId() == id);

	}

	@Override
	public void editPlaylistName(String name, int id) {
		if (id > playlist.size())
			return;
		PlaylistDTO play = playlist.get(id);
		play.setName(name);

	}

	@Override
	public List<PlaylistDTO> getAllPlaylists() {
		return playlist;
	}

	@Override
	public PlaylistDTO getPlaylist(int id) {
		if (id > playlist.size())
			return null;
		return playlist.get(id);
	}
}
