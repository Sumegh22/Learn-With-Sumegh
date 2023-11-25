# Notes - Multithreading and Concurrency

## Single Threaded App Example

**Serial Threaded vs Multi-threaded -**
* Multi-threaded applications normally do parallel processing of tasks where as single threaded applications do one task at a time i.e. If there are two tasks such as T1, T2 they are executed in serial order i.e T1 after T2 or T2 after T1, where as multi threading enables us to execute them simultaneously i.e. T1 along with T2. We can choose single threaded applications when parallel processing is not required example use cases such as simple text editor.

* Below is a simple example for single threaded application, in this the Task need to print 1500 T's and Main is another task which prints 1500 M's. If we execute this in serial order then it is referred as serial execution.

**Serial Execution Example -**

* Some task; here it will print 1500 T's.


    class Task {
    
        public void doTask() {
        for(int i=1; i <= 1500; i++) {
            System.out.print("T");
        }
        }
    }

* Main

      public class Main {
      
          public static void main(String[] args) {
              
          // Print M's
          for(int i=1; i <= 1500; i++) {
              System.out.print("M");
          }
              
          // Call the task to print T's
          Task t1 = new Task();
          t1.doTask();
          }
      
      }

When you run the above example you will see 1500 M's first and then followed by 1500 T's.



===================================================================================================================================
### True Parallelism vs Logical Parallelism**

* True Parallelism vs Logical Parallelism
* True parallelism is achieved by assigning tasks to individual CPUs or Processors. This is possible through multicore processors or executing the tasks using multiple CPUs.

* If there is one CPU and you want to perform multiple tasks in parallel, then CPU will be shared across those tasks for some stipulated time interval, which stands for interleaved execution, and this way of executing the tasks is known as logical parallelism or psuedo parallelism.

* In case of logical parallelism, lets assume there are two tasks T1 and T2, when executed in parallel and using one CPU, CPU is switched between T1 and T2 i.e. CPU executed T1 for some stipulated time interval and then switched to T2. Once T2's time slice is completed then it is switched back to T1 and starts from where it stops.



**Example -**
* In the below example Task is a Thread(explained later), and run is the entry point of the thread execution where it starts printing 1500 T's.

* main() runs in Thread i.e. the Main thread which is started by the JVM.

      Note In the main method we are not calling doTask directly, instead we are using the start() method of the Thread class, which runs the Task using a separate Thread.



    class Task extends Thread {

          // Thread execution begins here.
        public void run() {
          // performs the task i.e. prints 1500 T's
          doTask(); 
        }
	
        public void doTask() {
          for(int i=1; i <= 1500; i++) {
              System.out.print("T");
          }
        }
    }

-----------------------------------------------------------------------------------------------------

    public class Main {

                // Runs with in the Main thread started by JVM.
      public static void main(String[] args) {
          
          Task t1 = new Task();
                  // Starts a separate Thread using the 
                  // the start method of the Thread class.
            t1.start(); 
       
                    // runs in the Main thread and prints 1500 M's
              for(int i=1; i <= 1500; i++) {
                  System.out.print("M");
              }	
        }
    }


Here main() and Task are run using two separate threads, which means they are executed in parallel (logical parallelism in case of single CPU) and hence you will see output like MMMTTTMMMTTT...

=============================================================================================================================

### Designing Threads

**Thread -**
A thread is a light weight process, it is given its own context and stack etc. for preserving the state. Thread state enables the CPU to switch back and continue from where it stopped.

**Creating Threads in Java -**
When you launch Java application, JVM internally creates few threads, e.g. Main thread for getting started with main() and GarbageCollector for performing garbage collection and few other threads which are need for execution of a Java application.

You can create a thread and execute the tasks with in the application, this enables you to perform parallel activities with in the application.

There are two approaches,

1) Extending the Thread class and performing the task. This is not a preferred approach because you are not extending the Thread functionality, instead you are using the Thread to execute a task, hence you should prefer the second approach.

2) Implementing the Runnable interface and then submitting this task for execution. Similarly there is a Callable interface(explained later) as well.



run() method -

Once you choose your approach, you can consider the run() method as the entry point for thread execution. To simplify just think like main() for a program, run() for a thread.

start() method -

Execution of the thread should be initiated using the start() method of the Thread class. This submits the thread for execution. This takes the associated thread to ready state, this doesn't mean it is started immediately. i.e. in simple terms, when you call the start() method, it marks the thread as ready for execution and waits for the CPU turn.



**1. Extending the Thread class.**

    class MyThread extends Thread 
    {
        // Thread execution begins here.
        public void run() {
            // DO THAT TASK HERE.
            for(int i=0; i <= 1000; i++) {
                System.out.print("T");
            }
        }

    }
-----------------------------------------------------------------------------------------------------

**2. Implementing the Runnable interface,**
// This marks this class as Runnable and
// assures that this class contains the run()
// method. Because any class implementing the
// interface should define the abstract method
// of the interface, otherwise it becomes abstract.

    class MyTask implements Runnable {          
                                                      // Thread execution begins here.
          @Override
          public void run() {
                                                      // DO THAT TASK HERE.
              for(int i=0; i <= 1000; i++) {
                  System.out.print("-");
              }
          }
    }


    public class Main {
        // Will run with in the main thread.
        public static void main(String[] args) 
        {
                
            // Because MyThread extends the Thread
            // class, you can call the start() method
            // directly, as it is also a member of this
            // class, courtesy inheritance relation.
            MyThread thr = new MyThread();
            thr.start();
                
            // MyTask is a Runnable task and not a 
            // Thread and hence we need to create a 
            // Thread object and assign it the task 
            // Note here we are calling the start 
            // method over Thread object and not on 
            // task object.
        
            MyTask task = new MyTask();
            Thread thr2 = new Thread(task);
            thr2.start();
                
                    for(int i=0; i <= 1000; i++) {
                        System.out.print("M");
                    }	
        }
    }

There are three threads in the above program (system threads ignored). One the Main thread that prints "M" and thr that prints "T" and thr2 that prints "-". Because they are executed in parallel, you will see the output like MMMTTT---MMMTTT--- ...

=============================================================================================================================

### Transform code to achieve parallelism

**Transforming serial code to parallel code using Threads.**





A simple utility to copy the content
* of the given source file to the given
  * destination file.
    * Copies the given source stream
    * i.e. src to the given
    * destination stream i.e. dest.
          

          package com.example.io.utils;
          import java.io.FileInputStream;
          import java.io.FileOutputStream;
          import java.io.IOException;
          import java.io.InputStream;
          import java.io.OutputStream;

          public class IOUtils 
          {
      
            public static void copy(InputStream src, OutputStream dest) throws IOException 
            {
              int value;
                  while ((value = src.read()) != -1) {
                    dest.write(value);
                  }
            }

                                                                 // Copies the given srcFile to the destFile.
    
            public static void copyFile(String srcFile, String destFile) throws IOException 
            {
              FileInputStream fin = new FileInputStream(srcFile);
              FileOutputStream fout = new FileOutputStream(destFile);
          
                copy(fin, fout);
          
                fin.close();
                fout.close();
            }
          }


