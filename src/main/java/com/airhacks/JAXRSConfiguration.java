package com.airhacks;




import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@Path("/")


public class JAXRSConfiguration extends Application {

@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHelloWorld() {


        return "Hello world";
    }
}
