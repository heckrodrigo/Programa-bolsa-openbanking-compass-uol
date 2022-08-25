package uol.compass.test;

import uol.compass.Programabolsaopenbanking.model.Product;
import uol.compass.Programabolsaopenbanking.model.dto.ProductDTO;

public final class TestCreator {

    private static final String DEFAULT_PRODUCT_NAME = null;
	private static final String DEFAULT_PRODUCT_DESCRIPTION = null;
	private static final double DEFAULT_PRODUCT_PRICE = 0;

	private TestCreator() {}

    public static ProductDTO createProductDTO() {
        return new ProductDTO(DEFAULT_PRODUCT_NAME, DEFAULT_PRODUCT_DESCRIPTION, DEFAULT_PRODUCT_PRICE);
    }

    public static Product createProduct() {
        return new Product(DEFAULT_PRODUCT_NAME, DEFAULT_PRODUCT_DESCRIPTION, DEFAULT_PRODUCT_PRICE);
    }
}
