package achraf.service.publicity;



import java.util.List;

import javax.ejb.Local;

import Entities.*;


@Local
public interface PublicityLocal {

	
	public boolean addPublicity(Publicity e);
	public void updatePublicity(Publicity e);
	public void deletePublicity(int e);
	public List<Publicity> listPub();
	public Publicity findPublicityById(int e);
	public String idProduit(int id);
	public String nameProduit(int id );
	public Product findProduitByid(int idprod);
	public void updateProduct(Product e);
	public  List<Publicity> currentPublicities();
	public  List<Publicity> donePublicities();
	

	
}
