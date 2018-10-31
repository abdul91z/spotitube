package dea.oose.spotitube.dataSource.daos;

import dea.oose.spotitube.dataSource.DatabaseConnection;
import dea.oose.spotitube.dtos.TracksDTO;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class TracksDao extends DatabaseConnection implements ITracksDao {


    private Connection connection = getConnection();

    PreparedStatement preparedStatement;


    private ResultSet getResultsTracks(int id) {
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement("select * from tracks where id =?");

            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            rs.first();

        } catch (SQLException e) {
            System.out.println("Fout hierzo");
            e.printStackTrace();

        }
        return rs;
    }


    @Override
    public void deleteOneTrackFromPlaylist(int playlistId, int trackId) {
        PreparedStatement preparedStatement = null;
        String query = "Delete from linktracktoplaylist WHERE playlist=? AND track=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addTracktoPlaylist(int playlistId, int trackId) {
        PreparedStatement preparedStatement = null;


        try {

            preparedStatement = connection.prepareStatement("INSERT INTO linktracktoplaylist(playlist,track)  VALUES(?,?)");

            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    @Override
    public void updateOfflineAvailable(int trackId, Boolean offlineAvailable) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tracks SET offlineavailable=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, offlineAvailable);
            preparedStatement.setInt(2, trackId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TracksDTO getOneTrack(int idTrack) {

        try {


            int id = getResultsTracks(idTrack).getInt("id");
            String title = getResultsTracks(idTrack).getString("title");
            String album = getResultsTracks(idTrack).getString("album");
            String description = getResultsTracks(idTrack).getString("description");
            int duration = getResultsTracks(idTrack).getInt("duration");
            boolean offlineavailable = getResultsTracks(idTrack).getBoolean("offlineavailable");
            String performer = getResultsTracks(idTrack).getString("performer");
            int playcount = getResultsTracks(idTrack).getInt("playcount");
            String publicationdate = getResultsTracks(idTrack).getString("publicationdate");

            TracksDTO track = new TracksDTO(id, title, performer, duration, album, playcount, publicationdate, description, offlineavailable);


            return track;

        } catch (SQLException e) {
            e.printStackTrace();


            return null;
        }

    }

    @Override
    public List<TracksDTO> getTracksOfPlaylist(int playlistId) {

        // Get connection to database.

        PreparedStatement ps = null;
        ResultSet rs = null;

        // Build SQL.
        String query = ("SELECT * FROM linktracktoplaylist where playlist = ?");


        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, playlistId);
            rs = ps.executeQuery();


            List<TracksDTO> tracks = new ArrayList<>();


            while (rs.next()) {

                TracksDTO track = getOneTrack(rs.getInt("track"));


                tracks.add(track);
            }


            return tracks;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return null;
        }
    }

    @Override
    public List<TracksDTO> getAllTracks() {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String query = "select * from tracks";


        List<TracksDTO> tracks = new ArrayList<>();

        try {

            // Execute SQL.
            preparedStatement = connection.prepareStatement(query);

            // Populate ResultSet with results from the database.
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Map ResultSet to newly created comments.
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String album = resultSet.getString("album");
                String description = resultSet.getString("description");
                int duration = resultSet.getInt("duration");
                boolean offlineavailable = resultSet.getBoolean("offlineavailable");
                String performer = resultSet.getString("performer");
                int playcount = resultSet.getInt("playcount");
                String publicationdate = resultSet.getString("publicationdate");

                TracksDTO t = new TracksDTO(id, title, performer, duration, album, playcount, publicationdate, description, offlineavailable);

                // Add track to ArrayList.
                tracks.add(t);
            }

            return tracks;

        } catch (SQLException e) {
            e.printStackTrace();


            return null;
        }


    }

    @Override
    public int getAllDuration() {
        ResultSet rs = null;

        int AllDuration = 0;
        List<Integer> durations = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("select * from tracks");
            rs = preparedStatement.executeQuery();


            while (rs.next()) {

                int duration = rs.getInt("duration");

                durations.add(duration);

            }

            for (int i = 0; i < durations.size(); i++) {

                AllDuration = AllDuration + durations.get(i);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return AllDuration;

    }

}