package com.berthoud.p7.webapp.config;

import com.berthoud.p7.webapp.clients.CustomerClientWs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the  specified in
        // pom.xml
        marshaller.setContextPath("customers.wsdl");
        return marshaller;
    }

    @Bean
    public CustomerClientWs movieClient(Jaxb2Marshaller marshaller) {
        CustomerClientWs client = new CustomerClientWs();
        client.setDefaultUri("http://localhost:8080/ws/customers.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


}
