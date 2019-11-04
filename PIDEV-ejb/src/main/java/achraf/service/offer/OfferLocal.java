package achraf.service.offer;

import java.util.List;

import Entities.Category;
import Entities.Offer;
import Entities.Product;
import Entities.Publicity;
import Entities.Purchase;

public interface OfferLocal {

	public boolean addOffer(Offer e);
	public void updateOffer(Offer e);
	public void deleteOffer(int e);
	public boolean activateOffer(int idOffer);
	public boolean DesactivateOffer(int idOffer);
	public List<Offer> listOffer();
	public Offer findOfferById(int e);
	public String idProduit(int id);
	public String nameProduit(int id );
	public Product findProduitByid(int idprod);
	public void updateProduct(Product e);
	public List<Offer>OfferProduitOperateur();
	public List<Offer>OfferProduitClientRentabilite();
	public List<Offer>OfferProduitClientCategorie();
	public Offer getOffreProductCourante(int idprod) ;
	public List<Offer>getOperteurSuggestOffer();
	public List<Offer>getClientSuggestOffer(int id);
	public List<Purchase>getallpurchases();
	public List<Product>getProducts();
	public List<Category>getAllCategories();



		



}
