package dea.oose.spotitube.controllers;


import dea.oose.spotitube.dtos.PlaylistDTO;


import dea.oose.spotitube.services.LoginService;
import dea.oose.spotitube.services.PlaylistsService;
import org.json.simple.JSONObject;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/")
public class PlaylistsController {


    JSONObject jsonPlaylist = new JSONObject();


    LoginService loginService;

    PlaylistsService playlistsService;


    // get all playlists
    @GET
    @Path("/playlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {

        if (loginService.isTokenCorrect(token)) {

            return playlistsService.getAllPlaylistsJson(jsonPlaylist, 200);


        } else return Response.status(403).build();


    }


    //get playlist by id
    @GET
    @Path("/playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksofPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {

        if (loginService.isTokenCorrect(token)) {

            return playlistsService.getplaylist(jsonPlaylist, 200, id);


        } else return Response.status(403).build();


    }


    @DELETE
    @Path("/playlists/{id}")

    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {


        if (loginService.isTokenCorrect(token)) {

            playlistsService.deletePlaylist(id);

            return playlistsService.getAllPlaylistsJson(jsonPlaylist, 200);

        } else return Response.status(403).build();

    }

    @POST
    @Path("/playlists")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO pl) {


        if (loginService.isTokenCorrect(token)) {

            playlistsService.addPlaylist(pl.getName(), true);

            return playlistsService.getAllPlaylistsJson(jsonPlaylist, 200);

        } else return Response.status(403).build();
    }


    @PUT
    @Path("/playlists/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylistName(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO pl) {


        if (loginService.isTokenCorrect(token)) {
            playlistsService.editPlaylistName(pl.getName(), id);

            return playlistsService.getAllPlaylistsJson(jsonPlaylist, 200);

        } else return Response.status(403).build();
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Inject
    public void setPlaylistsService(PlaylistsService playlistsService) {
        this.playlistsService = playlistsService;
    }
}

