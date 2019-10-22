package Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pack implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;
	String type;
	float price;
	@OneToMany(mappedBy = "pack", cascade = CascadeType.ALL)
	private List<QuantityPerPack> quantityPerPacks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<QuantityPerPack> getQuantityPerPacks() {
		return quantityPerPacks;
	}

	public void setQuantityPerPacks(List<QuantityPerPack> quantityPerPacks) {
		this.quantityPerPacks = quantityPerPacks;
	}
}
