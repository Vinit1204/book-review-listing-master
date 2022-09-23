package com.dotdash.recruiting.bookreview.service;

import org.springframework.stereotype.Component;

@Component
public class HelpService {

    public String getHelpMessage(){
        return " You can excess this application with URl http://localHost:3000. " +
                " User can search books with your desired pattern -> input for Search Text. " +
                " User can sort books with either title or authore -> input for Search sort field. " +
                " User can specify the page number of the result by default each page has 20 books. ";
    }
}
