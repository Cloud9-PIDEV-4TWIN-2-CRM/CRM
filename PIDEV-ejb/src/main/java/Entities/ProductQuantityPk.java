package Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductQuantityPk implements Serializable {

	int basket;
int product;
}
