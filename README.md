# Book Listing Exercise #

The purpose of this exercise is to test your familiarity with Java/Kotlin full-stack development.  You'll be building a small book listing app using frameworks of your choice and the Goodreads' public API.

This is a server side application for Book Listing developed in springboot.

Command to start application (mvn spring-boot:run) Prerequisite (Maven installed on your machine)

once you start the application it runs on default port : 8080 

Different apis 
1. Search
2. HostName
3. Help

Sample curl request once server is started

1. Search (curl --location --request GET 'http://localhost:8080/search?query=software&pageNumber=10&sortField=title')
2. HostName (curl --location --request GET 'localhost:8080/hostName')
3. Help (curl --location --request GET 'localhost:8080/help')

This project also included some of the integration tests to test different scenarios including 4xx and 5xx erros.

In real world server side application, needs to implement security protocol and integrate it will logging and monitoring sytstems like Splunk, SIgnalFx


