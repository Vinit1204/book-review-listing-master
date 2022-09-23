package com.dotdash.recruiting.bookReview.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookListingControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testSearch() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "software");
        webTestClient.get().uri(
                uriBuilder -> uriBuilder.path("/search")
                        .queryParams(params).build()).exchange().expectStatus().is2xxSuccessful();

    }

    /**
     *
     * Testing 404 error with api not found
     */
    @Test
    public void test4xxClientError() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "software");
        webTestClient.get().uri(
                uriBuilder -> uriBuilder.path("/searchError")
                        .queryParams(params).build()).exchange().expectStatus().is4xxClientError();
    }

    /**
     *
     *  Testing 500 error with internal server error while converting xml to json
     */
    @Test
    public void test5xxServerError() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "*");
        webTestClient.get().uri(
                uriBuilder -> uriBuilder.path("/search")
                        .queryParams(params).build()).exchange().expectStatus().is5xxServerError();
    }

    /**
     *
     * Testing hostName api
     */
    @Test
    public void testGetHostName() throws Exception {
        webTestClient.get().uri(
                uriBuilder -> uriBuilder.path("/hostName")
                      .build()).exchange().expectStatus().is2xxSuccessful();

    }

    /**
     *
     * Testing help api
     */
    @Test
    public void testGetHelp() throws Exception {
        webTestClient.get().uri(
                uriBuilder -> uriBuilder.path("/help")
                        .build()).exchange().expectStatus().is2xxSuccessful();

    }

}
