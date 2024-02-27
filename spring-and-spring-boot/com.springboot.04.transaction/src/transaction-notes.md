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

 - **Atomicity** describes an all or nothing principle. Either all operations performed within the transaction get executed or none of them.
	 - That means if you commit the transaction successfully, you can be sure that all operations got performed.
	 - It also enables you to abort a transaction and roll back all operations if an error occurs.
   
 - **Consistency** characteristic ensures that your transaction takes a system from one consistent state to another consistent state.
	 - That means that either all operations were rolled back and the data was set back to the state you started with or the changed data passed all
	   consistency checks. In a relational database, that means that the modified data needs to pass all constraint checks, like foreign key or unique constraints, 	   defined in your database. 
   
  - **Isolation** means that changes that you perform within a transaction are not visible to any
   other transactions until you commit them successfully .
   
  - **Durability** ensures that your committed changes get persisted.

## NESTED Propogation -   

- NESTED acts like REQUIRED, only it uses savepoints between nested invocations.
- For NESTED propagation, Spring checks if a transaction exists, and if so, it marks a save point. This means that if our business logic execution throws an exception, then the transaction rollbacks to this save point. If there's no active transaction, it works like REQUIRED.

## MANDATORY Propagation -   
- I’m not going to open up a transaction myself, but I’m going to throw exception if no one else opened one up. No New connection will be created by me.
- If this inner logical transaction is rolled back, then the outer logical transaction is rolled back as well, exactly as with the case of Propagation.REQUIRED.


## NEVER Propogation -   
- I’m going to throw an exception if someone else started up a transaction.
- The Propagation.NEVER states that no physical transaction should exist.
- If a physical transaction is found, then NEVER will cause an exception as follows -
  `org.springframework.transaction.IllegalTransactionStateException: Existing transaction found for transaction marked with propagation 'never'`
- When you call a method annotated with NEVER, you must ensure that no physical transaction is open.
- The code inside this method can open physical transactions with no problem.
- eg: : When the code reaches the save() method, Spring will open a physical transaction especially for running this call.
- This happens because save() takes advantage of the default Propagation.REQUIRED. JPA Repository methods are by default enclosed in transaction boundaries.

## Propagation.NOT_SUPPORTED -  

- If a current transaction exists, first Spring suspends it, and then the business logic is executed without a transaction:
- This physical transaction will be automatically resumed at the end. After this transaction is resumed, it can be rolled back (in case of a failure) or committed.
- If the RuntimeException was thrown in child(), then this exception was propagated in parent(), and this logical transaction is rolled back.


## SUPPORTS Propogation -   

 - I don’t really care if a transaction is open or not, i can work either way
 -  For SUPPORTS, Spring first checks if an active transaction exists.
 -  If a transaction exists, then the existing transaction will be used.
 -  If there isn't a transaction, it is executed non-transactional.
