package dea.oose.spotitube.controllers;


import dea.oose.spotitube.dtos.TracksDTO;
import dea.oose.spotitube.services.LoginService;
import dea.oose.spotitube.services.TracksService;
import org.json.simple.JSONObject;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Singleton
@Path("/")
public class TracksController {


    JSONObject jsonTracks = new JSONObject();


    LoginService loginService;

    TracksService tracksService;


    @GET
    @Path("/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlltracks(@QueryParam("token") String token) {

        if (loginService.isTokenCorrect(token)) {

                return tracksService.getAllTracksJson(jsonTracks, 200);

        }
     return Response.status(403).build();


    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("playlists/{playlistId}/tracks")
    public Response getTracksFromPlaylist(@PathParam("playlistId") int playlistId, @QueryParam("token") String token) {
        if (loginService.isTokenCorrect(token)) {
            return tracksService.getTrackOfPlaylist(jsonTracks, 200, playlistId);
        }
        return Response.status(401).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("playlists/{id}/tracks")
    public Response addTrackToPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token, TracksDTO trackDTO) {


        if (loginService.isTokenCorrect(token) && tracksService.isTrackExistsInServer(trackDTO.getId())) {


            if (!tracksService.offlineAvailableIsTheSame(trackDTO.getId(), trackDTO.isOfflineAvailable())) {
                tracksService.updateOfflineAvailable(trackDTO.getId(), trackDTO.isOfflineAvailable());
            }


            //   check if track doesn't already exists inside the playlist
            if (!tracksService.trackExistsInPlaylist(playlistId, trackDTO.getId())) {
                tracksService.addTrackToPlaylist(playlistId, trackDTO.getId());
            }

            return tracksService.getTrackOfPlaylist(jsonTracks, 200, playlistId);
        } else {
            return Response.status(401).build();
        }
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("playlists/{playlistId}/tracks/{trackId}")
    public Response deleteTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {


        if (loginService.isTokenCorrect(token)) {      //tested :)


            if (tracksService.trackExistsInPlaylist(playlistId, trackId)) {
                tracksService.deleteTrackFromPlaylist(playlistId, trackId);
            }

            return tracksService.getTrackOfPlaylist(jsonTracks, 200, playlistId);
        } else {
            return Response.status(401).build();
        }
    }


    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    @Inject
    public void setTracksService(TracksService tracksService) {
        this.tracksService = tracksService;
    }
}