package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Customer implements Serializable {

	@EmbeddedId
	CustomerPK customerPk;
	@ManyToOne
	@JoinColumn(name = "idOperator", referencedColumnName = "ID_Operator", updatable = false, insertable = false)
	Operator operator;

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Prospect getProspect() {
		return prospect;
	}

	public void setProspect(Prospect prospect) {
		this.prospect = prospect;
	}

	@ManyToOne
	@JoinColumn(name = "idProspect", referencedColumnName = "id", updatable = false, insertable = false)
	Prospect prospect;

	public CustomerPK getCustomerPk() {
		return customerPk;
	}

	public void setCustomerPk(CustomerPK customerPk) {
		this.customerPk = customerPk;
	}

}
