# Message Driven Systems

## Synchronous
1. Request->Response blocking also known as Sycn communication
2. Client stay idle and wait for response
3. Latency subject to chain of actions

## Asynchronous 
1. The client can make a request and can do other processing whilst it awaits the response


## Event Driven
1. In this model we are not expecting a response, when an event occurs
2. Services work asynchronously, and simply broadcast events or commands in the form of messages
3. Since there isn't any micro-service that is awaiting to send response, systems are very Loosely coupled.

## What is Kafka 
**Apache Kafka is an open-source, distributed event streaming platform**
* Kafka works on a pub-sub model -> meaning the publisher service will produce and publish message on a specific topic and then the subscriber service will consume messages from the topics**
* Since it is distributed, It can sustain heavy load and is fault tolerance
* Kafka can retain message for long-long time as configured. 
* As the name suggests it acts as a broker between communicating services. 
* event streaming can be simplified as creation of an event stream and processing it in real time

## Example:
1. PayTm users across globe make payments-> which creates an event, Once Kafka server recieves data, the client ( written by Devs as Paytm) can consume messages/ events from here and act accordingly.
2. Giving cashback to user
3. Restricting free upi transactions in a day (Post which user will be charged)

## Why use it ?..
1. Kafka uses pull mechanism to read message
2. other MQ services use push mechanism
3. Queue discard messages when read. Kafka retain them as defined
4. Message replay

![kafka-architecture-img.png](..%2Fimages%2Fkafka-architecture-img.png)

## Key Terminologies in Kafka world :
1. Kafka Cluster : Group of Kafka Brokers 
2. Broker : An individual server or instance of kafka. A broker acts as a station between your producing agent (MS) and a consuming agent (MS)
3. Producer: Producer produces/ writes on kafka broker using a kafka topic.
4. Consumer: Consumer consumes/ reads messages from Kafka broker using kafka topic
5. Zookeeper: Zookeeper is a pre-requisite to kafka, Kafka is a distributed system, and it uses Zookeeper for coordination. Keeps track of Kafka CLuster health, topics partitions etc.
6. Kafka-Connect: If you want to pull data from external entity into Kafka cluster -> then you can use kafka-connect. This is called declarative integration.
   For inward movement from source -> kafka-broker use kafka-connect-source integration
   For outward movement from kafkabroker to external db -> use kafka-connect-sink 

7. Kafka-Streams: kafka-stream api enables one to read data from kafka-broker in the form of stream, perform transformation and dump back data onto kafka again

Kafka Topic and Partitions
--------------------

### Kafka Topic: 
1. Named container for similar events/ messages . A topic's name is its unique identifier
2. Ex. Student topic must have student related data only. A topic can be compared to tables in a database
3. Topics reside inside the Kafka-Brokers
4. Producer produce messages, each message is written on a kafka-topic (on partition of topic) using round-robin mechanism

### Partition:
1. Kafka topics are split (partitioned) in number as configured. It is so done to achieve distributed computing
2. Each partition contains an ordered, immutable sequence of records.
3. Each partition is independent of each other.
4. Each message that comes to a topic-partition is stored with an incremental id, known as Offset.
5. Ordering of messages is maintained per partition. Meaning, each partition will have separate order for incoming messages.
6. Thus, if ordering of messages is to be maintained, try sending message on same topic-partition
7. kafka-partitions are available to producers in round-robin fashion
8. If messages are sent along with Key (Key, Message) Pair. then Hashing is applied on key and when same key is recieved later on,
   when message is recived with same key then that message is pushed to the same partition when message with previous key was pushed.
   (Messages with same key are pushed to same partition, messages without key are pushed in round robin) 

### Replication factor:
1. A partition is replicated by its factor, and it is replicated in another broker to prevent fault tolerance 


## Consumer
**When a consumer group reads messages from a topic, each member of the group maintains its own offset and updates it
as it consumes messages.**
1. A consumer is always associated with a group id. Hence, when a consumer is spun up it is assigned a group id
2. Using group id multiple consumers can be put into same group
3. __consumer_offset is a built in topic in apache kafka that keeps track of latest offset committed for each partition of each  consumer group
4. The topic is internal to kafka cluster and not meant to be read or written to directly by clients.
5. The offset information is stored in the topic and updated by kafka broker to reflect the position of each consumer in each partition. 
6. The information in __consumer_offset topic is used by kafka to maintain the reliability of the consumer groups and to ensure that messages are not lost or duplicated.
7. When a consumer joins a consumer group it sends a join request to group co-ordinator (Not in picture when single consumer is used)
8. The grp coordinator determines which partitions the consumer should be assigned based on the number of consumers in the group and the current assignment of partitions to consumers.
9. The group coordinator then sends a new assignment of partitions to the consumer which includes the set of partitions that the cinsumer is responsible for sonsuming
10. the consumer starts consuming data from assigned partitions