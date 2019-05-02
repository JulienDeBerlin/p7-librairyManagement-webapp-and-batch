package com.berthoud.p7.webapp.business.managers;

import com.berthoud.p7.webapp.consumer.contracts.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p7.webapp.model.beans.Customer;

@Service
public class LoginManager {

    @Autowired
    CustomerDAO customerDAO;

    public Customer loginCustomer (String email, String password){
        return customerDAO.getCustomer(email, password);
    }


}
