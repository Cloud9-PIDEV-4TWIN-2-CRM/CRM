package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
@Entity
public class Customer implements Serializable {

 @EmbeddedId
 CustomerPK customerPk;
 

public CustomerPK getCustomerPk() {
	return customerPk;
}

public void setCustomerPk(CustomerPK customerPk) {
	this.customerPk = customerPk;
}


		

 }
