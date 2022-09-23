package com.dotdash.recruiting.bookreview.model;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;


public class SearchRequest {

    private String url;

    private HttpMethod httpMethod;

    private MultiValueMap<String,String> params;

    public String getUrl() {
        return url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public MultiValueMap<String, String> getParams() {
        return params;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setParams(MultiValueMap<String, String> params) {
        this.params = params;
    }
}
