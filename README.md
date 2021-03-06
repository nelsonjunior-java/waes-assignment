# Waes Assignment

Project
===================
This project's main goal is to create a tool to analyze the differences between two base64 encoded value payloads.

# Architecture


[![N|Solid](https://i.imgur.com/lHJVOav.png)](https://www.wearewaes.com)


The application was built using the microservices architecture approach in order to make it scalable by nature.
I chose a more complex architecture to solve the problem dividing it in microservices in order to demonstrate my knowledge of these subjects.
Each service has its own responsibility.

| Service       | Description |
| ------------- | ------------- |
| data-diff-service   |  Compare values and return differences when they exist |
| decoder-service | This service is responsible for getting a base64 encoded value and decoding it  |
| registry-service  | This service follows the registry and service discovery pattern |

The services don't have a state and can be easily scaled using docker-compose.

## Database

| Database       | Description |
| ------------- | ------------- |
| MongoDB   |  This is the database used by the data-diff-service |

All components of this application were chosen with scalability in mind, MongoDB for example was chosen as the data-diff-service database due 
to its performance benefits and also the horizontal scaling capability.

MongoDB credentials:

user: ```data_diff_user``` 

password: ```waes_assignment```

database: ```data_diff```

You can start the MongoDB using the ```docker-compose.yml``` file as is explained in the next sections.

### Recomended MongoDB GUI:
[Robo3T](https://robomongo.org/)

## Technologies used in the project

* Language
   * Java 11

* Unit Tests
    * Junit

* Integration Tests
    * cucumber
    * rest-assured
    * testcontainers

* Build automation
    * Gradle 6.7

* Framework
   * SpringBoot
   * SpringCloud
* Libs
    * Lombok    
    * Swagger    

## Getting Started

### Pre-requisite
Before running the project on your local machine, please make sure that the following items are installed;
- docker
- docker-compose

### Installation

Clone the source code.

```sh
git clone https://github.com/nelsonjunior-developer/waes-assignment
```

#### Running Tests

```sh
./buildAllProjects.sh
```

#### Running Integration Tests  
```sh
./runIntegrationTests.sh
```

#### Building the docker images
```shell
./buildDockerImage.sh
```

# How to run
## On docker
You can run the application using docker, after building the projects and running the script ```./buildDockerImage.sh ```
For starting the application after following the previous steps, you can use this docker-compose command:
```shell
docker-compose up -d
```

For stopping all containers:
```shell
docker-compose stop
```
For stopping and removing containers and networks:
```shell
docker-compose down
```
## On Gradle
Before running the application locally with gradle, it's necessary to start the MongoDB that is configured in the ```docker-compose.yml``` file as follows:
```shell
docker-compose up -d mongo-database
```
Each project can be executed individually through the gradle wrapper:
```shell
./registry-service/gradlew -p registry-service bootRun
```

## Accessing the application
The application (data-diff-service) that is the entry point to communicating to the other services runs on port 8083 and can be acesses at:
```
http://localhost:8083
```
## Swagger
The application Swagger documentation can be accessed at:
```
http://localhost:8083/swagger-ui.html
```

### Application usage examples

#### Example #1 - When values are EQUAL 

##### Saving a Left value
```
curl -X POST "http://localhost:8083/v1/diff/1/left" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"base64EncodedValue\": \"bXkgZXF1YWwgdGVzdGluZyBlbmNvZGVkIHZhbHVlIQ==\"}"
```
Response body:
```
{
  "diffResponseStatus": "WAITING_EVALUATION"
}
```

##### Saving a right value
```
curl -X POST "http://localhost:8083/v1/diff/1/right" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"base64EncodedValue\": \"bXkgZXF1YWwgdGVzdGluZyBlbmNvZGVkIHZhbHVlIQ==\"}"
```
Response body:
```
{
  "diffResponseStatus": "WAITING_EVALUATION"
}
```

##### Comparing the previously saved values
```
curl -X GET "http://localhost:8083/v1/diff/1" -H "accept: */*"
```
Response body:
```
{
  "diffResponseStatus": "EQUAL"
}
```

#### Example #2 - When values have DIFFERENT SIZE

##### Saving a Left value
```
curl -X POST "http://localhost:8083/v1/diff/2/left" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"base64EncodedValue\": \"bXkgc21hbCB0ZXN0aW5nIGVuY29kZWQgdmFsdWU=\"}"
```
Response body:
```
{
  "diffResponseStatus": "WAITING_EVALUATION"
}
```

##### Saving a right value
```
curl -X POST "http://localhost:8083/v1/diff/2/right" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"base64EncodedValue\": \"bXkgc21hbCB0ZXN0aW5nIGVuY29kZWQgdmFsdWUgYmlnZ2VyIGluIHNpemUhISE=\"}"
```
Response body:
```
{
  "diffResponseStatus": "WAITING_EVALUATION"
}
```

##### Comparing the previously saved values
```
curl -X GET "http://localhost:8083/v1/diff/2" -H "accept: */*"
```
Response body:
```
{
  "diffResponseStatus": "DIFFERENT_SIZE"
}
```

#### Example #3 - When values have SAME SIZE WITH DIFFERENCES

##### Saving a Left value
```
curl -X POST "http://localhost:8083/v1/diff/3/left" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"base64EncodedValue\": \"eyAibmFtZSI6IkFsYmVydCJ9Cg==\"}"
```
Response body:
```
{
  "diffResponseStatus": "WAITING_EVALUATION"
}
```

##### Saving a right value
```
curl -X POST "http://localhost:8083/v1/diff/3/right" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"base64EncodedValue\": \"eyJuYW1lIjoiU2NvdHR5In0=\"}"
```
Response body:
```
{
  "diffResponseStatus": "WAITING_EVALUATION"
}
```

##### Comparing the previously saved values
```
curl -X GET "http://localhost:8083/v1/diff/3" -H "accept: */*"
```
Response body:
```
{
  "diffResponseStatus": "SAME_SIZE_WITH_DIFFERENCES",
  "diferences": [
    {
      "offset": 9,
      "length": 6
    }
  ]
}
```



### Suggestions for improvements

* Implement some microservices resilience patterns such as Circuit Breaker and Retry

* Create an API Gateway as a common entrypoint routing requests to the services

* Implement some authentication and authorization method such as OAuth or JWT

* Implement a caching system with Redis to avoid unnecessary hits on the mongo DB and improve app performance
