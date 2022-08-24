package uol.compass.Programabolsaopenbanking.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uol.compass.Programabolsaopenbanking.dtos.ProductDto;
import uol.compass.Programabolsaopenbanking.models.ProductModel;
import uol.compass.Programabolsaopenbanking.resources.exceptions.DataIntegrityException;
import uol.compass.Programabolsaopenbanking.resources.exceptions.StandardError;
import uol.compass.Programabolsaopenbanking.services.ProductService;

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
	 @ApiOperation("Salva um produto")
	    @ApiResponses(value = {
	            @ApiResponse(code = 201, message = "Retorna o novo produto cadastrado"),
	            @ApiResponse(code = 400, message = "Entrada inválida"),
	            @ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação")
	    })
	 //Início do método 
	@PostMapping
	public ResponseEntity<Object> saveProduct(@RequestBody @Validated ProductDto productDto) {

		var productModel = new ProductModel();
		// converter DTO em Model
		BeanUtils.copyProperties(productDto, productModel);
		//
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));

	}

	// Método GET ALL
	@ApiOperation("Listar todos os produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de todos os produtos"),
			@ApiResponse(code = 400, message = "Entrada inválida"),
			@ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação") })
	//Início do método
	@GetMapping
	public ResponseEntity<List<ProductModel>> getAllProducts() {

		return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());

	}
	// Método GET search maior preço max_price
	@ApiOperation("Filtrar produtos pelo parâmetro max_price")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o produto de acordo com o(s) parâmetro(s) passado(s)"),
			@ApiResponse(code = 400, message = "Entrada inválida"),
			@ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação") })
	// início do método
	@GetMapping(value = "/search/max_price")

	public ResponseEntity<List<ProductModel>> getProductByGreaterThan(@RequestParam double price) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceGreaterThan(price));

	}
	// Método GET search menor preço min_price
	@ApiOperation("Filtrar produtos pelo parâmetro min_price")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o produto de acordo com o(s) parâmetro(s) passado(s)"),
			@ApiResponse(code = 400, message = "Entrada inválida"),
			@ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação") })
	// início do método
	@GetMapping(value = "/search/min_price")
	public ResponseEntity<List<ProductModel>> getProductByLessThan(@RequestParam double price) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceLessThan(price));

	}
	
	// Método GET ID
	@ApiOperation("Retorna um produto específico pelo id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o produto"),
			@ApiResponse(code = 404, message = "Prodoto não encontrado"),
			@ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação") })
	// início do método
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		try {
			ProductModel objModel = productService.findById(id);
			return ResponseEntity.ok().body(objModel);
		}
		// tratando exceção 500 - converter para 400
		catch (DataIntegrityException e) {
			StandardError err = new StandardError();
			err.setStatus_code(HttpStatus.NOT_FOUND.value());
			err.setMessage(e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

		}

	}
	
	// Método PUT - Atualizar
    @ApiOperation("Atualiza um produto")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna dados do produto atualizado"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
            @ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação")
    })
    // início do método
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProductModel(@PathVariable(value = "id") long id,
			@RequestBody @Validated ProductDto productDto) {

		try {
			Optional<ProductModel> productModelOptional = Optional.ofNullable(productService.findById(id));

			var productModel = productModelOptional.get();
			productModel.setName(productDto.getName());
			productModel.setDescription(productDto.getDescription());
			productModel.setPrice(productDto.getPrice());

			return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));

		}

		catch (DataIntegrityException e) {
			StandardError err = new StandardError();
			err.setStatus_code(HttpStatus.NOT_FOUND.value());
			err.setMessage(e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}

	}

	// Método delete
	 @ApiOperation("Remove um produto específico pelo id")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "O produto é removido"),
	            @ApiResponse(code = 404, message = "Produto não encontrado"),
	            @ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação")
	    })
	// início do método
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") long id) {
		try {
			Optional<ProductModel> productModelOptional = Optional.ofNullable(productService.findById(id));
			productService.delete(productModelOptional.get());
		}

		catch (DataIntegrityException e) {
			StandardError err = new StandardError();
			err.setStatus_code(HttpStatus.NOT_FOUND.value());
			err.setMessage(e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}

		return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");

	}

}