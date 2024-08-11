# Event Management System

## Introduction

This project is a RESTful service that manages and queries event data based on a User's Geographical Location and a specified Date. The service ingests data from a provided CSV dataset, store it in a database, and offers an API to find events for users based on user-specified criteria. The system integrates with external APIs to provide additional information such as weather conditions and distances from the user's location to the event locations.

## Features

- **Data Ingestion**: Upload event data from a **CSV file/dataset** and store it in the database, using a dependency **commons-csv** by org.apache.commons
- **Data BackUp Option by API**: To backup the data into .csv file during Nightly Patches/Updates to the Application to ensure **safe backup** option.
- **Event Finder API**: Retrieve events occurring within the next **14 days** from a specified **date** based on user's **Latitude** and **Longitude**.
- **Weather Integration**: Fetch **weather** information for event locations on the specified event dates.
- **Distance Calculation Integration**: Compute the **distance** between the user's location and the event locations.
- **Pagination & Sorting**: Support for **pagination** and **sorting** to efficiently handle large datasets.
- **Swagger/OpenAPI**: For API Documentation using the dependencies from [springdoc-openapi v2.6.0](https://springdoc.org/)
- **Docker**: To Containerize the Application. **Containerization** is a Software Development process that bundles an Application's code with all the files and libraries, .jar files it needs to run on any infrastructure. With this Software process developers can package their applications and their dependencies together in a single container. This container can then be easily shipped and deployed on any platform that supports them.
- **Error Handling**: Robust error handling for external API failures and other potential issues.
- **Logging**: Logging is an essential feature for **monitoring** and **debugging** the application. It helps in tracking the flow of the application, recording significant events, and diagnosing issues. In this project, we have implemented a comprehensive logging mechanism (INFO, DEBUG, ERROR) to capture various aspects of the application's behavior.

## Tech Stack and Database Choice

### Tech Stack

- **Spring Boot**: Chosen for it's comprehensive support for building **Production-ready** Applications and Ease of Integration with other Spring Components.
- **Spring Data JPA**: Facilitates the data access layer and abstracts much of the boilerplate code.
- **Maven**: Maven is a **Project Management tool**, required for building and running the project.
- **PostgreSQL**: Chosen for its **Robustness**, **Performance**, and support for **Advanced** data types.
- **Docker**: To **Containerize** an Application.

### Design Decisions

- **RESTful API Design**: Ensures Scalable and Stateless Communication between the Client (**Postman**) and Server (**Backend Application**).
- **Pagination & Sorting**: Implements pagination to manage large sets of data and improve performance.
- **External API Integration**: Retrieves Weather data and calculates distances dynamically, enhancing the relevance of event information.
- **Logging**: Logging is an essential feature for **monitoring** and **debugging** the application. It helps in tracking the flow of the application, recording significant events, and diagnosing issues. In this project, we have implemented a comprehensive logging mechanism (INFO, DEBUG, ERROR) to capture various aspects of the application's behavior.
- **Key Features of Logging**:
  1. **Informational Logs**:
     - **Logs key events and important milestones within the application.**
     - **Example**:
       ```sh
       LOGGER.info("Successfully fetched data from the database.");
       ```
  2. **Debug Logs**:
     - **Provides detailed information useful during the debugging process.**
     - **Example**:
       ```sh
       LOGGER.debug("Entering method calculateDistance with parameters: lat1={}, lon1={}, lat2={}, lon2={}", lat1, lon1, lat2, lon2);
       ```
  3. **Error Logs**:
     - **Captures exceptions and errors that occur during the execution of the application.**
     - **Example**:
       ```sh
       LOGGER.error("Exception occurred while processing the request", ex);
       ```
  4. **Method Entry and Exit Logs**:
     - **Logs entry and exit points of methods to trace the flow of execution.**
     - **Example**:
       ```sh
       LOGGER.info("Entering method createNewEvent.");
       LOGGER.info("Exiting method createNewEvent.");
       ```
  5. **External API Call Logs**:
     - **Logs details when calling external APIs, including the request and response status.**
     - **Example**:
       ```sh
       LOGGER.info("Calling Weather API for city: {}", cityName);
       ```
  6. **Configuration and Initialization Logs**:
     - **Logs configuration settings and initialization steps.**
     - **Example**:
       ```sh
       LOGGER.info("Initializing application with configuration: {}", config);
       ```
- **How Logging is Implemented**:
  1. **Logger Initialization**:
     - **Each class where logging is required initializes a logger instance.**
     ```sh
     private static final Logger LOGGER = LoggerFactory.getLogger(ClassName.class);
     ```
  2. **Log Levels**:
     - **Different log levels (INFO, DEBUG, ERROR) are used based on the importance and purpose of the log message.**
  3. **External Libraries**:
     - **The project uses SLF4J (Simple Logging Facade for Java) along with a logging framework such as Logback or Log4j for flexible and efficient logging.**
- **Benefits of Logging**:
  1. **Monitoring**:
     - **Helps in keeping track of the application's behavior in a production environment.**
  2. **Debugging**:
     - **Aids developers in diagnosing and resolving issues by providing a detailed execution trace.**
  3. **Auditing**:
     - **Maintains a record of significant events and transactions for audit purposes.**
  4. **Performance Analysis**:
     - **Assists in identifying performance bottlenecks by analyzing the execution flow and timing.**
- **By implementing a robust logging mechanism, we ensure that the application is maintainable, easier to debug, and provides valuable insights into its operation.**

- **Package by Layer**: Choosing a package-by-layer architecture for the Events Management System project offers several benefits, particularly in terms of organization, maintainability, and scalability. Here’s a detailed explanation of why this approach was chosen:

  1. **Seperation of Concerns**: **Modularity** Each layer in the package-by-layer approach has a distinct responsibility, which leads to a clear separation of concerns. For instance, the service layer handles business logic, the controller layer manages incoming HTTP requests, and the repository layer interacts with the database. **Maintainability** By separating different concerns into distinct layers, it becomes easier to maintain and update the code. Changes in one layer (e.g., updating business logic) do not directly affect other layers (e.g., controllers or repositories).
  2. **Scalability**: **Layered Architecture** As the project grows, adding new features or components is more straightforward. For example, introducing a new service or a new endpoint can be done without significantly altering the existing structure. **Team Collaboration** Different teams can work on different layers simultaneously without causing conflicts. This parallel development accelerates the development process and enhances productivity.
  3. **Testability**: **Unit Testing** Each layer can be tested independently. Services can be tested without worrying about the controllers, and repositories can be tested without involving the business logic. **Mocking Dependencies** It’s easier to mock dependencies between layers. For instance, in service layer tests, repositories can be mocked to isolate and test only the business logic.
  4. **Reusability**: **Common Functionality** Common functionalities, such as exception handling, logging, and security, can be centralized in a particular layer (often a service or utility layer). This reduces code duplication and ensures consistency across the application. **Reusable Components** Components from one layer can be reused in different parts of the application. For example, a repository can be used by multiple services.
  5. **Clear Structure**: **Navigability** A package-by-layer structure makes the codebase easier to navigate. Developers can quickly locate where specific types of logic are implemented. Controllers are in one package, services in another, and repositories in yet another. **Standardization** It provides a standard way to organize code, which is beneficial for onboarding new developers. They can easily understand the structure and find their way around the codebase.
  6. **Adaptability to Patterns**: **Design Patterns** This approach naturally fits with common design patterns such as MVC (Model-View-Controller). Controllers map to the view, services to the controller, and repositories to the model. **Layered Architecture Patterns** It supports implementing other architectural patterns like Service-Oriented Architecture (SOA) or Microservices, as each service can be further modularized.

### Example Package Structure

Here's how the package structure might look:

```
com.event.eventsmanagement
|
|-- eventcontroller
|   |-- EventController.java
|
|-- downloadcontroller
|   |-- DataCsvFileDownloader.java
|
|-- uploadcontroller
|   |-- DataCsvFileUploader.java
|
|-- service
|   |-- eventservice
|       |-- EventService.java
|   |-- eventfileservice
|       |-- EventFileService.java
|
|-- eventrepository
|   |-- EventRepository.java
|
|-- entity
|   |-- Event.java
|
|-- dto
|   |-- EventRequest.java
|   |-- EventResponse.java
|   |-- EventFinderResponse.java
|   |-- EventResponseWithExternalAPI.java
|
|-- openapiconfiguration
|   |-- SwaggerConfiguration.java
|
|-- applicationutils
|   |-- CsvHelper.java
|   |-- AppUtils.java
|
|-- exceptionhandlers
|   |-- EventGlobalExceptionHandler.java
|   |-- FileResourceGlobalExceptionHandler.java
|
|-- externalapis
|   |-- DistanceCalculationAPI.java
|   |-- WeatherAPI.java
|   |-- apiservice
|       |-- ApiService.java
|
|-- mainapplication
|   |-- EventsmanagementApplication.java
|
End
```

### Conclusion

The package-by-layer approach was chosen for the Events Management System project to ensure a well-organized, maintainable, and scalable codebase. This architecture supports clear separation of concerns, facilitates testing and reusability, and provides a structured way to manage the application’s growth. By adhering to this architecture, the project becomes more robust, easier to manage, and adaptable to future changes and enhancements.

![Image of Package by Feature & Package by Layer](https://github.com/BHIMAVARAPU-MANOJ-KUMAR/Event-Management-System-GyanGrove-Backend-Intern-Assignment/blob/main/images/FeatureLayer.webp?raw=true)

### Error Handling

- **Graceful Degradation**: The system handles external API failures gracefully by providing fallback responses.

## Challenges and Solutions

- **External API Latency**: Implemented parallel calls to external APIs to reduce response times.
- **Pagination and Sorting**: Used Spring Data JPA’s pagination and sorting capabilities to handle large datasets efficiently.

## Future Improvements

- **Database Optimization**: Implement further optimizations as data grows, including indexing strategies.
- **Scalability**: Explore horizontal scaling options for handling increased loads.
- **Spring Batch Processing**: To further implement the data processing by using Springs Batch Processors.
- **At present, when the User tries to access the Events Finder API, he/she should type the page and size in the API Query Parameters. This makes the User Weird. Needs to further Imporve the API Events Finder.**
- **Spring Boot Starter Actuator**: To Implement **Spring Boot Actuator**, it adds several **production grade** service to the Application. It mainly exposes operational information about the running application -- health, metrics, info, dump, env, etc.
- **Spring Boot Starter Security**: To Implement **Spring Security** Authentication and Authorization, Role based access management, etc to the Application, hence only the events finder API is accessible to the public & CSV import, export & create new events API's are accessible to the System Administrator.

## Contribution

Contributions are welcome! Please fork the repository and submit a pull request for review. Connect me on [LinkedIn](https://in.linkedin.com/in/bhimavarapu-manoj-kumar-reddy)

## Setup and Running the Project

### Prerequisites

- **Java 17**: Ensure **Java 17** is installed on your system.
- **Maven**: Maven is a Project Management tool, required for building and running the project.
- **PostgreSQL**: Ensure PostgreSQL is installed and running.
- **Docker**: Ensure Docker Machine is installed in your machine.

### Running the Project

1. **Clone the Repository**

```sh
git clone https://github.com/BHIMAVARAPU-MANOJ-KUMAR/Event-Management-System-GyanGrove-Backend-Intern-Assignment.git
cd Event-Management-System-GyanGrove-Backend-Intern-Assignment
```

2. **Configure PostgreSQL**

- **Create a database named `event_management_db_name`.** Any **database_name** of your choice.
- **Update the `application.properties` file with your `PostgreSQL credentials`.**

```sh
spring.datasource.url=jdbc:postgresql://localhost:5432/event_management_db_name
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

- **For more go to application.properties file in the project**

3. **Build the Project**

```sh
mvn clean install
```

4. **Package the Application**

```sh
mvn clean package -DskipTests
```

- **-DskipTests flag is used to skip tests**.

5. **Run the Application**

```sh
mvn spring-boot:run
```

6. **Access the Application**

```sh
Access the Application on `http://localhost:8090/api/v1/**/**`
```

7. **To Docker Compose & Containerize the Application**

```sh
docker-compose up --build
```

8. **Running the Application**

- **Accessing the Application**:
  The Application will be available at `http://localhost:8090`.
- **SwaggerUI**:
  API Documentation is available at `http://localhost:8090/swagger-ui/index.html`. From this SwaggerUI you can directly send requests to the Application without the postman.

9. **Using `application-dev.properties` for Local Development**

- **Overview**:
  For local development purposes, I have introduced an `application-dev.properties` file. This file contains configuration settings tailored for development environments, allowing developers to test and run the application with development-specific configurations. The `dev` profile is used to activate these settings.

- **Running the Application with the `dev` Profile**:
  You can run the Spring Boot application with the dev profile in several ways. Below are the methods you can use.

- **Using VM Arguments in an IDE**:
  If you are running the application from an IDE like Eclipse, Spring Tools Suit or IntelliJ, you can specify the active profile using VM arguments:

  - Go to your Run/Debug Configurations.
  - Under the **VM arguments** section, add the following:

  ```sh
  -Dspring.profiles.active=dev
  ```

  - Save and run the application.

- **Running from the Command Line with the Executable JAR**:
  If you have built your application as a JAR file, you can run it with the `dev` profile by navigating to the directory containing the JAR file and using one of the following commands:

```sh
java -jar myapp.jar --spring.profiles.active=dev
```

```sh
java -Dspring.profiles.active=dev -jar myapp.jar
```

- **Running with Maven**:
  If you are running the application directly from the source using Maven, you can activate the dev profile as follows:

```sh
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

```sh
mvn spring-boot:run -Dspring.profiles.active=dev
```

### Important Notes

- **Ensure that the `application-dev.properties` file is correctly placed in the `src/main/resources` directory.**
- **The `dev` profile is specifically configured for local development and should not be used in production environments.**
- **Make sure to replace `myapp.jar` with the actual name of your `JAR` file.**

## API Endpoints

### Upload Events CSV File/Dataset (Admin API)

- **Endpoint**: `POST /api/v1/import/csv`
- **Description**: Uploads a CSV file containing event data and processes it.
- **Example `curl` Request for Importing CSV Data**:

```sh
curl -X POST "http://localhost:8090/api/v1/import/csv" \
     -H "Content-Type: multipart/form-data" \
     -H "import-csv-api-version: 1" \
     -F "file=@/path/to/your/file.csv"
```

- **Explanation**:
  `-X POST`: Specifies the request method as POST.
  `"http://localhost:8090/api/v1/import/csv"`: The URL of your endpoint. Replace `http://localhost:8090` with your actual server URL if different.
  `-H "Content-Type: multipart/form-data"`: Sets the content type to multipart/form-data for file upload.
  `-H "import-csv-api-version: 1"`: Adds the required header `import-csv-api-version` with the value `1`.
  `-F "file=@/path/to/your/file.csv"`: Specifies the file to upload. Replace `/path/to/your/file.csv` with the actual path to your CSV file.
  `consumes = MediaType.MULTIPART_FORM_DATA_VALUE`

### Download Events Data from Database to CSV File (Admin API)

- **Endpoint**: `GET /api/v1/export/csv`
- **Description**: Downloads a CSV File containing data from database.
- **Example `curl` Request for Exporting CSV Data**:

```sh
curl -X GET "http://localhost:8090/api/v1/export/csv" \
     -H "export-csv-api-version: 1" \
     -o "data_export_d MMM uuuu 'T' HH:mm:ss.SSS.csv"
```

- **Explanation**:
  `-X GET`: Specifies the request method as GET.
  `"http://localhost:8090/api/v1/export/csv"`: The URL of your endpoint. Replace `http://localhost:8090` with your actual server URL if different.
  `-H "export-csv-api-version: 1"`: Adds the required header `export-csv-api-version` with the value `1`.
  `-o data_export_d MMM uuuu 'T' HH:mm:ss.SSS.csv`: Specifies the output file name where the exported CSV data will be saved.

### Find Events (Event Finder API) (public API)

- **Endpoint**: `GET /api/v1/events/find`
- **Description**: Finds events based on user's latitude, longitude, a specified date, page and size.
- **Example `curl` Request for Finding Events**:

```sh
curl -X GET "http://localhost:8090/api/v1/events/find?latitude=40.7128&longitude=-70.0060&date=2024-03-15&page=1&size=10" \
     -H "Content-Type: application/json" \
     -H "Accept: application/json" \
     -H "events-finder-api-version: 1"
```

- **Explanation**:
  `-X GET`: Specifies the request method as GET.
  `"http://localhost:8090/api/v1/events/find?latitude=40.7128&longitude=-70.0060&date=2024-03-15&page=1&size=10"`: The URL of your endpoint. Replace `http://localhost:8090` with your actual server URL if different.
  `-H "Content-Type: application/json"`: Sets the content type to application/json.
  `-H "events-finder-api-version: 1"`: Adds the required header `events-finder-api-version` with the value `1`.
- **Event Finder API curl Request**:
  ![Screen Shot of curl request - Event Finder API](https://github.com/BHIMAVARAPU-MANOJ-KUMAR/Event-Management-System-GyanGrove-Backend-Intern-Assignment/blob/main/images/curl_request.png?raw=true)

- **Event Finder API Response**:

![Screen Shot of Event Finder API - Response](https://github.com/BHIMAVARAPU-MANOJ-KUMAR/Event-Management-System-GyanGrove-Backend-Intern-Assignment/blob/main/images/event_finder_api_response.png?raw=true)

### Create Events Version-1 & Version-2 (Admin API)

- **Endpoint**: `POST /api/v1/events/event` , `POST /api/v1/events/events`
- **Description**: Create a new Events, insert & save into the Database.
- **Example `curl` Request for Creating an Event API Version-1**:

```sh
curl -X POST "http://localhost:8090/api/v1/events/event" \
     -H "Content-Type: application/json" \
     -H "event-api-version: 1" \
     -H "Accept: application/v1+json" \
     -d '{
           "eventName": "Music Festival",
           "cityName": "San Francisco",
           "date": "2024-07-30",
           "time": "18:00:00",
           "latitude": 37.7749,
           "longitude": -122.4194
         }'
```

- **The main difference between the Version-1 & Version-2 API's are V1 sends back the response data with the Resource created `id` while inserting into the database and this is not a best practise.**

- **Example `curl` Request for Creating an Event API Version-2**

```sh
curl -X POST "http://localhost:8090/api/v1/events/events" \
     -H "Content-Type: application/json" \
     -H "events-api-version: 2" \
     -H "Accept: application/v2+json" \
     -d '{
           "eventName": "Music Festival",
           "cityName": "San Francisco",
           "date": "2024-07-30",
           "time": "18:00",
           "latitude": 37.7749,
           "longitude": -122.4194
         }'
```

- **Explanation**:
  `-X POST`: Specifies the request method as POST.
  `"http://localhost:8090/api/v1/events"`: The URL of your endpoint. Replace `http://localhost:8090` with your actual server URL if different.
  `-H "Content-Type: application/json"`: Sets the content type to application/json.
  `-H "event-api-version: 1"`: Adds the required header `event-api-version` with the value `1` for version 1 of the API.
  `-H "events-api-version: 2"`: Adds the required header `events-api-version` with the value `2` for version 2 of the API.
  `-H "Accept: application/v2+json"`: Specifies that the client accepts version 2 of the API response in JSON format.
  `-H "Accept: application/v1+json"`: Specifies that the client accepts version 1 of the API response in JSON format.
  `-d '{ "eventName": "Music Festival", "cityName": "San Francisco", "date": "2024-07-30", "time": "18:00", "latitude": 37.7749, "longitude": -122.4194 }'`: Sends the event data in JSON format.

## Some Useful Docker and Maven Commands that I used in this Project

- **Docker Commands**:
  ![Screen Shot of useful Docker Commands](<https://github.com/BHIMAVARAPU-MANOJ-KUMAR/Event-Management-System-GyanGrove-Backend-Intern-Assignment/blob/main/images/useful_docker_commands%20(2).png?raw=true>)

- **Maven Commands**:
  ![Screen Shot of useful Maven Commands](https://github.com/BHIMAVARAPU-MANOJ-KUMAR/Event-Management-System-GyanGrove-Backend-Intern-Assignment/blob/main/images/maven_commands.png?raw=true)

## Contribution

Contributions are welcome! Please fork the repository and submit a pull request for review. Connect me on [LinkedIn](https://in.linkedin.com/in/bhimavarapu-manoj-kumar-reddy)

## For Assignment Project Requirements

- **[Backend Intern Assignment @ GyanGrove](https://docs.google.com/document/d/1roMRKnjb2z8ap7K4F0Ls3GVP-_WoXhR85ePqOLXdk9Y/mobilebasic)**
- **[Backend_assignment_gg_dataset - dataset.csv](https://drive.google.com/file/d/1sZXyOT_V1NcZj3dDQIKY9Ea_W7XdGum_/view)**
- **[Frontend Dev Internship Assignment @ GyanGrove](https://docs.google.com/document/d/1G7KEJTCsgZzvzqVqmg23Zw7wDYm3Ioai597U6749ntU/mobilebasic)**
