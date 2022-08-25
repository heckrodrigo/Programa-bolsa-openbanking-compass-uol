package uol.compass.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import uol.compass.Programabolsaopenbanking.exception.ProductNotFoundException;
import uol.compass.Programabolsaopenbanking.service.ProductService;
import uol.compass.test.utils.TestConstants;



	@WebMvcTest
	class ProductControllerTest {

	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private ProductService productService;

	    @Test
	    void testGetProductsSuccess() throws Exception {
	        this.mockMvc.perform(get("/products")).andExpect(MockMvcResultMatchers.status().isOk());
	    }

	    @Test
	    void testGetOneProductNotFound() throws Exception {
	        when(productService.getOneProduct(10L)).thenThrow(new ProductNotFoundException("product not found"));
	        this.mockMvc.perform(get("/products/10")).andExpect(MockMvcResultMatchers.status().isNotFound());
	    }
	}

