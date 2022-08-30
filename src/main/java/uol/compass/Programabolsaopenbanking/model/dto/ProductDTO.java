package uol.compass.Programabolsaopenbanking.model.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import uol.compass.Programabolsaopenbanking.model.Product;

	public class ProductDTO {

	private Long id;
	@NotNull(message = "O nome é obrigatório")
	@NotEmpty(message = "O nome é obrigatório")
	private String name;
	@NotNull(message = "A descrição é obrigatória")
	@NotEmpty(message = "A descrição é obrigatória")
	private String description;
	@NotNull(message = "O preço é obrigatório")
	@Positive(message = "Não é permitido valores negativos")
	private double price;
	
	public static ProductDTO mapperToProductDTO(Product product) {
	    return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
	}
	
	public ProductDTO() {}
	
	public ProductDTO(Long id, String name, String description, double price) {
	    this.id = id;
	    this.name = name;
	    this.description = description;
	    this.price = price;
	}
	
	public ProductDTO(String name, String description, double price) {
	    this.name = name;
	    this.description = description;
	    this.price = price;
	}
	
	public Long getId() {
	    return id;
	}
	
	public void setId(Long id) {
	    this.id = id;
	}
	
	public String getName() {
	    return name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}
	
	public String getDescription() {
	    return description;
	}
	
	public void setDescription(String description) {
	    this.description = description;
	}
	
	public double getPrice() {
	    return price;
	}
	
	public void setPrice(double price) {
	    this.price = price;
	}
	
	}
