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


/**
 * This class is designed for a task, a task being part of a batch job.
 * The task designed here is about  processing a list of {@link Customer} objects by sending each of the customer a reminder email.
 */

@Component
public class ProcessCustomerListTask {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private ReminderEmailBuilder reminderEmailBuilder;


    /**
     * This method sends a html email to each {@link Customer} object of the input list
     *
     * @param customerList a list of {@link Customer} objects, all of them having late books to return.
     * @throws MessagingException
     */
    public void sendHtmlEmail(List<Customer> customerList) throws MessagingException {

        for (Customer c : customerList) {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            reminderEmailBuilder.initReminderEmailBuilder(c);
            String htmlMsg = reminderEmailBuilder.buildEmailContentHtml();

            helper.setTo(c.getEmail());

            helper.setSubject("Retour de prêts");

            helper.setText(htmlMsg, true);

            FileSystemResource res = new FileSystemResource(new File("/Users/admin/Documents/PROGRAMMING/OPENCLASSROOMS/P7/Apps_P7/Webapp/p7-batch/src/main/resources/logoSmall.jpg"));
            helper.addInline("smallLogo", res);

            this.emailSender.send(message);

        }
    }

}
