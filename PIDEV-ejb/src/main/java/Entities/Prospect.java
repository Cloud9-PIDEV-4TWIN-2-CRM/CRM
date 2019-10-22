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
import javax.persistence.Table;

@Entity
@Table(name = "Prospect")
public class Prospect implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;

	@Column(name = "firstName")
	String firstName;
	@Column(name = "lastName")
	String lastName;
	@Column(name = "email")
	String email;
	@Column(name = "password")
	String password;
	@Column(name = "phone")
	int phone;
	@OneToMany(mappedBy = "prospect", cascade = CascadeType.ALL)
	private List<Customer> customers;
	@OneToMany(mappedBy = "prospect", cascade = CascadeType.ALL)
	private List<Claim> claimsprospects;
	@OneToMany(mappedBy = "prospect", cascade = CascadeType.ALL)
	private List<Basket> basketprospects;

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Claim> getClaimsprospects() {
		return claimsprospects;
	}

	public void setClaimsprospects(List<Claim> claimsprospects) {
		this.claimsprospects = claimsprospects;
	}

	public List<Basket> getBasketprospects() {
		return basketprospects;
	}

	public void setBasketprospects(List<Basket> basketprospects) {
		this.basketprospects = basketprospects;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

}
