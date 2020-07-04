package br.com.lojavirtual;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.lojavirtual.controller.OrdersController;
import br.com.lojavirtual.model.Orders;
import br.com.lojavirtual.service.OrdersService;

public class OrderControllerTest  extends LojaVirtualApplicationTests{
	
	private MockMvc mockMvc;

	@Autowired
	private OrdersController ordersController;

	@Autowired
	private OrdersService ordersService;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ordersController).build();
	}
	
	@Test
	public void testGetOrder() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/virtualstore/orders")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	

	@Test
	public void testPosOrder() throws Exception {
		Orders order = (Orders) ordersService.save(new Orders(null,
				Long.valueOf(1), "8594849", null, "Danilo", "Danilo@gmail.com", "123.456.789-00",
				"Danilo Souza", "20,00R$", "50,00R$", "03-07-2020", "15 dias"));
		this.mockMvc.perform(MockMvcRequestBuilders.post("/virtualstore/orders/"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/virtualstore/orders/" + order.getIdOrder()));
	}
	
	@Test
	public void testPutOrder() throws Exception {
		Orders order = (Orders) ordersService.save(new Orders(Long.valueOf(1),
				Long.valueOf(1), "8594849", null, "Danilo", "Danilo@gmail.com", "123.456.789-00",
				"Danilo Souza", "20,00R$", "50,00R$", "03-07-2020", "15 dias"));
		this.mockMvc.perform(MockMvcRequestBuilders.put("/virtualstore/orders/"+ order.getIdOrder()))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/virtualstore/orders/" + order.getIdOrder()));
	}

}
