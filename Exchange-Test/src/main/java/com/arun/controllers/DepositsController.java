package com.arun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exchange.core2.core.ExchangeApi;
import exchange.core2.core.common.api.ApiAdjustUserBalance;

@RestController
@RequestMapping("/api/deposits")
public class DepositsController {

	@Autowired private ExchangeApi exchangeApi;
	private int id=0;
	
	@PostMapping("/userId/{userId}/currencyCodeLtc/{currencyCodeLtc}/amount/{amount}")
	public Object deposits(@PathVariable int userId, @PathVariable int currencyCodeLtc, @PathVariable long amount ) {
		return exchangeApi.submitCommandAsync(ApiAdjustUserBalance.builder()
		        .uid(userId)
		        .currency(currencyCodeLtc)
		        .amount(amount)
		        .transactionId(id++)
		        .build());
	}
	
}
