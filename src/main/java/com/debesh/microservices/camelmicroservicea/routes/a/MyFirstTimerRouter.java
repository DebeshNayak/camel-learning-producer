package com.debesh.microservices.camelmicroservicea.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyFirstTimerRouter extends RouteBuilder{
	
	@Autowired
	private GetCurrentTimeBean getCurrentTimeBean;

	@Autowired
	private SimpleLoggingProcessingComponent loggingComponent;

	@Override
	public void configure() throws Exception {
		// instead of queue we will use timer 
		// transformation
		// instead for saving to database we will log
		// Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
		from("timer:first-timer") // queue
		.log("${body}") // null
		.transform().constant("My Constant Message")
		.log("${body}") // My Constant Message
		// .transform().constant("Time now is " + LocalDateTime.now())

		// Processing
		// Transformation 
		
		.bean(getCurrentTimeBean, "getCurrentTime") 
		.log("${body}") //Time now is 2021-12-15T20:37:49.542633800
		.bean(loggingComponent)
		.log("${body}") //Time now is 2021-12-15T20:37:49.542633800
		.process(new SimpleLoggingProcessor())
		.to("log:first-timer"); // database
	}

}

@Component
class GetCurrentTimeBean {
	public String getCurrentTime() {
		return "Time now is " + LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingComponent {
	
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
	
	public void process(String message) {
		logger.info("SimpleLoggingProcessingComponent {}", message);
	}
}

class SimpleLoggingProcessor implements Processor {

	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
	}
}

