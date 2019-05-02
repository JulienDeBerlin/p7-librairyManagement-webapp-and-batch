package com.berthoud.p7.webapp.controllers;

import com.berthoud.p7.webapp.business.managers.BookResearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import p7.webapp.model.beans.BookReference;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes (value = "bookReferenceList")
@Controller
public class ResearchResultController {

    @Autowired
    BookResearchManager bookResearchManager;

    @RequestMapping(value = "/researchResult", method = RequestMethod.POST)
    public String getResult(ModelMap map,
                            @RequestParam(value = "author") String authorSurname,
                            @RequestParam(value = "title") String titleElement,
                            @RequestParam(value = "tags") String tagsAsString,
                            @RequestParam(value = "librairy") int librairyId) {

        List<String> tagList = new ArrayList<>();
        if (!tagsAsString.isEmpty()) {
            tagList = bookResearchManager.convertTagsIntoList(tagsAsString);
        }
        List<BookReference> bookReferenceList = bookResearchManager.getResultBookResearch(authorSurname, titleElement, tagList, librairyId);
        bookReferenceList = bookResearchManager.getAmountAvailableBooks(bookReferenceList);

        map.addAttribute("bookReferenceList", bookReferenceList);

        return "researchResultOverview";
    }


    @RequestMapping(value = "/backToResultOverview")
        public String backToResultOverview(){
            return "researchResultOverview";
        }



    @RequestMapping(value = "/bookDetails", method = RequestMethod.GET)
    public String getBookDetails( ModelMap map,
                                  @RequestParam(value = "bookRef") int bookReferenceId,
                                  @SessionAttribute (value = "bookReferenceList") List<BookReference> bookReferenceList) {

        BookReference selectedBookReference = bookResearchManager.getSelectedBookReference(bookReferenceId, bookReferenceList);
        selectedBookReference.setTagsAsString(bookResearchManager.convertTagsSetIntoString(selectedBookReference.getTags()));
        selectedBookReference = bookResearchManager.getAvailabilitiesEachLibrairy(selectedBookReference);
        map.addAttribute("selectedBookReference", selectedBookReference);

        return "researchResultDetails";
    }




}