**Serial Mode -**
In the below example we are making a direct call to copyFile and it is executed in serial order i.e. first Copy a.txt to c.txt is executed and then once it is done, the next copy i.e. b.txt to d.txt will be initiated.

Note - For this program to work you need to create two files a.txt and b.txt in the src/ folder and place some content in it.



    import java.io.IOException;
    import com.example.io.utils.IOUtils;

    public class Main {

      public static void main(String[] args) throws IOException {
          
        String sourceFile1 = "a.txt";
        String sourceFile2 = "b.txt";
            
        String destFile1 = "c.txt";
        String destFile2 = "d.txt";
            
        // 1. Copy a.txt to c.txt
        IOUtils.copyFile(sourceFile1, destFile1);
     
        // 2. Copy b.txt to d.txt
        IOUtils.copyFile(sourceFile2, destFile2);
      }
    }


**Parallel Mode -**
The two copy operations above are initiated through two different threads, which enables us to perform the operation in parallel. For this we defined a CopyTask which is a Runnable task, you should pass the source and the destination to the constructor which are then used to perform the copy operation once the task execution begins.

* A Runnable task to copy the given source file to the given destination file.

        import java.io.IOException;
        import com.example.io.utils.IOUtils;
        class CopyTask implements Runnable {
    
        String sourceFile;
        String destFile;
    
        public CopyTask(String sourceFile, String destFile) {
        this.sourceFile = sourceFile;
        this.destFile = destFile;
        }

  * Initiate the copy once thread execution begins.
      
        public void run() {
          try {
            IOUtils.copyFile(sourceFile, destFile);
            System.out.println("Copied from - " + sourceFile + " to " + destFile);
          }catch(IOException e) {
              e.printStackTrace();
              }
            }
        }

        public class Main {
        public static void main(String[] args) throws IOException {
            
        String sourceFile1 = "a.txt";
        String sourceFile2 = "b.txt";
            
        String destFile1 = "c.txt";
        String destFile2 = "d.txt";
            
        // A new thread is created to initiate copy 
        // from a.txt to c.txt
        // Thread-1
     
            new Thread(new CopyTask(sourceFile1, destFile1)).start();		
        
            // A new thread to initiate copy from 
        // b.txt to d.txt
        // Thread-2
     
            new Thread(new CopyTask(sourceFile2, destFile2)).start();
        }
        }
**Important Note -**
Although the main thread is completed after starting the two other threads, application won't be terminated until both the threads are done with the copy.

==========================================================================================================================

### Executor Service
**ExecutorService -**

* In the previous example we executed the copyTask using separate threads, but there is a critical point to consider here, Thread creation is a costly activity as it includes creating a separate execution context, stack etc.. Hence we should refrain from creating too many threads. And also creating a thread for each task is not a good idea, instead we can create a pool of threads and effectively utilise them in executing all the task. This could be achieved using ExecutorService in Java. Use the execute method of the ExecutorService to submit a Runnable task, if a thread is available in the pool then it assigns this task to the thread otherwise the task is added to the blocking queue and is kept till a thread is available.

**Creating a ThreadPool -**

Below statement creates a thread pool of size 5.

ExecutorService executor = Executors.newFixedThreadPool(5);


**Submitting a Runnable task -**

* We can submit a task for execution using the execute method.
  **executor.execute( runnableTaskInstance );**

* e.g. executor.execute(new CopyTask(sourceFile1, destFile1));



* Modified version of previous example to use ExecutorService -

      public class Main {
  
        public static void main(String[] args) throws IOException {
            
          String sourceFile1 = "a.txt";
          String sourceFile2 = "b.txt";
              
          String destFile1 = "c.txt";
          String destFile2 = "d.txt";
              
          // Creates a fixed thread pool of size 5.
              
          ExecutorService executor = Executors.newFixedThreadPool(5);
              
          // Assume you are submitting 100 copy tasks,
          // then executor service uses a fixed thread 
          // pool of size 5 to execute them.
       
              executor.execute(new CopyTask(sourceFile1, destFile1));
          executor.execute(new CopyTask(sourceFile2, destFile2));	
        }
  }
===================================================================================================================

### Stopping Thread in the middle

**Stopping threads in the middle -**
* stop() method of the Thread class could be used to stop the thread in the middle. But this is the dangerous thing to do as it leaves the system in inconsistent state, because we are not giving the opportunity to the thread to rollback or reverse the actions that it has taken. And hence the stop method is deprecated and should not be used.

* Correct approach would be to call the interrupt() method on the thread and then it is up to the thread to consider whether to stop or not.

* A thread can then check if it was interrupted or not using interrupted() method of the Thread class. We can design the thread in a way that it reverses the actions/operations performed and then stop when interrupted.

* Note - If you are not extending Thread class and instead implementing Runnable interface, then you can use Thread.isInterrupted() to check if the current thread is interrupted.

* interrupted() and Thread.isInterrupted() both methods return true if the thread is interrupted when it is alive.



Example -

    class MyThread extends Thread {
    
        public void run() {
            
        // Intentionally kept in infinite loop
        for( ;; ) {
                
            // Returns true if the thread is interrupted.
            if (interrupted()) {
                    
                // You are supposed to rollback or reverse the operation
            // in progress and stop.
     
            System.out.println("Thread is interrupted hence stopping..");
                    
            // Terminates the loop.
            break;
            }
                
            System.out.print("T");
        }	
        }
    }

    public class Main {
    
        public static void main(String[] args) {
     
        MyThread thr = new MyThread();
        thr.start();
            
        // Just for demo, wait for 2 seconds
        // before interrupting thr.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            
        // Interrupt the thread.
        thr.interrupt();
        }
    }


sleep() method -

sleep() method of the thread class is used to block the thread for the given time interval in milliseconds. This method throws InterruptedException if the thread is interrupted while it is in sleep.

========================================================================================================================
### Thread States

**Thread States -**
  A thread can be in one of the following states:

**NEW:**
  A thread that is created but not yet started is in this state.

**RUNNABLE:**
  A thread executing in the Java virtual machine is in this state, internally we can think of it as a combination of two sub states Ready and Running, i.e. when you start the thread it comes to Ready state and wait for the CPU, and if CPU is allocated then it goes into Running state. Once allocated CPU time is completed, in other words when the Thread schedular suspends the thread then it goes back to the Ready state and waits for its turn again.

* The yield() method instructs the thread scheduler to pass the CPU to other waiting thread if any.

**BLOCKED:**
  A thread is blocked if it is waiting for a monitor lock is in this state. Refer synchronized methods and blocks.

*** WAITING:**
  A thread that is waiting indefinitely for another thread to perform a particular action is in this state. Refer wait(), join()

**TIMED_WAITING**
  A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state. Refer wait(millis), join(millis)

**TERMINATED**
  A thread that has exited i.e. it has either completed its task or terminated in the middle of the execution.


