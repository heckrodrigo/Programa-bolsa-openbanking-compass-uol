package uol.compass.Programabolsaopenbanking.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import uol.compass.Programabolsaopenbanking.models.ProductModel;
import uol.compass.Programabolsaopenbanking.repositories.ProductRepository;
import uol.compass.Programabolsaopenbanking.services.exceptions.*;




@Service
public class ProductService {
	
	// Injeção de dependências do Repository
	
	@Autowired
	private ProductRepository productRepository;
	
	

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;

	}

	// Método que salva um produto no banco atráves do POST
	@Transactional
	public Object save(ProductModel productModel) {
		return productRepository.save(productModel);

	}

	// Método de busca buscar todos - findAll
	public List<ProductModel> findAll() {

		return productRepository.findAll();
	}

	

	// Método de busca buscar por ID - findById
	public ProductModel findById(Long id) {
		
		return productRepository.findById(id).orElseThrow(
				()-> new  EntityNotFoundException("ID " +id+ " não existente." ));
		
		
	}
	

	
	// Método que deleta um produto atráves de ID
	@Transactional
	public void delete(ProductModel productModel) {
		productRepository.delete(productModel);

	}

	// Método que busca o produto listando através do maior preço
	public List findByPriceGreaterThan(BigDecimal price) {
		return productRepository.findByPriceGreaterThan(price);
	}

	// Método que busca o produto listando através do menor preço
	public List findByPriceLessThan(BigDecimal price) {
		return productRepository.findByPriceLessThan(price);
	}


}