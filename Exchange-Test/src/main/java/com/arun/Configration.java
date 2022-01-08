package com.arun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arun.entitys.IEventsHanlerImpl;

import exchange.core2.core.ExchangeApi;
import exchange.core2.core.ExchangeCore;
import exchange.core2.core.SimpleEventsProcessor;
import exchange.core2.core.common.config.ExchangeConfiguration;
import exchange.core2.core.processors.journaling.DummySerializationProcessor;

@Configuration
public class Configration {

	@Bean
	public ExchangeApi exchangeApi() { 
		ExchangeCore exchangeCore = ExchangeCore.builder()
		        .resultsConsumer(new SimpleEventsProcessor(new IEventsHanlerImpl()))
		        .serializationProcessorFactory(() -> DummySerializationProcessor.INSTANCE)
		        .exchangeConfiguration(ExchangeConfiguration.defaultBuilder().build())
		        .build();

		exchangeCore.startup();
		return exchangeCore.getApi();
	}
}