![thread-states.jpeg](..%2Fthreading-images%2Fthread-states.jpeg)

**yield() method -**
  yield() method is important in few scenarios, suppose a thread is given 5 min of CPU time, now after a minute thread knows that it doesn't need the CPU anymore with in that time period, in such scenarios do you think that blocking the CPU for the next four minutes is a good idea ? No, it is better to pass on the control to the threads if any waiting for CPU and that is when we can use the yield() method. Usage Thread.yield(), it is a static method of the Thread class and it affects the current thread from which the method is invoked.

========================================================================================================================

### Thread Priorities
Let us look at how we can change the thread priorities.

Thread priorities range between 1 and 10.

* MIN_PRIORITY - 1 being the minimum priority

* NORM_PRIORITY - 5 is the normally priority, this is the default priority value.

* MAX_PRIORITY - 10 being the max priority.

**setPriority(int newPriority)  -**
  A method in the Thread class, this is used to set the new priority for the thread. If the newPriority value is more than the maximum priority allowed for the group then maximum priority is considered, i.e. if you try to set 15 then it takes only 10. And for a given ThreadGroup if the maximum allowed priority is 7 then any thread with in that group can have a maximum of 7.

Example -
Just think about a software installer app, the thread that copies the files should be given more priority than the thread which display the progress etc, that speeds up the installation process. Below example demonstrates setting higher priority for copyThread.

    class CopyTask implements Runnable {
      @Override
      public void run() {
        while(true) {
        System.out.print("C");
        }
      }
    }

    class ProgressTask implements Runnable {
      @Override
      public void run() {
        while(true) {
        System.out.print("-");
        }
      }
    }

    public class Main {

      public static void main(String[] args) {
        CopyTask copyTask = new CopyTask();
        Thread copyThread = new Thread(copyTask);
        copyThread.setPriority(Thread.NORM_PRIORITY + 3);
        copyThread.start();
            
        ProgressTask progressTask = new ProgressTask();
        Thread progressThread = new Thread(progressTask);
        progressThread.start();
      }
    }
========================================================================================================================

### Internal System Threads and ThreadGroup

Thread.currentThread() -

currentThread() is a static method in the class Thread and all the static method in the Thread class normally operate on the thread in which it is being executed. Here Thread.currentThread() returns a reference to the current thread i.e. the main thread.

getThreadGroup() - [Thread class method]

A Thread class method that returns a reference to the ThreadGroup to which the corresponding thread instance belongs.

getParent() - [ThreadGroup class method]

A ThreadGroup class method that returns a reference to the parent thread group if any. If there is no parent then this method returns null.

setMaxPriority(int maxPriority) - [ThreadGroup class method]

Sets the maximum priority for that group so that no thread can exceed this priority with in the group.

Example - 

      public class Main {
      
            public static void main(String[] args) {
            System.out.println("System threads..........");
            Thread thr = Thread.currentThread();
            ThreadGroup grp = thr.getThreadGroup();
            while (grp.getParent() != null) {
                grp = grp.getParent();
            }
            grp.list();
            }
      }

**Sample Output -**
NOTE - OUTPUT MAY VARY WITH JAVA VERSION.

System threads..........
java.lang.ThreadGroup[name=system,maxpri=10]
Thread[Reference Handler,10,system]
Thread[Finalizer,8,system]
Thread[Signal Dispatcher,9,system]
java.lang.ThreadGroup[name=main,maxpri=10]
Thread[main,5,main]
Associating a Thread with ThreadGroup -
Straight forward, create an instance of the ThreadGroup and give it a name. You can do group level operations over the ThreadGroup object. To associate a thread with the thread group, pass the thread group reference to the Thread class constructor.

Example -

    class MyTask implements Runnable {
    
        @Override 
        public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }

    public class Main {
    
        public static void main(String[] args) {
            
        // CREATING A THREADGROUP
        ThreadGroup myGroup = new ThreadGroup("MyGroup");
        myGroup.setMaxPriority(4);
            
        // ASSOCIATING A THREAD WITH THREAD GROUP
        Thread myThread = new Thread(myGroup, new MyTask(), "DemoThread");
        myThread.start();
        
        System.out.println("System threads..........");
        Thread thr = Thread.currentThread();
        ThreadGroup grp = thr.getThreadGroup();
        while (grp.getParent() != null) {
            grp = grp.getParent();
        }
        grp.list();	
        }
    }

**Sample Output -**
System threads..........
java.lang.ThreadGroup[name=system,maxpri=10]
Thread[Reference Handler,10,system]
Thread[Finalizer,8,system]
Thread[Signal Dispatcher,9,system]
java.lang.ThreadGroup[name=main,maxpri=10]
Thread[main,5,main]
java.lang.ThreadGroup[name=MyGroup,maxpri=4]
Thread[DemoThread,4,MyGroup]

**Technical Note -**
It is important to note that even the main method runs with a thread called the main thread. And its default priority is 5.

========================================================================================================================

### Daemon Threads
Daemon threads are the ones which does not prevent the JVM from exiting the application once it finishes. Daemon threads are handy for performing background tasks such as garbage collection or collecting application statistics etc. Note that the Java Virtual Machine exits when the only threads running are all daemon threads.

Example -
In the below example at the end of the while loop grp will point to system thread group. And enumerate method lists the threads in that group and copies the references to the given array. It also returns the number of threads copied. And later I am printing the thread name along with the boolean value checking if it is a daemon thread or not, using the method isDaemon().

    public class Main1 {
    
        public static void main(String[] args) {
            
          System.out.println("System threads..........");
              
          Thread thr = Thread.currentThread();
          ThreadGroup grp = thr.getThreadGroup();
          while (grp.getParent() != null) {
              grp = grp.getParent();
          }
              
          Thread [] threads = new Thread[10];
          int n = grp.enumerate(threads);
              
          for (int i=0; i < n; i++) {
              System.out.println(
              "Thread Name: " + threads[i].getName() + 
              " ; isDaemon: " + threads[i].isDaemon());
          }
        }
    }

Output -
System threads..........
Thread Name: Reference Handler ; isDaemon: true
Thread Name: Finalizer ; isDaemon: true
Thread Name: Signal Dispatcher ; isDaemon: true
Thread Name: main ; isDaemon: false
You can see that Reference Handler, Finalizer, Single Dispatcher all these are daemon threads because they run the background and they doesn't stop the application from exiting.

setDaemon(boolean on) -
The method of the Thread class makes a enables us to set whether a thread is a daemon thread or user thread.

Example -
    
    class MyTask implements Runnable {
        
            @Override
            public void run() {
            for (;;) {
                System.out.print("T");
            }
            }
    }

    public class Main {
      public static void main(String[] args) {
          Thread thr = new Thread(new MyTask());
          thr.setDaemon(true);
          thr.start();
      
          for (int i=1; i <= 200; i++) {
              System.out.print(" M ");
          }
      }
    }
Output -
MMTTMMT

A combination of M and T but the application ends once the main ends.

========================================================================================================================
### Callable Task

