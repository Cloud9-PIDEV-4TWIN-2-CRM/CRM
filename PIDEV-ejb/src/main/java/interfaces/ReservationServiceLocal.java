package interfaces;

import java.util.List;

import javax.ejb.Local;

import Entities.Reservation;

@Local
public interface ReservationServiceLocal {
	public String bookingBasket(int idProspect);
	public void cancelReservation(int idReservation,int idProspect);
	public List<Reservation> getAllReservationUser(int idProspect);
}
