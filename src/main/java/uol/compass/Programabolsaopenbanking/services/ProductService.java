package uol.compass.Programabolsaopenbanking.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import uol.compass.Programabolsaopenbanking.models.ProductModel;
import uol.compass.Programabolsaopenbanking.repositories.ProductRepository;

@Service
public class ProductService {

	final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;

	}

	@Transactional
	public Object save(ProductModel productModel) {
		return productRepository.save(productModel);

	}

	public List<ProductModel> findAll() {

		return productRepository.findAll();
	}

	public Optional<ProductModel> findById(long id) {

		return productRepository.findById(id);
	}

	@Transactional
	public void delete(ProductModel productModel) {
		productRepository.delete(productModel);

	}

	public List findByPriceGreaterThan(BigDecimal price) {
		return productRepository.findByPriceGreaterThan(price);
	}

	public List findByPriceLessThan(BigDecimal price) {
		return productRepository.findByPriceLessThan(price);
	}

	
	
	

}
