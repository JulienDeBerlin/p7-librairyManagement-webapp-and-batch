package com.berthoud.p7.webapp.clients;

import books.wsdl.BookReferenceWs;
import books.wsdl.BookRequest;
import books.wsdl.BookResponse;
import books.wsdl.BookWs;
import com.berthoud.p7.webserviceapp.model.entities.Book;
import com.berthoud.p7.webserviceapp.model.entities.BookReference;
import com.berthoud.p7.webserviceapp.model.entities.Librairy;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.berthoud.p7.webapp.utils.Utils.convertXmlDateToLocal;

public class BooksClientWs extends WebServiceGatewaySupport {

    public BookResponse researchBookReferencesWs(String titleElement, String authorSurname, List<String> tags, int librairyId) {
        BookRequest request = new BookRequest();
        request.setAuthorSurname(authorSurname);
        request.setTitleElement(titleElement);
        request.setLibrairyId(librairyId);
        request.getTags().addAll(tags);

        return (BookResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public List<BookReference> researchBookReferencesMapped(String titleElement, String authorSurname, List<String> tags, int librairyId) {
        BookResponse bookResponseWs = researchBookReferencesWs(titleElement, authorSurname, tags, librairyId);

        List<BookReference> bookReferenceList = new ArrayList<>();


        for (BookReferenceWs bookReferenceWs : bookResponseWs.getBookReferences()) {
            BookReference bookReference = new BookReference();
            BeanUtils.copyProperties(bookReferenceWs, bookReference);

            Set<Book> bookSet = new HashSet<>();
            for (BookWs bookWs : bookReferenceWs.getBook()) {
                Book book = new Book();
                BeanUtils.copyProperties(bookWs, book);

                book.setDatePurchase(convertXmlDateToLocal(bookWs.getDatePurchase()));

                switch (bookWs.getStatus()) {
                    case AVAILABLE:
                        book.setStatus(Book.Status.AVAILABLE);
                        break;
                    case BOOKED:
                        book.setStatus(Book.Status.BOOKED);
                        break;
                    case BORROWED:
                        book.setStatus(Book.Status.BORROWED);
                        break;
                }

                Librairy librairy = new Librairy();
                BeanUtils.copyProperties(bookWs.getLibrairy(), librairy);
                book.setLibrairy(librairy);

                bookSet.add(book);
            }
            bookReference.setBooks(bookSet);
            bookReferenceList.add(bookReference);
        }

        return bookReferenceList;
    }

}


