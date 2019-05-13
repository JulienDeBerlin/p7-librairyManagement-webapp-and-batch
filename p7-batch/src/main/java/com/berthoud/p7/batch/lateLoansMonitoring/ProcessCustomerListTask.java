package com.berthoud.p7.batch.lateLoansMonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import p7.webapp.model.beans.Customer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;


@Component
public class ProcessCustomerListTask {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private ReminderEmailBuilder reminderEmailBuilder;


    public void sendHtmlEmail(List<Customer> customerList) throws MessagingException {

        for (Customer c : customerList) {
            MimeMessage message = emailSender.createMimeMessage();

//            boolean multipart = true;

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            reminderEmailBuilder.initReminderEmailBuilder(c);
            String htmlMsg = reminderEmailBuilder.buildEmailContentHtml();

////            message.setContent(htmlMsg, "text/html ; charset=UTF-8");
//            message.setContent(htmlMsg, "text/html");


            helper.setTo(c.getEmail());

            helper.setSubject("Retour de prêts");

            helper.setText(htmlMsg, true);

            FileSystemResource res = new FileSystemResource(new File("/Users/admin/Documents/PROGRAMMING/OPENCLASSROOMS/P7/Apps_P7/Webapp/p7-batch/src/main/resources/logoSmall.jpg"));
            helper.addInline("smallLogo", res);

            this.emailSender.send(message);

        }
    }

}
