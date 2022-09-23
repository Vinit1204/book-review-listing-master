package com.dotdash.recruiting.bookreview.service;

import com.dotdash.recruiting.bookreview.model.Book;
import com.dotdash.recruiting.bookreview.model.SearchRequest;
import com.dotdash.recruiting.bookreview.util.SearchConstants;
import com.dotdash.recruiting.bookreview.util.WebClientUtil;
import com.dotdash.recruiting.bookreview.xformer.SearchResultXformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * This class is used to fetch data from Goodreads search API and convert it to json Object
 */
@Component
public class SearchService {

    @Autowired
    WebClientUtil webClientUtil;

    @Autowired
    SearchResultXformer searchResultXformer;

    @Autowired
    Environment environment;

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    public List<Book> getSearchResults(String searchPattern, String sortField, int pageNumber) {
        SearchRequest request = buildSearchRequest(searchPattern, sortField, pageNumber);
        String xmlResult = webClientUtil.getSearchResponse(request);
        logger.info("Got results from Goodreads");
        List<Book> result = searchResultXformer.mapResult(xmlResult, sortField);
        logger.info("Got the search results successfully");
        return result;


    }

    private SearchRequest buildSearchRequest(String searchPattern, String sortField, int pageNumber) {
        String searchUrl = environment.getProperty("searchUrl");
        SearchRequest request = new SearchRequest();
        request.setHttpMethod(HttpMethod.GET);
        request.setUrl(searchUrl);
        request.setParams(getParams(searchPattern, pageNumber));
        return request;
    }

    private MultiValueMap<String, String> getParams(String searchPattern, int pageNumber) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String key = environment.getProperty(SearchConstants.KEY_PARAM);
        params.add(SearchConstants.QUERY_PARAM, searchPattern);
        params.add(SearchConstants.PAGE_NUM_PARAM, String.valueOf(pageNumber));
        params.add(SearchConstants.KEY_PARAM, key);
        return params;
    }

}
