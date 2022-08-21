/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import dao.BilldetailsDAO;
import entity.Billdetails;
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
@Path("/billdetails")
public class BilldetailsService {

    BilldetailsDAO dao = new BilldetailsDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billdetails> getAll() {
        return dao.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(Billdetails p) {
        return dao.add(p) > 0 ? "{msg : \"Success\"}" : "{msg : \"Error\"}";
    }
    
  
}
