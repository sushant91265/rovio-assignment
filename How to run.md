### Pre-requisites
- Java 8+
- Maven 3+
- Mac OS X or Linux with configured Java environment


### How to run
- Clone the repository
- `javac App.java` and then `java App <offerJson.json> <marketJson.json>`
- OR
- Run from root directory => `mvn clean install` and then `java -jar target/PlushMarket-1.0.0-SNAPSHOT.jar <offerJson.json> <marketJson.json>`


### Assumptions
- Assuming that there will be no garbadge data in the input json file.
- No duplicate entries in the input json file.


### Strucutre of the application
- The application is divided into 3 layers
    - Model
    - Service
    - Util
- The model layer contains the POJOs for the input json files.
- The service layer contains the business logic.
- The util layer contains the utility classes.
- The application is divided into 2 packages
    - com.rovio.plushmarket
    - com.rovio.plushmarket.test
- The first package contains the main application.
- The second package contains the test cases.


### How to add new tests
- Add the test case file input and output json files in the `src/test/java/com/rovio/plushmarket/resources` package.
- Update the `src/test/java/com/rovio/plushmarket/test/PlushMarketTest.java` file to add the test case number
- Run the tests using `mvn clean test` command.
- The test cases are written using JUnit 5.
- Add newly added testcase documentation under `Testcases.md` file.


## Future enhancements
- Add more test cases.
- Add more validations.
- Add more loggers.
