package services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Basket;
import Entities.ProductQuantity;
import Entities.Reservation;
import interfaces.ReservationServiceLocal;
import interfaces.ReservationServiceRemote;

/**
 * Session Bean implementation class ReservationService
 */
@Stateless
@LocalBean
public class ReservationService implements ReservationServiceRemote, ReservationServiceLocal {

	@PersistenceContext
	EntityManager em;
    public ReservationService() {
        // TODO Auto-generated constructor stub
    }
    //@Schedule(second="*",minute="*",hour="*")
    public void rejectReservation() {
    	
    }

	@Override
	public void bookingBasket(int idProspect) {
		ProductQuantity prodQte =null;
		Basket basket=	em.createQuery("select u from Basket u where u.prospect="+idProspect,Basket.class).getSingleResult();
		try {

			prodQte = em.createQuery(
					"select u from ProductQuantity u where u.basket=" + basket.getId() ,
					ProductQuantity.class).getSingleResult();

		} catch (javax.persistence.PersistenceException k) {
			// Ignore this because as per your logic this is ok!
		}
		if(prodQte==null) {
			System.out.println("basket empty, nothing to book");
		}
		else {
			Reservation reservation = new Reservation();
			reservation.setBasket(basket);
			reservation.setStatus("In progress");
			Date currentDate = new Date();
			Calendar c = Calendar.getInstance();
		    c.setTime(currentDate);
		    c.add(Calendar.DATE, 2);
		    Date finishDate = c.getTime();
			reservation.setFinishDate(finishDate);
			em.persist(reservation);
		}
		
		
	}

	@Override
	public void cancelReservation(int idReservation) {
		em.createQuery("delete from Reservation u where u.id=" + idReservation).executeUpdate();
		
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
