package uol.compass.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import uol.compass.Programabolsaopenbanking.repository.ProductRepository;
import uol.compass.Programabolsaopenbanking.service.ProductService;
import uol.compass.test.utils.TestConstants;



@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void testGetProductSuccess() {
        var product = TestCreator.createProduct();
        when(productRepository.findById(TestConstants.DEFAULT_PRODUCT_ID)).thenReturn(Optional.of(product));

        var productDTO = productService.getOneProduct(TestConstants.DEFAULT_PRODUCT_ID);

        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getDescription(), productDTO.getDescription());
        assertEquals(product.getPrice(), productDTO.getPrice());

        verify(productRepository, times(1)).findById(any());
    }



}
