# Microservices

1. A microservice is like an independent SpringBoot application that solves a purpose.
2. A huge application can be broker down into several microservices each solving a different problem, addressing a different constraints
3. Thus, breaking down Mononlith application into microservices provides an enterprise many benifits like
     - Independent And Rapid application development
     - Variety in tech, for ex: different teams owning different MS can use different prog language, Whole point is that they should be able to communicate
     - Fault Tolerance
     - Embeded configurations makes it is easier
     - Deployment time for application gets reduced etc.
  
4. Usually Microservices communicate with each other using REST protocol. a REST MS is a lightweight springboot app.
5. It uses JSON (Java Script Object Notation) object to transfer data
6. One has use HTTP Methods Such as (GET/ PUT/ PATCH/ POST/ DELETE) methods to perform CRUD operations on database and datasets
7. It is recommended that a dev writes a framework or mechanism for validation and exception handling for input validation from users, or busniess exception
8. In any case the REST app must send a valid and meaningful message from backend.
9. Document your REST services using Swagger or other tools
    
-------------------------------------------------------

Service Registry
----------------------
  - Service Registry or Discovery Mechanism acts as a Load Balancer mechanism between clients and your application. It takes-in the client requests and based on requirements routes the requests to end points of Microservices which will perform business logic and return the response.
  - Here we will take the example of Eureka Server
  - Step 1 : Create a project for Service-Registry and Add following spring-dependencies
         - cloud-bootstrap : (spring-cloud-starter) this dependency provides Non-specific spring cloud features
         - Eureka Server : (spring-cloud-starter-netflix-eureka-server) this dependency acts as spring cloud discovery service
  - Step 2: Go to main class, ( class annotated with @SpringBootApplication) and add one more annotation `@EnableEurekaServer`
  - Step 3: To avoid the service to self-register in the discovery list, we have to exclude our MS used for Service resgistry
         - configure the Application.yaml or configmap.yaml as below. registr-with-eureka as false and Set fetch-registry as false
         - `Note` : The Eureka Server's default port is 8761
```
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
server:
  port: 8761
```
- Step 4: Register this new created service as a Client in all your other Microservices
----------------------------------------------------

Service Discovery
-----------
- Here we will make use of Spring cloud eurka client
- refer configs from : https://github.com/Sumegh22/Microservices-Tutorial-Series/blob/main/UserService/UserService/src/main/resources/application.yml
