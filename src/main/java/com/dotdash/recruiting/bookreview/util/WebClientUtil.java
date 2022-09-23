package com.dotdash.recruiting.bookreview.util;

import com.dotdash.recruiting.bookreview.Exception.SearchException;
import com.dotdash.recruiting.bookreview.model.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientUtil {

    @Autowired
    Environment environment;

    /**
     *
     * Sending request to Goodreads API to search books,
     * in real application we can send response time error information to logging systems like splunk
     */
    public String getSearchResponse(SearchRequest request) {
        String baseUrl = environment.getProperty("baseUrl");
        return WebClient.create(baseUrl)
                        .method(HttpMethod.GET)
                        .uri(uriBuilder -> uriBuilder.path(request.getUrl())
                        .queryParams(request.getParams()).build())
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError,
                         error -> Mono.error(new SearchException("API not found")))
                        .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new SearchException("Server is not responding")))
                        .bodyToMono(String.class)
                        .block();
    }
}