**Callable interface -**
Unlike Runnable, Callable interface allows us to create an asynchronous task which is capable of returning an Object.

interface Callable<V> {
V call() throws Exception;
}
If you implement Runnable interface we can not return any result. But if you implement Callable interface then you can return the result as well. Like run() method in the Runnable interface, you need to override the call() method. The return type of the call() method should match with the intended return type of the result. Callable<Double> means the call method returns Double value,  Callable<Fruit> means call method returns an instance of type Fruit.

    class GetStockQuoteTask implements Callable<Double> {
      private String stockSymbol;
   
      public GetStockQuoteTask(String stockSymbol) {
        this.stockSymbol = stockSymbol;
      }
   
      public Double call() {
        // Write some logic to fetch the stock price
        // for the given symbol and return it.
        return 0.0;
      }
    }
To submit this task for execution, you can use the submit method on the ExecutorService.

String symbol = "ABCD";
GetStockQuoteTask task = new GetStockQuoteTask(symbol);
Future<Double> future = executor.submit( task );
Double price = future.get();


Future Object -
When you submit a Callable task to the ExecutorService, it returns a Future object. This object enables us to access the request and check for the result of the operation if it is completed.

Important methods -

isDone() - Returns true if the task is done and false otherwise.

get() - Returns the result if the task is done, otherwise waits till the task is done and then it returns the result.

cancel(boolean mayInterrupt) - Used to stop the task, stops it immediately if not started, otherwise interrupts the task only if mayInterrupt is true.



Example -

      
      import java.util.concurrent.Callable;
      import java.util.concurrent.ExecutorService;
      import java.util.concurrent.Executors;
      import java.util.concurrent.Future;
      
      class MyMath {
      public static int add(int a, int b) {
      return a + b;
      }
      }

    public class Main {

      public static void main(String[] args) throws Exception {
      
        int x = 10;
        int y = 20;
            
        ExecutorService executor = 
                    Executors.newFixedThreadPool(1);
        
            // Submit a Callable task and use the Future
            // object to fetch the result.	
        Future<Integer> future = 
                        executor.submit(
                              new Callable<Integer>() {
                                public Integer call() {
                                    return MyMath.add(x, y);
                                }
                               }
                        );
     
         // do some parallel task
         // Inefficient to simply wait,
             // instead you can release the CPU 
             // by calling Thread.yield() inside 
             // the while loop.	
         while( ! future.isDone())
            ; // wait
        
             // fetch the result 	
         int z = future.get();
            
         System.out.println( "Result is " + z );
      }
    }
========================================================================================================================

### Notes - Pattern Search in folder
**Pattern Finder Example -**


    package com.example.utils;
    
    import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileReader;
    import java.util.ArrayList;
    import java.util.List;
    
    // Below utility searches the given pattern in the file.

      public class PatternFinder {
    
      /*
      * Looks for the given pattern in the file,
      * and returns the list of line numbers
      * in which the pattern is found.
      */

        public List<Integer> find(File file, String pattern) {
    
        List<Integer> lineNumbers = new ArrayList<Integer>();
    
      // Open the file for reading.
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    
          int lineNo = 1;
          String line;
    
          // for each line in the file.
          while ( (line = br.readLine()) != null) {
    
        if (line.contains(pattern)) {
            // capture the lineNo where the pattern is found.
            lineNumbers.add(lineNo);
        }
    
        lineNo++;
          }
    
      } catch(Exception e) {
      e.printStackTrace();
      }
    
      // Just introduced the delay for demo.
      try { Thread.sleep(1000); } catch(Exception e) {}
    
      return lineNumbers;
      }
    
    }


**Note -** For this program to work, create a folder named "sample" under "src" folder and create few files with content with in "sample" folder.



**Serial approach -**
Here we are not using threads, instead we are searching each file in sequential order.

    import java.io.File;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Set;
    
    import com.example.utils.PatternFinder;
    
    public class Main {

    public static void main(String[] args) throws Exception {
	
        // pattern to search
        String pattern = "public";
	       
        // Directory or folder to search	
	File dir = new File("./src/sample");
 
	// list all the files present in the folder.
	File [] files = dir.listFiles();
		
	PatternFinder finder = new PatternFinder();
		
	long startTime = System.currentTimeMillis();
		
	// for each file in the list of files
 
	for (File file : files) {
 
	    List<Integer> lineNumbers = finder.find(file, pattern);
 
	    if (! lineNumbers.isEmpty()) {
		System.out.println(
                    pattern + "; found at " + lineNumbers + 
                    " in the file - " + file.getName());
	    }
			
	}
        	
	System.out.println(
            " Time taken for search - " + (System.currentTimeMillis() - startTime));
		
    }
    }


**Parallel approach -**
Here we are creating a fixed thread pool of size 3 and using it to search the files, so that we can scan 3 files in parallel. And taking the help of the Future object to return the search result.



    import java.io.File;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Set;
    import java.util.concurrent.Callable;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.concurrent.Future;
    
    import com.example.utils.PatternFinder;
    
    public class Main {

    public static void main(String[] args) throws Exception {
		
        String pattern = "public";
		
        File dir = new File("./src/sample");
	File [] files = dir.listFiles();
		
	PatternFinder finder = new PatternFinder();
		
	// Fixed thread pool of size 3.
	ExecutorService executor = Executors.newFixedThreadPool(3);
 
	// Map to store the Future object against each 
	// file search request, later once the result is obtained 
	// the Future object will be 
	// replaced with the search result.
 
	Map<String, Object> resultMap = new HashMap<String,Object>();
		
	long startTime = System.currentTimeMillis();
		
	for (File file : files) {
		
	    // Submit a Callable task for the file.
	    Future<List<Integer>> future = 
		executor.submit(
		    new Callable<List<Integer>>() {
			public List<Integer> call() {
			    List<Integer> lineNumbers = finder.find(file, pattern);
			    return lineNumbers;
			}
		    });
			
	    // Save the future object in the map for 
	    // fetching the result.
 
	    resultMap.put(file.getName(), future);
	}
		
	// Wait for the requests to complete.
	waitForAll( resultMap );
		
	// Display the result.
	for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
		System.out.println( 
                    pattern + " found at - " + entry.getValue() + 
                    " in file " + entry.getKey());
	}
		
		
	System.out.println(
            " Time taken for search - "  
            + (System.currentTimeMillis() - startTime));
		
    }
 
    private static void waitForAll(Map<String, Object> resultMap) 
                            throws Exception {
		
	Set<String> keys =  resultMap.keySet();
		
	for (String key : keys) {
	    Future<List<Integer>> future = 
                    (Future<List<Integer>>) resultMap.get(key);
 
	    while (! future.isDone()) {
 
		// Passing the CPU to other 
		// threads so that they can 
		// complete the operation.
                // With out this we are simply 
                // keeping the CPU in loop and 
                // wasting its time.
 
		Thread.yield(); 
	    }
 
	    // Replace the future object with the obtained result.
	    resultMap.put(key, future.get());
	}
		
    }
    }


