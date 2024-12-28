# Customers-report-service API

Simple service that use template pattern in a real world example.

### Requirements
For building and running the application you need:

- [JDK 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Maven 3](https://maven.apache.org)

### Repository

```shell
[git clone https://github.com/Mauro2888/report-customers-service.git](https://github.com/Mauro2888/report-customers-service.git)
```

### Running the application locally
This will expose port 8081 in localhost.
This will expose port 8083 in localhost for testing.
Moreover, the application in dev redirect automatically to swagger UI
```shell
mvn quarkus:dev
```

### H2 Database
The application uses an in-memory H2 database.
You can access the console at [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
with the following credentials:
- username: sa
- password: sa
- Jdbc-url: jdbc:h2:mem:report

### Swagger UI
Open your browser and navigate to [http://localhost:8081/q/swagger-ui](http://localhost:8081/q/swagger-ui/#/)


## API Endpoints
### Report Generation
####  /api/v1/customers/report
* `POST` : The endpoint provides a POST request that generates a report from customer data.
- Request example
```json
{
  "startDate": "2015-01-01",
  "endDate": "2025-12-31",
  "reportType": "JSON"
}
```
- Response example
```json
[
  {
    "id": "116ef3c1-b76a-487b-9e3d-933dbf078694",
    "name": "John Doe",
    "address": "123 Main St",
    "phoneNumber": "555-555-5555",
    "createdAt": "2015-01-01"
  },
  {
    "id": "01700a8e-a703-4f56-992a-44d2f1c584b1",
    "name": "Jane Doe",
    "address": "456 Main St",
    "phoneNumber": "555-555-5555",
    "createdAt": "2016-01-01"
  }
]
```
### Data Types

reportType (enum: CSV, HTML, JSON, XML)
startDate (format: date)
endDate (format: date)


### Customer Retrieval (Read)
####  /api/v1/customers
* `GET` : The endpoint provides a GET request that retrieves a list of all customer data.
- Response example
```json
[
  {
    "address": "123 Main St",
    "id": "116ef3c1-b76a-487b-9e3d-933dbf078694",
    "name": "John Doe",
    "phoneNumber": "555-555-5555"
  }
]
```

### Customer Save (Create)
####  /api/v1/customers
* `POST` : The endpoint provides a POST request that saves a new customer.
- Request example
```json
{
  "name": "Luca Rossi",
  "phoneNumber": "123-456-7890",
  "address": "Via Roma, 123"
}
```

### Customer Address (Update)
####  /api/v1/customers/{id}
* `PATCH` : The /api/v1/customers/{id} endpoint provides a PATCH request that updates the customer address.
- Request example
- id (format: uuid)
```json
{
  "address": "Via Roma, 123"
}
```

- Response example
```json
  {
  "address": "Via Roma, 123",
  "id": "116ef3c1-b76a-487b-9e3d-933dbf078694",
  "name": "John Doe",
  "phoneNumber": "555-555-5555"
}
```
