# Spring Boot Application DBS-File Utility

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/90dd899ee438f2b960dc)

## Built With
	
### Server - Backend

* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management

###  Libraries and Plugins

* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

### Others 

* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system

## External Tools Used

* 	[Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
* 	[Postman Echo](https://docs.postman-echo.com/?version=latest) - A service that can be used to test your REST clients and make sample API calls. It provides endpoints for GET, POST, PUT, various auth mechanisms and other utility endpoints.
* 	[Travis CI](https://travis-ci.org/github/Spring-Boot-Framework/Spring-Boot-Application-Template) - A hosted continuous integration service used to build and test software projects hosted at GitHub and Bitbucket.
* 	[Codecov](https://codecov.io/gh/Spring-Boot-Framework/Spring-Boot-Application-Template) - A hosted tool that is used to measure the test coverage of your codebase.
*	[Dependabot](https://dependabot.com/) - Automated dependency updates.

## To-Do

* 	[x] Logger (Console, File)
* 	[x] RESTful Web Service (CRUD)
* 	[x] Content Negotiation
* 	[ ] [Spring Profiles] (https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-profiles)
* 	[ ] HATEOS
* 	[ ] Spring Boot Admin

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.dbs.test.file.DBSFileApplication` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to `root folder` containing pom.xml
* 	Open Eclipse
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>

```text
`mvn package -Dmaven.test.skip=true`    //skip all tests and build
```

```text
`mvn clean package`                     //run all tests and build
```

## Explore Rest APIs

The app defines following CRUD APIs.

### URLs

|  URL |  Method | Remarks |
|----------|--------------|--------------|
|`http://localhost:8080/v1/file`           | GET | Get File Details       |
|`http://localhost:8080/v1/dir`            | GET | Get Directory Details  |

## Documentation

* 	[Postman Collection](https://github.com/ankitverma19/File-Utility/blob/master/DBS-File.postman_collection.json) - offline
* 	[Swagger](http://localhost:8080/swagger-ui.html) - `http://localhost:8080/swagger-ui.html`- Documentation & Testing

## packages

* 	`controller` - to listen to the client;
* 	`exception` - to hold custom exception handling;
* 	`service` - to hold our business logic;

* 	`resources/` - Contains all the static resources, templates and property files.
* 	`resources/application.properties` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.

* 	`test/` - contains unit and integration tests

* 	`pom.xml` - contains all the project dependencies

## Resources

* 	[My API Lifecycle Checklist and Scorecard](https://dzone.com/articles/my-api-lifecycle-checklist-and-scorecard)
* 	[HTTP Status Codes](https://www.restapitutorial.com/httpstatuscodes.html)
* 	[Common application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

## License