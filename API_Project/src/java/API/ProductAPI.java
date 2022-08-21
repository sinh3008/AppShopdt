/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import dao.ProductDAO;
import entity.Product;
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
@Path("/product")
public class ProductAPI {

    ProductDAO dao = new ProductDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll() {
        return dao.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getid/{idp}")
    public List<Product> getById(@PathParam("idp") int id) {
        return dao.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getname/{nameproduct}")
    public List<Product> search(@PathParam("nameproduct") String nameproduct) {
        return dao.search(nameproduct);
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String delete(@PathParam("id") int id) {
        return dao.delete(id) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(Product p) {
        return dao.add(p) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Product p) {
        return dao.update(p) > 0 ? "{msg : \"Error\"}" : "{msg : \"Success\"}";
    }
}
