package com.berthoud.p7.webapp.business.managers;

import com.berthoud.p7.webapp.consumer.contracts.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p7.webapp.model.beans.Customer;

@Service
public class LoginManager {

    @Autowired
    CustomerDAO customerDAO;

    /**
     * This method is used for the login of a {@link Customer}.
     *
     * @param email    the email of the {@link Customer}
     * @param password the password of the {@link Customer}
     * @return If the password matches with the email, the corresponding {@link Customer} object is returned.
     */
    public Customer loginCustomer (String email, String password){
        return customerDAO.getCustomer(email, password);
    }


}
