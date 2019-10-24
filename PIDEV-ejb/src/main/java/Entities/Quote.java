package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Quote implements Serializable{



	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	@ManyToOne
	Basket basket;
	float total;
	
	@ManyToOne
	private Bill bill;
	
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
}
