package Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductQuantityPk implements Serializable {

	int basket;
	int product;
public ProductQuantityPk() {
	
}

	public ProductQuantityPk(int basket, int product) {
		this.basket = basket;
		this.product = product;
	}

	public int getBasket() {
		return basket;
	}

	public void setBasket(int basket) {
		this.basket = basket;
	}

	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

}
