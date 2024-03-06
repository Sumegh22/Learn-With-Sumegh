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
