package Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class QuantityPerPackPk implements Serializable {

	int product;
	int pack;
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getPack() {
		return pack;
	}
	public void setPack(int pack) {
		this.pack = pack;
	}
}
