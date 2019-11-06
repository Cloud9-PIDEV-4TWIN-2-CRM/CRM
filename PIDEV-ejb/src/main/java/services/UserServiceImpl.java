package services;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;

import Entities.Operator;
import Entities.Prospect;
import interfaces.UserServiceInt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Stateless
public class UserServiceImpl implements UserServiceInt {

	@PersistenceContext
	EntityManager em;

	@Override
	public Prospect tryLogin(String username, String password) {
		
		Prospect p = getProspectByUsername(username);
		if(p!=null) {
			if(!BCrypt.checkpw(password, p.getPassword()))
				return null;
		}
		return p;
	}


	@Override
	public String forgetPassword(String mail) {
		String result = "";
        if(mailVerification(mail))
        {
        	String subject = "CRM Application Reset Password";
    		String message = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
    				"	<head>\r\n" + 
    				"		<title>Internal_email-29</title>\r\n" + 
    				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
    				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
    				"		<style type=\"text/css\">\r\n" + 
    				"			* {\r\n" + 
    				"				-ms-text-size-adjust:100%;\r\n" + 
    				"				-webkit-text-size-adjust:none;\r\n" + 
    				"				-webkit-text-resize:100%;\r\n" + 
    				"				text-resize:100%;\r\n" + 
    				"			}\r\n" + 
    				"			a{\r\n" + 
    				"				outline:none;\r\n" + 
    				"				color:#40aceb;\r\n" + 
    				"				text-decoration:underline;\r\n" + 
    				"			}\r\n" + 
    				"			a:hover{text-decoration:none !important;}\r\n" + 
    				"			.nav a:hover{text-decoration:underline !important;}\r\n" + 
    				"			.title a:hover{text-decoration:underline !important;}\r\n" + 
    				"			.title-2 a:hover{text-decoration:underline !important;}\r\n" + 
    				"			.btn:hover{opacity:0.8;}\r\n" + 
    				"			.btn a:hover{text-decoration:none !important;}\r\n" + 
    				"			.btn{\r\n" + 
    				"				-webkit-transition:all 0.3s ease;\r\n" + 
    				"				-moz-transition:all 0.3s ease;\r\n" + 
    				"				-ms-transition:all 0.3s ease;\r\n" + 
    				"				transition:all 0.3s ease;\r\n" + 
    				"			}\r\n" + 
    				"			table td {border-collapse: collapse !important;}\r\n" + 
    				"			.ExternalClass, .ExternalClass a, .ExternalClass span, .ExternalClass b, .ExternalClass br, .ExternalClass p, .ExternalClass div{line-height:inherit;}\r\n" + 
    				"			@media only screen and (max-width:500px) {\r\n" + 
    				"				table[class=\"flexible\"]{width:100% !important;}\r\n" + 
    				"				table[class=\"center\"]{\r\n" + 
    				"					float:none !important;\r\n" + 
    				"					margin:0 auto !important;\r\n" + 
    				"				}\r\n" + 
    				"				*[class=\"hide\"]{\r\n" + 
    				"					display:none !important;\r\n" + 
    				"					width:0 !important;\r\n" + 
    				"					height:0 !important;\r\n" + 
    				"					padding:0 !important;\r\n" + 
    				"					font-size:0 !important;\r\n" + 
    				"					line-height:0 !important;\r\n" + 
    				"				}\r\n" + 
    				"				td[class=\"img-flex\"] img{\r\n" + 
    				"					width:100% !important;\r\n" + 
    				"					height:auto !important;\r\n" + 
    				"				}\r\n" + 
    				"				td[class=\"aligncenter\"]{text-align:center !important;}\r\n" + 
    				"				th[class=\"flex\"]{\r\n" + 
    				"					display:block !important;\r\n" + 
    				"					width:100% !important;\r\n" + 
    				"				}\r\n" + 
    				"				td[class=\"wrapper\"]{padding:0 !important;}\r\n" + 
    				"				td[class=\"holder\"]{padding:30px 15px 20px !important;}\r\n" + 
    				"				td[class=\"nav\"]{\r\n" + 
    				"					padding:20px 0 0 !important;\r\n" + 
    				"					text-align:center !important;\r\n" + 
    				"				}\r\n" + 
    				"				td[class=\"h-auto\"]{height:auto !important;}\r\n" + 
    				"				td[class=\"description\"]{padding:30px 20px !important;}\r\n" + 
    				"				td[class=\"i-120\"] img{\r\n" + 
    				"					width:120px !important;\r\n" + 
    				"					height:auto !important;\r\n" + 
    				"				}\r\n" + 
    				"				td[class=\"footer\"]{padding:5px 20px 20px !important;}\r\n" + 
    				"				td[class=\"footer\"] td[class=\"aligncenter\"]{\r\n" + 
    				"					line-height:25px !important;\r\n" + 
    				"					padding:20px 0 0 !important;\r\n" + 
    				"				}\r\n" + 
    				"				tr[class=\"table-holder\"]{\r\n" + 
    				"					display:table !important;\r\n" + 
    				"					width:100% !important;\r\n" + 
    				"				}\r\n" + 
    				"				th[class=\"thead\"]{display:table-header-group !important; width:100% !important;}\r\n" + 
    				"				th[class=\"tfoot\"]{display:table-footer-group !important; width:100% !important;}\r\n" + 
    				"			}\r\n" + 
    				"		</style>\r\n" + 
    				"	</head>\r\n" + 
    				"	<body style=\"margin:0; padding:0;\" bgcolor=\"#eaeced\">\r\n" + 
    				"		<table style=\"min-width:320px;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#eaeced\">\r\n" + 
    				"			<!-- fix for gmail -->\r\n" + 
    				"			<tr>\r\n" + 
    				"				<td class=\"hide\">\r\n" + 
    				"					<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px !important;\">\r\n" + 
    				"						<tr>\r\n" + 
    				"							<td style=\"min-width:600px; font-size:0; line-height:0;\">&nbsp;</td>\r\n" + 
    				"						</tr>\r\n" + 
    				"					</table>\r\n" + 
    				"				</td>\r\n" + 
    				"			</tr>\r\n" + 
    				"			<tr>\r\n" + 
    				"				<td class=\"wrapper\" style=\"padding:0 10px;\">"
    				+ "<table data-module=\"module-2\" data-thumb=\"thumbnails/02.png\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
    				"						<tr>\r\n" + 
    				"							<td data-bgcolor=\"bg-module\" bgcolor=\"#eaeced\">\r\n" + 
    				"								<table class=\"flexible\" width=\"600\" align=\"center\" style=\"margin:0 auto;\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
    				"									<tr>\r\n" + 
    				"										<td class=\"img-flex\"><img src=\"https://studentone.com/wp-content/uploads/2019/08/Mt-Coot-tha.jpg\" style=\"vertical-align:top;\" width=\"600\" height=\"306\" alt=\"\" /></td>\r\n" + 
    				"									</tr>\r\n" + 
    				"									<tr>\r\n" + 
    				"										<td data-bgcolor=\"bg-block\" class=\"holder\" style=\"padding:58px 60px 52px;\" bgcolor=\"#f9f9f9\">\r\n" + 
    				"											<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
    				"												<tr>\r\n" + 
    				"													<td data-color=\"title\" data-size=\"size title\" data-min=\"25\" data-max=\"45\" data-link-color=\"link title color\" data-link-style=\"text-decoration:none; color:#292c34;\" class=\"title\" align=\"center\" style=\"font:35px/38px Arial, Helvetica, sans-serif; color:#292c34; padding:0 0 24px;\">\r\n" + 
    				"														Welcome to our Phone Operator CRM Application \r\n" + 
    				"													</td>\r\n" + 
    				"												</tr>\r\n" + 
    				"												<tr>\r\n" + 
    				"													<td data-color=\"text\" data-size=\"size text\" data-min=\"10\" data-max=\"26\" data-link-color=\"link text color\" data-link-style=\"font-weight:bold; text-decoration:underline; color:#40aceb;\" align=\"center\" style=\"font:bold 16px/25px Arial, Helvetica, sans-serif; color:#888; padding:0 0 23px;\">\r\n" + 
    				"														To reset your password pls click on this link :  <a href='http://localhost:9080/PIDEV-web/crm/user/"+mail+"/"+ getToken(mail)+"'>http://localhost:9080/PIDEV-web/crm/user/forget/"+ getToken(mail) + "</a>\r\n" + 
    				"													</td>\r\n" + 
    				"												</tr>\r\n" + 
    				"												<tr>\r\n" + 
    				"													<td style=\"padding:0 0 20px;\">\r\n" + 
    				"														<table width=\"134\" align=\"center\" style=\"margin:0 auto;\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
    				"															<tr>\r\n" +  
    				"															</tr>\r\n" + 
    				"														</table>\r\n" + 
    				"													</td>\r\n" + 
    				"												</tr>\r\n" + 
    				"											</table>\r\n" + 
    				"										</td>\r\n" + 
    				"									</tr>\r\n" + 
    				"									<tr><td height=\"28\"></td></tr>\r\n" + 
    				"								</table>\r\n" + 
    				"							</td>\r\n" + 
    				"						</tr>\r\n" + 
    				"					</table>"
    				+ "</td>\r\n" + 
    				"			</tr>\r\n" + 
    				"			<!-- fix for gmail -->\r\n" + 
    				"			<tr>\r\n" + 
    				"				<td style=\"line-height:0;\"><div style=\"display:none; white-space:nowrap; font:15px/1px courier;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div></td>\r\n" + 
    				"			</tr>\r\n" + 
    				"		</table>\r\n" + 
    				"	</body>\r\n" + 
    				"</html>";
    		sendMail(mail, subject, message); 
    		result = "Done";
        }else {
        	result = "user not existing";
        }
		return result;
	}
	
