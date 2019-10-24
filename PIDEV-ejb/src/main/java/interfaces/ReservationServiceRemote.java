package interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.Reservation;

@Remote
public interface ReservationServiceRemote {

	public void bookingBasket(int idProspect);
	public void cancelReservation(int idReservation);
	public List<Reservation> getAllReservationUser(int idProspect);
}
