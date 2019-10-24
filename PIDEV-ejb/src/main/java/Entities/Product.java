package Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	Set<StockProduct> productStock;

	

	public Set<StockProduct> getProductStock() {
		return productStock;
	}

	public void setProductStock(Set<StockProduct> productStock) {
		this.productStock = productStock;
	}

	

	String name;
	float price;
	@ManyToOne
	Category category;
	@ManyToOne
	Agent agent;
	String description;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	Set<ProductQuantity> productQte;
	@ManyToOne
	private Publicity publicity;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Offer> offers;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<QuantityPerPack> quantityPerPacks;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	Set<Purchase> purchases;
	

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Set<ProductQuantity> getProductQte() {
		return productQte;
	}

	public void setProductQte(Set<ProductQuantity> productQte) {
		this.productQte = productQte;
	}

	public Publicity getPublicity() {
		return publicity;
	}

	public void setPublicity(Publicity publicity) {
		this.publicity = publicity;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Set<QuantityPerPack> getQuantityPerPacks() {
		return quantityPerPacks;
	}

	public void setQuantityPerPacks(Set<QuantityPerPack> quantityPerPacks) {
		this.quantityPerPacks = quantityPerPacks;
	}

	

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
