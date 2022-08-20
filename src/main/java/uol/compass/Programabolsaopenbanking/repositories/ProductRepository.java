package uol.compass.Programabolsaopenbanking.repositories;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uol.compass.Programabolsaopenbanking.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Id> {

	Optional<ProductModel> findById(long id);
	
	List<ProductModel> findByPriceGreaterThan (BigDecimal priceBigDecimal);
	List<ProductModel> findByPriceLessThan (BigDecimal priceBigDecimal);

	

	

			
	
	
	
}