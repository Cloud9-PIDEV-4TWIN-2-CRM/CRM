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
public class Basket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;
	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
	List<ProductQuantity> productQte;
	@ManyToOne
	private Prospect prospect;

	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
	private List<Quote> quotes;

	public List<ProductQuantity> getProductQte() {
		return productQte;
	}

	public void setProductQte(List<ProductQuantity> productQte) {
		this.productQte = productQte;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
	private List<Reservation> reservations;

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