IMPORTANT NOTE - If a thread doesn't need CPU, it is always a good idea to pass the control to the other threads so that CPU time is effectively utilized.

========================================================================================================================

### Thread Synchronization

**Thread Synchronization**
Thread synchronization is used to solve concurrency problems that exist in parallel processing. Concurrency problem exist when more than one thread is accessing the same shared resource.

E.g.

1) More than one transaction is being performed on the same account

2) Multiple resources are booking tickets for the same train from different locations.

etc.

**Synchronization in Java Threads**
It can be achieved through

1) Synchronized methods

2) Synchronized block

3) Locks discussed later.

synchronized method
Consider the class Sample

class Sample {
synchronized void f() {...}
}
Consider Three threads T1, T2, T3 and two objects for Sample they are A,B.

T1  ..........A.f();  locks A and proceeds

T2  ..........B.f();  locks B and proceeds

T3  ..........A.f();  wait till T1 unlocks A.

To run a synchronized method object must be locked.

synchronized block
When synchronization is not required for the entire method i.e only certain part of the code must be synchronized then we use synchronized block.

    synchronized( object ) {
    // operations over the object
    }
The above code is executed only after obtaining lock over the object.

**Thread Safe Code or Re-entrant code:-**
When a code block is safe from concurrency problems then the code is referred as thread safe or re-entrant.In case of the below operation incr() operation is considered as thread safe or re-entrant.

**Example -**
In the below example try removing synchronized keyword before the incr() operation and check the result. You will find inconsistent result. By making the method synchronized, we are forcing the thread to lock the object before performing the incr() operation. Though control is intentionally passed to other thread, other thread won't be able to proceed with the operation as it need to first lock (obj) before proceeding forward.

i.e. lets assume t1 locks obj then t2 should wait till t1 releases the lock, hence object is modified by only one thread at a time and you will see consistent results.

    class Sample {
    
        private int x;
     
        public int getX() {
        return x;
        }
     
        public void setX(int x) {
        this.x = x;
        }
        
        /*
         * Try removing synchronized. 
         */
        public synchronized void incr() {
            
            // read the value of x.
        int y = getX();
     
        // Increment the value
        y++;
            
        // Just assume if control is switched to 
        // some other thread and it too looks at
        // the old value of x and proceeds with 
        // modification. Such scenarios lead to 
        // in consistent results.
        // To simulate such scenarios lets us just
        // pass the control to some other thread. 
        
            // with sleep this thread will go to blocked state
            // for the given time interval, hence other thread
            // will get a chance.
        try { Thread.sleep(1); } catch(Exception e) {}
            
        // set x to new value.
        setX(y);
        }
    }


    class MyThread extends Thread {
    
        Sample obj;
        
        public MyThread(Sample obj) {
            this.obj = obj;
        }
        
        public void run() {
        obj.incr();
        }
    
    }


    public class Main {
    
        public static void main(String[] args) {
     
        Sample obj = new Sample();
        obj.setX(10);
            
        // In this case both the threads t1 and t2
        // are sharing the same Sample object obj.
        // Both the threads will try to perform the
        // increment operation simultaneously.
     
        MyThread t1 = new MyThread(obj);
        MyThread t2 = new MyThread(obj);
            
        t1.start();
        t2.start();
            
            // Here main thread called the join operation 
            // on t1 and t2. join() operations waits for 
            // thread to complete before returning.		
        try {
            t1.join();
                t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }	
        
        System.out.println( obj.getX() );		
        }
    
    }


**Synchronizing static data operations -**
Is the below increment() operation thread-safe ?

    class Sample {
    
        static int a = 5;
        int b = 10;
     
        public synchronized void increment( ) {
             a++;
         b++;
        }
     
        // ....
    }
Answer is NO. Because we made it synchronized we may think that it is thread-safe but it is not. And it is because of the static variable a. Lets assume that there are two objects for Sample, in that case both of them will share the same copy of a because it is a class member, where as they get different copies of b, because b is non static i.e. the instance member and each instance will get a separate copy of b.

Assume Thread 1 invoked the increment method over the first object and Thread 2 invoked the increment method over the second object. Because the increment() method is non-static and it is synchronized, object should be locked before getting into the method.

Here Thread1 locks the first object and gets in and also Thread 2 locks the second object and gets in, because both are different objects and hence both the thread acquire locks and they both proceed with the operation.

You can see that b++ is not having any issues, because both the threads are operating on different copies of b, but what about a++ it is still not thread safe.

**Solution 1 -**

      class Sample {
      
          static int a = 5;
          int b = 10;
       
          public  void increment( ) {
              // lock the Class object before modifying
              // static content.
              synchronized(Sample.class) {
                  a++;
              }
              
              // lock the object before modifying 
              // instance members.    
              synchronized(this) {
              b++;
              }
          }
          
          // ....
      }
In this case the increment() operation is thread-safe, because for modifying the static member 'a' we are locking the class object, Sample.class returns a reference to class object and synchronized block will acquire lock over the object and then proceed forward with the operation. And for b++ we are locking the current object using the this reference. And hence both the operations are now thread-safe, as we properly locked the corresponding object before modification.

**Solution 2 -**

    class Sample {
    
        static int a = 5;
        int b = 10;
     
        // This method is static and hence it locks the Class object.
        public static synchronized void incrementA( ) {
        a++;
        }
     
        // This method is non static and hence it locks the object
        // on which it is invoked.
        public synchronized void increment( ) {
            incrementA();
        b++;
        }
     
        // ....
    
    }

Create a static method for incrementing a and declare it as synchronized, so that when the thread enters this method it locks the class object. Just remember that whenever you are modifying the static content in a multi-threaded environment you should lock the class object to make your code thread-safe.

Issue with synchronized methods -
Synchronized methods doesn't always solve concurrency problems. Lets consider a simple List class and assume that the size() and add() operations are synchronized

class List {
...
public synchronized int size(){
...
}
public synchronized void add(Object value) {
....
}
}
You can think that there is no concurrency issue here. But, lets analyze a simple scenario consider that list should not contain more than 5 elements, and assume that list is already having 4 elements and two threads are trying to insert an element into the list.

Thread 1 -

1) if (list.size() < 5) {
2) 	list.add(value1);
      }
      Thread 2 -

a) if (list.size() < 5) {
b) 	list.add(value2);
}
Lets assume this execution sequence (1)(a)(2)(b) in this case both threads will see that list size is 4 and is less than 5. Hence both will add an element into the list, which makes the list size as 6 violating the condition. You can see the issue is not resolved even with both size() and add() being synchronized methods.

Solution -
You should apply thread synchronization at operation level with the help of synchronized block. i.e.

Thread 1 -

1) synhronized(list) {
2)   if (list.size() < 5) {
3) 	list.add(value1);
      }
      }
      Thread 2 -

a) synchronized(list) {
b)   if (list.size() < 5) {
c) 	list.add(value2);
}
}
Now consider the execution sequence (1)(a)(2)(3)(b)(c)

