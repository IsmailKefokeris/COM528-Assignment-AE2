# COM528 - Shopping Application - AE2

### Assessment Task 
This is an individual assignment which will build on the work you have done as a group in AE1. If useful, you may reuse any of the AE1 artifacts generate by your group but you must fork the code and add any improvements, extra features and documentation in a project hosted in your own Git repository. 

[Design Documentation](./UMLmodel/DESIGNDOCUMENTATION.md)
[Design Diagrams](./UMLmodel/DESIGNDIAGRAM.md)


## Running the Application

### To run using stand alone spring boot maven plugin

at base of project do a build
```
mvn clean install
```
move to web module and run project
```
cd web
mvn spring-boot:run
```
the project will be served at http://localhost:8080/index.html

### To run using stand alone cargo maven plugin

Alternatively you can use the cargo plugin
```
mvn cargo:run
```
then navigate to http://localhost:8080/shoppingCartApplication/


### To run the project using  in Netbeans embedded Tomcat server

If you right click on the webfacade-example1 project in netbeans and select 'run' netbeans will spin up a tomcat instance and launch your application.

If you are asked for username and password just use the default admin admin.
 
(You must make sure you have stopped any maven started tomcat before you do this.)

You will be able to see the application at http://localhost:8080/shoppingCartApplication/

Note that sometimes the browser caches the old index.html so you may need to enter the correct url to access the app

http://localhost:8080/shoppingCartApplication/home

not

http://localhost:8080/shoppingCartApplication/home.jsp

## Default Values
When Starting the Application there are two default accounts you are able to log into:

Username: globaladmin
Password: globaladmin

Username: user1234
Password: user1234

## Known Issues

Currently shoppingCartApplication-service tests dont seem to be running and are counted as a failure, From my own tests all methods do actually work and I am still in the process of debugging this error.
`[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test (default-test) on project shoppingCartApplication-service: There are test failures.`


Unable to create a Javadoc
`[ERROR] Failed to execute goal org.apache.maven.plugins:maven-javadoc-plugin:3.3.1:javadoc (default-cli) on project shoppingCartApplication-model: An error has occurred in Javadoc report generation:`

## Licensing and Copyright

Licensed uner the Apache License Version 2.0.
