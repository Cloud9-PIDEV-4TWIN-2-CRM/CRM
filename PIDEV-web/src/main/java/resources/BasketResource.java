package resources;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Entities.Operator;
import interfaces.BasketServiceLocal;
import interfaces.BasketServiceRemote;
import interfaces.test;
import interfaces.testLocal;
import services.BasketService;


@Path("basket")
public class BasketResource {

	

	@EJB
	BasketServiceLocal basl;
	
	@POST
	@Path("{idProspect}/{idProduct}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addProductToBasket(@PathParam("idProspect")int idProspect,@PathParam("idProduct")int idProduct) {

	basl.addProductToBasket(idProspect, idProduct);
	return Response.status(Response.Status.OK).entity("added successfully").build();
		
	}
	
	@PUT
	@Path("{idProspect}/{idProduct}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeProductFromBasket(@PathParam("idProspect") int idProspect,@PathParam("idProduct") int idProduct) {
		basl.removeProductFromBasket(idProspect, idProduct);
		return Response.status(Response.Status.OK).entity("removed successfully").build();
	}
	
	@GET
	@Path("{idProspect}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBasket(@PathParam("idProspect") int idProspect) {
		//basl.getBasketUser(idProspect);
		return Response.ok(basl.getBasketUser(idProspect), MediaType.APPLICATION_JSON).build();
	}
	
}
