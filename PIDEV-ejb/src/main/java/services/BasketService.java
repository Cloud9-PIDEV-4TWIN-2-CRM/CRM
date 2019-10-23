package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Basket;
import Entities.Product;
import Entities.ProductQuantity;
import Entities.Prospect;
import interfaces.BasketServiceLocal;
import interfaces.BasketServiceRemote;


/**
 * Session Bean implementation class BasketService
 */
@Stateless
@LocalBean
public class BasketService implements BasketServiceRemote, BasketServiceLocal {
	

	@PersistenceContext
    EntityManager em ;
    public BasketService() {
        
    }

	@Override
	public void addToBasket(int idProspect, int idProduct) {
		Basket basket  =  em.createQuery("select u from basket u where u.prospect_id="+idProspect,Basket.class).getSingleResult();
		Product product  =  em.createQuery("select u from product u where u.id="+idProduct,Product.class).getSingleResult();
		ProductQuantity prodQte=em.createQuery("select u from productquantity where u.basket="+basket.getId()+" and u.product="+idProduct,ProductQuantity.class).getSingleResult();
		if(prodQte.getQte()==0) {//new insert into table productquantity
			ProductQuantity pq = new ProductQuantity(1,basket,product);
			em.persist(pq);
		}
		
		else {// update line in table productquantity 
			int nvqte=prodQte.getQte()+1;
			em.createQuery("update productquantity u set u.qte="+nvqte+" where u.basket="+prodQte.getBasket().getId()+" and u.product="+prodQte.getProduct().getId()).executeUpdate();
		}
		
		
		
	}

}
