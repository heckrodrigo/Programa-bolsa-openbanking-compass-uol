package uol.compass.Programabolsaopenbanking.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import uol.compass.Programabolsaopenbanking.exception.ObjectNotFoundException;
import uol.compass.Programabolsaopenbanking.model.Product;
import uol.compass.Programabolsaopenbanking.model.dto.ProductDTO;
import uol.compass.Programabolsaopenbanking.repository.ProductRepository;





@Service
@Transactional
public class ProductService {
    public static final String MESSAGE_PRODUCT_NOT_FOUND = "Produto não encontrado.";

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    
    // Método que busca todos os produtos
    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(ProductDTO::mapperToProductDTO)
                .collect(Collectors.toList());
    }

    // Método que salva um novo produto na lista
    public ProductDTO saveProduct(ProductDTO productDTO) {
        var productPersisted =  repository.save(Product.mapperToProduct(productDTO));

        return ProductDTO.mapperToProductDTO(productPersisted);
    }
    
    // Método que busca o produto pelo do ID
    public ProductDTO getOneProduct(Long id) throws ObjectNotFoundException {
        return ProductDTO.mapperToProductDTO(repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(MESSAGE_PRODUCT_NOT_FOUND)));
    }

    // Método que atualiza os dados de um produto na lista
    public ProductDTO updateProduct(ProductDTO productDTO, Long id) throws ObjectNotFoundException {
        var findedProduct = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(MESSAGE_PRODUCT_NOT_FOUND));

        findedProduct.setName(productDTO.getName());
        findedProduct.setDescription(productDTO.getDescription());
        findedProduct.setPrice(productDTO.getPrice());

        return ProductDTO.mapperToProductDTO(repository.save(findedProduct));
    }

    // Método que deleta um produto
    public void deleteProduct(Long id) throws ObjectNotFoundException {
        var findedProduct = repository.findById(id).orElseThrow(() -> new uol.compass.Programabolsaopenbanking.exception.ObjectNotFoundException(MESSAGE_PRODUCT_NOT_FOUND));

        repository.delete(findedProduct);
    }
    
    
	// Método que busca o produto listando através do maior preço
	public List findByPriceGreaterThan(double price) throws ObjectNotFoundException{
		return repository.findByPriceGreaterThan(price);
	}

	
	// Método que busca o produto listando através do menor preço
	public List findByPriceLessThan(double price) {
		return repository.findByPriceLessThan(price);
	}

	public List<Product> search(String name) {
		
		return repository.search(name);
	}
	
	
	
	}
	
	
	


