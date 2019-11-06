package interfaces;

import java.util.List;

import javax.ejb.Local;

import Entities.Prospect;

@Local
public interface UserServiceInt {

	public Prospect tryLogin(String username,String password);
	public String register(String mail,String pswd,String fName,String lName,String phone,String idOp);
	public String forgetPassword(String mail);
	public void sendMail(String to,String subject,String message);
	public String confirmRegister(String token);
	public Prospect getProspectByid(int  id);
	public String resetPassword(String username, String psw);
	public List<Prospect> search(String like);
	public String resendRegisterToken(String username);
	public String resendResetToken(String username);
	public String checkToken(String token);
	public boolean mailVerification(String mail);
	public String disableAccount(int id);

}