You can see that list object is locked by Thread1 and hence even when the control switched to Thread2 it can not proceed as the lock is with Thread1. And Thread1 will add the element where as Thread2 will fail.

So when it comes to synchronizing operations synchronized blocks are always better choice over synchronized methods.

==============================================================================================================================================================

### Deadlocks and solution with lock sequencing

**Deadlock -**
Two threads are said to be in a deadlock when both the threads are circularly waiting for a lock over the object and hence they both get into a situation where they can not proceed with the execution.


Assume that for a writer to write lock should be acquired on both the Book and the Pen. And also assume there is only one instance of the book and the pen is available. In that case assume that writer1 acquires lock over the book object and writer 2 acquires lock over the pen object. Now writer1 is waiting for the pen and writer2 is waiting for the book, and they both are deadlocked.

Simulated Example -

    class Writer1 extends Thread {
    
        Object book;
        Object pen;
        
        public Writer1(Object book, Object pen) {
        this.book = book;
        this.pen = pen;
        }
        
        @Override
        public void run() {
            
        synchronized(book) {
            try { Thread.sleep(10); } catch(Exception e) {}
            synchronized(pen) {
            System.out.println("Writer1 writing");
            }
        }	
        }
    }

    class Writer2 extends Thread {
    
        Object book;
        Object pen;
        
        public Writer2(Object book, Object pen) {
        this.book = book;
        this.pen = pen;
        }
        
        @Override
        public void run() {
            
        synchronized(pen) {
            try { Thread.sleep(10); } catch(Exception e) {}
            synchronized(book) {
            System.out.println("Writer2 writing");
            }
        }	
        }
    }

    public class Main {
    
        public static void main(String[] args) {	
        Object book = new Object();
        Object pen = new Object();
            
        new Writer1(book, pen).start();
        new Writer2(book, pen).start();
            
        System.out.println("Main is done");
        }
    }

**Solution -**

Lock sequencing is one possible solution for deadlock avoidance. Adjust the lock sequence for Writer2

    @Override
    public void run() {
      synchronized(book) {
          try { Thread.sleep(10); } catch(Exception e) {}
        synchronized(pen) {
          System.out.println("Writer2 writing");
        }
      }
    }

===============================================================================================================

### Reentrant Locks

**Read/Write Lock -**
Read/Write Lock gives us more flexibility during locking and unlocking. Based on the type of operation being performed over the object we can segregate the locks into

1) readLock

2) writeLock

readLock allows us to lock the object for read operation, and the interesting point is that the read operation can be shared i.e if two threads are waiting for readLock then both of them can proceed forward with the operation as read operation doesn't change the data.

Whereas writeLock is mutually exclusive i.e. if a writeLock is accepted then all the other lock requests should wait till the thread that owns the lock releases it.

For example let us assume the following chronologically ordered lock requests

T1 -> lock.readLock();

T2 -> lock.readLock();

T3 -> lock.readLock();

T4 -> lock.writeLock();

T5 -> lock.readLock();

Here T1, T2, T3 can share the readLock and proceed forward with the operation.  Where T4 should wait till T1, T2 and T3 unlocks.

Why T5 is waiting ?

* Because writeLock is requested by T4 before its request and hence all subsequent requests to read/write locks should wait.

* This is in contrast to synchronized methods/blocks because for synchronized method/block there is no segregation of read and write operations. Object is locked no matter whether it is read or write.

* Caution - It is always better to put the unlock operation in finally, as you need to unlock irrespective of exceptions.

**Example -**
Example is just for demo, hence lock/unlock operations are kept in incr() method itself. They can be added to getX() and setX() operations as well.

    import java.util.concurrent.locks.Lock;
    import java.util.concurrent.locks.ReadWriteLock;
    import java.util.concurrent.locks.ReentrantReadWriteLock;
    
    class Sample {
    
        private int x;
        
        // ReadWriteLock object for requesting the lock.
        ReadWriteLock rw_lock = new ReentrantReadWriteLock();
     
        public int getX() {
            return x;
        }
     
        public void setX(int x) {
        this.x = x;
        }
        
        public void incr() {
            
        // Request the write lock as the 
        // operation is intended to modify the data.
     
        Lock lock = rw_lock.writeLock();
        lock.lock();
     
        try {
            
            int y = getX();
            y++;
                
            // Just for simulation
            try { Thread.sleep(1); } catch(Exception e) {}
                
            setX(y);
     
        } finally {
            // Unlock 
            lock.unlock();
        }	
        }
    
    }

    class MyThread extends Thread {
    
        Sample obj;
        
        public MyThread(Sample obj) {
        this.obj = obj;
        }
        
        public void run() {
        obj.incr();
        }	
    }

    public class Main {
    
        public static void main(String[] args) {
     
        Sample obj = new Sample();
        obj.setX(10);
            
        MyThread t1 = new MyThread(obj);
        MyThread t2 = new MyThread(obj);
            
        t1.start();
        t2.start();
            
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            
        System.out.println( obj.getX() );	
        }
    }
================================================================================================================

### Producer and Consumer Problem
**Thread signalling using wait() and notify() -**

Threads can signal each other using the wait and notify methods. wait(), notify() and notifyAll() are the methods of the class Object and hence they are part of all the objects. Just think about a scenario where one thread waits for a signal from some other thread in order to proceed with the execution.

wait() method Releases the lock over the object and takes the thread to WAITING state. And the thread remains in that state until some other thread calls the notify() method over the same object. Once notify() is invoked it ends the wait for one single thread and takes the thread to BLOCKED state where the thread remains in that state till the lock is obtained. wait() only returns after obtaining the lock.

wait(long millis)  slightly differs, as it takes thread to TIMED_WAITING and waits only for the specified duration.

notify() - notifies one single thread where as notifyAll() notifies all the threads waiting for the signal.

Note - In order to call the wait and notify methods the corresponding thread should hold the lock on the object using synchronized method or block.

Producer and Consumer Problem -
Here the problem that we are dealing with is the slow consumer, problem occurs when the producer produces the messages at a faster rate than the consumer can consume.

How to solve this problem?

There are different ways of solving it, one approach is to ask the producer to wait till the message is consumed.

