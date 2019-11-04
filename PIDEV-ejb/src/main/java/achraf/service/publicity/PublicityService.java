package achraf.service.publicity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Product;
import Entities.Publicity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class PublicityService implements PublicityLocal,PublicityRemote {

	@PersistenceContext(unitName="crm")
	EntityManager em;

	@Override
	public boolean addPublicity(Publicity e) {
		// TODO Auto-generated method stub
		em.persist(e);
		return true;

	}

	@Override
	public void updatePublicity(Publicity e) {
		// TODO Auto-generated method stub
		em.merge(e);

	}

	@Override
	public void deletePublicity(int e) {
		// TODO Auto-generated method stub
		em.remove(this.findPublicityById(e));
	}

	@Override
	public List<Publicity> listPub() {
		// TODO Auto-generated method stub
		return em.createQuery("select U from Publicity U", Publicity.class).getResultList();	}

	@Override
	public Publicity findPublicityById(int e) {
		// TODO Auto-generated method stub
		return em.find(Publicity.class, e);
	}

	@Override
	public String idProduit(int id) {
		// TODO Auto-generated method stub
		return "hi";
	}

	@Override
	public String nameProduit(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Product findProduitByid(int idprod) {
		// TODO Auto-generated method stub
		
		return em.find(Product.class, idprod);
		
	}

	@Override
	public void updateProduct(Product e) {
		// TODO Auto-generated method stub
		em.merge(e);

	}

	@Override
	public List<Publicity> currentPublicities() {
		List<Publicity>p = listPub();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    Date dataFinish = new Date();
	    Date dateJour = new Date();
	    Date DateStart = new Date();
	    try {
	    	dateJour = df.parse(dateJour.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Publicity>donePublicities = new ArrayList<Publicity>();
		for (Publicity U : listPub() )
		{
			
			
			
						try {
				dataFinish = df.parse(U.getFinishDate());
							} catch (ParseException e)
						{
								e.printStackTrace();
						}
						try {
							DateStart = df.parse(U.getStartDate());
										} catch (ParseException e)
									{
											e.printStackTrace();
									}
						
						
						if (((dateJour.compareTo(dataFinish))<= 0)&&((dateJour.compareTo(DateStart)>=0) )){
				
							donePublicities.add(U);
							}
			
			
			
			
		};
		return donePublicities;
	}

	@Override
	public List<Publicity> donePublicities() {
		// TODO Auto-generated method stub
		List<Publicity>p = listPub();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    Date dataFinish = new Date();
	    Date dateJour = new Date();
	    try {
	    	dateJour = df.parse(dateJour.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Publicity>donePublicities = new ArrayList<Publicity>();
		for (Publicity U : listPub() )
		{
			
			
			
						try {
				dataFinish = df.parse(U.getFinishDate());
							} catch (ParseException e)
						{
								e.printStackTrace();
						}
						if ((dateJour.compareTo(dataFinish))> 0) {
				
							donePublicities.add(U);
							}
			
			
			
			
		};
		return donePublicities;
		
		
		
		
	}
	
	
	
	
}
