package com.berthoud.p7.webapp.consumer.ws.impl;

import com.berthoud.p7.webapp.clients.CustomersAndLoansClientWs;
import com.berthoud.p7.webapp.consumer.contracts.LoanDAO;
import org.springframework.stereotype.Repository;



/**
 * The class is the implementation of the interface {@link LoanDAO}.
 * The implementation is made by using the webservice client class {@link CustomersAndLoansClientWs}
 */
@Repository
public class LoanWsImpl extends AbstractWsImpl implements LoanDAO {

    @Override
    public int extendLoan(int loanId) {
        return customersAndLoansClientWs.extendLoanMapped(loanId);
    }
}
