package com.debesh.microservices.camelmicroservicea.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .log("${body}")
        .to("file:files/output");
    }
}

// docker run -p 61616:61616 -p 8161:8161 rmohr/activemq