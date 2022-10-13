package com.system.pagos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.pagos.domain.PagosDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class SyspagosApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void getallPagos() throws Exception {
		String uri = "/v1/pagos/allPagos";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
			.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		log.info("Resultado :: {}", mvcResult.getResponse().getStatus());
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


	@Test
	void createPago() throws Exception {
		String uri = "/v1/pagos";
		PagosDTO pago = new PagosDTO();
		pago.setClientePago("TEST");
		pago.setEstatusPago("ACTIVO");
		pago.setMontoPago(100.20);
		pago.setUsuarioMovimiento("ADMIN");
		pago.setCantidad(10);
		pago.setConcepto("PAGO PRESTAMOS");
		String inputJson = mapToJson(pago);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
			.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}



	@Test
	void updateStatus() throws Exception {
		String uri = "/v1/pagos";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.param("idPago", "11")
			.param("statusPago", "TESTING.....")
		).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	void getStatusPago() throws Exception {
		String uri = "/v1/pagos/status/cvePago/11";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
		).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	void getPago() throws Exception {
		String uri = "/v1/pagos/cvePago/11";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
		).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


	public String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

}
