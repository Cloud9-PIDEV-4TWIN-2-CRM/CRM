package achraf.service.pack;

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
import Entities.Pack;
import Entities.Product;
import Entities.ProductQuantity;
import Entities.ProductQuantityPk;
import Entities.Purchase;
import Entities.QuantityPerPack;
import Entities.QuantityPerPackPk;
import achraf.service.offer.OfferService;

@Stateless
@LocalBean
public class PackService implements PackRemote,PackLocal{

	@PersistenceContext(unitName="crm")
	EntityManager em;

@Override
public boolean addPack(Pack e) {
	boolean added=true;
	em.persist(e);
	return added;
}

@Override
public void updatePack(Offer e) {
	// TODO Auto-generated method stub
	
}

@Override
public void deletePack(int e) {
	// TODO Auto-generated method stub
	em.remove(em.find(Pack.class, e));
}

@Override
public List<Pack> listPack() {
	// TODO Auto-generated method stub
	return em.createQuery("select U from Pack U", Pack.class).getResultList();
}

@Override
public Pack findPackById(int e) {
	// TODO Auto-generated method stub
	return em.find(Pack.class, e);
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
public Pack findPackByid(int idprod) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void updateProduct(Product e) {
	// TODO Auto-generated method stub
	
}

@Override
public List<Pack> PackSuggestOperateur() {
	// TODO Auto-generated method stub
	
	List<Purchase>listPurchase= em.createQuery("select U from Purchase U", Purchase.class).getResultList();
	List<Product>listProduct= em.createQuery("select U from Product U", Product.class).getResultList();
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

List<Pack>SuggestedOfferOperator= new ArrayList<Pack>();

Set<QuantityPerPack>QPK= new HashSet<QuantityPerPack>();
int x=0;
Pack p=new Pack();
float price=0;

 for(Map.Entry<Product , Integer> entry : hmap.entrySet()){
     System.out.println("Product "+entry.getKey()+" Occurance "+entry.getValue());
     
     if (entry.getValue()<2) {
    	 x=x+2;
    QuantityPerPack Qu= new QuantityPerPack();
    Qu.setQte(2);
    Qu.setProduct(entry.getKey());
    Qu.setPack(p);
    
    		price=(float)(price+2*entry.getKey().getPrice()*0.3);
    		QPK.add(Qu);
    		System.out.println(Qu);
	    		System.out.println(QPK);
     }
    		
     else if (entry.getValue()<5) {
    	 x=x+1;
    	    QuantityPerPack Qu= new QuantityPerPack();
    	    Qu.setQte(1);
    	    Qu.setProduct(entry.getKey());
    	    Qu.setPack(p);
    	    		
    	    		price=(float)(price+entry.getKey().getPrice()*0.2);
    	    		QPK.add(Qu);
    	    		System.out.println(Qu);
     	    		System.out.println(QPK);

     }
     
     else if (entry.getValue()<8){
    	 
    	 x=x+1;
 	    QuantityPerPack Qu= new QuantityPerPack();
 	    Qu.setQte(1);
 	    Qu.setProduct(entry.getKey());
 	    Qu.setPack(p);
 	    		
 	    		price=(float)(price+entry.getKey().getPrice()*0.1);
 	    		QPK.add(Qu);
    	
 	    		System.out.println(Qu);
 	    		System.out.println(QPK);
    	 
     }
     if (x>=3) {
    	 
    	 p.setQuantityPerPacks(QPK);
    	 p.setPrice(price);
    	 p.setType("Pack Promo");
    	 SuggestedOfferOperator.add(p);
    	 x=0;
    	QPK= new HashSet<QuantityPerPack>();

    	  p=new Pack();

         price=0;
    	 
     }
 }
	
	return SuggestedOfferOperator;
	
}

@Override
public List<Offer> PackSuggestClientRentabilite() {
	// TODO Auto-generated method stub
	
	return null;
}

@Override
public List<Pack>PackSuggestClientCategorie(int id) {
	// TODO Auto-generated method stub
	List<Category>listCategories= em.createQuery("select U from Category U", Category.class).getResultList();
	List<Purchase>listPurchases= em.createQuery("select U from Purchase U", Purchase.class).getResultList();
	Map<Category, Integer> hmap = new HashMap<>();
	
	
	
	
	for (Category U : listCategories ) {
		hmap.put(U, 0);
				}
	System.out.println(listCategories);
	
	List<Purchase>listPurchase= listPurchases;

	for (Purchase U : listPurchase ) {
		if (U.getProspect().getId()==id) {
			hmap.merge(U.getProduct().getCategory(), 1, Integer::sum);
		}
	}
	
	for(Map.Entry<Category , Integer> entry : hmap.entrySet()){
		 System.out.println("Categorie "+entry.getKey()+" occurance "+entry.getValue());
	}
	
	
	
	List<Pack>clientsuggest= new ArrayList<Pack>();
	
	 for (Pack u : listPack()) {
		 
		 u.getQuantityPerPacks().forEach(
				 e-> {
				if (hmap.get(e.getProduct().getCategory())>0) {
				if (!clientsuggest.contains(u)) {
					clientsuggest.add(u);
				}
					
					
				}
					 
				 }
				 
				 );
		 
			
			
		}
	 
	 		
	
	return clientsuggest;
	
}

@Override
public boolean addProductToPack(int Product, int idPack,int qunatite) {
	// TODO Auto-generated method stub
	
	Pack pa=em.find(Pack.class, idPack);
	Product pr= em.find(Product.class, Product);
	System.out.println(pa);
	System.out.println(pr);

	
	
	/*quPerPack.setQte(qunatite);
	quPerPack.setProduct(em.find(Product.class, Product));
	quPerPack.setPack(pa);
	Set<QuantityPerPack> aux = new HashSet<QuantityPerPack>();
	aux=pa.getQuantityPerPacks();
	aux.add(quPerPack);
	pa.setQuantityPerPacks(aux);
	em.merge(o);
	*/
	
	QuantityPerPack quPerPack=null;
	try {

		 quPerPack = em.createQuery(
				"select u from QuantityPerPack u where u.pack=" + pa.getId() + " and u.product=" + pr.getId(),
				QuantityPerPack.class).getSingleResult();

	} catch (javax.persistence.PersistenceException k) {
		// Ignore this because as per your logic this is ok!
	}
	
	if (quPerPack == null) {// new insert into table productquantity
		QuantityPerPack pq = new QuantityPerPack();
		QuantityPerPackPk pqpk = new QuantityPerPackPk(pa.getId(), pr.getId());
		pq.setQuantityperpackpk(pqpk);
		pq.setQte(qunatite);
		em.persist(pq);
	}

	else {// update line in table productquantity
		int nvqte = quPerPack.getQte() + qunatite;
		em.createQuery("update QuantityPerPack u set u.qte=" + nvqte + " where u.pack="
				+ pa.getId() + " and u.product=" + pr.getId()).executeUpdate();
	}
	
	
	
	
	
return true; }

@Override
public void updatePrix(int idPack, float prix) {
	// TODO Auto-generated method stub
	
	Pack e= em.find(Pack.class, idPack);
	e.setPrice(prix);
	em.merge(e);
	
	
	
}


	
	
	
}
