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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Basket")
public class Basket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;
	
	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	Set<ProductQuantity> productQte;
	
	@ManyToOne
	private Prospect prospect;

	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Quote> quotes;

	public Set<ProductQuantity> getProductQte() {
		return productQte;
	}

	public void setProductQte(Set<ProductQuantity> productQte) {
		this.productQte = productQte;
	}

	

	public Set<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(Set<Quote> quotes) {
		this.quotes = quotes;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Reservation> reservations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Prospect getProspect() {
		return prospect;
	}

	public void setProspect(Prospect prospect) {
		this.prospect = prospect;
	}

}
