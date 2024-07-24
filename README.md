# Event Management System

## Introduction
This project is a RESTful service that manages and queries event data based on a User's Geographical Location and a specified Date. The service ingests data from a provided CSV dataset and offers an API to find events for users.

## Tech Stack and Database Choice

### Tech Stack
- **Spring Boot**: Chosen for it's comprehensive support for building Production-ready Applications and Ease of Integration with other Spring Components.
- **Spring Data JPA**: Facilitates the data access layer and abstracts much of the boilerplate code.

### Design Decisions
- **RESTful API Design**: Ensures Scalable and Stateless Communication between the Client (Postman) and Server.
- **Pagination & Sorting**: Implements pagination to manage large sets of data and improve performance.
- **External API Integration**: Retrieves Weather data and calculates distances dynamically, enhancing the relevance of event information.

## Setup and Running the Project

### Prerequisites
- **Java 17**: Ensure Java 17 is installed on your system.
- **Maven**: Maven is a Project Management tool, required for building and running the project.

### Running the Project

1. **Clone the Repository**
```sh
git clone https://github.com/BHIMAVARAPU-MANOJ-KUMAR/Event-Management-System-GyanGrove-Backend-Intern-Assignment.git
cd Event-Management-System-GyanGrove-Backend-Intern-Assignment
