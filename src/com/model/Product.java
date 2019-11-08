package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(generator = "id_gen")
	@GenericGenerator(name = "id_gen",strategy = "uuid")
	private String id;
	private String name;
	private String description;
	private float price;
	private int quantity;
	public Product(String name, String description, String price, String quantity) {
		super();
		this.name = name;
		this.description = description;
		this.price = Float.parseFloat(price);
		this.quantity = Integer.parseInt(quantity);
	}
	public String getId() {
		return id;
	}
	public Product() {
		super();
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = Float.parseFloat(price);
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = Integer.parseInt(quantity);
	}
	
}
