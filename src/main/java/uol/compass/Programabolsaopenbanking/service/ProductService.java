package uol.compass.Programabolsaopenbanking.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import uol.compass.Programabolsaopenbanking.exception.ProductNotFoundException;
import uol.compass.Programabolsaopenbanking.model.Product;
import uol.compass.Programabolsaopenbanking.model.dto.ProductDTO;
import uol.compass.Programabolsaopenbanking.repository.ProductRepository;





@Service
@Transactional
public class ProductService {
    public static final String MESSAGE_PRODUCT_NOT_FOUND = "Product not found";

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(ProductDTO::mapperToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        var productPersisted =  repository.save(Product.mapperToProduct(productDTO));

        return ProductDTO.mapperToProductDTO(productPersisted);
    }

    public ProductDTO getOneProduct(Long id) {
        return ProductDTO.mapperToProductDTO(repository.findById(id).orElseThrow(() -> new ProductNotFoundException(MESSAGE_PRODUCT_NOT_FOUND)));
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        var findedProduct = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(MESSAGE_PRODUCT_NOT_FOUND));

        findedProduct.setName(productDTO.getName());
        findedProduct.setDescription(productDTO.getDescription());
        findedProduct.setPrice(productDTO.getPrice());

        return ProductDTO.mapperToProductDTO(repository.save(findedProduct));
    }

    public void deleteProduct(Long id) {
        var findedProduct = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(MESSAGE_PRODUCT_NOT_FOUND));

        repository.delete(findedProduct);
    }
    
    
	// Método que busca o produto listando através do maior preço
	public List findByPriceGreaterThan(double price) {
		return repository.findByPriceGreaterThan(price);
	}

	
	// Método que busca o produto listando através do menor preço
	public List findByPriceLessThan(double price) {
		return repository.findByPriceLessThan(price);
	}

}
