package com.berthoud.p7.webapp.controllers;

import com.berthoud.p7.webapp.business.managers.BookResearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import p7.webapp.model.beans.BookReference;

import java.util.ArrayList;
import java.util.List;

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
        if (!tagsAsString.isEmpty()){
            tagList = bookResearchManager.convertTagsIntoList(tagsAsString);
        }
        List<BookReference> bookReferenceList = bookResearchManager.getResultBookResearch(authorSurname, titleElement, tagList, librairyId);
        bookReferenceList = bookResearchManager.addAmountAvailableBooks(bookReferenceList);

        map.addAttribute(bookReferenceList);


        return "researchResultOverview";
    }

    @RequestMapping(value = "/bookDetails", method = RequestMethod.GET)
    public String getBookDetails() {
        return "researchResultDetails.html";
    }


}
