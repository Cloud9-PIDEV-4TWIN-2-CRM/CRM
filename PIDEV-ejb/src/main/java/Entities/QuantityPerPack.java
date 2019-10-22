package Entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class QuantityPerPack implements Serializable {

	@EmbeddedId
	QuantityPerPackPk quantityperpackpk;
	@ManyToOne
	@JoinColumn(name = "product", referencedColumnName = "id", updatable = false, insertable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "pack", referencedColumnName = "id", updatable = false, insertable = false)
	private Pack pack;
	int qte;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Pack getPack() {
		return pack;
	}

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
