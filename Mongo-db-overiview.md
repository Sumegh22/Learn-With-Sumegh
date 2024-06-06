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
    
## MongoShell


## SetUp
1. Create replicasets using config files.
2. config = {_id = "your_rs_name", members[{Array of},{members/ instances},{ in replica set}]
3. rs.initiate(config) here argument is name of the config file you created
4. Note : use passwordPromt() method while using mongoshell (mongosh) so that your password is not made vsisible, rather you get a prompt to type password after submitting a command.
5. rs,getSiblingDB("name-of-database").auth("username") provide password when prompted
6. rs.status() -> returns the status of your replicaset
7. It will contain list of all the members in current replicaset
8. db.serverStatus()['repl']

## Debug   
1. To check the op logs -> db.oplog.rs
2. db.getLogComponents() to check log levels and modify

## CRUD
1. use db test -> will automatically create test db if it does not exists.
2. Inside Mongo Db there are dbs or say Schemas and inside schemas we have collections ( like tables)
3. MQL or MongoDb Query Language is used to perform the CRUD operations, it is also known as MongoDB Query Api
4. insertOne and insertMany() methods are used to insert documents in mongodb
5. While writing you can specify the writeConcerns to the mongoDb.
6. Write Concerns could be 1 or majority, If you need your change, to persist and get replicated to majority nodes in the replica set only after which the confirmation will be sent to the client then in such case the w (writeConcern) has to be set to majority.
7. This way the writing mechanism would take a little longer but the durability is maintained.
   
