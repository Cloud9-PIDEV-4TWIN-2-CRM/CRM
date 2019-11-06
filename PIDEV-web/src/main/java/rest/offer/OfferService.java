package rest.offer;

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
import Entities.Product;
import Entities.Publicity;
@Path("Offer")

public class OfferService {
	@EJB
	achraf.service.offer.OfferService OfferService;
	
	

	
	
	
	@Path("addOffer")
	@POST
	 @Consumes
	 public Response  addOffer(@QueryParam("startdate") String startdate,@QueryParam("finishdate") String finishdate,@QueryParam("reduction") float reduction,@QueryParam("idprod") int idprod) {
		Offer e = new Offer();
		e.setStartDate(startdate);
		 e.setFinishDate(finishdate);
		e.setReduction(reduction);
		e.setEtat(false);
		Product p = OfferService.findProduitByid(idprod);
		e.setProduct(p);	
		OfferService.updateProduct(p);
		OfferService.addOffer(e);
		return Response.ok("Offer added Successfully Without being activated", javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();

	}
	
	
	
	@Path("addAndActivateOffer")
	@POST
	 @Consumes
	 public Response  addOfferAndActivate(@QueryParam("startdate") String startdate,@QueryParam("finishdate") String finishdate,@QueryParam("reduction") float reduction, @QueryParam("idprod") int idprod) {
		Offer e = new Offer();
		e.setStartDate(startdate);
		 e.setFinishDate(finishdate);
		e.setReduction(reduction);
		e.setEtat(true);
		Product p = OfferService.findProduitByid(idprod);
		if (OfferService.getOffreProductCourante(p.getId())!=null) {
			return Response.ok("Product is Already Activated : Delete Current Offer First", javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();
		}
		
		// prod already has an offer delete it 
		
		
		p.setPrice(p.getPrice()-p.getPrice()*reduction);
		e.setProduct(p);
		OfferService.updateProduct(p);
		OfferService.addOffer(e);
		return Response.ok("Offer added and Activated Successfully", javax.ws.rs.core.MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
	
	
	@Path("activate")
	 @PUT
	 @Consumes
	 public Response  activateOffer(@QueryParam("id") int id) {
		
		Offer e= OfferService.findOfferById(id);
		if (OfferService.getOffreProductCourante(e.getProduct().getId())!=null) {
			return Response.ok("Product is Already Activated : Delete Current Offer First", javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();
		}
		
		
		
				boolean x=OfferService.activateOffer(id);
				if (x==false) {
					 return Response.ok("Offer already  Activated ", javax.ws.rs.core.MediaType.APPLICATION_JSON)
								.header("Access-Control-Allow-Origin", "*").build();
				}
				
				OfferService.activateOffer(id);
			 return Response.ok("Offere added and Activated Successfully", javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();
			
			
	}
	
	
	@Path("desactivate")
	 @PUT
	 @Consumes
	 public Response  DesactivateOffer(@QueryParam("id") int id) {
	
		Offer e= OfferService.findOfferById(id);
		if (e.isEtat()==false) {
			return Response.ok("Offer is already desactivated  ", javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();	
		}
		
		if (OfferService.getOffreProductCourante(e.getProduct().getId())==null) {
			return Response.ok("Product does not have a valid offer", javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();
		}
	
		
		OfferService.DesactivateOffer(id);

			 return Response.ok("Offer Desactivated Successfully ", javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();	
	 }
	
	
	
	 @GET
	 @Path("alloffers")
	 @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	 public Response getAllOffers(){
		 
		 return Response.ok(OfferService.listOffer(), javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();		 
	 }
	 
	 @GET
	 @Path("OffreCourante")
	 @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	 @Consumes
	 public Response getOffreProductCourante(@QueryParam("idprod") int idprod){
		
		
	return Response.ok(OfferService.getOffreProductCourante(idprod), javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();	
		 
		 
		 
	 }
	 
	 @GET
	 @Path("operateurSuggestOffer")
	 @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	 public Response getOperteurSuggestOffer(){
		 
		 
		 
		 return Response.ok(OfferService.getOperteurSuggestOffer(), javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();
		 
		 
	 }
	 
	 @GET
	 @Path("clientSuggestOffer")
	 @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	 public Response getOperteurClientOffer(@QueryParam("id") int id ){
		 
		 
		 
		 return Response.ok(OfferService.getClientSuggestOffer(id), javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();
		 
		 
	 }
	 
	 
	 @Path("updateOffer")
	 @PUT
	 @Consumes
	 public Response  updateOffer(@QueryParam ("id") int id , @QueryParam("startdate") String startdate,@QueryParam("finishdate") String finishdate,@QueryParam("reduction") float reduction) {
		

			Offer e= OfferService.findOfferById(id);
			
			
			e.setStartDate(startdate);
			 e.setFinishDate(finishdate);
			e.setReduction(reduction);
		if (e.isEtat()==true) {
			
			OfferService.DesactivateOffer(id);
			e.setEtat(false);
			OfferService.updateOffer(e);
			OfferService.activateOffer(id);
			
			
		}
					
		else {	//Product p = OfferService.findProduitByid(idprod);
			//e.setProduct(p);	
			OfferService.updateOffer(e);
		}
			
			return Response.ok("Offer added Successfully Without being activated", javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();
	 }
	 
	 
	 
	 
	 
	 
	 @DELETE
	 @Consumes
	 @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	 public Response deleteOffer(@QueryParam("id") int id ){
		 
			Offer e= OfferService.findOfferById(id);
			if (e.isEtat()==true) {
			/*
				OfferService.DesactivateOffer(id);
				Product b= OfferService.findProduitByid(e.getProduct().getId());
				Set<Offer> l= new HashSet<Offer>();
				l.remove(e);
				b.setOffers(l);
			    OfferService.updateProduct(b);
			   */
				OfferService.DesactivateOffer(id);
				e.setProduct(null);
				OfferService.updateOffer(e);
				//OfferService.deleteOffer(e.getId());
				OfferService.deleteOffer(id);

				
				
			}
			else {
				e.setProduct(null);
				OfferService.updateOffer(e);
				OfferService.deleteOffer(id);

			}
		 
		 return Response.ok("deleted Successfully", javax.ws.rs.core.MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();
		 
		 
	 }
	 
	 
	 
	 
	 
	
	
	
	 
	 
	 
	 
	 
	
}
