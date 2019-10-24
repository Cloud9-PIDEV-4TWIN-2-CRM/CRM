package Entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StockProduct implements Serializable {
	@EmbeddedId
	private StocKProductPK stockProductPk;
	private int qte ;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idStock",referencedColumnName="id",updatable=false,insertable=false,nullable=false)
	Stock stock;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idProduct",referencedColumnName="id",updatable=false,insertable=false,nullable=false)
	Product product;
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public StocKProductPK getStockProductPk() {
		return stockProductPk;
	}
	public void setStockProductPk(StocKProductPK stockProductPk) {
		this.stockProductPk = stockProductPk;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	

}
