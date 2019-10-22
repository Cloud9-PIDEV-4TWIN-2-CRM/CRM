package Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity

public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;
	String name;
	float price;
	@ManyToOne
	Category category;
	@ManyToOne
	Agent agent;
	String description;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	List<ProductQuantity> productQte;
	@ManyToOne
	private Publicity publicity;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Offer> offers;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<QuantityPerPack> quantityPerPacks;

	public List<ProductQuantity> getProductQte() {
		return productQte;
	}

	public void setProductQte(List<ProductQuantity> productQte) {
		this.productQte = productQte;
	}

	public Publicity getPublicity() {
		return publicity;
	}

	public void setPublicity(Publicity publicity) {
		this.publicity = publicity;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public List<QuantityPerPack> getQuantityPerPacks() {
		return quantityPerPacks;
	}

	public void setQuantityPerPacks(List<QuantityPerPack> quantityPerPacks) {
		this.quantityPerPacks = quantityPerPacks;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@ManyToOne
	private Stock stock;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
