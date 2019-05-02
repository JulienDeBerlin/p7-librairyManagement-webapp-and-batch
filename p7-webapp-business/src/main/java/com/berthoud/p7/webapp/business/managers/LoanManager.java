package com.berthoud.p7.webapp.business.managers;

import com.berthoud.p7.webapp.consumer.contracts.LoanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanManager {

    @Autowired
    LoanDAO loanDAO;

    public int extendLoan(int loanId){
        return loanDAO.extendLoan(loanId);
    }
}
