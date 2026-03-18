package com.cora.api.controller;

import com.cora.api.model.Account;
import com.cora.api.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	@BeforeEach
	public void setup() {}

	@Test
	public void testCreateAccount() throws Exception {
		Account newAccount = new Account("Adonias Vitorio", "12345678901");
		when(accountService.createAccount("Adonias Vitorio", "12345678901")).thenReturn(newAccount);

		mockMvc.perform(post("/accounts")
			.contentType("application/json")
			.content(new ObjectMapper().writeValueAsString(newAccount)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.name").value("Adonias Vitorio"))
			.andExpect(jsonPath("$.cpf").value("12345678901"));
	}

	@Test
	public void testListAccounts() throws Exception {
		Account account = new Account("Adonias Vitorio", "12345678901");
		when(accountService.listAccounts()).thenReturn(java.util.Collections.singletonList(account));

		mockMvc.perform(get("/accounts"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name").value("Adonias Vitorio"))
			.andExpect(jsonPath("$[0].cpf").value("12345678901"));
	}
}