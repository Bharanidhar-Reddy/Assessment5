package com.dao;

import java.util.List;

import com.model.Product;

public interface ProductDao {
	public void create(Product p);
	public List<Product> findall();
	public Product find(String id);
	public Product findByName(String name);
	public void update(Product p);
	public void remove(Product p);
	List<Product> findAllNames(String name);
}
