package com.dotdash.recruiting.bookreview.service;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class HostNameService {

    public String getHostName(){
        InetAddress myHost = null;
        try {
            myHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

       return myHost.getHostAddress() != null ?  myHost.getHostAddress() : "127.0.0.1";
    }
}
