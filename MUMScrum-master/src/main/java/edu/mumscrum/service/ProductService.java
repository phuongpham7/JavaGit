package edu.mumscrum.service;

import java.util.List;

import edu.mumscrum.domain.Product;

public interface ProductService {

	List<Product> getAllProducts();
	Product save(Product p);
	Product findById(long id);
}
