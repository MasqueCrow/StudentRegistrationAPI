# StudentRegistration API

Develop REST and GraphQL API with CRUD operations on Student and Subject entities to learn how to build microservices

### Setup

To get started with the app, make sure you have Java JDK, mySql, MySQL Workbench and Git installed on your machine

Clone this repo to your machine:
```ruby
$ git clone https://github.com/MasqueCrow/StudentRegistrationAPI.git

```

Next, install and update Maven dependencies defined in pom.xml 

Then, create a new database schema in mySql Workbench with the following tables:
**Student**

| Column    | Type         | Nullable |
| --------- | -----------  |--------  | 
| id        | int(11)      | no       |
| name      | varchar(45)  | no       | 
| mobile_no | int(11)      | yes      |
| city      | varchar(200) | yes      |

**Subject**

| Column    | Type         | Nullable |
| --------- | -----------  |--------  | 
| id        | int(11)      | no       |
| name      | varchar(45)  | no       | 

**Student_Subject**

| Column     | Type         | Nullable |
| ---------  | -----------  |--------  | 
| student_id | int(11)      | no       |
| subject_id | int(11)      | no       | 

**User**

| Column    | Type         | Nullable
| --------- | -----------  |------ | 
| id        | int(11)      | no    |
| username  | varchar(45)  | no    | 
| password  | varchar(45)  | no    |

Copy and Paste SQL queries in [db.sql](/src/main/resources/db.sql) to insert records to both tables

Run Java app and open browser to view the application:
```ruby
Rest: localhost:8989/student/{action}
GraphQL: localhost:8989/stud/{action}
```
