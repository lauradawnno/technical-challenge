# Graduate Data Engineer Technical Challenge

This is a Spring Boot project that uses an [H2 embedded database](http://www.h2database.com/html/main.html) to manage Car instances and provides REST APIs to interact with it. More info are in the JavaDocs and comments in the source code.

## Build and Deploy

To build the executable jar, after cloning the repository, you just need to run the Maven wrapper script for your system located in the root directory with: *./mvnw install*. This will build the project, run the tests and create the jar in the *target* folder, without the need to install Maven locally (you just need Java installed and the JAVA_HOME environment variable set).

To run the jar, after going to the target folder you just need to run: *java -jar .\technical-challenge-0.0.1-SNAPSHOT.jar*.
This command will start the REST service (usually on port 8080) on an embedded Tomcat istance and the embedded database aswell and even create and populate the Car table using the SQL commands found in *src/main/resources/data.sql*.


## Usage

To test the APIs you can use the bash scripts provided or any other REST client. 

NOTE: The scripts use localhost and port 8080 to run, if your server started on a different port (you can check it in the logs when the application starts) or you are calling from a different machine, change the scripts accordingly.
