package uol.compass.Programabolsaopenbanking.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;

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

import uol.compass.Programabolsaopenbanking.dtos.ProductsDto;
import uol.compass.Programabolsaopenbanking.models.ProductModel;
import uol.compass.Programabolsaopenbanking.services.ProductService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")

public class ProductController {

	final ProductService productService;

	public ProductController(ProductService productService) {

		this.productService = productService;
	}

	// Método POST

	@PostMapping
	public ResponseEntity<Object> saveProduct(@RequestBody ProductsDto productsDto) {
		var productModel = new ProductModel();
		// converter DTO em Model
		BeanUtils.copyProperties(productsDto, productModel);
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
	public ResponseEntity<List<ProductModel>> getProductByGreaterThan(@RequestParam BigDecimal price) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceGreaterThan(price));

	}

	// Método GET search menor preço min_price
	@GetMapping(value = "/search/min_price")
	public ResponseEntity<List<ProductModel>> getProductByLessThan(@RequestParam BigDecimal price) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceLessThan(price));

	}

	// Método GET ID
	@GetMapping("{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") long id) {
		Optional<ProductModel> productModelOptional = productService.findById(id);
		// Verifica se o ID é existente
		if (!productModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não existente.");

		}

		return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());

	}

	// Método PUT - Atualizar

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProductModel(@PathVariable(value = "id") long id,
			@RequestBody @Valid ProductsDto productsDto) {

		Optional<ProductModel> productModelOptional = productService.findById(id);
		if (!productModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");

		}

		var productModel = productModelOptional.get();
		productModel.setName(productsDto.getName());
		productModel.setDescription(productsDto.getDescription());
		productModel.setPrice(productsDto.getPrice());

		return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));

	}

	// Método delete

	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") long id) {
		Optional<ProductModel> productModelOptional = productService.findById(id);
		// Verifica se o ID é existente
		if (!productModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produdo não existente.");

		}

		productService.delete(productModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");

	}

}
