package Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class StocKProductPK implements Serializable {

	private int idStock;
	private int idProduct;
	public int getIdStock() {
		return idStock;
	}
	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
}
