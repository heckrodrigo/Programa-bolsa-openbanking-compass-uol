package uol.compass.Programabolsaopenbanking.dtos;

import java.math.BigDecimal;



import javax.validation.constraints.NotBlank;


public class ProductDto {

	private long id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	private BigDecimal price;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
}


