package uol.compass.Programabolsaopenbanking.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import uol.compass.Programabolsaopenbanking.model.Product;



public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	List<Product> findByPriceGreaterThan (double price);
	List<Product> findByPriceLessThan (double price);
	
	
}

