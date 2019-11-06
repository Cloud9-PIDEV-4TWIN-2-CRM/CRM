package resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import Entities.Prospect;
import interfaces.UserServiceInt;

@Path("user")
public class UserResources {
	
	@EJB
	UserServiceInt usi;
	
	@GET
	@Produces("application/json")
	public Prospect tryLogin(@QueryParam("login") String username,@QueryParam("password") String password) {
		return usi.tryLogin(username, password);
		
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String register(@QueryParam("login") String mail,@QueryParam("pswd") String pswd,@QueryParam("fName") String fName,@QueryParam("lName") String lName,@QueryParam("phone") String phone,@QueryParam("op") String idOp) {
		
		return usi.register(mail, pswd, fName, lName, phone, idOp);
		
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("confirm/{username}")
	public String confirm(@PathParam("username") String username) {
		return usi.confirmRegister(username);
		
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("forgetPassword")
	public String forgetPassword(@QueryParam("login") String mail) {
		
		return usi.forgetPassword(mail);
		
	}	
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("resetPassword")
	public String resetPassword(@QueryParam("username") String username,@PathParam("psw") String psw) {
		return usi.resetPassword(username, psw);
		
	}
	
	@GET
	@Produces("application/json")
	@Path("search")
	public List<Prospect> search(@QueryParam("like") String like) {
		return usi.search(like);
		
	}
	
	@GET
	@Produces("application/json")
	@Path("{token}")
	public String checkToken(@PathParam("token") String token) {
	
		return usi.checkToken(token);
		
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("resendRegister")
	public String resendRegister(@QueryParam("login") String mail) {
		
		return usi.resendRegisterToken(mail);
		
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("resendForget")
	public String resendForget(@QueryParam("login") String mail) {
		
		return usi.resendResetToken(mail);
		
	}
    
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("disable")
	public String disableAccount(@PathParam("id") String id) {
		return usi.disableAccount(Integer.parseInt(id));
		
	}

}
