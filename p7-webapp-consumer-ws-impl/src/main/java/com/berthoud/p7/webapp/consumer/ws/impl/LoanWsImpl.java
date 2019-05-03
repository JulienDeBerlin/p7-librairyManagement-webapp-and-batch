package com.berthoud.p7.webapp.consumer.ws.impl;

import com.berthoud.p7.webapp.clients.CustomersAndLoansClientWs;
import com.berthoud.p7.webapp.config.SoapClientConfig;
import com.berthoud.p7.webapp.consumer.contracts.LoanDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;



/**
 * The class is the implementation of the interface {@link LoanDAO}.
 * The implementation is made by using the webservice client class {@link CustomersAndLoansClientWs}
 */
@Repository
public class LoanWsImpl implements LoanDAO {

    @Override
    public int extendLoan(int loanId) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        CustomersAndLoansClientWs client = context.getBean(CustomersAndLoansClientWs.class);
        return client.extendLoanMapped(loanId);
    }
}
