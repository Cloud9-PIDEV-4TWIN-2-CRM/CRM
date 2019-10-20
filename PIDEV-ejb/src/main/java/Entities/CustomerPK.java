package Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerPK implements Serializable {

	int idProspect;
	int idOperator;
}
