package com.berthoud.p7.webapp.business.managers;

import com.berthoud.p7.webapp.consumer.contracts.BookReferenceDAO;
import com.berthoud.p7.webapp.consumer.contracts.LibrairyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p7.webapp.model.beans.Book;
import p7.webapp.model.beans.BookReference;
import p7.webapp.model.beans.Librairy;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookResearchManager {

    @Autowired
    LibrairyDAO librairyDAO;

    @Autowired
    BookReferenceDAO bookReferenceDAO;


    public List<Librairy> getAllLibrairies() {
        return librairyDAO.findAllLibrairy();
    }


    public List<String> convertTagsIntoList(String tagsAsString) {

        tagsAsString = tagsAsString.toLowerCase();
        List<String> tagList = new ArrayList<>();

        if (tagsAsString.indexOf(',') != -1) {
            while (tagsAsString.indexOf(',') != -1) {
                String additionalTag = tagsAsString.substring(0, tagsAsString.indexOf(','));
                tagList.add(additionalTag);
                tagsAsString = tagsAsString.substring(tagsAsString.indexOf(',') + 2);
            }
        }
        tagList.add(tagsAsString);
        return tagList;
    }

    public List<BookReference> getResultBookResearch(String authorSurname, String titleElement, List<String> tags, int librairyId) {
        return bookReferenceDAO.getResultBookResearch(authorSurname, titleElement, tags, librairyId);
    }


    public List<BookReference> addAmountAvailableBooks(List<BookReference> inputList) {

        for (BookReference br : inputList) {
            int amountAvailableBook = 0;

            for (Book b : br.getBooks()) {
                if (b.getStatus().equals(Book.Status.AVAILABLE)) {
                    amountAvailableBook++;
                }
            }
            br.setAmountAvailableBooks(amountAvailableBook);
        }
        return inputList;
    }

}
