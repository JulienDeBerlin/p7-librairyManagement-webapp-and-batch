package com.berthoud.p7.webapp.business.managers;

import com.berthoud.p7.webapp.consumer.contracts.LoanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p7.webapp.model.beans.*;

@Service
public class LoanManager {

    @Autowired
    LoanDAO loanDAO;

    /**
     * The method is used to extend an active {@link Loan}. The extension of a loan is only possible if all following conditions are met:
     * - membership is still valid
     * - max number of extension has not been reached (value to be set in separate settings.properties file)
     * - and of course, if loan id matches with an existing and still open loan.
     * <p>
     * The length of the extension is also to be set in the separate settings.properties file.
     *
     * @param loanId the id of the {@link Loan} to be extended
     * @return :
     * 1 = success (loan has been extended),
     * 0 = failure (membership expired),
     * -1 = failure (max amount of extensions reached),
     * -2 = failure (loanId not correct)
     */
    public int extendLoan(int loanId){
        return loanDAO.extendLoan(loanId);
    }
}
