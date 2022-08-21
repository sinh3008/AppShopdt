/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import dao.BillsDAO;
import entity.Bills;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Acer
 */
@Stateless
@Path("/bills")
public class BillsService {
    BillsDAO dao = new BillsDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bills> getAll() {
        return dao.getAll();
    }
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getid/{idcustomer}")
    public List<Bills> getById(@PathParam("idcustomer") int idcustomer) {
        return dao.getById(idcustomer);
    }
    

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(Bills p) {
        return dao.add(p) > 0 ? "{msg : \"Success\"}" : "{msg : \"Error\"}";
    }
}
