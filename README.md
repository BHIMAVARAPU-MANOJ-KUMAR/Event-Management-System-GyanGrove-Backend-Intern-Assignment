# Event Management System

## Introduction
This project is a RESTful service that manages and queries event data based on a User's Geographical Location and a specified Date. The service ingests data from a provided CSV dataset, store it in a database, and offers an API to find events for users based on user-specified criteria. The system integrates with external APIs to provide additional information such as weather conditions and distances from the user's location to the event locations.

## Features
- **Data Ingestion**: Upload event data from a **CSV file/dataset** and store it in the database, using a dependency **commons-csv** by org.apache.commons
- **Event Finder API**: Retrieve events occurring within the next **14 days** from a specified **date** based on user's **Latitude** and **Longitude**.
- **Weather Integration**: Fetch **weather** information for event locations on the specified event dates.
- **Distance Calculation**: Compute the **distance** between the user's location and the event locations.
- **Pagination & Sorting**: Support for **pagination** and **sorting** to efficiently handle large datasets.
- **Error Handling**: Robust error handling for external API failures and other potential issues.

## Tech Stack and Database Choice

### Tech Stack
- **Spring Boot**: Chosen for it's comprehensive support for building **Production-ready** Applications and Ease of Integration with other Spring Components.
- **Spring Data JPA**: Facilitates the data access layer and abstracts much of the boilerplate code.
- **Maven**: Maven is a Project Management tool, required for building and running the project.
- **PostgreSQL**: Chosen for its robustness, performance, and support for advanced data types.

### Design Decisions
- **RESTful API Design**: Ensures Scalable and Stateless Communication between the Client (**Postman**) and Server (**Backend Application**).
- **Pagination & Sorting**: Implements pagination to manage large sets of data and improve performance.
- **External API Integration**: Retrieves Weather data and calculates distances dynamically, enhancing the relevance of event information.

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
spring.datasource.username=your_username
spring.datasource.password=your_password
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
