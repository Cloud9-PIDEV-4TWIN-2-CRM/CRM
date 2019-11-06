package Entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class QuantityPerPack implements Serializable {


	@EmbeddedId
	QuantityPerPackPk quantityperpackpk;
	@ManyToOne
	@JoinColumn(name = "product", referencedColumnName = "id", updatable = false, insertable = false)
	private Product product;

	@ManyToOne()
	@JoinColumn(name = "pack", referencedColumnName = "id", updatable = false, insertable = false)
	private Pack pack;
	

	@Override
	public String toString() {
		return "QuantityPerPack [product=" + product.getName()+", Price="+product.getPrice() + ", qte=" + qte + "]";
	}

	int qte;

	public Product getProduct() {
		return product;
	}
public QuantityPerPack() {
	
}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Pack getPack() {
		return pack;
	}
	@XmlTransient
	public void setPack(Pack pack) {
		this.pack = pack;
	}

	public QuantityPerPackPk getQuantityperpackpk() {
		return quantityperpackpk;
	}

	public void setQuantityperpackpk(QuantityPerPackPk quantityperpackpk) {
		this.quantityperpackpk = quantityperpackpk;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}
	
	

}
