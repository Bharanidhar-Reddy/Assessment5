package com.main;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.dao.ProductDaoImpl;
import com.model.Product;

public class MainClass {
	List<Product> products = new LinkedList<>();

	class NameException extends Exception {
		public NameException(String message) {
			super(message);
		}
	}

	public boolean validatename(String name) {
		Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z\\s]+$");
		try {
			if (p.matcher(name).matches()) {
				return false;
			}
			if (!(p.matcher(name).matches())) {
				throw new NameException("Name Should not contain numbers or special characters or be null");
			} else {
				return false;
			}
		} catch (NameException e) {
			System.err.println(e.getMessage());
			return true;
		}
	}

	class DescriptionException extends Exception {
		public DescriptionException(String message) {
			super(message);
		}
	}

	public boolean validatedesc(String desc) {
		try {
			if (desc == null || desc.length() == 0) {
				throw new DescriptionException("Description should not be null");
			} else {
				return false;
			}
		} catch (DescriptionException d) {
			System.out.println(d.getMessage());
			return true;
		}
	}

	public boolean validateprice(String price) {
		try {
			Float f = Float.parseFloat(price);
			return false;
		} catch (Exception e) {
			System.err.println("Price must be float int the format xx.xx");
			return true;
		}
	}

	public boolean validatequant(String quant) {
		try {
			int q = Integer.parseInt(quant);
			return false;
		} catch (Exception e) {
			System.err.println("Quantity must be an integer");
			return true;
		}
	}

	private void createProduct(Product p) {
		ProductDaoImpl pdao = new ProductDaoImpl();
		pdao.create(p);
	}
	
	private void updateProduct(Product upp) {
		Scanner u = new Scanner(System.in);
		System.out.printf("%70s\n", "ENTER UPDATES OF PRODUCT " + upp.getName());
		
		System.out.println("Name of product : ");
		String name = u.nextLine();
		while (validatename(name)) {
			System.out.println("Please enter valid Name of Product : ");
			name = u.nextLine();
		}
		
		System.out.println("Description of Product: ");
		String desc = u.nextLine();
		while (validatedesc(desc)) {
			System.out.println("Please enter valid Description : ");
			desc = u.nextLine();
		}
		
		System.out.println("Price of Product : ");
		String price = u.nextLine();
		while (validateprice(price)) {
			System.out.println("Please enter valid Price : ");
			price = u.nextLine();
		}
		
		System.out.println("Quantity of Product : ");
		String quant = u.nextLine();
		while (validatequant(quant)) {
			System.out.println("Please enter valid Quantity : ");
			quant = u.nextLine();
		}
		ProductDaoImpl pdao = new ProductDaoImpl();
		Product p =pdao.find(upp.getId());
		p.setName(name);
		p.setDescription(desc);
		p.setPrice(price);
		p.setQuantity(quant);
		pdao.update(p);
		System.out.printf("%70s\n","UPDATED SUCCESFULLY");
		
	}
	
	
	private void deleteProduct(Product dp) {
		ProductDaoImpl pdao = new ProductDaoImpl();
		pdao.remove(dp);
		System.out.printf("%70s\n","Deleted SUCCESFULLY");
	}
	
	
	public void viewproduct(Product p) {
		System.out.printf("Item id : %-10s\n",p.getId());
		System.out.printf("Item Name : %-10s Item price : %-7s Rs Stock of Item : %-4s Kg\n",p.getName(),String.valueOf(p.getPrice()),String.valueOf(p.getQuantity()));
		System.out.println("Item Description : " + p.getDescription());
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
	}
	
	
	
	public static void main(String args[]) {
		MainClass m = new MainClass();
		ProductDaoImpl pdao = new ProductDaoImpl();
		Scanner sc = new Scanner(System.in);
		int option = 0;
		do {
			System.out.println("\nPress");
			System.out.println("1. Add a product to DataBase");
			System.out.println("2. Update Stock");
			System.out.println("3. Delete a Product");
			System.out.println("4. To view Stock");
			System.out.println("5. To Search a Product");
			System.out.println("6. To Exit");
			System.out.println("Enter your Option");
			int a = sc.nextInt();
			sc.nextLine();
			switch (a) {
			
			
			
			case 1:
				System.out.printf("%70s\n", "ENTER DETAILS OF PRODUCT");
				
				System.out.println("Name of product : ");
				String name = sc.nextLine();
				while (m.validatename(name)) {
					System.out.println("Please enter valid Name of Product : ");
					name = sc.nextLine();
				}
				
				System.out.println("Description of Product: ");
				String desc = sc.nextLine();
				while (m.validatedesc(desc)) {
					System.out.println("Please enter valid Description : ");
					desc = sc.nextLine();
				}
				
				System.out.println("Price of Product : ");
				String price = sc.nextLine();
				while (m.validateprice(price)) {
					System.out.println("Please enter valid Price : ");
					price = sc.nextLine();
				}
				
				System.out.println("Quantity of Product : ");
				String quant = sc.nextLine();
				while (m.validatequant(quant)) {
					System.out.println("Please enter valid Quantity : ");
					quant = sc.nextLine();
				}
				Product p =new Product(name,desc,price,quant);
				m.createProduct(p);
				break;
				
				
				
			case 2:
				System.out.printf("%70s\n","Enter name of the item to update");
				String upname = sc.nextLine();
				List<Product> updates=pdao.findAllNames(upname);
				System.out.println("FOUND PRODUCTS");
				if(updates.isEmpty()) {System.err.println("No Products Found");}
				else {
					int i =0;
					for(Product pr : updates) {
						System.out.println("\nPress " + i + "  to Update\n");
						m.viewproduct(pr);
						i++;
					}
					String in=sc.nextLine();
					Product upp= updates.get(Integer.parseInt(in));
					m.updateProduct(upp);
				}
				break;
				
			case 3:
				System.out.printf("%70s\n","Enter name of the item to Delete");
				String delname = sc.nextLine();
				List<Product> deletes=pdao.findAllNames(delname);
				System.out.println("FOUND PRODUCTS");
				if(deletes.isEmpty()) {System.err.println("No Products Found");}
				else {
					int j =0;
					for(Product pr : deletes) {
						System.out.println("\nPress " + j + "  to Delete\n");
						m.viewproduct(pr);
						j++;
					}
					System.out.println("Enter your Option");
					String jin=sc.nextLine();
					Product dp= deletes.get(Integer.parseInt(jin));
					m.deleteProduct(dp);
				}
				break;
				
			case 4:
				System.out.println(
						"----------------------------------------------------------------------------------------------------");
				System.out.printf("%70s\n", "Stock");
				System.out.println(
						"----------------------------------------------------------------------------------------------------");
				System.out.printf("%-13s%-20s%-10s%-15s\n\n", "ItemID", "Item Name", "Quantity", "Price");
				List<Product> searchall = pdao.findall();
				for (Product pro : searchall) {
					m.viewproduct(pro);
				}
				System.out.println(
						"=======================================================================================================");
				break;
			
			case 5:
				System.out.printf("%70s\n","Enter name of the item to Search");
				String seaname = sc.nextLine();
				List<Product> search=pdao.findAllNames(seaname);
				System.out.println("FOUND PRODUCTS");
				if(search.isEmpty()) {System.err.println("No Products Found");}
				else {
					for(Product pr : search) {
						m.viewproduct(pr);
					}
				}
				break;
			case 6:
				option = -1;
				break;
			default:
				System.out.println("Please Enter valid option");
			}
		} while (option != -1);
	}
}
