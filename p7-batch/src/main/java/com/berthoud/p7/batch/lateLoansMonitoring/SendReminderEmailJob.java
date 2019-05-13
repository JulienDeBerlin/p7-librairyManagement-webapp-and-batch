package com.berthoud.p7.batch.lateLoansMonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Controller
public class SendReminderEmailJob {

    private GetListLateUserTask getListLateUserTask;
    private ProcessCustomerListTask processCustomerListTask;

    @Autowired
    public SendReminderEmailJob(GetListLateUserTask getListLateUserTask, ProcessCustomerListTask processCustomerListTask) {
        this.getListLateUserTask = getListLateUserTask;
        this.processCustomerListTask = processCustomerListTask;
    }


    @ResponseBody
    @RequestMapping("/sendHtmlEmail")
    public String launchJob() throws MessagingException {
        processCustomerListTask.sendHtmlEmail(getListLateUserTask.getListLateUserTask());
        return "Reminder emails sent!";
    }

}
