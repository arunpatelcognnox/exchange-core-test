package com.arun.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exchange.core2.core.ExchangeApi;
import exchange.core2.core.common.api.ApiAddUser;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

	private ExchangeApi exchangeApi;
	
	@PostMapping("/{id}")
	public Object save(@PathVariable long id) {
		return exchangeApi.submitCommandAsync(ApiAddUser.builder().uid(id).build());
	}
	
}
