/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import dao.AttributeDAO;
import entity.Attribute;
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
 * @author This PC
 */
@Stateless
@Path("/attribute")
public class AttributeAPI {

    AttributeDAO dao = new AttributeDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Attribute> getAll() {
        return dao.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Attribute getById(@PathParam("id") int id) {
        return dao.getById(id);
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String delete(@PathParam("id") int id) {
        return dao.delete(id) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json;charset=utf-8")
    public String add(Attribute ab) {
        return dao.add(ab) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json;charset=utf-8")
    public String update(Attribute ab) {
        return dao.update(ab) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }
}
