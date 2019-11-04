package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@XmlRootElement
public class Publicity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;
	@OneToMany(mappedBy = "publicity")
	private Set<Product> product;
	String startDate, finishDate,image;

	 
	public Set<Product> getProduct() {
		return product;
	}
	/*
	public Set<Product> getProductsNameId() {
		
	}
	*/


	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public String getImage() {
		return image;
	}
	@XmlElement(name="ImageUrl")
	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}
	@XmlAttribute(name="Id",required=true)
	public void setId(int id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}
	@XmlElement(name="StartDate")
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}
	@XmlElement(name="FinishDate")
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
}
