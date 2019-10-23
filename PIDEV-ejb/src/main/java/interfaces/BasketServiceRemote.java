package interfaces;

import javax.ejb.Remote;

@Remote
public interface BasketServiceRemote {
public void addToBasket(int idProspect,int idProduct);
	
	
}
