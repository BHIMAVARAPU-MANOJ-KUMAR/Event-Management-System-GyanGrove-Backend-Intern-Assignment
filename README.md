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
|-- eventscontroller
|   |-- EventsController.java
|
|-- datauploaddownloadcontroller
|   |-- DataCsvFileUploader.java
|   |-- DataCsvFileUploader.java
|
|-- service
|   |-- eventservice
|       |-- EventsService.java
|   |-- eventfileservice
|       |-- EventsFileService.java
|
|-- eventsrepository
|   |-- EventsRepository.java
|
|-- entity
|   |-- Events.java
|
|-- dtos
|   |-- EventsRequest.java
|   |-- EventsResponse.java
|
|-- openapiswaggerconfiguration
|   |-- SwaggerConfig.java
|
|-- applicationutil
|   |-- CsvHelper.java
|   |-- AppUtils.java
|
|-- exceptionshandlers
|   |-- GlobalExceptionHandler.java
|
|-- externalapis
|   |-- DistanceCalculationAPI.java
|   |-- WeatherAPI.java
|
|-- eventsresponse
|   |-- EventsFinderResponse.java
|   |-- EventsResponseWithExternalAPIs.java
|
|-- filters
|   |-- CustomHeadersFilter.java
|   |-- CustomHttpServletRequest.java
|
|-- mainapplication
|   |-- EventsmanagementApplication.java
|
|--------------------------------------------------------
```
### Conclusion
The package-by-layer approach was chosen for the Events Management System project to ensure a well-organized, maintainable, and scalable codebase. This architecture supports clear separation of concerns, facilitates testing and reusability, and provides a structured way to manage the application’s growth. By adhering to this architecture, the project becomes more robust, easier to manage, and adaptable to future changes and enhancements.

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

## Contribution
Contributions are welcome! Please fork the repository and submit a pull request for review.

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

4. **Run the Application**
```sh
mvn spring-boot:run
```

5. **Access the Application**
```sh
Access the Application on `http://localhost:8090/api/v1/**/**`
```

## API Endpoints
### Upload Events CSV File/Dataset
- **Endpoint**: `POST /api/v1/import/csv`
- **Description**: Uploads a CSV file containing event data and processes it.
- **Request**:
```sh
curl -F "file=@path/to/your/file.csv" http://localhost:8090/api/v1/import/csv
```

### Find Events
- **Endpoint**: `GET /api/v1/events/find`
- **Description**: Finds events based on user's latitude, longitude, a specified date, page and size.
- **Request**:
```sh
curl "http://localhost:8090/api/v1/events/find?latitude=40.7128&longitude=-74.0060&date=2024-03-15&page=1&size=10"
```
