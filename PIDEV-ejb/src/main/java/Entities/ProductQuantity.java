package Entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class ProductQuantity implements Serializable{

	@EmbeddedId
	ProductQuantityPk productQuantitypk;
	public ProductQuantity(int qte, Basket basket, Product product) {
		this.qte = qte;
		this.basket = basket;
		this.product = product;
	}

	int qte;
	@ManyToOne
	@JoinColumn(name="basket",referencedColumnName="id",updatable=false,insertable=false)
	Basket basket;
	@ManyToOne
	@JoinColumn(name="product",referencedColumnName="id",updatable=false,insertable=false)
	Product product;
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

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
