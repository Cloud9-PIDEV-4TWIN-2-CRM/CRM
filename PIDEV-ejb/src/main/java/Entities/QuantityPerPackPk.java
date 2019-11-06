package Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlTransient;

@Embeddable
public class QuantityPerPackPk implements Serializable {

	int product;
	int pack;
	@XmlTransient
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getPack() {
		return pack;
	}
	@XmlTransient
	public void setPack(int pack) {
		this.pack = pack;
	}
	
	public QuantityPerPackPk() {
		
	}
	public QuantityPerPackPk(int pack,int produit) {
		this.pack=pack;
		this.product=produit;
	}
}
