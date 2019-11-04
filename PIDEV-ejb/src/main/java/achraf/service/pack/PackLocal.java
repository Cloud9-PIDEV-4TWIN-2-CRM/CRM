package achraf.service.pack;

import java.util.List;

import Entities.Offer;
import Entities.Pack;
import Entities.Product;

public interface PackLocal {
	public boolean addPack(Pack e);
	public void updatePack(Offer e);
	public void deletePack(int e);
	public List<Offer> listPack();
	public Offer findPackById(int e);
	public String idProduit(int id);
	public String nameProduit(int id );
	public Pack findPackByid(int idprod);
	public void updateProduct(Product e);
	public List<Offer>PackSuggestOperateur();
	public List<Offer>PackSuggestClientRentabilite();
	public List<Offer>PackSuggestClientCategorie();
}
