package Entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "ProductQuantity",
uniqueConstraints=@UniqueConstraint(columnNames={"basket", "product"}))
public class ProductQuantity implements Serializable{

	

	@EmbeddedId
	ProductQuantityPk productQuantitypk;
	public ProductQuantity(int qte, Basket basket, Product product) {
		this.qte = qte;
		this.basket = basket;
		this.product = product;
	}
	public ProductQuantity() {
		
	}

	int qte;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="basket",referencedColumnName="id",updatable=false,insertable=false,nullable=false)
	Basket basket;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product",referencedColumnName="id",updatable=false,insertable=false,nullable=false)
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
