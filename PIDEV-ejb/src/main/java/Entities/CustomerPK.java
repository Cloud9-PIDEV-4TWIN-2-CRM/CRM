package Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerPK implements Serializable {

	int idProspect;

	public int getIdProspect() {
		return idProspect;
	}

	public void setIdProspect(int idProspect) {
		this.idProspect = idProspect;
	}

	public int getIdOperator() {
		return idOperator;
	}

	public void setIdOperator(int idOperator) {
		this.idOperator = idOperator;
	}

	int idOperator;
}
