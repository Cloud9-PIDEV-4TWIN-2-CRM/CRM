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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity

public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	
	int id;
	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
	Set<StockProduct> productStock;

	public Product() {
		
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productStock=" + productStock + ", name=" + name + ", price=" + price
				+ ", category=" + category + ", agent=" + agent + ", description=" + description + ", productQte="
				+ productQte + ", publicity=" + publicity + ", offers=" + offers + ", quantityPerPacks="
				+ quantityPerPacks + ", purchases=" + purchases + "]";
	}

	public Product( Set<StockProduct> productStock, String name, float price, Category category, Agent agent,
			String description, Set<ProductQuantity> productQte, Publicity publicity, Set<Offer> offers,
			Set<QuantityPerPack> quantityPerPacks, Set<Purchase> purchases) {
		super();
		this.id = id;
		this.productStock = productStock;
		this.name = name;
		this.price = price;
		this.category = category;
		this.agent = agent;
		this.description = description;
		this.productQte = productQte;
		this.publicity = publicity;
		this.offers = offers;
		this.quantityPerPacks = quantityPerPacks;
		this.purchases = purchases;
	}

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
	@ManyToOne(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)

	private Publicity publicity;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Offer> offers;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<QuantityPerPack> quantityPerPacks;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	Set<Purchase> purchases;
	
	@XmlTransient
	public Set<Purchase> getPurchases() {
		return purchases;
	}
	@XmlTransient
	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	@XmlTransient
	public Set<ProductQuantity> getProductQte() {
		return productQte;
	}
	@XmlTransient
	public void setProductQte(Set<ProductQuantity> productQte) {
		this.productQte = productQte;
	}

	public Publicity getPublicity() {
		return publicity;
	}
	@XmlTransient
	public void setPublicity(Publicity publicity) {
		this.publicity = publicity;
	}

	public Set<Offer> getOffers() {
		return offers;
	}
	@XmlTransient
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	@XmlTransient
	public Set<QuantityPerPack> getQuantityPerPacks() {
		return quantityPerPacks;
	}
	@XmlTransient
	public void setQuantityPerPacks(Set<QuantityPerPack> quantityPerPacks) {
		this.quantityPerPacks = quantityPerPacks;
	}

	

	public int getId() {
		return id;
	}
	@XmlAttribute(name="Id",required=true)
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	@XmlAttribute(name="nameProduct")

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}
	@XmlTransient
	public void setPrice(float price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}
	@XmlTransient
	public void setCategory(Category category) {
		this.category = category;
	}

	public Agent getAgent() {
		return agent;
	}
	@XmlTransient
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getDescription() {
		return description;
	}
	@XmlTransient
	public void setDescription(String description) {
		this.description = description;
	}

}
