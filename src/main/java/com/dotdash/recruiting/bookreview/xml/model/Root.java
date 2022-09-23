package com.dotdash.recruiting.bookreview.xml.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root{
    @JsonProperty("GoodreadsResponse")
    public GoodreadsResponse goodreadsResponse;
}