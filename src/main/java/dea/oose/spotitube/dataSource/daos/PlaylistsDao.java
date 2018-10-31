package dea.oose.spotitube.dataSource.daos;


import dea.oose.spotitube.dataSource.DatabaseConnection;
import dea.oose.spotitube.dtos.PlaylistDTO;
import dea.oose.spotitube.dtos.TracksDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsDao extends DatabaseConnection implements IPlaylistDao {


    private Connection connection = getConnection();
    private PreparedStatement getPlaylists;
    private PreparedStatement addPlaylist;
    private PreparedStatement deletePlaylist;
    private PreparedStatement editPlaylist;

    private TracksDao tracksDao = new TracksDao();


    public int getId(int id) {

        try {
            return getResultsPlaylists(id).getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getName(int id) {
        try {
            return getResultsPlaylists(id).getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean getIfOwner(int id) throws SQLException {


        return getResultsPlaylists(id).getBoolean("owner");

    }


    private ResultSet getResultsPlaylists(int id) {
        ResultSet rs = null;
        try {
            getPlaylists = connection.prepareStatement("select * from playlists where id =?");

            getPlaylists.setInt(1, id);
            rs = getPlaylists.executeQuery();
            rs.first();

        } catch (SQLException e) {
            System.out.println("Fout hierzo");
            e.printStackTrace();

        }
        return rs;
    }


    @Override
    public void addPlaylist(String namePlaylist, boolean isOwner) {

        try {
            addPlaylist = connection.prepareStatement("INSERT INTO playlists(name,owner) VALUES(?,?)");

            addPlaylist.setString(1, namePlaylist);
            addPlaylist.setBoolean(2, isOwner);
            addPlaylist.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void deletePlaylist(int id) {

        try {
            deletePlaylist = connection.prepareStatement("DELETE FROM playlists WHERE id = ?");

            deletePlaylist.setInt(1, id);

            deletePlaylist.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void editPlaylistName(String name, int id) {

        try {
            editPlaylist = connection.prepareStatement("UPDATE playlists SET name = ? WHERE id=?");

            editPlaylist.setString(1, name);
            editPlaylist.setInt(2, id);

            editPlaylist.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<PlaylistDTO> getAllPlaylists() {

        // Get connection to database.

        PreparedStatement ps = null;
        ResultSet rs = null;

        // Build SQL.
        String query = ("SELECT * FROM playlists");


        try {


            // Execute SQL.
            ps = connection.prepareStatement(query);

            // Populate ResultSet with results from the database.
            rs = ps.executeQuery();

            // Declare ArrayList of comments.
            List<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();

            // Process results from database.
            while (rs.next()) {

                // Map ResultSet to newly created comments.
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean isOwner = rs.getBoolean("owner");


                PlaylistDTO p = new PlaylistDTO(id, name, isOwner, null);

                // Add playlist to ArrayList.
                playlists.add(p);
            }

            // Return ArrayList of playlists.
            return playlists;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return null;
        }
    }


    @Override
    public PlaylistDTO getPlaylist(int idPlaylist) {


        try {

            int id = getResultsPlaylists(idPlaylist).getInt("id");
            String name = getResultsPlaylists(idPlaylist).getString("name");
            boolean isOwner = getResultsPlaylists(idPlaylist).getBoolean("owner");

            List<TracksDTO> allTracks = tracksDao.getTracksOfPlaylist(idPlaylist);

            PlaylistDTO p = new PlaylistDTO(id, name, isOwner, allTracks);


            return p;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return null;
        }
    }

}
