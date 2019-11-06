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
import javax.persistence.Table;

@Entity
@Table(name = "Prospect")
public class Prospect implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "registerDate")
	private Date registerDate;
	@Column(name = "phone")
	private int phone;
	@Column(name = "customer")
	private boolean customer;
	@Column(name = "disabled")
	private boolean disabled;
	@OneToMany(mappedBy = "prospect", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Claim> claimsprospects;
	@OneToMany(mappedBy = "prospect", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Basket> basketprospects;
	@ManyToOne
	private Operator operator;
	@OneToMany(mappedBy = "prospect", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Purchase> purchases;
	private boolean isConfirmed;

	public Prospect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prospect(String firstName, String lastName, String email, String password, int phone, boolean customer,
			Operator operator, boolean isConfirmed,boolean disabled) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.customer = customer;
		this.operator = operator;
		this.isConfirmed = isConfirmed;
		this.disabled = disabled;
	}
	

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public boolean isCustomer() {
		return customer;
	}

	public void setCustomer(boolean customer) {
		this.customer = customer;
	}

	public Set<Claim> getClaimsprospects() {
		return claimsprospects;
	}

	public void setClaimsprospects(Set<Claim> claimsprospects) {
		this.claimsprospects = claimsprospects;
	}

	public Set<Basket> getBasketprospects() {
		return basketprospects;
	}

	public void setBasketprospects(Set<Basket> basketprospects) {
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
