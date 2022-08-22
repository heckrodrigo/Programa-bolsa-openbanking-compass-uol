package uol.compass.Programabolsaopenbanking.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uol.compass.Programabolsaopenbanking.dtos.ProductDto;
import uol.compass.Programabolsaopenbanking.models.ProductModel;
import uol.compass.Programabolsaopenbanking.resources.exceptions.StandarError;
import uol.compass.Programabolsaopenbanking.services.ProductService;
import uol.compass.Programabolsaopenbanking.services.exceptions.EntityNotFoundException;

@RestController
// Permite ser acessado de qualquer fonte
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")

public class ProductController {
	
	// Ponto de injeção para ProductService
	@Autowired
	private ProductService productService;

	
	public ProductController(ProductService productService) {

		this.productService = productService;
	}

	
	// Método POST

	@PostMapping
	public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto) {
				
		var productModel = new ProductModel();
		// converter DTO em Model
		BeanUtils.copyProperties(productDto, productModel);
		//
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));

	}

	// Método GET ALL

	@GetMapping
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());

	}

	// Método GET search maior preço max_price
	@GetMapping(value = "/search/max_price")

	public ResponseEntity<List<ProductModel>> getProductByGreaterThan(@RequestParam double price) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceGreaterThan(price));

	}

	// Método GET search menor preço min_price
	@GetMapping(value = "/search/min_price")
	public ResponseEntity<List<ProductModel>> getProductByLessThan(@RequestParam double price) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceLessThan(price));

	}
	


	// Método GET ID
	@GetMapping("{id}")
	public ResponseEntity<?> findById (@PathVariable Long id){
		
		try {
		ProductModel objModel= productService.findById(id);
		return ResponseEntity.ok().body(objModel);
		}
		//tratando exceção 500 - converter para 400
		catch (EntityNotFoundException e) {
			StandarError err = new StandarError();
			err.setStatus_code(HttpStatus.NOT_FOUND.value());
			err.setMessage(e.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
			
		}
		
		
	}
	
	// Método PUT - Atualizar

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProductModel(@PathVariable(value = "id") long id,
			@RequestBody @Valid ProductDto productDto) {
		
		try {
		Optional<ProductModel> productModelOptional = Optional.ofNullable(productService.findById(id));
		

		var productModel = productModelOptional.get();
		productModel.setName(productDto.getName());
		productModel.setDescription(productDto.getDescription());
		productModel.setPrice(productDto.getPrice()); 
		
		return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
		
		}
		
		catch (EntityNotFoundException e) {
			StandarError err = new StandarError();
			err.setStatus_code(HttpStatus.NOT_FOUND.value());
			err.setMessage(e.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}

		

	}

	// Método delete

	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") long id) {
		try {
		Optional<ProductModel> productModelOptional = Optional.ofNullable(productService.findById(id));
		productService.delete(productModelOptional.get());	
		}

		catch (EntityNotFoundException e) {
			StandarError err = new StandarError();
			err.setStatus_code(HttpStatus.NOT_FOUND.value());
			err.setMessage(e.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");

	}

	
	



}
