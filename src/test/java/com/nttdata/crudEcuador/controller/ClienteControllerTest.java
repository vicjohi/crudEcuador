package com.nttdata.crudEcuador.controller;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.crudEcuador.model.Cliente;
import com.nttdata.crudEcuador.service.ClienteService;

@RunWith(MockitoJUnitRunner.class)
class ClienteControllerTest {
	private MockMvc mockMvc;

	  @InjectMocks
	  private ClienteController clienteController;
	  
	  @Mock
	  ClienteService clienteService;
	  
	  /**
	   * @throws java.lang.Exception
	   */
	  @BeforeEach
	  public void setUp() throws Exception {
	    MockitoAnnotations.openMocks(this);
	    this.mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
	  }

	  @Test
	  void crearClientes() throws Exception{
		  System.out.println("por que");
			Cliente cliente = new Cliente();
			cliente.setContrasenia("val");
			
			UUID id = UUID.randomUUID();			
			
			Cliente result = new Cliente();
			result.setContrasenia("val");
			result.setClienteid(id);
			
			Mockito.when(clienteService.saveCliente(Mockito.any(Cliente.class))).thenReturn(result);
			
			this.mockMvc
	        .perform(post("/add-user").content(asJsonString(cliente)).contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk()).andDo(print())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(notNullValue())))
	        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(equalTo(id.toString()))))
	        .andReturn();
			
	  }
	  static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
