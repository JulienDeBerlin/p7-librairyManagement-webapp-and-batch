package com.berthoud.p7.webapp.consumer.ws.impl;

import com.berthoud.p7.webapp.clients.CustomersAndLoansClientWs;
import com.berthoud.p7.webapp.config.SoapClientConfig;
import com.berthoud.p7.webapp.consumer.contracts.CustomerDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import p7.webapp.model.beans.Customer;


@Repository
public class CustomerWsImpl implements CustomerDAO {

    @Override
    public Customer getCustomer(String email, String password) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        CustomersAndLoansClientWs client = context.getBean(CustomersAndLoansClientWs.class);
        return client.getCustomerMapped(email, password);
    }
}
