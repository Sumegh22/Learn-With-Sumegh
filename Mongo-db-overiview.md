# Mongo DB essentials

## Overview
- MongoDb was launched in 2009 and it became immesely popular with its lauch because of the feature it was offering, It allowed us to use and store data the it the form it was being used in applications i.e. Documents.
- With time there have been several improvements in MongDB which makes it reliable and a good choice for any data driven system. Few examples are Schema Validation
- Mongo DB follows ACID principles, Joins
- The Mongo instances can be scalled horizontally -> ( Sharding your data)
- Client side level encryption

## Mongod 
1. Mongod is a Deamon process, and is a core unit of mongodb
2. It handles data requests from mongo shell or mongodb drivers, manages data acess and performs management operations
3. Multiple Mongod instances having same datasets are refferred as replica set. As a good practice each replica set must have at least 3 or more members.
4. Meaning your data should be copied at least 2 other instances. One of the instance ( Node) is primary and the other two are secondary
5. The Primary node is the one that recieves any read / write operations in the first place.
6. The secodary nodes take all the write changes being node on primary node by connecting asynchronoulsy.
7. If a primary becomes unavailable, The remaining secondary nodes go into election to choose one secondary node to replace the primary, In this election, more than 50% if the nodes must vote for a particular secondary node so that it becomes the primary
8. This way one of the secondary nodes becomes primary by auto balancing, This is how high availability is achived in MongoDb
9. It is worth noticing that if your replica set has only two nodes 1 primary and 1 secondary in this case if the primary goes down the secondary will not become primary
   
