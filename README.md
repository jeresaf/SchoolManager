
# School Manager

A web application to control (reflect the creation, modification, editing, deletion)
students and teachers. Information about students (id, name, surname) and teachers (in,
name, list of students)

## Running the project

To run the project successfully, follow the steps below:

- Start by clonning the project from github

### Setting up the database

Create a database by running the foloowing file in your sql terminal by:

- Redirect to the project root folder
- Open terminal at that location
- Run the following commands
- Replace your <username> with your database username

`mysql -u <username> -p`

- Then type in your password otherwise use the following command

`mysql -u <username>`

- Type the next command to create the database using a file located in the project root;

`source create_database.sql`

- Otherwise you can type the next command directly in the terminal

`CREATE DATABASE school_manager;`

- Exit the sql terminal by using

`exit`

### Change the database credentials

- Navigate to and open the file: src/main/resources/application.properties
-Change the following lines to match your database username and password

`spring.datasource.username= root`
`spring.datasource.password= 123`

### Start the project

- The following code can be used to start the project while in the project root

`./mvnw clean spring-boot:run`

- Navigate to localhost:8080 to view the web application

### Running tests

- To run tests, update the following properties in the src/main/resources/application.properties file by:
- Comment out all lines before the line 
`# Unit Test Configurations`
- We do this since we use the H2 databse for JPAUnit tests
- Tests can then be run using 
`./mvnw clean install`
`./mvnw test`

