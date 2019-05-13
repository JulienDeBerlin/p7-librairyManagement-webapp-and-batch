package com.berthoud.p7.batch.lateLoansMonitoring;

import com.berthoud.p7.webapp.business.managers.LoanManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import p7.webapp.model.beans.Customer;
import p7.webapp.model.beans.Loan;

import java.util.List;

@Component
public class GetListLateUserTask {

    @Autowired
    LoanManager loanManager;

    public List<Customer> getListLateUserTask() {

        List <Loan> loanLateList = loanManager.getListOpenLoansLate();
        List<Customer> customerLateList = loanManager.convertLoanListIntoCustomerList(loanLateList);

        return customerLateList;
    }
}
