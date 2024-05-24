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
- wait(): Causes the current thread to wait until another thread notifies it.
- notify(): Wakes up one of the threads that are currently waiting on the object.
- notifyAll(): Wakes up all threads that are currently waiting on the object.
- yield(): Holds the current thread and provides cpu or computing power to another thread

### Q7: Explain how would you stop or pause a Thread   
- **Thread.stop():** The Thread.stop() method is a deprecated method that can be used to stop a thread. However, it is not recommended to use this method as it can lead to unexpected results. The Thread.stop() method throws an InterruptedException.
- **Thread.interrupt():** The Thread.interrupt() method can be used to interrupt a thread. When a thread is interrupted, it throws an InterruptedException. The interrupted thread can then handle the exception or ignore it.
- **Thread.join():** The Thread.join() method can be used to wait for a thread to finish executing. The Thread.join() method blocks the calling thread until the joined thread finishes executing.
- **Thread.sleep():** The Thread.sleep() method can be used to pause the execution of a thread for a specified period of time. The Thread.sleep() method throws an InterruptedException
- **Thread.yield():** Giving Up CPU Time in Java
The Thread.yield() method suggests to the thread scheduler that the current thread is willing to yield its current time slice. While it does not guarantee a pause, it might allow other threads with equal or higher priority to run. However, it is important to note that relying solely on Thread.yield() for thread coordination is not recommended, as it depends on the thread scheduler’s behavior and might not provide consistent results across different Java Virtual Machine (JVM) implementations.

### Q8: What is the purpose of the volatile keyword in Java?
* Answer: The volatile keyword is used to indicate that a variable's value may be changed by multiple threads simultaneously. It ensures that a thread sees the most recent modification to the variable.
