package Entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ProductQuantity implements Serializable{

	@EmbeddedId
	ProductQuantityPk productQuantitypk;
	int qte;
	public ProductQuantityPk getProductQuantitypk() {
		return productQuantitypk;
	}
	public void setProductQuantitypk(ProductQuantityPk productQuantitypk) {
		this.productQuantitypk = productQuantitypk;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
}
