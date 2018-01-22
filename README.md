# Theatre-Booking

This project is used to book the tickets for our theatre. The algorithms I am using is both Best-fit and first-fit in order to process request optimally and as per requirements. 

## Getting Started

This project is built using Maven, we are generating executable JAR to process the input from the users.

### Prerequisites

Users need to have the following:


```
 install JDK/JRE 1.5 and above.
 install eclipse latest version
 Within Eclipse install Cucumber Plugin - (http://toolsqa.com/cucumber/install-cucumber-eclipse-plugin/)
 Within Eclipse install Maven Plugin - (http://toolsqa.com/java/maven/how-to-install-maven-eclipse-ide/)
```

### Installing

To build and run the project. Please use the following

```
To Build Jar file
•	Import the project to your eclipse.
•	Right-click on the project and click on configure.
•	Click on Convert to Maven project. 
•	right click on the project , click Run As.
•	Click on Maven build , please provide "Goals" details as "-e clean verify install".
•	and click on skip test.
•	Executable Jar will be created with the name Theatre-0.0.1-SNAPSHOT.jar in target folder of the project.
```

```
To Run the java project.
•	Right-click on the project.
•	Click on Run As and click on Java Application.

```
```
To Run the jar file
•	Please go to the Jar folder on your system.
•	Open the terminal or powershell and enter command.
•	java -jar *jar file name*.

```

## Running the tests on eclipse
```
To Run the test cases
•	Right-click on the project
•	Click on Run As and click on Run Configuration
•	Click on Cucumber Feature , add the new configuration
•	New Dialogue box will be opened 
•	Please enter the Project,Feature Path and Glue path
•	Click Run , in the console the output will be shown



### Cucumber Test 

•	I have used cucumber test cases for behavioral testing. As we follow test-driven development, before writing a piece a code to application we need to write the test cases in cucumber. So this will help to refine our logic and behavior of the code accordingly.
•	Cucumber test cases consists of two parts one where we write the code in Java (src/test/java folder) and second part is  feature file which is written in natural language(Standard English sentences) in  src/test/resource folder .   There will be another Java files which glues those two parts.
•	Typically cucumber test case for scenario consists three steps Given When and Then(we can also add “And”   & “But”). With “Given” keyword we usually write the code to upload a file which is required for execution into persistence layer or login in a user into application in UI. “When” keyword is used for starting any type of action such as AWS new stack is launched in Cloudformation. “Then” keyword is used for observe the behavior of the Webservice or the component we are testing. 




## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


