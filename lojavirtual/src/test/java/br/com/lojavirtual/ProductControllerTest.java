package br.com.lojavirtual;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.lojavirtual.controller.ProductsController;
import br.com.lojavirtual.model.Products;
import br.com.lojavirtual.service.ProductsService;

public class ProductControllerTest extends LojaVirtualApplicationTests {
	
    private MockMvc mockMvc;
	
	@Autowired
	private ProductsController productsController;
	
	@Autowired
	private ProductsService productsService;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
	}
	
	@Test
	public void testGetProduct() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/virtualstore/products")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	

	@Test
	public void testPostProduct() throws Exception {
		Products products = (Products) productsService.save(new Products(null, null, "8574584", "Apple", "Iphone X", "5.000,00R$"));
		this.mockMvc.perform(MockMvcRequestBuilders.post("/virtualstore/products/"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/virtualstore/products/" + products.getIdProduct()));
	}
	
	@Test
	public void testPutProduct() throws Exception {
		Products products = (Products) productsService.save(new Products(Long.valueOf(1), Long.valueOf(23), "8574584", "Apple", "Iphone X", "5.000,00R$"));
		this.mockMvc.perform(MockMvcRequestBuilders.put("/virtualstore/products/"+ products.getIdProduct()))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/virtualstore/products/" + products.getIdProduct()));
	}

}
