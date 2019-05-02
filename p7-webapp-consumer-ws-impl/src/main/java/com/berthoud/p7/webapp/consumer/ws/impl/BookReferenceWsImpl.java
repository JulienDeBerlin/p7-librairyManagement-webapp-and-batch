package com.berthoud.p7.webapp.consumer.ws.impl;

import com.berthoud.p7.webapp.clients.BooksClientWs;
import com.berthoud.p7.webapp.config.SoapClientConfig;
import com.berthoud.p7.webapp.consumer.contracts.BookReferenceDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import p7.webapp.model.beans.BookReference;

import java.util.List;


@Repository
public class BookReferenceWsImpl implements BookReferenceDAO {

    @Override
    public List<BookReference> getResultBookResearch(String authorSurname, String titleElement, List<String> tags, int librairyId) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        BooksClientWs client = context.getBean(BooksClientWs.class);
        return client.researchBookReferencesMapped(titleElement, authorSurname, tags, librairyId);
    }
}
