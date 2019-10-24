package services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import Entities.Basket;
import Entities.Product;
import Entities.ProductQuantity;
import Entities.ProductQuantityPk;
import Entities.Prospect;
import Entities.Reservation;
import interfaces.BasketServiceLocal;
import interfaces.BasketServiceRemote;

/**
 * Session Bean implementation class BasketService
 */
@Stateless
@LocalBean
public class BasketService implements BasketServiceRemote, BasketServiceLocal {

	@PersistenceContext
	EntityManager em;

	public BasketService() {

	}

	@Override
	public void addProductToBasket(int idProspect, int idProduct) {
		ProductQuantity prodQte = null;
		Basket basket = null;
		Product product = null;
		basket = em.createQuery("select u from Basket u where u.prospect=" + idProspect, Basket.class)
				.getSingleResult();
		product = em.createQuery("select u from Product u where u.id=" + idProduct, Product.class).getSingleResult();
		System.out.println("hahaha" + basket.getId());
		try {

			prodQte = em.createQuery(
					"select u from ProductQuantity u where u.basket=" + basket.getId() + " and u.product=" + idProduct,
					ProductQuantity.class).getSingleResult();

		} catch (javax.persistence.PersistenceException k) {
			// Ignore this because as per your logic this is ok!
		}

		if (prodQte == null) {// new insert into table productquantity
			System.out.println("hahahha" + basket.getId() + " nananna" + product.getId());
			ProductQuantity pq = new ProductQuantity();
			ProductQuantityPk pqpk = new ProductQuantityPk(basket.getId(), product.getId());
			pq.setProductQuantitypk(pqpk);
			pq.setQte(1);
			em.persist(pq);
		}

		else {// update line in table productquantity
			int nvqte = prodQte.getQte() + 1;
			em.createQuery("update ProductQuantity u set u.qte=" + nvqte + " where u.basket="
					+ prodQte.getBasket().getId() + " and u.product=" + prodQte.getProduct().getId()).executeUpdate();
		}

	}

	@Override
	public void removeProductFromBasket(int idProspect, int idProduct) {
		ProductQuantity prodQte = null;
		Basket basket = null;
		basket = em.createQuery("select u from Basket u where u.prospect=" + idProspect, Basket.class)
				.getSingleResult();
		try {

			prodQte = em.createQuery(
					"select u from ProductQuantity u where u.basket=" + basket.getId() + " and u.product=" + idProduct,
					ProductQuantity.class).getSingleResult();

		} catch (javax.persistence.PersistenceException k) {
			// Ignore this because as per your logic this is ok!
		}
		if (prodQte.getQte() >= 2) {
			int nvqte = prodQte.getQte() - 1;
			em.createQuery("update ProductQuantity u set u.qte=" + nvqte + " where u.basket="
					+ prodQte.getBasket().getId() + " and u.product=" + prodQte.getProduct().getId()).executeUpdate();
		} else if (prodQte.getQte() == 1) {
			em.createQuery("delete from ProductQuantity u where u.basket=" + prodQte.getBasket().getId()
					+ " and u.product=" + prodQte.getProduct().getId()).executeUpdate();
		}

	}

	@Override
	public List<ProductQuantity> getBasketUser(int idProspect) {
	Basket basket=	em.createQuery("select u from Basket u where u.prospect="+idProspect,Basket.class).getSingleResult();
	List<ProductQuantity> list = em.createQuery("select u from ProductQuantity u where u.basket="+basket.getId()).getResultList();
	return list;
	}

}