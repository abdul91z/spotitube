package dea.oose.spotitube.dtos;

import java.util.List;

public class PlaylistDTO {


    private int id ;
    private String name;
    private boolean owner;
    private    List<TracksDTO> tracks ;


    public PlaylistDTO(int id, String name, boolean owner, List<TracksDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public PlaylistDTO() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOwner() {
        return owner;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TracksDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TracksDTO> tracks) {
        this.tracks = tracks;
    }
}
