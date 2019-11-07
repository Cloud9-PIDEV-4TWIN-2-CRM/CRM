package interfaces;

import java.util.List;

import javax.ejb.Local;

import Entities.ProductQuantity;

@Local
public interface BasketServiceLocal {

	public void addProductToBasket(int idProspect,int idProduct);
	public void removeProductFromBasket(int idProspect , int idProduct);
	public List<ProductQuantity> getBasketUser(int idProspect);


	
}
