package uol.compass.Programabolsaopenbanking.controller;


import java.util.List;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uol.compass.Programabolsaopenbanking.model.Product;
import uol.compass.Programabolsaopenbanking.model.dto.ProductDTO;
import uol.compass.Programabolsaopenbanking.service.ProductService;




@RestController
@RequestMapping("/products")
@Api(value = "Product")
public class ProductController {

    private final ProductService productService;
    
    public ProductController(ProductService productService) { 
    	this.productService = productService;
       
    }  
   
	// Método GET ALL
	@ApiOperation("Listar todos os produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de todos os produtos"),
			@ApiResponse(code = 400, message = "Entrada inválida"),
			@ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação") })
	// início do método
	@GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
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
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.CREATED);
    } 
	 
	 	 
    
	// Método GET search maior preço max_price
	@ApiOperation("Filtrar produtos pelo parâmetro max_price")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o produto de acordo com o(s) parâmetro(s) passado(s)"),
			@ApiResponse(code = 400, message = "Entrada inválida"),
			@ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação") })
	// início do método
	@GetMapping(value = "/search/max_price")

	public ResponseEntity<List<Product>> getProductByGreaterThan(@RequestParam double price) {
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
	public ResponseEntity<List<Product>> getProductByLessThan(@RequestParam double price) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceLessThan(price));

	}
 
	// Método GET ID
	@ApiOperation("Retorna um produto específico pelo id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o produto"),
			@ApiResponse(code = 404, message = "Prodoto não encontrado"),
			@ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação") })
	// início do método
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOneProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getOneProduct(id));
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
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProduct(productDTO, id));
    }
    
	 @ApiOperation("Remove um produto específico pelo id")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "O produto é removido"),
	            @ApiResponse(code = 404, message = "Produto não encontrado"),
	            @ApiResponse(code = 500, message = "Ocorreu alguma exceção na aplicação")
	    })
	// início do método
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");
    }
  
 
    
    
}