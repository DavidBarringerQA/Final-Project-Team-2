# Choonz

A web application storing and displaying details about artists, songs, playlists and more

## Getting Started
This is a guide for installing the project locally for testing and development. The prerequisites for the project are listed below:

### Prerequisites
* [Java JDK 14+](https://www.oracle.com/java/technologies/javase-downloads.html)
* [MySQL 8.0+](https://dev.mysql.com/downloads/installer/)
* [Apache Maven 3.8](https://maven.apache.org/download.cgi)

You will also need to ensure that Java and Maven have been correctly added to your `PATH`, [(see this example for Java)](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/). To check that they have been installed correctly, run the following commands in a terminal of your choosing:
`java -version`
`mvn -version`

### Installing
1. Fork this repository
2. Clone your forked repository using `git clone <url>`
3. Update the application-production.properties (located in the src/main/resources folder) to match the credentials of your database (you may also need to create the database and run the schema file located in the same folder). You should also check that the port given in the application.properties file is not in use.
4. To run the project, either open the project in Eclipse and run the project as a Sping Boot Application, or use `mvn compile` to compile the project, then move to the target/classes folder in the terminal and run using `java com.qa.choonz.ChoonzApplication`
5. To test that everything is running correctly, go to http://localhost:8082 in a browser. You will first want to register from the login screen, then log in. You should then be able to add items, (note that some items require others to exist first, e.g. a track needs an album to exist). If the items aren't displaying immediately after confirming, then try refreshing the page; if they still fail to display, there may be an error.

**Note** If you change the port used in the application.properties file, it will need changing in the files in (src/main/resources/static) folder

## Running tests
Before running tests, make sure that Spring Boot devtools are not in the pom.xml file (or are commented out).
Then the full test suite can be run using `mvn test`

### Unit tests
Unit tests are written using JUnit and Mockito, they can be run individually as JUnit tests in Java IDEs (such as Eclipse)

### Integration tests
Integration tests are written using Spring Boot Test and MockMvc to mimic the HTTP requests send by the website. They can also be run as a JUnit test in an IDE.

### User-Acceptance tests
UATs are written using Cucumber and Selenium, they can also be run as individual feature files. Before running UATs, make sure that an instance of the server is running.

## Deployment
Before building the project, make sure that the server.port value in application.properties is not in use and that the database credentials in application-prod.properties are correct.

A packaged version of the project can be created by running mvn package in the terminal, this will also run the test suite. The resulting .jar file can be found in the target folder, to run the packaged file, run java -jar path/to/file.jar in the terminal. The site can then be accessed by going to http://localhost:8082 in your browser. For others to access the site, you will need to provide a static ip/register a domain.

## Built With
* [Maven](https://maven.apache.org/) - Dependency manager and build tool
* [Spring Boot](https://spring.io/projects/spring-boot) - Handling HTTP requests, security and database input

## Authors

### Training Team

- **Client** - [**Angelica Charry**](https://github.com/acharry) - **Software Delivery Manager**
- **Product Owner** - [**Nick Johnson**](https://github.com/nickrstewarttds) - **Initial work (backend & frontend development, specification)**
- **Product Owner** - [**Edward Reynolds**](https://github.com/Edrz-96) - **Initial work (testing, specification)**
- [**Jordan Harrison**](https://github.com/JHarry444) - **General Java wizardry**
- [**Alan Davies**](https://github.com/MorickClive)
- [**Savannah Vaithilingham**](https://github.com/savannahvaith)
- [**Vinesh Ghela**](https://github.com/vineshghela)
- [**Piers Barber**](https://github.com/PCMBarber)

### Development Team
- [Simon White](https://github.com/simonwhiteQA) - Front-end, Scrum Master
- [David Barringer](https://github.com/DavidBarringerQA) - Back-end, Funtional tests
- [Megan Crouch](https://github.com/megancrouch24) - UAT, non-functional tests

## Acknowledgements

- [JavaInUse](https://javainuse.com) for guide on user authentication
