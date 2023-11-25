## Threads Interview Questions: 

Here are some questions related to threading concepts in Java along with their answers:

### Q1: What is a thread in Java?
* Answer: A thread is the smallest unit of execution in Java. It represents an independent path of execution, allowing multiple tasks to be performed concurrently.

### Q2: How can you create a thread in Java?
* Answer: There are two ways to create a thread in Java: by extending the Thread class or by implementing the Runnable interface.

### Q3: What is the difference between Thread and Runnable in Java?
* Answer:
When a class extends Thread, it cannot extend any other class, limiting its flexibility.
Implementing Runnable allows a class to extend another class while still being able to run as a thread.*

### Q4: Explain the concept of synchronization in Java.
* Answer: Synchronization in Java is the capability to control the access of multiple threads to shared resources. It prevents data corruption and maintains data consistency.

### Q5: What is the purpose of the synchronized keyword?
* Answer: The synchronized keyword is used to control access to critical sections by allowing only one thread to execute the synchronized code block or method at a time.

### Q6: What is the Java Memory Model (JMM)?
* Answer: The Java Memory Model defines the rules for how threads interact through memory. It ensures that the changes made by one thread are visible to other threads.

### Q7: Explain the difference between wait(), notify(), and notifyAll() in Java.
* Answer:
wait(): Causes the current thread to wait until another thread notifies it.
notify(): Wakes up one of the threads that are currently waiting on the object.
notifyAll(): Wakes up all threads that are currently waiting on the object.*

### Q8: What is the purpose of the volatile keyword in Java?
* Answer: The volatile keyword is used to indicate that a variable's value may be changed by multiple threads simultaneously. It ensures that a thread sees the most recent modification to the variable.
