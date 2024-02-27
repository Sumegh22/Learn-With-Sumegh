# Notes and Interview Questions on Transaction in SpringBoot


## How to implement transaction in spring / Hibernate?
When you integrate Your hibernate with a spring boot project then you don’t need to use Hibernate Transaction Management, as you can leave it to the Spring declarative transaction management using @Transactional annotation. 

## Using @Transactional annotation. What Is @Transactional? 
We can use @Transactional to wrap a method in a database transaction. It allows us to set propagation, isolation, timeout, read-only, and rollback conditions for our transaction. 

## How @Transactional works internally ? 
Spring creates a proxy, or manipulates the class byte-code, to manage the creation, commit, and rollback of the transaction. If we have a method like callMethod and we mark it as @Transactional, Spring will wrap some transaction management code around the invocation@Transactional method called: createTransactionIfNecessary(); 

```
	try {
	     addEmployee(); 
             commitTransactionAfterReturning(); 
	     } catch (exception) { 
		 rollbackTransactionAfterThrowing(); 
		 throw exception; 
		}

```

## How to use @Transactional ?
You can use this annotation on following in the lowest to highest priority order : interface, superclass, class, interface method, superclass method, and method. The EmployeeService class is annotated at the class level with the settings for a read-only transaction, but the @Transactional annotation on the addEmployee() method in the same class takes precedence over the transactional settings defined at the class level. Usually it's not recommended to set @Transactional on the interface; however, it is acceptable for cases like @Repository with Spring Data. We can put the annotation on a class definition to override the transaction setting of the interface/superclass: 

## What is a transaction? 
Transactions manage the changes that you perform in one or more systems. These can be databases, message brokers, or any other kind of software system. The main goal of a transaction is to provide ACID characteristics to ensure the consistency and validity of your data. 

## What is ACID transaction?
 ACID is an acronym that stands for 
 - atomicity,  consistency,  isolation, and  durability: 

 - Atomicity describes an all or nothing principle. Either all
   operations performed within the transaction get executed or none of
   them. That means if you commit the transaction successfully, you can
   be sure that all operations got performed. It also enables you to
   abort a transaction and roll back all operations if an error occurs.
   
 - The consistency characteristic ensures that your transaction takes a
   system from one consistent state to another consistent state. That
   means that either all operations were rolled back and the data was
   set back to the state you started with or the changed data passed all
   consistency checks. In a relational database, that means that the
   modified data needs to pass all constraint checks, like foreign key
   or unique constraints, defined in your database. 
   
  - Isolation means that changes that you perform within a transaction are not visible to any
   other transactions until you commit them successfully .
   
  - Durability ensures that your committed changes get persisted.
