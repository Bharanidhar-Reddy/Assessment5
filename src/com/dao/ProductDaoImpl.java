package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.Product;


public class ProductDaoImpl implements ProductDao{
	
	private static EntityManagerFactory emf= Persistence.createEntityManagerFactory("myProduct");
	
	@Override
	public void create(Product p) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}

	@Override
	public List<Product> findall() {
		EntityManager em=emf.createEntityManager();
		List<Product> products=em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
		return products;
	}

	@Override
	public Product find(String id) {
		EntityManager em =emf.createEntityManager();
		Product p=em.find(Product.class, id);
		return p;
	}

	@Override
	public Product findByName(String name) {
		EntityManager em=emf.createEntityManager();
		Product p=em.createQuery("SELECT p FROM Product p WHERE p.name= :name", Product.class).setParameter("name", name).getResultList().get(0);
		return p;
	}

	@Override
	public void update(Product p) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}

	@Override
	public void remove(Product p) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(p) ? p : em.merge(p));
		em.getTransaction().commit();
	}
	
	@Override
	public List<Product> findAllNames(String name) {
		EntityManager em=emf.createEntityManager();
		List<Product> pp=em.createQuery("SELECT p FROM Product p WHERE p.name= :name", Product.class).setParameter("name", name).getResultList();
		return pp;
	}

}
