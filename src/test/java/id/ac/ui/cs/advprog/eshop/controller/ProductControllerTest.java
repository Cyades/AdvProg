package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureJsonTesters
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    @Autowired
    private JacksonTester<Product> jsonProduct;

    @Test
    void testProductListPage() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/product/list"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testCreateProductPage() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/product/create"))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Mockito.when(service.findById(product.getProductId())).thenReturn(product);

        MockHttpServletResponse response = mockMvc.perform(get("/product/edit/" + product.getProductId()))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        String json = jsonProduct.write(product).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                post("/product/create")
                        .contentType("application/json")
                        .content(json)
        ).andReturn().getResponse();

        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }

    @Test
    void testEditProduct() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Castorice");
        updatedProduct.setProductQuantity(420);

        String updatedJson = jsonProduct.write(updatedProduct).getJson();

        MockHttpServletResponse response = mockMvc.perform(
                post("/product/edit")
                        .contentType("application/json")
                        .content(updatedJson)
        ).andReturn().getResponse();
        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }

    @Test void testDeleteProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Tribbie");
        product.setProductQuantity(500);

        MockHttpServletResponse response = mockMvc.perform(
                post("/product/delete/" + product.getProductId())
        ).andReturn().getResponse();

        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }
}