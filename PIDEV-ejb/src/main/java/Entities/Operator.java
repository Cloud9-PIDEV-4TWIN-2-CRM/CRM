package Entities;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "Operator")
public class Operator implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Operator")
	int id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "operator",fetch=FetchType.EAGER)
	Set<Agent> agents;

	public Set<Agent> getList() {
		return agents;
	}

	public void setList(Set<Agent> agents) {
		this.agents = agents;
	}

	@Column(name = "Name")
	String nom;
	@Column(name = "email")
	String email;
	@Column(name = "adress")
	String adress;
	@Column(name = "contact")
	int contact;
	@OneToMany(mappedBy = "operator", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Prospect> prospects;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public Set<Prospect> getProspects() {
		return prospects;
	}

	public void setProspects(Set<Prospect> prospects) {
		this.prospects = prospects;
	}

	

}
