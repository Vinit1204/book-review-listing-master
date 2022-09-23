package com.dotdash.recruiting.bookreview.controller;

import com.dotdash.recruiting.bookreview.model.Book;
import com.dotdash.recruiting.bookreview.service.HelpService;
import com.dotdash.recruiting.bookreview.service.HostNameService;
import com.dotdash.recruiting.bookreview.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controller class to handle apis like
 *  search
 *  hostName
 *  help
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BookListingController {

    @Autowired
    SearchService searchService;

    @Autowired
    HostNameService hostNameService;

    @Autowired
    HelpService helpService;


    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> search(@RequestParam(name = "query", defaultValue = "*") String query,
                             @RequestParam(name = "sortField", defaultValue = "title") String sortField,
                             @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        return searchService.getSearchResults(query, sortField, pageNumber);
    }

    @GetMapping(value = "/hostName")
    public String getHostName(){
        return hostNameService.getHostName();
    }

    @GetMapping(value = "/help")
    public String getHelpMessage() {
        return helpService.getHelpMessage();
    }

}
