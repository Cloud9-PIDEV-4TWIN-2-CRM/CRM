package Entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class QuantityPerPack implements Serializable{

	@EmbeddedId
	QuantityPerPackPk quantityperpackpk;
	int qte;
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
