package Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity

public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;

	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public Set<Product> getProducts() {
		return products;
	}
	public Category() {
		
	}

	public Category(String name, String type, Set<Product> products) {
		super();
		this.name = name;
		this.type = type;
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", type=" + type  + "]";
	}
	@XmlTransient
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	String name;
	String type;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Product> products;
}
