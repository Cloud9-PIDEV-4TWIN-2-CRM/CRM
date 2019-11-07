package services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import Entities.Basket;
import Entities.Product;
import Entities.ProductQuantity;
import Entities.Reservation;
import interfaces.ReservationServiceLocal;


/**
 * Session Bean implementation class ReservationService
 */
@Stateless
public class ReservationService implements  ReservationServiceLocal {

	@PersistenceContext
	EntityManager em;
    public ReservationService() {
        // TODO Auto-generated constructor stub
    }
   /* @Schedule(second="*",minute="*",hour="*")
    public void rejectReservation() {
    	Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
	    c.setTime(currentDate);
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    Date todayDate = c.getTime();
	    System.out.println("hhhhhh"+todayDate.toString());
	   List<Reservation> reservations=	em.createQuery("select u from Reservation u ",Reservation.class).getResultList();
	    for(int i=0;i<reservations.size();i++) {
	    	if(todayDate.after(reservations.get(i).getFinishDate()) && reservations.get(i).getStatus().equals("In progress")) {
	    		List<ProductQuantity> pq= em.createQuery("select u from ProductQuantity u where u.basket="+reservations.get(i).getBasket().getId(),ProductQuantity.class).getResultList();
	    		for(int j=0; j<pq.size();j++) {
	    			int oldQte=(int) em.createQuery("select u.qte from StockProduct u where u.product="+pq.get(i).getProduct().getId()).getSingleResult();
	    			int newQte=oldQte+pq.get(i).getQte();
	    			em.createQuery("update StockProduct u set u.qte=" + newQte + " where u.product="
							+ pq.get(i).getProduct().getId()).executeUpdate();
	    		}
	    		em.createQuery("update Reservation u set u.status='Canceled'").executeUpdate();
	    		em.createQuery("delete from ProductQuantity u where u.basket="+reservations.get(i).getBasket().getId()).executeUpdate();
	    		
	    	}
	    }
    	
    	
    }*/

	@Override
	public String bookingBasket(int idProspect) {
		List<ProductQuantity> prodQte =null;
		Reservation res = null;
		String response="";
		Basket basket=	em.createQuery("select u from Basket u where u.prospect="+idProspect,Basket.class).getSingleResult();
		System.out.println(basket.getId());
		try {

			prodQte = em.createQuery(
					"select u from ProductQuantity u where u.basket=" + basket.getId() ,
					ProductQuantity.class).getResultList();
			System.out.println("okk"+prodQte);

		} catch (NoResultException k) {
			// Ignore this because as per your logic this is ok!
		}
		catch(NonUniqueResultException u) {
			
		}
		try{
			 res=(Reservation) em.createQuery("select u from Reservation u where u.status='In progress' and u.basket="+basket.getId()).getSingleResult();
		}
		catch(NoResultException e) {
			
		}
		
		if(prodQte==null) {
			System.out.println(prodQte);
			return"basket empty, nothing to book";
			
		}
		
	
		
		else if (res==null){
			Reservation reservation = new Reservation();
			reservation.setBasket(basket);
			reservation.setStatus("In progress");
			Date currentDate = new Date();
			Calendar c = Calendar.getInstance();
		    c.setTime(currentDate);
		    c.add(Calendar.DATE, 2);
		    c.set(Calendar.HOUR_OF_DAY, 0);
		    c.set(Calendar.MINUTE, 0);
		    c.set(Calendar.SECOND, 0);
		    c.set(Calendar.MILLISECOND, 0);
		    c.add(Calendar.DATE, 2);
		    Date finishDate = c.getTime();
		    
			reservation.setFinishDate(finishDate);
			em.persist(reservation);
			List<ProductQuantity> listProductQte = em.createQuery("select u from ProductQuantity u where u.basket="+basket.getId()).getResultList();
			
			for(int i=0;i<listProductQte.size();i++) {
				int oldQte=(int) em.createQuery("select u.qte from StockProduct u where u.product="+listProductQte.get(i).getProduct().getId()).getSingleResult();
				int newQte=oldQte-listProductQte.get(i).getQte();
				em.createQuery("update StockProduct u set u.qte=" + newQte + " where u.product="
						+ listProductQte.get(i).getProduct().getId()).executeUpdate();
			}
			return "ok";
		}
		return "you have already a reservation";
		
		
	}

	@Override
	public void cancelReservation(int idReservation,int idProspect) {
		Basket basketId= (Basket) em.createQuery("select u.basket from Reservation u where u.id="+idReservation+" and u.status='In progress'").getSingleResult();
		em.createQuery("update Reservation u set u.status='Canceled' where u.id=" + idReservation).executeUpdate();
		List<ProductQuantity> products=em.createQuery("select u from ProductQuantity u where u.basket="+basketId.getId(),ProductQuantity.class).getResultList();
		int basket = em.createQuery("select u.id from Basket u where u.prospect=" + idProspect, Integer.class).getSingleResult();
		
		for(int i=0;i<products.size();i++) {
			int oldQte=(int) em.createQuery("select u.qte from StockProduct u where u.product="+products.get(i).getProduct().getId()).getSingleResult();
			
			int newQte=oldQte+products.get(i).getQte();
			em.createQuery("update StockProduct u set u.qte=" + newQte + " where u.product="
					+ products.get(i).getProduct().getId()).executeUpdate();
			em.createQuery("delete from ProductQuantity u where u.basket="+basket+" and u.product="+products.get(i).getProduct().getId()).executeUpdate();
			
		}
		
	}

	@Override
	public List<Reservation> getAllReservationUser(int idProspect) {
		Basket basket=	em.createQuery("select u from Basket u where u.prospect="+idProspect,Basket.class).getSingleResult();
		List<Reservation> list = em.createQuery(
				"select u from Reservation u where u.basket=" + basket.getId() ,
				Reservation.class).getResultList();
		return list;
	}

}
