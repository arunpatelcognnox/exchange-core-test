package com.arun.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exchange.core2.core.ExchangeApi;
import exchange.core2.core.common.CoreSymbolSpecification;
import exchange.core2.core.common.SymbolType;
import exchange.core2.core.common.api.binary.BatchAddSymbolsCommand;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/symbol")
@AllArgsConstructor
public class CreateSymbol {

	private ExchangeApi exchangeApi;
	
	@PostMapping("/{currencyCodeXbt}/{currencyCodeLtc}/{symbolXbtLtc}")
	public Object saveSymbol(@PathVariable int currencyCodeXbt, @PathVariable int currencyCodeLtc, @PathVariable int symbolXbtLtc) {
		CoreSymbolSpecification symbolSpecXbtLtc = CoreSymbolSpecification.builder()
		        .symbolId(symbolXbtLtc)         // symbol id
		        .type(SymbolType.CURRENCY_EXCHANGE_PAIR)
		        .baseCurrency(currencyCodeXbt)    // base = satoshi (1E-8)
		        .quoteCurrency(currencyCodeLtc)   // quote = litoshi (1E-8)
		        .baseScaleK(1_000_000L) // 1 lot = 1M satoshi (0.01 BTC)
		        .quoteScaleK(10_000L)   // 1 price step = 10K litoshi
		        .takerFee(1900L)        // taker fee 1900 litoshi per 1 lot
		        .makerFee(700L)         // maker fee 700 litoshi per 1 lot
		        .build();

		return exchangeApi.submitBinaryDataAsync(new BatchAddSymbolsCommand(symbolSpecXbtLtc));
	}
	
}
