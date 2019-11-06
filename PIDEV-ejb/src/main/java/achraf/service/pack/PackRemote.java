package achraf.service.pack;

import java.util.List;

import Entities.Offer;
import Entities.Pack;
import Entities.Product;

public interface PackRemote {
	public boolean addPack(Pack e);
	public boolean addProductToPack(int Product, int idPack,int qunatite);
	
	
	public void updatePack(Offer e);
	public void deletePack(int e);
	public List<Pack> listPack();
	public Pack findPackById(int e);
	public String idProduit(int id);
	public String nameProduit(int id );
	public Pack findPackByid(int idprod);
	public void updateProduct(Product e);
	public void updatePrix(int idPack,float prix);
	public List<Pack>PackSuggestOperateur();
	public List<Offer>PackSuggestClientRentabilite();
	public List<Pack>PackSuggestClientCategorie(int id);
}
