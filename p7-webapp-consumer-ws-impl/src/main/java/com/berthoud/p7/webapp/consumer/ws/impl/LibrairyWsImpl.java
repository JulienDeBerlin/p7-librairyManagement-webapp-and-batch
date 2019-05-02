package com.berthoud.p7.webapp.consumer.ws.impl;

import com.berthoud.p7.webapp.clients.BooksClientWs;
import com.berthoud.p7.webapp.config.SoapClientConfig;
import com.berthoud.p7.webapp.consumer.contracts.LibrairyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import p7.webapp.model.beans.Librairy;

import java.util.List;


@Repository
public class LibrairyWsImpl implements LibrairyDAO {

//    @Autowired
//    BooksClientWs booksClientWs;

    @Override
    public List<Librairy> findAllLibrairy() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        BooksClientWs client = context.getBean(BooksClientWs.class);
        return client.getListLibrairiesMapped();
    }

}
