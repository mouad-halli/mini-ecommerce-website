package org.upec.ecommerce.utils;

import java.util.ArrayList;

public class Repository {
	static public ArrayList<Product> myDataBase = new ArrayList<Product>();
	
	static {
		myDataBase.add(new Product(1, "PC Intel", "Informatique", 700, 20));
		myDataBase.add(new Product(2, "Tablette iPad", "Informatique", 1100, 20));
		myDataBase.add(new Product(3, "Marteau", "Bricolage", 10, 20));
		myDataBase.add(new Product(4, "Scie", "Bricolage", 50, 20));
		myDataBase.add(new Product(5, "Bibliothèque", "Meuble", 200, 20));
		myDataBase.add(new Product(6, "Table basse", "Meuble", 500, 20));
	}
}
