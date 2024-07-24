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

## Contribution
Contributions are welcome! Please fork the repository and submit a pull request for review.

## Setup and Running the Project

### Prerequisites
- **Java 17**: Ensure **Java 17** is installed on your system.
- **Maven**: Maven is a Project Management tool, required for building and running the project.

### Running the Project

1. **Clone the Repository**
```sh
git clone https://github.com/BHIMAVARAPU-MANOJ-KUMAR/Event-Management-System-GyanGrove-Backend-Intern-Assignment.git
cd Event-Management-System-GyanGrove-Backend-Intern-Assignment
