package rest.publicity;


import java.awt.PageAttributes.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import Entities.Product;
import Entities.Publicity;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;



@Path("Publicity")

public class PublicityService   {
	
	@EJB
	achraf.service.publicity.PublicityService publicityservice;
	
	public PublicityService() {

	
		
	}



 @POST
 @Consumes
 public Response  addPublicity(@QueryParam("startdate") String startdate,@QueryParam("finishdate") String finishDate,@QueryParam("image") String image,@QueryParam("idprod") int idprod) {
		
	 
	 Publicity e = new Publicity();
	 e.setImage(image);
	e.setStartDate(startdate);
	e.setFinishDate(finishDate);
	e.setImage(image);
	 publicityservice.addPublicity(e);
	
	 
	
	 
	/////////////
	 
	Product p = publicityservice.findProduitByid(idprod);
	System.out.println(idprod);

	System.out.println(p);
	/*
	Set<Product> s = new HashSet<Product>();
	s.add(p);
	 e.setProduct(s);
	 */
	p.setPublicity(e);
	
	 publicityservice.updateProduct(p);
	return Response.ok("added Successfully", javax.ws.rs.core.MediaType.APPLICATION_JSON)
		.header("Access-Control-Allow-Origin", "*").build();

	
	
		
	}
 @GET
 @Path("PubActive")
 @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
 public Response getPublicityActive(){
	 
	 return Response.ok(publicityservice.currentPublicities(), javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();
	 
	 
 }
 @GET
 @Path("PubInactive")
 @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
 public Response getPublicityInactive(){
	 
	 return Response.ok(publicityservice.donePublicities(), javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();
	 
	 
 }
 
 
 
 
 @GET
 @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
 public Response getAllPublicity(){
	 
	;
	 return Response.ok(publicityservice.listPub(), javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();
	 
	 
 }
 

 
/*
 @PUT
 @Consumes(javax.ws.rs.core.MediaType.APPLICATION_XML)
 @Produces
 
 public String updatePublicity(Publicity e){
	 publicityservice.updatePublicity(e);
	 return "ok";
	
}
*/
 @PUT
 @Consumes
 @Produces
 
 public Response updatePublicity(@QueryParam("startdate") String startdate,@QueryParam("finishdate") String finishDate,@QueryParam("image") String image,@QueryParam("id") int id,@QueryParam("idprod") int idprod){
	 
	Publicity p = publicityservice.findPublicityById(id);
	p.setImage(image);
	p.setStartDate(startdate);
	p.setFinishDate(finishDate);
	p.setImage(image);
	 publicityservice.updatePublicity(p);
	 
	 Product o = publicityservice.findProduitByid(idprod);

		System.out.println(p);
		/*
		Set<Product> s = new HashSet<Product>();
		s.add(p);
		 e.setProduct(s);
		 */
		o.setPublicity(p);
		

	 
	 
	 
	 
	 return Response.ok("Updated Successfully ", javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();
	 
	
}

 
 @DELETE
 @Produces
 
 public Response deletePublicity(@QueryParam("id") int id){
	 
	 publicityservice.deletePublicity(id);
	
	 return Response.ok("Deleted Succssfully", javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();
	 
	
}
	
 
	

}
 
 
 

 
