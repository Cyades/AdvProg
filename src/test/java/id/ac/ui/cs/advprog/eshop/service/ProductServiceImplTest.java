package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllIfEmpty() {
        List<Product> products = productService.findAll();
        assertTrue(products.isEmpty());
    }

    @Test
    void testCreateAndFindAll() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        List<Product> allProducts = productService.findAll();
        assertEquals(1, allProducts.size());
    }

    @Test
    void testCreateMultipleAndFindAll() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productService.create(product1);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Hipos");
        product2.setProductQuantity(200);
        productService.create(product2);

        Product product3 = new Product();
        product3.setProductName("Sampo Cap WumWum");
        product3.setProductQuantity(300);
        productService.create(product3);

        List<Product> allProducts = productService.findAll();
        assertEquals(3, allProducts.size());
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        productService.delete(product.getProductId());
        List<Product> allProducts = productService.findAll();
        assertEquals(0, allProducts.size());
    }

    @Test
    void testDeleteIfMoreThanOne() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productService.create(product1);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Hipos");
        product2.setProductQuantity(200);
        productService.create(product2);

        Product product3 = new Product();
        product3.setProductName("Sampo Cap WumWum");
        product3.setProductQuantity(300);
        productService.create(product3);

        // Verify
        List<Product> allProducts = productService.findAll();
        assertEquals(3, allProducts.size());
        productService.delete(product2.getProductId());
        allProducts = productService.findAll();
        assertEquals(2, allProducts.size());
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        Product productUpdate = new Product();
        productUpdate.setProductId(product.getProductId());
        productUpdate.setProductName("Sampo Cap Hipos");
        productUpdate.setProductQuantity(200);
        productService.update(productUpdate);

        List<Product> allProducts = productService.findAll();
        Product newProduct = allProducts.getFirst();

        assertEquals(newProduct.getProductName(), productUpdate.getProductName());
        assertEquals(newProduct.getProductQuantity(), productUpdate.getProductQuantity());
    }

    @Test
    void testFindById() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productService.create(product1);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Hipos");
        product2.setProductQuantity(200);
        productService.create(product2);

        Product product3 = new Product();
        product3.setProductName("Sampo Cap WumWum");
        product3.setProductQuantity(300);
        productService.create(product3);

        Product returnedProduct = productService.findById(product2.getProductId());

        // Verify
        assertEquals(product2.getProductName(), returnedProduct.getProductName());
    }
}