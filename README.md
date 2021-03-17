# Waes Assignment

Project
===================
This project main goal is to create a tool to analyze the differences between two base64 encoded value payloads.

#Architecture
The application was built using the microservices architecture approach in order to make it scalable by nature.
I chose a more complex architecture to solve the problema dividing it in microservices in order to demonstrate my knowledge of these subjects.
Each service has its own responsibility.

| Service       | Description |
| ------------- | ------------- |
| data-diff-service   |  Compare values and return  differences when they exist |
| decoder-service | This service is responsible for getting an base64 encoded value and decoding it  |
| registry-service  | This service follows the registry and service discovery pattern |

| Database       | Description |
| ------------- | ------------- |
| MongoDB   |  This is the database used by the data-diff-service |

All components of this application were chosen with scalability in mind, MongoDb for example was chosen as the data-diff-service database due 
to its performance benefits and also the horizontal scaling capability.

The services don have a state and can be easily scaled using docker-compose.

## Technologies

## Getting Started

### Pre-requisite
Before running the project on your local machine, please make sure that following items are installed;
- docker
- docker-compose

### Installation

Clone the source code.

```sh
$ git clone https://github.com/nelsonjunior-developer/waes-assignment
```

#### Running Tests

```sh
$ ./buildAllProjects.sh
```

#### Running Integration Tests  
```sh
$ ./runIntegrationTests.sh
```

#### Building the docker images
```shell
$ ./buildDockerImage.sh
```

# How to run
## On docker
You can run the application using docker, after running the script ```./buildDockerImage.sh ```
For starting the application after following the previous steps, you can use the following docker-compose command:
```shell
$ docker-compose up -d
```
## On Gradle
Each project can be executed individually through gradle wrapper:
```shell
$ ./registry-service/gradlew -p registry-service bootRun
```

## Accessing the application
The application (data-diff-service) runs on port 8083 and can be acesses at:
```
http://localhost:8083
```
## Swagger
The application Swagger documentation can be accessed at:
```
http://localhost:8083/swagger-ui.html
```
























[comment]: <> (##Suggestions for improvements)

[comment]: <> (* Implement some microservices resilience patterns such as Circuit Breaker and Retry)

[comment]: <> (* Create an API Gateway as a common entrypoint routing requests to the services)

[comment]: <> (* Implement some authentication and authorization method such OAuth or JWT)
