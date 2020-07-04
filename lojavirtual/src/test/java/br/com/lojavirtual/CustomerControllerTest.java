package br.com.lojavirtual;

import br.com.lojavirtual.controller.CustomersController;
import br.com.lojavirtual.model.Customers;
import br.com.lojavirtual.service.CustomersService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CustomerControllerTest  extends LojaVirtualApplicationTests{


private MockMvc mockMvc;
	
	@Autowired
	private CustomersController customersController;
	
	@Autowired
	private CustomersService customersService;
	
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(customersController).build();
	}
	
	@Test
	public void testGetCustomer() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/virtualstore/customers")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPostCustomer() throws Exception {
		Customers customer = (Customers) customersService.save(new Customers(null, "Danilo", "(81)3525-3095", "teste@mail.com", "123.456.789-00", "25", "12-04-1995", null, null, null, null, null, null, null));
		this.mockMvc.perform(MockMvcRequestBuilders.post("/virtualstore/customers/"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/virtualstore/customers/" + customer.getIdCostumer()));
	}
	

	@Test
	public void testPutCustomer() throws Exception {
		Customers customer = (Customers) customersService.save(new Customers(Long.valueOf(1), "Danilo", "(81)3525-3095", "teste@mail.com", "123.456.789-00", "25", "12-04-1995", "Rua Frei Caneca", null, null, null, null, null, null));
		this.mockMvc.perform(MockMvcRequestBuilders.put("/virtualstore/customers/" + customer.getIdCostumer()))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/virtualstore/customers/" + customer.getIdCostumer()));
	}


}