**Example -**
// Producer and Consumer problem
//    producer --> messageQueue --> consumer.

    import java.util.ArrayList;
    import java.util.List;
    
    class MessageQueue {
    
        List<String> messages;
        int limit;
        
        public MessageQueue(int limit) {
        messages = new ArrayList<String>();
        this.limit = limit;
        }
        
        public boolean isFull() {
        return messages.size() == limit;
        }
        
        public boolean isEmpty() {
        return messages.size() == 0;
        }
        
        public synchronized void enqueue(String msg) {
            
        while (isFull()) {
            try {
            this.wait();
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        }
            
        messages.add(msg);
        this.notify();	
        }
        
        public synchronized String dequeue() {
            
        while (isEmpty()) {
            try {
            this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
            
        String message = messages.remove(0);
        this.notify();
        return message;
        }
    
    }

    class ProducerThread extends Thread {
    MessageQueue queue;
    
        public ProducerThread(MessageQueue queue) {
        this.queue = queue;
        }
        
        @Override
        public void run() {
        for(int i=1; i <= 10; i++) {
            String msg = "Hello-" + i;
            queue.enqueue(msg);
            System.out.println("Produced - " + msg);
        }
        }
    }

    class ConsumerThread extends Thread {
    MessageQueue queue;
    
          public ConsumerThread(MessageQueue queue) {
          this.queue = queue;
          }
          
          @Override
          public void run() {
          for(int i=1; i<=10; i++) {
              String message = queue.dequeue();
              System.out.println("Consumed - " + message);
          }
          }
      }

    public class Main {
    public static void main(String[] args) throws InterruptedException {
    MessageQueue queue = new MessageQueue(1);
    new ProducerThread(queue).start();
    new ConsumerThread(queue).start();
    }
    }

**Explanation -**
* Here we have a class MessageQueue which acts as a buffer between producer thread and the consumer thread. And the buffer is bounded, so we can set the limit during object creation.

* **isFull()** returns true if messages size reaches the limit.

* **isEmpty()** return true if messages size is equal to zero.

* **enqueue()** - Here it is invoked only by ProducerThread; it adds (at the end) the message to the messages array if it is not full, if full it calls the wait() over the queue object (this) till the consumer consumes the message and notifies it. Also once it adds the message to the queue, it calls the notify() in order to end the consumer's wait() if any.

* **dequeue()**  - Here it is invoked only by ConsumerThread; it removes the first element and returns it. If the queue is empty it calls the wait() over the queue object till the producer produces the message. Once it consumes the message it calls the notify() to end the producer's wait() if any.

* **main()** initiates these operations, by creating the MessageQueue object and passing it to both the producer and consumer threads.

================================================================================================================

### ThreadLocal

**ThreadLocal -**
ThreadLocal is considered an anti-pattern please refrain from using it directly.

ThreadLocal allows us to associate an object with the Thread. It is normally used to hold the information which should be accessible anywhere during the thread execution.

**Important Methods -**
get() - Returns the value associated with the thread.

set(T value) - Associates the value with the thread.

remove() - Removes the value associated with the thread.



**SimulatedExample -**
Assume we have two requests, and we assigned two WorkerThreads for servicing them, think that this is a server application assigning a client request to a thread. Now inside the WorkerThread assume that the request is delegated to the framework. And Framework does the pre-processing. During the pre-processing step think that it extracts the user information from the request and sets up the UserContext. The setUserName operation sets the userName in the ThreadLocal object. And then it forwards the request to the API. Assume API calls the services Service1 and Service2, and in between with a delay.Now assume Service1 want to know the user information then it can use the UserContext.getUserName() which retrieves the userName from the thread local object.

And the most important thing is that once the task is done, we need to clear this value. The postProcess step will clear the context i.e. It removes the value associated with the current Thread. May not be important for this example, but in normal applications the same thread is used to service multiple client request, if not cleared the existing value associated with the thread might interfere with some other client request.

    public class Main {
    
        public static void main(String[] args) {
        String request1 = "a";
        String request2 = "b";
            
        new WorkerThread(request1).start();
        new WorkerThread(request2).start();
        }
    }

    class UserContext {
    private static ThreadLocal<String> userInfoThreadLocal =
    new ThreadLocal<String>() {
    public String initialValue() {
    return null;
    }
    };
    
        public static String getUserName() {
            return userInfoThreadLocal.get();
        }
        
        public static void setUserName(String userName) {
        userInfoThreadLocal.set(userName);
        }
        
        public static void clear() {
        userInfoThreadLocal.remove();
        }
    }

    class WorkerThread extends Thread {
    
        String request;
        Framework framework = new Framework();
        
        public WorkerThread(String request) {
        this.request = request;
        }
        
        @Override 
        public void run() {
        framework.delegate(request);
        }
    }

    class Framework {
    
        API api = new API();
        
        public void delegate(String request) {
        preProcess(request);
        try {
            api.process(request);
        } finally {
            postProcess();
        }
        }
     
        private void preProcess(String request) {
        String userName = getUserName(request);
        UserContext.setUserName(userName);
        }
        
        private void postProcess() {
        UserContext.clear();
        }
     
        private String getUserName(String request) {
        return request;
        }
    }

    class API {
    
        Service1 service1 = new Service1();
        Service2 service2 = new Service2();
        
        public void process(String request) {
        service1.doService1();
        sleep();
        service2.doService2();
        }
     
        private void sleep() {
        try { 
            Thread.sleep(3000); 
        } catch(InterruptedException e) {}
        }
    }

    class Service1 {
    
        public void doService1() {
        System.out.println(
            "Performing service1 for user - " 
            + UserContext.getUserName() 
            + " "  + Thread.currentThread());
        }
    }

    class Service2 {
    
        public void doService2() {
        System.out.println(
            "Performing service2 for user - " 
            + UserContext.getUserName() 
            + " "  + Thread.currentThread());
        }
    }
Output -

You can see in the output that Thread-0 and Thread-1 has different usernames as each thread has its own copy of the userName.

Performing service1 for user - b Thread[Thread-1,5,main]
Performing service1 for user - a Thread[Thread-0,5,main]
Performing service2 for user - b Thread[Thread-1,5,main]
Performing service2 for user - a Thread[Thread-0,5,main]
Summary -

ThreadLocal could be used to supply some vital information to all the components involved in processing the request, where this could not be passed directly. But it considered an anti-pattern and creates trouble during asynchronous processing if not handled properly.

=================================================================================================================

### BlockingQueue and revised producer and consumer problem

#### java.util.concurrent package
Has several utilities which help developing multithreaded application.

* ExecutorService

* Callable interface

* Future object

* Locks

* BlockingQueue implementations

* Few other utilities.

BlockingQueue
BlockingQueue is an interface that extends Queue interface. And the implementations include

ArrayBlockingQueue

It is bounded i.e. you need to specify the size.

operate on FIFO logic i.e. first in first out, which means that the first inserted element will be the first to be removed.



LinkedBlockingQueue

Optionally Bounded, based on linked nodes i.e. nothing but the linked list.

It too operates on FIFO logic.



PriorityBlockingQueue

Unbounded

Objects should be Comparable or you should provide a Comparator.



And there are few other implementations as well.

**BlockingQueue operations**
Operations that throw Exception if the operation fails.

add(o)

It tries to add an element and if there is no sufficient capacity available this method will throw an exception.

remove(o)

Removes the element that matches with the given object it compares the elements using equals method.

element()

Returns the head element with out removing it. But element() method throws an exception if queue is empty

Operations that return a boolean value with out exception

offer(o)

Returns true if the element is added otherwise false.

poll()

Removes the head element of the queue and returns it, if queue is empty it returns null.

peek()

Returns the head element with out removing it, it returns null if queue is empty.

Operations that block.

put(o)

It will add the element to the queue, but if the queue is full, then it will block the thread till the space is available.

take()

Returns the head element of the queue, if queue is empty this method will block the thread till an element is available.

And the methods with timeout

offer(o, timeout, timeunit)

poll(timeout, timeunit)

**Revised Producer and Consumer Example -**

    import java.util.concurrent.ArrayBlockingQueue;
    import java.util.concurrent.BlockingQueue;
    
    // Changed from MessageQueue to BlockingQueue.

    class ProducerThread extends Thread {
    BlockingQueue<String> queue;
    
        public ProducerThread(BlockingQueue<String>  queue) {
        this.queue = queue;
        }
        
        @Override
        public void run() {
        for(int i=1; i <= 10; i++) {
            String msg = "Hello-" + i;
                
                // Blocks the thread until the space is available.
            try {
            queue.put(msg);
                    System.out.println("Produced - " + msg);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
           
        }
        }
    }

    class ConsumerThread extends Thread {
    BlockingQueue<String> queue;
    
        public ConsumerThread(BlockingQueue<String> queue) {
        this.queue = queue;
        }
        
        @Override
        public void run() {
              for(int i=1; i<=10; i++) {
                String message = null;
         
                    // Blocks the thread until the element is available.
                try {
                message = queue.take();
                        System.out.println("Consumed - " + message);
                } catch (InterruptedException e) {
                e.printStackTrace();
                }
              
              }
        }
    }

    public class Main {
      public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
        new ProducerThread(queue).start();
        new ConsumerThread(queue).start();
      }
    }


=================================================================================================================

## PriorityBlockingQueue

**PriorityBlockingQueue -**
PriorityBlockingQueue orders the elements through natural order if they are Comparable or we should use the Comparator.

**Example -**

    import java.util.Comparator;
    import java.util.concurrent.PriorityBlockingQueue;
    
    class Student {
    String name;
    int rank;
    
        public Student(String name, int rank) {
            this.name = name;
        this.rank = rank;
        }
        
        public int getRank() {
        return this.rank;
        }
        
        public String toString() {
        return String.format("name : %s, rank : %d", name, rank);
        }
    }

    // Compares the Student objects based on the rank field value.
  
    class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
    return o1.getRank() - o2.getRank();
    }
    }

    public class Main {
    
        public static void main(String[] args) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>();
        queue.add(10);
        queue.add(2);
        queue.add(5);
            
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
            
        PriorityBlockingQueue<Student> queue1 = 
            new PriorityBlockingQueue<Student>(5, new StudentComparator());
     
        queue1.add(new Student("a", 12));
        queue1.add(new Student("b", 1));
        queue1.add(new Student("c", 4));
            
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        }
    }
    
