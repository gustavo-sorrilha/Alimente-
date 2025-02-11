package controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class Index {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Alimente+ Alimentando vidas, combatendo a fome!";
    }
}
