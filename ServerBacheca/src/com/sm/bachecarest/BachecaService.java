
package com.sm.bachecarest;

import com.sm.bachecarest.model.DatabaseManager;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class BachecaService {
    
    @GET @Path("/message")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages(){
        
        Gson gson = new Gson();
        String ret = gson.toJson(DatabaseManager.getMessages());
        return Response.ok(ret).build();
    }
    
    @GET @Path("/messages/{when}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages(
        @PathParam("when") int when){
        
        Gson gson = new Gson();
        String ret = gson.toJson(DatabaseManager.getMessages(when));
        return Response.ok(ret).build();
    }
    
    @GET @Path("/messages/{id}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageComments(
        @PathParam("id") int id){
        
        Gson gson = new Gson();
        String ret = gson.toJson(DatabaseManager.getMessageComments(id));
        return Response.ok(ret).build();
    }
    
    @GET @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){
        
        Gson gson = new Gson();
        String ret = gson.toJson(DatabaseManager.getUsers());
        return Response.ok(ret).build();
    }
    
    @GET @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(
        @PathParam("id") String id){
        
        Gson gson = new Gson();
        String ret = gson.toJson(DatabaseManager.getUser(id));
        return Response.ok(ret).build();
    }
    
    
}

