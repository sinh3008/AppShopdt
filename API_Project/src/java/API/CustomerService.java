/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import dao.CustomerDAO;
import entity.Customer;
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
@Path("/customer")
public class CustomerService {

    CustomerDAO dao = new CustomerDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAll() {
        return dao.getAll();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login/{username}/{password}")
    public String loginn(@PathParam("username") String username , @PathParam("password")String password) {
        return dao.loginn(username, password);
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login/{username}")
    public String getByName(@PathParam("username") String username) {
        return dao.getByName(username);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getbyname/{username}")
    public List<Customer> getByNameTT(@PathParam("username") String username) {
        return dao.getByNameTT(username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Customer getById(@PathParam("id") int id) {
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
    public String add(Customer c) {
        return dao.add(c) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json;charset=utf-8")
    public String update(Customer c) {
        return dao.update(c) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }

}
