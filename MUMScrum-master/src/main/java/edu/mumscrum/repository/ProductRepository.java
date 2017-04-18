package edu.mumscrum.repository;

import org.springframework.data.repository.CrudRepository;

import edu.mumscrum.domain.Product;

public interface ProductRepository extends CrudRepository<Product,Long>{

	
}
