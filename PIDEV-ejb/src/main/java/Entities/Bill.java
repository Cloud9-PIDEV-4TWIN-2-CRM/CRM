package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bill implements Serializable {


	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	float tva;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTva() {
		return tva;
	}
	public void setTva(float tva) {
		this.tva = tva;
	}
}
