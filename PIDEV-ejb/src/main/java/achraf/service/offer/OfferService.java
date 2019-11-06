package achraf.service.offer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Category;
import Entities.Offer;
import Entities.Product;
import Entities.Publicity;
import Entities.Purchase;

@Stateless
@LocalBean
public class OfferService implements OfferLocal,OfferRemote{

	@PersistenceContext(unitName="crm")
	EntityManager em;

	@Override
	public boolean addOffer(Offer e) {
		// TODO Auto-generated method stub
		em.persist(e);
		return true;	
		}

	@Override
	public void updateOffer(Offer e) {
		// TODO Auto-generated method stub
		em.merge(e);
	}

	@Override
	public void deleteOffer(int e) {
		// TODO Auto-generated method stub
		em.remove(this.findOfferById(e));
	}

	@Override
	public List<Offer> listOffer() {
		// TODO Auto-generated method stub
		return em.createQuery("select U from Offer U", Offer.class).getResultList();
	}

	@Override
	public Offer findOfferById(int e) {
		// TODO Auto-generated method stub
		return em.find(Offer.class, e);
	}

	@Override
	public String idProduit(int id) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Offer> OfferProduitOperateur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> OfferProduitClientRentabilite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> OfferProduitClientCategorie() {
		// TODO Auto-generated method stub
		return null;
	}



	
	@Override
	public Offer getOffreProductCourante(int idprod) {
		// TODO Auto-generated method stub
		List<Offer>list= em.createQuery("select U from Offer U ", Offer.class).getResultList();	
	for  (Offer u: list) {
		
		if ((u.getProduct().getId()==idprod)&&(u.isEtat()==true)){
			return u;
		}
		
	}
	return null;
			
	}

	@Override
	public boolean DesactivateOffer(int idOffer) {
		// TODO Auto-generated method stub
		
		 Offer e = new Offer();
			e=this.findOfferById(idOffer);
			e.setEtat(false);
			Product p = this.findProduitByid(e.getProduct().getId());
p.setPrice(p.getPrice()/(1-e.getReduction()));
this.updateProduct(p);
this.updateOffer(e);
		return true;
	}

	@Override
	public boolean activateOffer(int idOffer) {
		// TODO Auto-generated method stub

		Offer e =this.findOfferById(idOffer);
		if (e.isEtat()==true) {
			return false;
		}
		e.setEtat(true);
		Product p = this.findProduitByid(e.getProduct().getId());
		p.setPrice(p.getPrice()-p.getPrice()*e.getReduction());
		e.setProduct(p);
		this.updateProduct(p);
		this.addOffer(e);
		
		
		
		return true;
	}

	@Override
	public List<Offer> getOperteurSuggestOffer() {
		
		
		
		
		System.out.println("hi");

		List<Purchase>listPurchase= this.getallpurchases();
		List<Product>listProduct= this.getProducts();
		Map<Product, Integer> hmap = new HashMap<>();
		// TODO Auto-generated method stub
		
		System.out.println("hi2");
		for (Product U : listProduct ) {
			hmap.put(U, 0);
			
		}

		System.out.println("hi3");
		

	for (Purchase U : listPurchase ) {
		
		hmap.merge(U.getProduct(), 1, Integer::sum);
			
			    
	};
	System.out.println("hi4");

	List<Offer>SuggestedOfferOperator= new ArrayList<Offer>();
	 for(Map.Entry<Product , Integer> entry : hmap.entrySet()){
         System.out.println("Product "+entry.getKey()+" Occurance "+entry.getValue());
         
         if (entry.getValue()<3) {
        	 Offer e= new Offer();
        	 e.setProduct(entry.getKey());
        	 e.setReduction((float)0.6);
        	
        	 Date  d = new Date();
        	 SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        	 try {
      	    	d = df.parse(d.toString());
      		} catch (ParseException b) {
      			// TODO Auto-generated catch block
      			b.printStackTrace();
      		}
        	 
        	 e.setStartDate(d.toString());
        	 
        	 
        	 d.setHours(d.getHours()+336);
        
   	 
        	 
        	 
        	 e.setFinishDate(d.toString());
        	 System.out.println(d);
        	 SuggestedOfferOperator.add(e);
        	       	 
         }
         else if (entry.getValue()<5) {
        	 Offer e= new Offer();
        	 e.setProduct(entry.getKey());
        	 e.setReduction((float)0.3);
        	
        	 Date  d = new Date();
        	 SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        	 try {
      	    	d = df.parse(d.toString());
      		} catch (ParseException b) {
      			// TODO Auto-generated catch block
      			b.printStackTrace();
      		}
        	 
        	 e.setStartDate(d.toString());
        	 d.setHours(d.getHours()+168);
        	 
        	 
        	 
        	 
        	 e.setFinishDate(d.toString());
        	 System.out.println(d);
        	 SuggestedOfferOperator.add(e);

         }
         
         else if (entry.getValue()<8){
        	 
        	 Offer e= new Offer();
        	 e.setProduct(entry.getKey());
        	 e.setReduction((float)0.2);
        	 
        	 Date  d = new Date();
        	 SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        	 try {
      	    	d = df.parse(d.toString());
      	    System.out.println(d.toString());
      		} catch (ParseException b) {
      			// TODO Auto-generated catch block
      			b.printStackTrace();
      		}
        	 
        	 e.setStartDate(d.toString());
        	 d.setHours(d.getHours()+72);
        	 e.setFinishDate(d.toString());
        	 System.out.println(d);
        	 SuggestedOfferOperator.add(e);
        	

        	 
         }
         
     }
	 
     
	 
	 
     


	return SuggestedOfferOperator;
	
	}

	@Override
	public List<Offer> getClientSuggestOffer(int id) {
		// TODO Auto-generated method stub
		Map<Category, Integer> hmap = new HashMap<>();
		for (Category U : this.getAllCategories() ) {
			hmap.put(U, 0);
					}
		System.out.println(this.getAllCategories());
		
		List<Purchase>listPurchase= this.getallpurchases();

		for (Purchase U : listPurchase ) {
			if (U.getProspect().getId()==id) {
				hmap.merge(U.getProduct().getCategory(), 1, Integer::sum);
			}
		}
		
		for(Map.Entry<Category , Integer> entry : hmap.entrySet()){
			 System.out.println("Categorie "+entry.getKey()+" occurance "+entry.getValue());
		}
		
		List<Offer> alloffersList = this.listOffer();
		
		List<Offer>clientsuggest= new ArrayList<Offer>();
		
		 for (Offer u : alloffersList) {
			 
			if ((u.isEtat() ==true) &&(hmap.get(u.getProduct().getCategory())>0)){
				System.out.println("i am here fellas");
				clientsuggest.add(u);
				
				
			}
		 }
		 		
		
		return clientsuggest;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Purchase> getallpurchases() {
		// TODO Auto-generated method stub
		
		return em.createQuery("select U from Purchase U", Purchase.class).getResultList();

		
	}

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return em.createQuery("select U from Product U", Product.class).getResultList();

	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return em.createQuery("select U from Category U", Category.class).getResultList();
		
		
		
		
		
	}
	
	
	
	

	

	
	
	
}
