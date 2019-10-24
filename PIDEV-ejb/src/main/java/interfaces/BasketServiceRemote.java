package interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.ProductQuantity;
import Entities.Reservation;


@Remote
public interface BasketServiceRemote {
public void addProductToBasket(int idProspect,int idProduct);
public void removeProductFromBasket(int idProspect , int idProduct);
public List<ProductQuantity> getBasketUser(int idProspect);

	
	
}
