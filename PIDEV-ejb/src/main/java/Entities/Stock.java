package Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Stock implements Serializable{

	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	Set<StockProduct> productStock;
	@ManyToOne
	Shop shop;
	
	public Set<StockProduct> getProductStock() {
		return productStock;
	}
	public void setProductStock(Set<StockProduct> productStock) {
		this.productStock = productStock;
	}
	int qte;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	
}
