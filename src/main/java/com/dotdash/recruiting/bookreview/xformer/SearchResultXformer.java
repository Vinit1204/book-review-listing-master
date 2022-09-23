package com.dotdash.recruiting.bookreview.xformer;

import com.dotdash.recruiting.bookreview.Exception.SearchException;
import com.dotdash.recruiting.bookreview.model.Book;
import com.dotdash.recruiting.bookreview.util.SearchConstants;
import com.dotdash.recruiting.bookreview.xml.model.Root;
import com.dotdash.recruiting.bookreview.xml.model.Work;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converting xml response to result josn object
 */
@Component
public class SearchResultXformer {

    @Autowired
    ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(SearchResultXformer.class);

    //
    public List<Book> mapResult(String rootXml, String sortField) {
        Root root = null;
        JSONObject jsonObj = XML.toJSONObject(rootXml);
        String json = jsonObj.toString(4);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            root = objectMapper.readValue(json, Root.class);
        } catch (JsonProcessingException e) {
            //In real world application we can send error information to splunk like logging system
            logger.error("Error while converting xml response to Json Object");
            throw new SearchException(e.getMessage());
        }
        logger.info("Successfully converted xml response to json object");
        return mapToSearchResult(root, sortField);
    }

    private List<Book> mapToSearchResult(Root root, String sortField) {
        if (root == null) {
           throw new SearchException("No Result found for search");
        }
        List<Book> result = new ArrayList<>();
        Stream<Book> bookStream = root.goodreadsResponse.search.results.work.stream().map(work -> mapToBook(work));
        if (sortField.equalsIgnoreCase(SearchConstants.AUTHOR)) {
            bookStream = bookStream.sorted((b1, b2) -> b1.getAuthor().compareTo(b2.getAuthor()));
        } else {
            bookStream = bookStream.sorted((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));
        }
        return bookStream.collect(Collectors.toList());

    }

    private Book mapToBook(Work work) {
        Book book = new Book();
        book.setAuthor(work.best_book.author.name);
        book.setTitle(work.best_book.title);
        book.setImageLink(work.best_book.image_url);
        return book;
    }
}
