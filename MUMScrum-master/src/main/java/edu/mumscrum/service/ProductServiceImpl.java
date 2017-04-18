package edu.mumscrum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.mumscrum.domain.Product;
import edu.mumscrum.repository.ProductRepository;

@Component("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product save(Product p) {
		return productRepository.save(p);
	}

	@Override
	public Product findById(long id) {
		return productRepository.findOne(id);
	}

}
