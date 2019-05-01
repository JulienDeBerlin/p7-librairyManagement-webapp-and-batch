package com.berthoud.p7.webapp.clients;


import customersAndLoans.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CustomersAndLoansClientWs extends WebServiceGatewaySupport {

    public LoginCustomerResponse getCustomer (String email, String password){
        LoginCustomerRequest request = new LoginCustomerRequest();
        request.setEmail(email);
        request.setPassword(password);
        return (LoginCustomerResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }


    public ExtendLoanResponse extendLoan (int loanId){
        ExtendLoanRequest request = new ExtendLoanRequest();
        request.setLoanId(loanId);
        return (ExtendLoanResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }


}
