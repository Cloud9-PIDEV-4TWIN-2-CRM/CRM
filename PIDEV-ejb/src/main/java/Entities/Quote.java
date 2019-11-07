package Entities;

import java.io.Serializable;
import java.util.List;

public class Quote implements Serializable {

	private List<String> nameProduct;
	private List<Float> prixProduct;
	private List<Integer> quantityProduct;
	private List<Float> reductionProduct;
	
	@Override
	public String toString() {
		return "Quote [nameProduct=" + nameProduct + ", prixProduct=" + prixProduct + ", quantityProduct="
				+ quantityProduct + ", reductionProduct=" + reductionProduct + "]";
	}
	public List<String> getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(List<String> nameProduct) {
		this.nameProduct = nameProduct;
	}
	public List<Float> getPrixProduct() {
		return prixProduct;
	}
	public void setPrixProduct(List<Float> prixProduct) {
		this.prixProduct = prixProduct;
	}
	public List<Integer> getQuantityProduct() {
		return quantityProduct;
	}
	public void setQuantityProduct(List<Integer> quantityProduct) {
		this.quantityProduct = quantityProduct;
	}
	public List<Float> getReductionProduct() {
		return reductionProduct;
	}
	public void setReductionProduct(List<Float> reductionProduct) {
		this.reductionProduct = reductionProduct;
	}
	
	
}
