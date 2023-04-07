# Dynamic Table Query by REST API
This is a simple Spring Boot application that allows you to execute dynamic SQL queries against a database using the javax.persistence.EntityManager API. The application exposes a REST endpoint that accepts a query parameter containing a SQL SELECT statement and returns the result set data as a list of JSON objects with named columns.
## Requirements
-   Java 8 or higher
- Spring Boot 2.5.0 or higher
- H2 Database or any other relational database

## Getting Started
1. Clone this repository to your local machine.
2. Open the project in your IDE.
3. Run the application using mvn spring-boot:run or by running the DynamicQueryApplication class.
4. Once the application is running, you can access the H2 Console by visiting `http://localhost:8080/h2-console`. Enter `jdbc:h2:mem:dcbapp` as the JDBC URL and click `connect`.
5. You can then execute SQL queries using the executeDynamicQuery endpoint. The endpoint URL is http://localhost:8080/?query=<your-query-here>. Replace <your-query-here> with your desired SQL query.

## Example
To execute a dynamic SQL query, make a GET request to the executeDynamicQuery endpoint with your desired SQL query as a parameter. For example, to retrieve all records from the employee table, you can make the following request:

```
GET http://localhost:8080/?query=SELECT * FROM employee
```
The response will be a JSON object containing the result set data. Here's an example response:

```json
[
    {
        "ID": 1,
        "EMAIL": "john@example.com",
        "SALARY": 10000.00,
        "DEPARTMENT_ID": 1
    },
    {
        "ID": 2,
        "EMAIL": "casey@example.com",
        "SALARY": 12000.00,
        "DEPARTMENT_ID": 1
    },
    {
        "ID": 3,
        "EMAIL": "jane@example.com",
        "SALARY": 8000.00,
        "DEPARTMENT_ID": 2
    }
]
```

```
GET http://localhost:8080/?query=SELECT * FROM employee JOIN department ON employee.department_id = department.id
```

```json
    [
        {
            "ID": 1,
            "EMAIL": "john@example.com",
            "SALARY": 10000,
            "DEPARTMENT_ID": 1,
            "NAME": "Engineering"
        },
        {
            "ID": 1,
            "EMAIL": "casey@example.com",
            "SALARY": 12000,
            "DEPARTMENT_ID": 1,
            "NAME": "Engineering"
        },
        {
            "ID": 2,
            "EMAIL": "jane@example.com",
            "SALARY": 8000,
            "DEPARTMENT_ID": 2,
            "NAME": "Sales"
        }
    ]
```

## Limitations
This application is designed for demonstration and testing purposes only and should not be used in production environments. It does not provide any security or access control mechanisms and assumes that the database connection is trusted.

## Contributing
If you find any issues with the project or want to contribute to its development, feel free to submit a pull request or open an issue.

## License
This project is licensed under the MIT License. See the LICENSE file for details.