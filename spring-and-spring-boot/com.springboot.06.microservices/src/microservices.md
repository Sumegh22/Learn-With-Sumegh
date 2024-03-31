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
  - Step 2: Go to main class, ( class annotated with @SpringBootApplication) and add one more annotation *@EnableEurekaServer*
  - Step 3: To avoid the service to self-register in the discovery list, we have to exclude our MS used for Service resgistry
         - configure the Application.yaml or configmap.yaml as below. registr-with-eureka as false and Set fetch-registry as false
         - `Note` : The Eureka Server's default port is 8761

  - Step 4: Your service registry must not self register, hence in the application.yaml file add the below mentioned config so that service-registry MS does not register itself    
    
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

- Step 5: Register this new created service as a Client in all your other Microservices
----------------------------------------------------

Service Discovery
-----------
- Now you need to configure each of your service so that they get discovered by the service-registry
- Step 1 : Add spring-cloud-netflix-eureka-client dependency in pom.xml
- Step 2 : Annotate the main class with *@EnableDiscoveryClient*
```
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
```
- Step 3 : Add configs in the application.yaml as shown here : [(https://github.com/Sumegh22/animeworld-configs-repo/blob/main/application-dev.yaml)](https://github.com/Sumegh22/animeworld-configs-repo/blob/main/application-dev.yaml)
- Step 4 : The next time you boot your application it will be registered as available and no matter how many time this service's instace go down and new instance with hostname and port are generated this service can be connected using the name given in service-registry server.

----------------------------------------------------

RestTemplate calls 
-----------  

- When there is a one to one communication betweeen service, you can use this method to establish communication between them.
  
```
    public List<Rating> getRatingsByUserId(String userId){
        Rating[] ratingsByThisUser =  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId, Rating[].class);
        List<Rating> ratings = Arrays.asList(ratingsByThisUser);
        List<Rating> allRatingsByUser = ratings.stream().map(rating -> {
          ResponseEntity<Anime> animeResponseEntity =  restTemplate.getForEntity("http://ANIME-SERVICE/anime/"+rating.getAnimeId(), Anime.class);
          Anime anime = animeResponseEntity.getBody();
          rating.setAnime(anime);
          return rating;
        }).collect(Collectors.toList()) ;
        return allRatingsByUser;
    }
```

----------------------------------------------------

Feign Client 
-----------  

-
-
-
-


----------------------------------------------------

Error Handling in Rest Calls | @RestControllerAdvice
----------- 

-
-
-
-

----------------------------------------------------

Externalized configurations using Config-Server App 
----------- 

-
-
-
-

----------------------------------------------------

Fault Tolerance | Circuit Breaker 
----------- 

-
-
-
-

----------------------------------------------------