	@Override
	public String resetPassword(String username, String psw) {
		TypedQuery<Prospect> query = em
				.createQuery("select p from Prospect p where p.email=:email", Prospect.class);
		query.setParameter("email", username);
		Prospect p = query.getSingleResult();
		p.setPassword(BCrypt.hashpw(psw, BCrypt.gensalt(12)));
		em.merge(p);
		return "done";
	}

	@Override
	public Prospect getProspectByid(int id) {
		return em.find(Prospect.class, id);

	}

	@Override
	public void sendMail(String to, String subject, String message) {
		
		final String username = "cloud9.crm.4twin@gmail.com";
        final String password = "cloud9@crm";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress("cloud9.crm.4twin@gmail.com"));
            message1.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message1.setSubject(subject);
            /*message1.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");*/
            message1.setContent(message
	,
	             "text/html");

            Transport.send(message1);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

	private String getToken(String username) {

		// Issue a token (can be a random String persisted to a database or a JWT token)
		// The issued token must be associated to a user
		// Return the issued token
		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		String jwtToken =

				Jwts.builder().setSubject(username).setIssuer(username)

						.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(120L)))
						.signWith(SignatureAlgorithm.HS512, key).compact();
		return jwtToken;
	}

	// ======================================
	// ========= Private methods ============
	// ======================================
	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	private Operator getOpById(int id) {
		return em.find(Operator.class, id);
	}
	@Override
	public boolean mailVerification(String mail) {

		TypedQuery<Prospect> query = em
				.createQuery("select p from Prospect p where p.email=:email", Prospect.class);
		query.setParameter("email", mail);
		
		try {
		Prospect p = new Prospect();
		p = query.getSingleResult();
		}
		catch(NoResultException e){
			return false;
		}
		return true;
	}

	@Override
	public String confirmRegister(String username) {
		
		TypedQuery<Prospect> query = em
				.createQuery("select p from Prospect p where p.email=:email", Prospect.class);
		query.setParameter("email", username);
		Prospect p = query.getSingleResult();
		p.setRegisterDate(new Date());
		p.setConfirmed(true);
		p.setDisabled(false);
		em.merge(p);
		return "done";
		
	}


	@Override
	public String register(String mail, String pswd, String fName, String lName, String phone, String idOp) {
		if(mailVerification(mail)==false) {
		int id = Integer.parseInt(idOp);
        int number = Integer.parseInt(phone);
		Prospect prospect = new Prospect(fName, lName, mail, BCrypt.hashpw(pswd, BCrypt.gensalt(12)), number, false, getOpById(id), false,true);
		em.persist(prospect);
		String subject = "Phone Operator CRM Application Register Confirmation";
		String message = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"	<head>\r\n" + 
				"		<title>Internal_email-29</title>\r\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
				"		<style type=\"text/css\">\r\n" + 
				"			* {\r\n" + 
				"				-ms-text-size-adjust:100%;\r\n" + 
				"				-webkit-text-size-adjust:none;\r\n" + 
				"				-webkit-text-resize:100%;\r\n" + 
				"				text-resize:100%;\r\n" + 
				"			}\r\n" + 
				"			a{\r\n" + 
				"				outline:none;\r\n" + 
				"				color:#40aceb;\r\n" + 
				"				text-decoration:underline;\r\n" + 
				"			}\r\n" + 
				"			a:hover{text-decoration:none !important;}\r\n" + 
				"			.nav a:hover{text-decoration:underline !important;}\r\n" + 
				"			.title a:hover{text-decoration:underline !important;}\r\n" + 
				"			.title-2 a:hover{text-decoration:underline !important;}\r\n" + 
				"			.btn:hover{opacity:0.8;}\r\n" + 
				"			.btn a:hover{text-decoration:none !important;}\r\n" + 
				"			.btn{\r\n" + 
				"				-webkit-transition:all 0.3s ease;\r\n" + 
				"				-moz-transition:all 0.3s ease;\r\n" + 
				"				-ms-transition:all 0.3s ease;\r\n" + 
				"				transition:all 0.3s ease;\r\n" + 
				"			}\r\n" + 
				"			table td {border-collapse: collapse !important;}\r\n" + 
				"			.ExternalClass, .ExternalClass a, .ExternalClass span, .ExternalClass b, .ExternalClass br, .ExternalClass p, .ExternalClass div{line-height:inherit;}\r\n" + 
				"			@media only screen and (max-width:500px) {\r\n" + 
				"				table[class=\"flexible\"]{width:100% !important;}\r\n" + 
				"				table[class=\"center\"]{\r\n" + 
				"					float:none !important;\r\n" + 
				"					margin:0 auto !important;\r\n" + 
				"				}\r\n" + 
				"				*[class=\"hide\"]{\r\n" + 
				"					display:none !important;\r\n" + 
				"					width:0 !important;\r\n" + 
				"					height:0 !important;\r\n" + 
				"					padding:0 !important;\r\n" + 
				"					font-size:0 !important;\r\n" + 
				"					line-height:0 !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"img-flex\"] img{\r\n" + 
				"					width:100% !important;\r\n" + 
				"					height:auto !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"aligncenter\"]{text-align:center !important;}\r\n" + 
				"				th[class=\"flex\"]{\r\n" + 
				"					display:block !important;\r\n" + 
				"					width:100% !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"wrapper\"]{padding:0 !important;}\r\n" + 
				"				td[class=\"holder\"]{padding:30px 15px 20px !important;}\r\n" + 
				"				td[class=\"nav\"]{\r\n" + 
				"					padding:20px 0 0 !important;\r\n" + 
				"					text-align:center !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"h-auto\"]{height:auto !important;}\r\n" + 
				"				td[class=\"description\"]{padding:30px 20px !important;}\r\n" + 
				"				td[class=\"i-120\"] img{\r\n" + 
				"					width:120px !important;\r\n" + 
				"					height:auto !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"footer\"]{padding:5px 20px 20px !important;}\r\n" + 
				"				td[class=\"footer\"] td[class=\"aligncenter\"]{\r\n" + 
				"					line-height:25px !important;\r\n" + 
				"					padding:20px 0 0 !important;\r\n" + 
				"				}\r\n" + 
				"				tr[class=\"table-holder\"]{\r\n" + 
				"					display:table !important;\r\n" + 
				"					width:100% !important;\r\n" + 
				"				}\r\n" + 
				"				th[class=\"thead\"]{display:table-header-group !important; width:100% !important;}\r\n" + 
				"				th[class=\"tfoot\"]{display:table-footer-group !important; width:100% !important;}\r\n" + 
				"			}\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"	<body style=\"margin:0; padding:0;\" bgcolor=\"#eaeced\">\r\n" + 
				"		<table style=\"min-width:320px;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#eaeced\">\r\n" + 
				"			<!-- fix for gmail -->\r\n" + 
				"			<tr>\r\n" + 
				"				<td class=\"hide\">\r\n" + 
				"					<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px !important;\">\r\n" + 
				"						<tr>\r\n" + 
				"							<td style=\"min-width:600px; font-size:0; line-height:0;\">&nbsp;</td>\r\n" + 
				"						</tr>\r\n" + 
				"					</table>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<td class=\"wrapper\" style=\"padding:0 10px;\">"
				+ "<table data-module=\"module-2\" data-thumb=\"thumbnails/02.png\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
				"						<tr>\r\n" + 
				"							<td data-bgcolor=\"bg-module\" bgcolor=\"#eaeced\">\r\n" + 
				"								<table class=\"flexible\" width=\"600\" align=\"center\" style=\"margin:0 auto;\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
				"									<tr>\r\n" + 
				"										<td class=\"img-flex\"><img src=\"https://studentone.com/wp-content/uploads/2019/08/Mt-Coot-tha.jpg\" style=\"vertical-align:top;\" width=\"600\" height=\"306\" alt=\"\" /></td>\r\n" + 
				"									</tr>\r\n" + 
				"									<tr>\r\n" + 
				"										<td data-bgcolor=\"bg-block\" class=\"holder\" style=\"padding:58px 60px 52px;\" bgcolor=\"#f9f9f9\">\r\n" + 
				"											<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
				"												<tr>\r\n" + 
				"													<td data-color=\"title\" data-size=\"size title\" data-min=\"25\" data-max=\"45\" data-link-color=\"link title color\" data-link-style=\"text-decoration:none; color:#292c34;\" class=\"title\" align=\"center\" style=\"font:35px/38px Arial, Helvetica, sans-serif; color:#292c34; padding:0 0 24px;\">\r\n" + 
				"														Welcome to our Phone Operator CRM Application \r\n" + 
				"													</td>\r\n" + 
				"												</tr>\r\n" + 
				"												<tr>\r\n" + 
				"													<td data-color=\"text\" data-size=\"size text\" data-min=\"10\" data-max=\"26\" data-link-color=\"link text color\" data-link-style=\"font-weight:bold; text-decoration:underline; color:#40aceb;\" align=\"center\" style=\"font:bold 16px/25px Arial, Helvetica, sans-serif; color:#888; padding:0 0 23px;\">\r\n" + 
				"														The last step to be one of us is by clicking on this link to confirm your account :  <a href='http://localhost:9080/PIDEV-web/crm/user/"+mail+"/"+ getToken(mail)+"'>http://localhost:9080/PIDEV-web/crm/user/"+ getToken(mail) + "</a>\r\n" + 
				"													</td>\r\n" + 
				"												</tr>\r\n" + 
				"												<tr>\r\n" + 
				"													<td style=\"padding:0 0 20px;\">\r\n" + 
				"														<table width=\"134\" align=\"center\" style=\"margin:0 auto;\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
				"															<tr>\r\n" +  
				"															</tr>\r\n" + 
				"														</table>\r\n" + 
				"													</td>\r\n" + 
				"												</tr>\r\n" + 
				"											</table>\r\n" + 
				"										</td>\r\n" + 
				"									</tr>\r\n" + 
				"									<tr><td height=\"28\"></td></tr>\r\n" + 
				"								</table>\r\n" + 
				"							</td>\r\n" + 
				"						</tr>\r\n" + 
				"					</table>"
				+ "</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<!-- fix for gmail -->\r\n" + 
				"			<tr>\r\n" + 
				"				<td style=\"line-height:0;\"><div style=\"display:none; white-space:nowrap; font:15px/1px courier;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div></td>\r\n" + 
				"			</tr>\r\n" + 
				"		</table>\r\n" + 
				"	</body>\r\n" + 
				"</html>";
		sendMail(prospect.getEmail(), subject, message);
		return "sucsessfully added";
		}else {
			return "username already exixt";
		}
		
	}


	
	@Override
	public List<Prospect> search(String like) {
		TypedQuery<Prospect> query = em
				.createQuery("select p from Prospect p where p.email like concat('%',:email,'%')", Prospect.class);
		query.setParameter("email", like);
	List<Prospect> prospects = new ArrayList<Prospect>();
		try {
			prospects = query.getResultList();
			
		} catch (Exception e) {
			return null;
		}
		return prospects;
	}


	
	@Override
	public String resendRegisterToken(String username) {
		String subject = "Phone Operator CRM Application Register Confirmation";
		String message = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"	<head>\r\n" + 
				"		<title>Internal_email-29</title>\r\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
				"		<style type=\"text/css\">\r\n" + 
				"			* {\r\n" + 
				"				-ms-text-size-adjust:100%;\r\n" + 
				"				-webkit-text-size-adjust:none;\r\n" + 
				"				-webkit-text-resize:100%;\r\n" + 
				"				text-resize:100%;\r\n" + 
				"			}\r\n" + 
				"			a{\r\n" + 
				"				outline:none;\r\n" + 
				"				color:#40aceb;\r\n" + 
				"				text-decoration:underline;\r\n" + 
				"			}\r\n" + 
				"			a:hover{text-decoration:none !important;}\r\n" + 
				"			.nav a:hover{text-decoration:underline !important;}\r\n" + 
				"			.title a:hover{text-decoration:underline !important;}\r\n" + 
				"			.title-2 a:hover{text-decoration:underline !important;}\r\n" + 
				"			.btn:hover{opacity:0.8;}\r\n" + 
				"			.btn a:hover{text-decoration:none !important;}\r\n" + 
				"			.btn{\r\n" + 
				"				-webkit-transition:all 0.3s ease;\r\n" + 
				"				-moz-transition:all 0.3s ease;\r\n" + 
				"				-ms-transition:all 0.3s ease;\r\n" + 
				"				transition:all 0.3s ease;\r\n" + 
				"			}\r\n" + 
				"			table td {border-collapse: collapse !important;}\r\n" + 
				"			.ExternalClass, .ExternalClass a, .ExternalClass span, .ExternalClass b, .ExternalClass br, .ExternalClass p, .ExternalClass div{line-height:inherit;}\r\n" + 
				"			@media only screen and (max-width:500px) {\r\n" + 
				"				table[class=\"flexible\"]{width:100% !important;}\r\n" + 
				"				table[class=\"center\"]{\r\n" + 
				"					float:none !important;\r\n" + 
				"					margin:0 auto !important;\r\n" + 
				"				}\r\n" + 
				"				*[class=\"hide\"]{\r\n" + 
				"					display:none !important;\r\n" + 
				"					width:0 !important;\r\n" + 
				"					height:0 !important;\r\n" + 
				"					padding:0 !important;\r\n" + 
				"					font-size:0 !important;\r\n" + 
				"					line-height:0 !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"img-flex\"] img{\r\n" + 
				"					width:100% !important;\r\n" + 
				"					height:auto !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"aligncenter\"]{text-align:center !important;}\r\n" + 
				"				th[class=\"flex\"]{\r\n" + 
				"					display:block !important;\r\n" + 
				"					width:100% !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"wrapper\"]{padding:0 !important;}\r\n" + 
				"				td[class=\"holder\"]{padding:30px 15px 20px !important;}\r\n" + 
				"				td[class=\"nav\"]{\r\n" + 
				"					padding:20px 0 0 !important;\r\n" + 
				"					text-align:center !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"h-auto\"]{height:auto !important;}\r\n" + 
				"				td[class=\"description\"]{padding:30px 20px !important;}\r\n" + 
				"				td[class=\"i-120\"] img{\r\n" + 
				"					width:120px !important;\r\n" + 
				"					height:auto !important;\r\n" + 
				"				}\r\n" + 
				"				td[class=\"footer\"]{padding:5px 20px 20px !important;}\r\n" + 
				"				td[class=\"footer\"] td[class=\"aligncenter\"]{\r\n" + 
				"					line-height:25px !important;\r\n" + 
				"					padding:20px 0 0 !important;\r\n" + 
				"				}\r\n" + 
				"				tr[class=\"table-holder\"]{\r\n" + 
				"					display:table !important;\r\n" + 
				"					width:100% !important;\r\n" + 
				"				}\r\n" + 
				"				th[class=\"thead\"]{display:table-header-group !important; width:100% !important;}\r\n" + 
				"				th[class=\"tfoot\"]{display:table-footer-group !important; width:100% !important;}\r\n" + 
				"			}\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"	<body style=\"margin:0; padding:0;\" bgcolor=\"#eaeced\">\r\n" + 
				"		<table style=\"min-width:320px;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#eaeced\">\r\n" + 
				"			<!-- fix for gmail -->\r\n" + 
				"			<tr>\r\n" + 
				"				<td class=\"hide\">\r\n" + 
				"					<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px !important;\">\r\n" + 
				"						<tr>\r\n" + 
				"							<td style=\"min-width:600px; font-size:0; line-height:0;\">&nbsp;</td>\r\n" + 
				"						</tr>\r\n" + 
				"					</table>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<td class=\"wrapper\" style=\"padding:0 10px;\">"
				+ "<table data-module=\"module-2\" data-thumb=\"thumbnails/02.png\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
				"						<tr>\r\n" + 
				"							<td data-bgcolor=\"bg-module\" bgcolor=\"#eaeced\">\r\n" + 
				"								<table class=\"flexible\" width=\"600\" align=\"center\" style=\"margin:0 auto;\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
				"									<tr>\r\n" + 
				"										<td class=\"img-flex\"><img src=\"https://studentone.com/wp-content/uploads/2019/08/Mt-Coot-tha.jpg\" style=\"vertical-align:top;\" width=\"600\" height=\"306\" alt=\"\" /></td>\r\n" + 
				"									</tr>\r\n" + 
				"									<tr>\r\n" + 
				"										<td data-bgcolor=\"bg-block\" class=\"holder\" style=\"padding:58px 60px 52px;\" bgcolor=\"#f9f9f9\">\r\n" + 
				"											<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
				"												<tr>\r\n" + 
				"													<td data-color=\"title\" data-size=\"size title\" data-min=\"25\" data-max=\"45\" data-link-color=\"link title color\" data-link-style=\"text-decoration:none; color:#292c34;\" class=\"title\" align=\"center\" style=\"font:35px/38px Arial, Helvetica, sans-serif; color:#292c34; padding:0 0 24px;\">\r\n" + 
				"														Welcome to our Phone Operator CRM Application \r\n" + 
				"													</td>\r\n" + 
				"												</tr>\r\n" + 
				"												<tr>\r\n" + 
				"													<td data-color=\"text\" data-size=\"size text\" data-min=\"10\" data-max=\"26\" data-link-color=\"link text color\" data-link-style=\"font-weight:bold; text-decoration:underline; color:#40aceb;\" align=\"center\" style=\"font:bold 16px/25px Arial, Helvetica, sans-serif; color:#888; padding:0 0 23px;\">\r\n" + 
				"														The last step to be one of us is by clicking on this link to confirm your account :  <a href='http://localhost:9080/PIDEV-web/crm/user/"+ getToken(username)+"'>http://localhost:9080/PIDEV-web/crm/user/"+ getToken(username) + "</a>\r\n" + 
				"													</td>\r\n" + 
				"												</tr>\r\n" + 
				"												<tr>\r\n" + 
				"													<td style=\"padding:0 0 20px;\">\r\n" + 
				"														<table width=\"134\" align=\"center\" style=\"margin:0 auto;\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
				"															<tr>\r\n" +  
				"															</tr>\r\n" + 
				"														</table>\r\n" + 
				"													</td>\r\n" + 
				"												</tr>\r\n" + 
				"											</table>\r\n" + 
				"										</td>\r\n" + 
				"									</tr>\r\n" + 
				"									<tr><td height=\"28\"></td></tr>\r\n" + 
				"								</table>\r\n" + 
				"							</td>\r\n" + 
				"						</tr>\r\n" + 
				"					</table>"
				+ "</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<!-- fix for gmail -->\r\n" + 
				"			<tr>\r\n" + 
				"				<td style=\"line-height:0;\"><div style=\"display:none; white-space:nowrap; font:15px/1px courier;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div></td>\r\n" + 
				"			</tr>\r\n" + 
				"		</table>\r\n" + 
				"	</body>\r\n" + 
				"</html>";
		sendMail(username, subject, message);
		return "Done";
	}


	@Override
	public String resendResetToken(String username) {
		
		return forgetPassword(username);
	}
    
	private Prospect getProspectByUsername(String username) {
		
		TypedQuery<Prospect> query = em
				.createQuery("select p from Prospect p where p.email=:email", Prospect.class);
		query.setParameter("email", username);
		Prospect p = null;
		try {
			p = query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return p;
	}


	@Override
	public String checkToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
	       .setSigningKey(new SecretKeySpec("simplekey".getBytes(), 0, "simplekey".getBytes().length, "DES"))
	       .parseClaimsJws(token).getBody();
			}catch(Exception e) {
				return "invalid token";
			}
		return claims.getSubject();
	}


	
	@Override
	public String disableAccount(int id) {
		Prospect p = getProspectByid(id);
		p.setDisabled(true);
		em.merge(p);
		return "Done";
	}



    
}
