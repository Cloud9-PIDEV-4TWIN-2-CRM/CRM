package interfaces;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class test
 */
@Stateless

public class test implements testLocal {

    /**
     * Default constructor. 
     */
    public test() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String test() {
		
		// TODO Auto-generated method stub
		return "ok";
	}

}
