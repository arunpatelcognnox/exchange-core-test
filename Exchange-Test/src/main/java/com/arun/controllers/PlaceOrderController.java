package com.arun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exchange.core2.core.ExchangeApi;
import exchange.core2.core.common.OrderAction;
import exchange.core2.core.common.OrderType;
import exchange.core2.core.common.api.ApiPlaceOrder;

@RestController
@RequestMapping("/api/placeOrder")
public class PlaceOrderController {

	@Autowired private ExchangeApi exchangeApi;
	private int orderId = 0 ;
	
	@PostMapping("/userId/{userId}/price/{price}/reservePrice/{reservePrice}/size/{size}/symbolXbtLtc/{symbolXbtLtc}")
	public Object save(@PathVariable int userId, @PathVariable int price, @PathVariable int reservePrice, @PathVariable int size, @PathVariable int symbolXbtLtc) {
		return exchangeApi.submitCommandAsync(ApiPlaceOrder.builder()
		        .uid(userId)
		        .orderId(orderId++)
		        .price(price)
		        .reservePrice(reservePrice) // can move bid order up to the 1.56 LTC, without replacing it
		        .size(size) // order size is 12 lots
		        .action(OrderAction.BID)
		        .orderType(OrderType.GTC) // Good-till-Cancel
		        .symbol(symbolXbtLtc)
		        .build());
	}
	
}
