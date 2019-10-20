package Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class QuantityPerPackPk implements Serializable {

	int product;
	int pack;
}
