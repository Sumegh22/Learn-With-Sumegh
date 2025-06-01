## High Level System Design: 

### Glossary:

1. **Vertical Scaling:** 
   * Optimize Processes using same/existing resource -> Here you reconfigure your resource with much better storage and CPU.

2. **Preprocessing:**
   * Prepare and deploy in cold hours, you might also require a backup
   
3. **BackUps:** 
   * Replicas of system data or system as a whole

4. **Horizontal Scaling:**
    * Buying and deploying more machines of similar type to share the load and optimize tasks

5. **Microservices Architecture:**
    * Distributes concerns and processes as miniature applications, or services
    * Decoupling-systems. 
   
6. **Distributed Systems:**
    * Ensuring your application/ service has high availability, you need to make it fault-tolerant. 
    * A Distributed System is said to have its presence at more than one location
    * Example could be Dominoz Pizza shop, when you place an order on the app
    * Your order is routed to the closest hub and then it gets delivered to u.

7. **Load Balancing:**
    * When a user request comes in it reaches a central place, from where it can be rerouted
    * This rerouting tool is load balancer and there has to be a mechanism which ensures request re-routing based on parameters

8. **Decoupling of Systems:**
    * Distributing services separately to ensure each of them is managed separately
    * Failure of one must not affect others

9. **Logging and metrics:**
    * To monitor health and metrics of the app
   
10. **Extensibility:**
    * The ability to extend a system
    * To re-use your code/ algorithm

---------------------------------------------

## Scaling:
   * The ability to make your application bigger by using bigger resources (Vertical scaling) or making using multiple resources of same or similar configs (Horizontal scaling)
   * Vertical Scaling : Deploying a bigger server to meet your demands. It can handle load, but it still is a single point of failure (SPOF)

### Horizontal Scaling vs Vertical Scaling

! [img1-scaling-comparison.png](img1-scaling-comparison.png).png](img1-scaling-comparison.png)


## Capacity Planning and Estimation:
   * Jot down few rough estimates to understand what are the bare minimum things to make an app up and running, 
   * Based upon the features and functionalities that are to be provided

! [img2-capacity-planning.png](img2-capacity-planning.png)
