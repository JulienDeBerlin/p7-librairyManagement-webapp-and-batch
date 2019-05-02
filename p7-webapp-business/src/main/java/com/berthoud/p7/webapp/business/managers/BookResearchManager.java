package com.berthoud.p7.webapp.business.managers;

import com.berthoud.p7.webapp.consumer.contracts.BookReferenceDAO;
import com.berthoud.p7.webapp.consumer.contracts.LibrairyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p7.webapp.model.beans.*;

import java.util.*;

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


    public String convertTagsSetIntoString(Set<Tag> tagSet) {

        String tagsAsString = new String();

        if (tagSet != null) {
            for (Tag tag : tagSet) {
                tagsAsString = tag.getName() + ", " + tag;
            }
            tagsAsString = tagsAsString.substring(0, tagsAsString.lastIndexOf(','));

        } else {
            tagsAsString = "";
        }

        return tagsAsString;
    }


    public List<BookReference> getResultBookResearch(String authorSurname, String titleElement, List<String> tags, int librairyId) {
        return bookReferenceDAO.getResultBookResearch(authorSurname, titleElement, tags, librairyId);
    }


    public List<BookReference> getAmountAvailableBooks(List<BookReference> inputList) {

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


    public BookReference getAvailabilitiesEachLibrairy(BookReference bookReference) {


        List<Integer> ownedBy = new ArrayList<>();
        List<Integer> availableIn = new ArrayList<>();

        for (Book br : bookReference.getBooks()) {
            ownedBy.add(br.getLibrairy().getId());
            if (br.getStatus().equals(Book.Status.AVAILABLE)) {
                availableIn.add(br.getLibrairy().getId());
            }
        }

        List <Availability> availabilities = new ArrayList<>();
        bookReference.setAvailabilities(availabilities);


        List<Librairy> librairyList = getAllLibrairies();
        for (Librairy l: librairyList){
            Availability availability = new Availability();
            availability.setLibrairyName( l.getName());
            availability.setAmountBooks( Collections.frequency(ownedBy, l.getId()));
            availability.setAmountAvailableBooks( Collections.frequency(availableIn, l.getId()));
            bookReference.getAvailabilities().add(availability);
        }

        return bookReference;
    }


    public BookReference getSelectedBookReference(int bookReferenceId, List<BookReference> bookReferenceList) {

        BookReference selectedBookReference = new BookReference();

        Iterator<BookReference> iterator = bookReferenceList.iterator();
        while (iterator.hasNext()) {
            BookReference bookReference = iterator.next();
            if (bookReference.getId() == bookReferenceId) {
                selectedBookReference = bookReference;
                break;
            }
        }
        return selectedBookReference;
    }


}
