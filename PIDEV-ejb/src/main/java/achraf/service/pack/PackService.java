package achraf.service.pack;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class PackService {

	@PersistenceContext(unitName="crm")
	EntityManager em;
	
	
	
}
