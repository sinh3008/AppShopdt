/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import dao.UserDAO;
import entity.user;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Acer
 */
@Stateless
@Path("/user")
public class UserAPI {
    UserDAO dao = new UserDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<user> getAll() {
        return dao.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{username}")
    public user getById(@PathParam("username")String username) {
        return dao.getById(username);
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String delete(@PathParam("username") String username) {
        return dao.delete(username) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json;charset=utf-8")
    public String add(user c) {
        return dao.add(c) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json;charset=utf-8")
    public String update(user c) {
        return dao.update(c) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }

}
