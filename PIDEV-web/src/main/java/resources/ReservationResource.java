package resources;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import interfaces.ReservationServiceLocal;

@Path("reservation")
public class ReservationResource {

	@EJB
	ReservationServiceLocal rsl ;
	
	
	@POST
	@Path("{idProspect}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response bookingBasket(@PathParam("idProspect")int idProspect) {
		return Response.status(Response.Status.OK).entity(rsl.bookingBasket(idProspect)).build();
		}
	@PUT
	@Path("{idReservation}/{idProspect}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response canceldReservation(@PathParam("idReservation")int idReservation,@PathParam("idProspect")int idProspect) {
		rsl.cancelReservation(idReservation, idProspect);
		return Response.status(Response.Status.OK).entity("ok").build();
		}
	
	@GET
	@Path("{idProspect}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllReservation(@PathParam("idProspect")int idProspect) {
		return Response.ok(rsl.getAllReservationUser(idProspect), MediaType.APPLICATION_JSON).build();
		}
	
}
