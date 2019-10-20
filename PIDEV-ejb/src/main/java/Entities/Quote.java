package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quote implements Serializable{


	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	Basket basket;
	float total;
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
