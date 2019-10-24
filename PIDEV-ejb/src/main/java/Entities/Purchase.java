package Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Purchase")
public class Purchase implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;
	@ManyToOne
	@JoinColumn(name = "prospect", referencedColumnName = "id", updatable = false, insertable = false)
	private Prospect prospect;
	@ManyToOne
	@JoinColumn(name = "product", referencedColumnName = "id", updatable = false, insertable = false)
	private Product product;
	private int qte;
	private Date datePur;
	private float sum;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public Date getDatePur() {
		return datePur;
	}
	public void setDatePur(Date datePur) {
		this.datePur = datePur;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	

}
