package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Basket implements Serializable{

	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	Prospect prospect;
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
