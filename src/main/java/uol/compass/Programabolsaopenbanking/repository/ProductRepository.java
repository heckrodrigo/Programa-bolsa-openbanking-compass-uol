package uol.compass.Programabolsaopenbanking.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uol.compass.Programabolsaopenbanking.model.Product;



public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "select u from Product u where u.name like %?1%")
	List<Product> search(String name);
	
	
	List<Product> findByPriceGreaterThan (double price);
	List<Product> findByPriceLessThan (double price);


	
	
	
}

