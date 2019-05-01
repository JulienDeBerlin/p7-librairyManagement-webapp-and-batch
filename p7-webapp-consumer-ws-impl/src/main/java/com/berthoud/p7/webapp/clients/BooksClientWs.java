package com.berthoud.p7.webapp.clients;

import books.wsdl.BookRequest;
import books.wsdl.BookResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;

public class BooksClientWs extends WebServiceGatewaySupport {

    public BookResponse researchBookReferences (String titleElement, String authorSurname, List<String> tags, int librairyId ){
        BookRequest request = new BookRequest();
        request.setAuthorSurname(authorSurname);
        request.setTitleElement(titleElement);
        request.setLibrairyId(librairyId);
        request.getTags().addAll(tags);

        return (BookResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }


}