**Output -**
    
    2
    5
    10
    name : b, rank : 1
    name : c, rank : 4
    name : a, rank : 12

=================================================================================================================

## Fork Join Framework

Fork Join Framework -Available from Java7

An ExecutorService for ForkJoinTasks

It differs from ExecutorService by virtue of employing Work-stealing. i.e. if a worker thread has no tasks in the pipeline it will take the task from the task queue of the other busy thread so that the workload is efficiently balanced.

To access the pool, A static common pool is available for the application and it can be accessed through commonPool() method of the ForkJoinPool class. Using the commonPool is the preferred approach because creating multiple thread pools might have an adverse impact on the performance of the application.

ForkJoinPool pool = ForkJoinPool.commonPool();

For applications which need separate thread pools, we can construct the ForkJoinPool using the level of parallelism needed and by default it is equal to the number of processors.

**ForkJoinPool pool = new ForkJoinPool();**

------------------------------------------------------------------------------------------------------------

**What is fork, join ?**

The primary thought process behind fork is that each task is recursively split into subtasks and executed in parallel where as the join operation will wait for the completion of the task and combines the obtained results. To make it simpler, lets assume that the task is to search 100,000 unordered elements and returns the number of occurrences of an element. Assume that, a task will perform the search operation on its own if the list size is 25000. Here the first tasks splits the total elements into two sets of size 50000  and at second level task will split that into two sets of size 25000 each,  and at this level it will not fork any subtasks because the size of the dataset is 25000 and as per our condition we shouLd not create sub tasks.

This is how recursion works as well, there will be a base condition and upon reaching that condition recursive call would be stopped. Similarly here we should put a threshold condition where we will not further fork.

Types of ForkJoinTask(s) -

ForkJoinTask implements Future interface so we can use it to extract the results once the task is done. ForkJoinTask is further divided into two subtypes one is RecursiveAction and RecursiveTask. These classes are abstract; and when you extend you need to override the compute method. If it is a RecursiveAction the compute operation doesn't return any value. Where as in case of RecursiveTask we can return a value. You can relate that with Runnable and Callable.

Submitting the tasks from outside non-fork join clients -
YourForkJoinTask task = ......;
ForkJoinPool pool = ForkJoinPool.commonPool();

// (1) Arranges for asynchronous execution of the given task.
pool.execute(task);

// (2) Invokes the task, waits for completion and returns the
// result.
result = pool.invoke(task);

// (3) Submits the task for execution
pool.submit(task);

// Later to obtain the result in case of (1) and (3).
Result = task.get();
Submitting the tasks from with in the fork join computations -
YourForkJoinSubTask subTask = ......;

// (1) Submit the task for execution
subTask.fork();

// join - waits for the task to complete the computation
// and returns its result.
result = subTask.join();

// (2) Direct approach - invoke the task and wait for result.
result = subTask.invoke();


**Example -**

    import java.util.concurrent.ForkJoinPool;
    import java.util.concurrent.RecursiveTask;
    
    class SearchTask extends RecursiveTask<Integer> {
    
        int arr[];
        int start, end;
        int searchEle;
        
        public SearchTask(int arr[], int start, int end, int searchEle) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchEle = searchEle;
        }
        
        @Override
        protected Integer compute() {
        System.out.println(Thread.currentThread());
        int size = end - start + 1;
        if (size > 3) {
            int mid = (end + start) / 2;
            SearchTask task1 = new SearchTask(arr, start, mid, searchEle);
            SearchTask task2 = new SearchTask(arr, mid + 1, end, searchEle);
            task1.fork();
            task2.fork();
            int result = task1.join() + task2.join();
            return result;
        } else {
            return processSearch();
        }
        }
     
        private Integer processSearch() {
        int count = 0;
        for(int i = start; i <= end; i++) {
            if (arr[i] == searchEle) {
            count++;
            }
        }
        return count;
        }
    }

    public class Main {
    
        public static void main(String[] args) {
        int arr[] = {6, 2, 6, 4, 5, 6, 7, 8, 6, 10, 11, 6};
        int searchEle = 6;
        int start = 0;
        int end = arr.length - 1;
            
        ForkJoinPool pool = ForkJoinPool.commonPool();
        SearchTask task = new SearchTask(arr, start, end, searchEle);
        int result = pool.invoke(task);
            
        System.out.printf("%d found %d times", searchEle, result);
        }
    }


=================================================================================================================



=================================================================================================================