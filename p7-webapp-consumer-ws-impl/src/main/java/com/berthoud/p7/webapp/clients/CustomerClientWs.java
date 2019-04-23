package com.berthoud.p7.webapp.clients;

import customers.wsdl.LoginCustomerRequest;
import customers.wsdl.LoginCustomerResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CustomerClientWs extends WebServiceGatewaySupport {

    public LoginCustomerResponse getCustomer (String nickname, String password){
        LoginCustomerRequest request = new LoginCustomerRequest();
        request.setNickname(nickname);
        request.setPassword(password);

        return (LoginCustomerResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
