package rest.pack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import Entities.Offer;
import Entities.Pack;
import Entities.Product;
import Entities.Publicity;
@Path("Pack")

public class PackService {
	@EJB
	achraf.service.pack.PackService PackService;
	
	
	
	
	
	@Path("addProductToPack")
	@POST
	 @Consumes
	 public Response  addProductToPack(@QueryParam("idPack") int idPack,@QueryParam("idprod") int idprod,@QueryParam("quantite") int quantite) {
		
		PackService.addProductToPack(idprod, idPack,quantite);
		
		
		return Response.ok("Proudct Added ToPack ", javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();


	}
	
	@Path("addPack")
	@POST
	 @Consumes
	 public Response addPack(@QueryParam("price") float price,@QueryParam("type") String type) {
		
		
		Pack p = new Pack();
		
		p.setPrice(price);
		p.setType(type);
		PackService.addPack(p);
		
		 return Response.ok("Pack Added Successfully", javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build(); 



	}
	
	@DELETE
	 @Consumes
	 public Response addPack(@QueryParam("idpack") int idpack) {
		
		PackService.deletePack(idpack);
		
		
		 return Response.ok("Pack Deleted Successfully", javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build(); 



	}
	
	@PUT
	 @Consumes
	 public Response updatePack(@QueryParam("idpack") int idpack,@QueryParam("price") float price) {
		
		PackService.updatePrix(idpack, price);
		
		
		
		 return Response.ok("Pack updated Succesfully", javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build(); 



	}
	@Path("listPack")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	 public Response listPacket() {
		
		
		
		
		
		 return Response.ok(PackService.listPack(), javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build(); 

	}
	
	@GET
	@Consumes
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	
	 public Response findPack(@QueryParam("id") int id) {
		
		
		
		
		
		 return Response.ok(PackService.findPackById(id), javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build(); 



	}
	@Path("SuggestOfferOpertator")
	@GET
	@Consumes
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	
	 public Response SuggestOfferOpertator(@QueryParam("id") int id) {
		
		
		 return Response.ok(PackService.PackSuggestOperateur(), javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build(); 



	}
	@Path("SuggestOfferClient")
	@GET
	@Consumes
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	
	 public Response SuggestOfferClient(@QueryParam("id") int id) {
		
		
		 return Response.ok(PackService.PackSuggestClientCategorie(id), javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build(); 



	}
	
	

	
	
	

	
	
	
	 
	 
	 
	 
	 
	
	
	
	 
	 
	 
	 
	 
	
}
