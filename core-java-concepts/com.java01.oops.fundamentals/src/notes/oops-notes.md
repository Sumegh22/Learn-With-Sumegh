
# Notes : Core Java Execution and Basics

## Fundamentals
Programming languages and thier way of interacting with computer
1. Procedural
2. Functional
3. Object-oriented

Java language follows all of these approches. Java is a static programming laguage, meaning it gives more control to the developer to specify all the things before hand. Java is still widely used in the industry. It is robust and it can take huge load at any given point of time.  Java is platform independent

Java is both interpreted and compiled programming language. To develop and work in Java you need JDK
JDK consists of JRE which has JVM Along with some additional file required by JVM.

1. In java everything has to be written inside a class
2. Files that have java code are saved with .java extention
3. First the human understandable source code is compiled by compiler and is converted into bytecode.  using javac <filename.java>  it is converted to <filename.class>
4. This byte code is the real platform independent thing. The byte code is then interpreted by interpreter present in JVM.
5. The JVM is present inside JRE.

5. Memory is managed by jvm itself by the process of garbage collection
6. The computer memory is devided in two parts. Stack and Heap. The variables/ ref variables are stored in stack and the actual object is created in heap
7. GC clears out memory in heap.
8. when a class say Demo is public class, then it should be declared in a separate file. meaning any inner class cannot be public class.
9. Explain PSVM and SOPLn.

**Type Casting and Type Conversion**
Type casting between two datatypes can happen only when following conditions are met
1. The two types must be compatible
2. The destination type should be greater than the source type

---------------------------------------------

## OOPS Fundamentals
https://github.com/kunal-kushwaha/DSA-Bootcamp-Java/tree/main/lectures/17-oop/notes

**Class :** a Class is a logical construct. It is kind of a stencil or casket based on which objects are created.

**Objects:** 
- Object is an instance of a class. Like as I said class is a stencil, so object is something that is created using that stencil. Objects exists physically.

- **Instance Variables:** vars defined inside an object, whose scope is limited to that particular obkect only are called instance vars
- **Ref-variables:** vars named used for objects.

Significance of "new" keyword, When we create any instance of a class, we create an object, this new keyword is used in scenario where we want to create a totally new object. it calls the constructor of class to create objects

Student s = new [constructor of Student Class]

**Pass by value or ref:** 
* Java is pass by value and not pass by reference, 
* real-Life ex: If a teacher (Jvm) wants to provide notes (object) to student for homework (assume homework as a method here). If this student who got notes changes/ manipulates or say destroy notes, then other students have nothing.
* Teacher Providing notes, can be considered as providing actual memory location of the object, thus if the manipulation is done here the actual object will be changed.

**explanation:** When you use any method and pass a variable as input to it, a copy of the value of that variable is taken into consideration and operations are performed on this given value, once the method execution is completed if you compare the variable passed and value of the output there may or may not be difference based upon method body, but one thing worth noticing is the memory address of both the vars that we are talking about would be different. this proves java is pass by value and not reference

**IMPORTANT:**
## What happens when you pass object references to a method?..
* If you pass object references to a method the object refernce (values of attributes inside this reference) can be changed.
* Because when we pass object refernce we -> this indicates that we are passing a memory address to a method
* So if any modification is done it is done on top of your obj-ref hence comparing an object before and after a method execution
  would differ based on what task is perform in the method you passed your object to


**this keyword :** In Java this keyword points to the current object under consideration. The object on which any operation is being performed, for example if you are creating an object of a class then, while defining the params of this object, you pass values to constructor at that point, parameterised constructor is called which handles definition of variables and params for current object which is being defined, and all of this is being done using the this keyword

In words the code tells JVM, using the 'new' keyword create an object in Heap memory, whose object name (obj refernce is 'sumegh', as stated before the '=' sign) and
to the attributes of the 'sumegh' object values as ```rno = 14``` and so on.

ex:
Student sumegh = new Student( 14, "Sumegh T", 98.09f)  then interanally it would be working as

     Student(Integer rNo, String name,  Float percentage) {
		this.rNo = rNo; 
		this.name = name; 
		this.percentage = percentage; 
	}
	
---------------------------------------------

## Garbage collection and finalize:

GB is an automatic process, managed by JVM. After any operation when a good amount of memory is utilized, the  JVM hits GC method, this first checks for objects that are unreferenced or idle. say the objects that are there in heap mem, but no ref variable is pointing to it, such things will be cleared.

Garbage Collection in Java is a process by which the programs perform automatic memory management. Java programs compile into bytecode that can be run on a Java Virtual Machine (JVM). When Java programs run on the JVM, objects are created on the heap, which is a portion of memory dedicated to the program. Eventually, some objects will no longer be needed. The garbage collector finds these unused objects and deletes them to free up memory.

The garbage collection process involves the following steps:

1. **Marking:** This is the first phase of garbage collection. The garbage collector identifies which pieces of memory are in use and which are not.

2. **Normal Deletion:** In this phase, the garbage collector removes the unused objects and reclaims their memory. The limitation of this approach is that it can leave fragmented memory.

3. **Deletion with Compacting:** To solve the fragmentation problem, the garbage collector can also move the remaining objects to one end of the heap so that the free memory is all in one block.

Java's garbage collector divides the heap into two areas (or generations), based on the observation that most of the objects are quickly becoming unreachable, these are:

1. **Young Generation:** This is where all new objects are allocated and aged. When the young generation fills up, this causes a minor garbage collection. Minor collections can be optimized assuming a high object mortality rate. A young generation full of dead objects is collected very quickly. Some surviving objects are aged and eventually move to the old generation.

2. **Old Generation:** When objects that have survived all minor garbage collections are moved to the old generation. Usually, a major garbage collection is performed in the Old generation space and it takes a longer time as it involves all live objects.

In summary, the purpose of garbage collection is to identify and discard objects that are no longer needed by a program so that their resources can be reclaimed and reused. A Java object is subject to garbage collection when it becomes unreachable to the program in which it is used. The balance between the Young and Old generations can greatly affect the efficiency of garbage collection in terms of speed and impact on application performance.

As stated earlier it is automatic process you do not have any control over it, but there could be certain operations or tasks that you would want to execute before your object is destroyed, you can put those operations in finalize

### Ways to garbage collect an object in Java

In Java, objects are automatically garbage collected by the Java Virtual Machine (JVM) when they are no longer reachable. However, developers can influence or expedite garbage collection in several ways:

Nullifying References:


	SomeClass obj = new SomeClass();
	// do something with obj
	obj = null; // Set the reference to null to make the object eligible for garbage collection
 
Calling System.gc():

	System.gc(); // Suggests to the JVM that it's a good time to run garbage collection
	
 
Using Runtime.getRuntime().gc():

	Runtime.getRuntime().gc(); // Similar to System.gc(), suggests garbage collection
 
Weak References:
Using weak references allows objects to be collected even if there are only weak references pointing to them.

	WeakReference<SomeClass> weakRef = new WeakReference<>(obj);
	obj = null;
	// The object can be garbage collected if there are no strong references to it
 
Phantom References:
Similar to weak references, but with stricter constraints. Phantom references are enqueued after the object is finalized.

	PhantomReference<SomeClass> phantomRef = new PhantomReference<>(obj, referenceQueue);
	obj = null;
	// The object can be collected, and the reference is enqueued for additional cleanup


Finalization (Deprecated):
Using the finalize() method (deprecated as of Java 9) to perform cleanup operations before an object is garbage collected.

	@Override
	protected void finalize() throws Throwable {
	    // Cleanup operations
	    super.finalize();
	}

### types of garbage collectors:

**Serial Garbage Collector:** Suitable for small applications with low memory requirements.

**Parallel Garbage Collector:** Also known as the throughput collector, it uses multiple threads to improve garbage collection performance for medium to large-sized applications.

**Concurrent Mark-Sweep (CMS) Garbage Collector:** 
- Designed for applications where low latency is crucial, it minimizes pauses by running most of its tasks concurrently with the application threads. 
- The Concurrent Mark Sweep (CMS) collector (also referred to as the concurrent low pause collector) collects the tenured generation. It attempts to minimize the pauses due to garbage collection by doing most of the garbage collection work concurrently with the application threads. Normally the concurrent low pause collector does not copy or compact the live objects. A garbage collection is done without moving the live objects. If fragmentation becomes a problem, allocate a larger heap.

**Garbage-First (G1) Garbage Collector:** 
- The Garbage-First (G1) collector is a server-style garbage collector, targeted for multi-processor machines with large memories. It meets garbage collection (GC) pause time goals with a high probability, while achieving high throughput. The G1 garbage collector is fully supported in Oracle JDK 7 update 4 and later releases.

**The G1 collector is designed for applications that:**
- Can operate concurrently with applications threads like the CMS collector.
- Compact free space without lengthy GC induced pause times.
- Need more predictable GC pause durations.
- Do not want to sacrifice a lot of throughput performance.
- Do not require a much larger Java heap.
- G1 is planned as the long term replacement for the Concurrent Mark-Sweep Collector (CMS). Comparing G1 with CMS, there are differences that make G1 a better solution. One difference is that G1 is a compacting collector. G1 compacts sufficiently to completely avoid the use of fine-grained free lists for allocation, and instead relies on regions. This considerably simplifies parts of the collector, and mostly eliminates potential fragmentation issues. Also, G1 offers more predictable garbage collection pauses than the CMS collector, and allows users to specify desired pause targets.



Each garbage collector has its strengths and is suited for specific use cases, allowing developers to choose the one that best fits the application's requirements.
 
Remember that explicitly invoking garbage collection methods (System.gc() or Runtime.getRuntime().gc()) doesn't guarantee immediate garbage collection, as the JVM decides when it's appropriate based on its internal algorithms. It's generally better to rely on the automatic garbage collection mechanisms unless there are specific reasons to intervene.

----------------------------------

# Q. In a performance-focused Java microservice application, handling memory efficiently is crucial. Here are some strategies to boost JVM performance:

1. **Choose the Right Garbage Collector:** Java offers several garbage collectors, each designed with different use cases in mind. For example, if you're working with a large heap and have a lot of spare CPU capacity, the G1 (Garbage First) or Z Garbage Collector could be a good choice. If you're working with a smaller heap and want to minimize pauses, the CMS (Concurrent Mark Sweep) collector might be better.

2. **Tune the JVM Heap Size:** The heap size should be tuned carefully. If it's too small, you'll get frequent garbage collections and possibly OutOfMemoryErrors. If it's too large, you might **Never** get OutOfMemoryErrors, but your garbage collections will take longer because there's more memory to scan. The optimal heap size depends on the number of live objects your application typically has, as well as the total amount of memory available.

3. **Use Appropriate Data Structures:** Using the right data structures can significantly reduce memory usage. For example, instead of using an ArrayList to store a large number of integers, you might use an int array to save memory.

4. **Avoid Memory Leaks:** Memory leaks can occur when objects are stored in collections and then forgotten about. Always ensure that objects are removed from collections when they're no longer needed.

5. **Use String.intern() Wisely:** The String.intern() method can help reduce the amount of memory used by your application by reusing strings that have already been created. However, it can also cause problems if used incorrectly, so use it wisely.

6. **Use Profiling Tools:** Tools like VisualVM, JProfiler, or YourKit can help you understand your application's memory usage and find bottlenecks or memory leaks.

7. **Use Compressed Oops:** Compressed ordinary object pointers (Oops) can reduce memory usage on 64-bit JVMs. This is enabled by default in recent JVMs when the heap size is less than 32GB.

8. **Use Off-Heap Memory:** For very large data sets, consider using off-heap memory. This can reduce garbage collection pauses, but it also makes your application more complex.

Remember, the best way to improve performance is to measure, make one change at a time, and then measure again. Always test your changes under realistic conditions to see how they affect your application's performance.

-----------------------------------

# PermGen vs MetaSpace

- It’s important to keep in mind that, starting with Java 8, the Metaspace replaces the PermGen – bringing some substantial changes.

###  PermGen
- The JVM keeps track of loaded class metadata in the PermGen. Additionally, the JVM stores all the static content in this memory section. This includes all the static methods, primitive variables, and references to the static objects.
- Furthermore, it contains data about bytecode, names, and JIT information. Before Java 7, the String Pool was also part of this memory. The disadvantages of the fixed pool size are listed below.
- The default maximum memory size for 32-bit JVM is 64 MB and 82 MB for the 64-bit version.

- However, we can change the default size with the JVM options:
  - -XX:PermSize=[size] is the initial or minimum size of the PermGen space
  - -XX:MaxPermSize=[size] is the maximum size
 
Most importantly, Oracle completely removed this memory space in the JDK 8 release. Therefore, if we use these tuning flags in Java 8 and newer versions, we’ll get the following warnings:

```
	>> java -XX:PermSize=100m -XX:MaxPermSize=200m -version
	OpenJDK 64-Bit Server VM warning: Ignoring option PermSize; support was removed in 8.0
	OpenJDK 64-Bit Server VM warning: Ignoring option MaxPermSize; support was removed in 8.0
	...
```
- With its limited memory size, PermGen is involved in generating the famous OutOfMemoryError. Simply put, the class loaders weren’t garbage collected properly and, as a result, generated a memory leak.
- Therefore, we receive a memory space error; this happens mostly in the development environment while creating new class loaders.

### Metaspace

- Simply put, Metaspace is a new memory space – starting from the Java 8 version; it has replaced the older PermGen memory space. The most significant difference is how it handles memory allocation.
- Specifically, this native memory region grows automatically by default.

We also have new flags to tune the memory:
- MetaspaceSize and MaxMetaspaceSize – we can set the Metaspace upper bounds.
- MinMetaspaceFreeRatio – is the minimum percentage of class metadata capacity free after garbage collection
- MaxMetaspaceFreeRatio – is the maximum percentage of class metadata capacity free after a garbage collection to avoid a reduction in the amount of space
- Additionally, the garbage collection process also gains some benefits from this change. The garbage collector now automatically triggers the cleaning of the dead classes, once the class metadata usage reaches its maximum metaspace size.
- Therefore, with this improvement, JVM reduces the chance to get the OutOfMemory error.

Despite all of these improvements, we still need to monitor and tune the metaspace to avoid memory leaks.

### Note
PermGen is still around with JDK 7 and older versions, but Metaspace offers more flexible and reliable memory usage for our applications.

-----------------------------------

## Access mods:
* public
* protected
* private

![image](https://github.com/Sumegh22/Learn-With-Sumegh/assets/84231944/57de1d78-b63e-4ee3-8a38-4d496ad175a5)

-------------------

## Static : 
static methods do not need objects of a class to be created. Static vars/ methods are object independent. hence you cannot/ must not use `this` keywords inside constructor or methods to reference static things, use the class name instead.

### static block:
When a class is loaded by the compiler, then static objects /methods are the very first thing that are evaluated.
Note: Static vars or methods are not object depended, and are called class level entities . You cannot have a non static member inside a static method.

A static block is executed only once.
```
static {
int a = 4;
}
```

* When a variable is declared static in Java programming, it means that the variable belongs to the class itself rather than to any specific instance of the class. This means that there is only one copy of the variable in memory, regardless of how many instances of the class are created.

* Here's an example. Say we have a Department class that has a static variable called numberOfWorker. We declare and increment the static variable at the constructor level to show the value of the static variable whenever the class object is created.

```
public class Department{
    public static int numberOfWorker= 0;
    public String name;
    
    public Department(String name) {
        this.name = name;
        numberOfWorker++; // increment the static variable every time a new
			  //Person is created
    }
}
```

* The results of the above code show that as we create new Department objects, the static variable numberOfWorker retains its value.

* When we print out the value of numberOfWorker in the console, we can see that it retains its value across all instances of the Department class. This is because there is only one copy of the variable in memory, and any changes to the variable will be reflected across all instances of the class.

```
Department dpt1 = new Department("Admin");
System.out.println(Department.numberOfWorker); // output: 1

Department dpt2 = new Department ("Finance");
System.out.println(Department.numberOfWorker); // output: 2

Department dpt3 = new Department ("Software");
System.out.println(Department.numberOfWorker); // output: 3

```

--------------------------

## OOPs Pillars

**Inheritance**
1. Single Inheritance: when One class is extended by another child class.
2. Multilevel , when one class B, which extends a class A is now extended by new child class C.  ( A is parent of B, B parent of C).
3. Multiple inheritance not supported in Java, but it can be achieved by using interfaces.
4. Hierarchial inheritance: When one class is inherited by multiple child classes
5. Cyclic inheritance: is not supported
6. Hybrid inheritance: involves mix of multiple inheritance, hence not supported in java.

- By default all class are extending obbject class.  When One class B extends a class A, then B has access to the methods and variables defined in A, which are not private. Also B can override methods in A.
- if we create an Object like
-  A obj = new B (), here the ref variable is of type A and hence determines what type of methods and vars object can access, meaning only the vars and methods in A, but suppose if a method in A is overriden in B then, if we call such method, the impl in B will be executed. by a phenomenon called dynamic method dispatch
-------------------------------------------------------------------------------------------------------------------------------------

**Polymorphism**
Meaning many ways to represent.
Static polymorphism, compile time poly, is when a method is overloaded, meaning when the params of a method are different. or at least order of params is different. method name should be same here.

Dynamic poly, runtime poly, is when a method is overridden. In this case, Same method name, same params but operation is different, and may be different (co-variant) return type.
Java supports* covariant return types for overridden methods. This means an overridden method may have a more specific return type as compared to method in parent class. That is, as long as the new return type is assignable to the return type of the method you are overriding, it's allowed.
If you don't have the same or narrower return type then you will get :- error: method() in subclass cannot override method() in superclass

Final key word, Final methods cannot be overridden, this means, when a final method is called, the compiler will call the byte code of this method, no matter how many time you make a call to it, and since there is no change, it increases execution efficiency and makes execution faster

- If you make constuctor of a class as Final, the class cannot be extended (inherited)
- static method cannot be overridden because, static methods are object/instance independent, so there is no point to make any change.

Overriding depends on object, Static does not depends on object, Hence STATIC CANNOT BE OVERRIDEEN

----------------------------------------------------------------------------------------------------------------------------------------

**Encapsulation:**
- Wrapping up of implementation of variables, methods and components  in a class. Hiding internal impl of a class and its data members.
- This is done, so that the child classes or others cannot access it. To do this one has to mark methods and member variables as private and now they become hidden from everyone else except, methods] in same class


Example
```
  public class Vehicle( )  {  
	  private String vehicleName;
	  private String company;
	  private Type vehicleType;
		
  	// Autowire dependencies or perform constructor based injection of dependencies.
	// use setter methods to set values to variables;
	// use getter methos to fetch values from variables, This way the internal implementation remains hidden
	// hence Encapsulation is achieved
  }

public class Car extends Vehicle implements PoweredVehicle{
	private String fuel;
	private Engine engine;
	private double mileage;

public Car(String vehicleName, String company, String vehilceType, String fuel, Engine engine){
super(vehicleName, company, vehilceType)
this.fuel = fuel;
this.engine = engine;

}
	// Override method from interface
	@Override
	public double mileage(String fuel, Engine engine){
	 returm mileage;
	}
 }

```

-------------------------------------------------------------------------------------------------------------------------------------
**Abstraction:**

- Abstraction is a fundamental concept of object-oriented programming that is used to hide the implementation details and show only the essential features of an object. It helps in reducing programming complexity and increasing efficiency.

Abstract Classes can have both Abstract methods (methods without a body) and normal method (non-abstract methods) : Abstraction can be achieved with either abstract classes or interfaces. An abstract class is a restricted class that cannot be used to create objects; to access it, it must be inherited from (extended by) another class. An abstract method can only be used in an abstract class, and it does not have a body. The body is provided by the subclass (inherited from or say extended by).

Giving flexibility to the child classes to implement existing methods based upon use case. Abstract methods do not have a body. Showing only the required information.
- If a class contains, one or more Abstract method, the class has to be made abstract.
Example:
```
// Abstract class

  public abstract class Parent( ) {  
    abstract void career(String name);
  }
```

- **Why Use Abstraction?:**
- Abstraction is used to achieve security - hide certain details and only show the important details of an object. It also helps in managing the complexity of large systems by separating the design and implementation.

- abstract classes can have variables, but what could be significance, since the method do not have a body
- Consider this example, Assume that there is a an abstract class vehicle, which has abstract run () method, which is being overridden in various vehicle as per the design and specification. Now if the govt lays new rules like BS6 vehicle have to keep 2L fuel in reserve, then this property will be directly inherited in each child class automatically. Means, it reduces overhead of child classes to implement variables explicitly if they all have some property in common

- You cannot create, Object of an abstract class, why because if the method is abstract, and you if you try to call this method through an object, how will it implement?..
- Constructors cannot be abstract, yet abstract classes have constructor, why ?, because if there are variables in abstract class, and you want to update the default value of that variable in any of a child class, then you can do that !.
- 
In Java, abstract classes can indeed have constructors, including parameterized constructors. However, you cannot directly create an object of an abstract class using the new keyword. The primary purpose of constructors in abstract classes is to be called by the constructors of their concrete subclasses.

- Initialization of Fields: Abstract classes may have fields that need to be initialized during construction. By having a constructor, you ensure that the initialization logic is executed when an instance of a concrete subclass is created. Parameters can be passed up the class hierarchy using the super keyword

- Enforcement of a Common Structure: Abstract classes may provide a common structure or behavior that should be shared among all concrete subclasses. Constructors in the abstract class can enforce certain initialization steps or behaviors that should be consistent across all subclasses.

```
abstract class Shape {
    protected String name;

    // Parameterized constructor in the abstract class
    public Shape(String name) {
        this.name = name;
    }

    // Abstract method to be implemented by subclasses
    abstract double calculateArea();
}

class Circle extends Shape {
    private double radius;

    // Constructor of the concrete subclass calling the constructor of the abstract class
    public Circle(String name, double radius) {
        super(name); // Calling the constructor of the abstract class
        this.radius = radius;
    }

    // Implementation of the abstract method
    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle("Circle1", 5.0);
        System.out.println("Area of " + circle.name + ": " + circle.calculateArea());
    }
}


```
------------------------

**Interface:**
- An interface is a completely "abstract class" that is used to group related methods with empty bodies.
- interface, is kind of a contract that binds any class which sign it to provide implementation to all its member method, why because interfaces contain abstract method only. Also any class can implement more than one interface
- members of an interface are public by default
- and variables are default static and final
- it is a file that is with .java extension same like class. Multiple inheritance can be achieved in a kind, because one class can implement multiple interfaces. Also resolution of methods  in interface (dynamic look up of methods) happen on runtime only, as a result they have a performance impact. Hence we should not use it causally in performance critical code


Can I implement a Java interface method as private or protected?

- No you cannot because there are some rules(more details are mentioned here) :
- In java, a method can only be written in Subclass, not in same class.
- The argument list should be exactly the same as that of the overridden method.
  The return type should be the same or a subtype of the return type declared in the original overridden method in the super class.
- The access level cannot be more restrictive than the overridden method’s access level. For example: if the super class method is declared public then the over-ridding method in the sub class cannot be either private or protected.
- Instance methods can be overridden only if they are inherited by the subclass.
- A method declared final cannot be overridden.
- A method declared static cannot be overridden but can be re-declared.
- If a method cannot be inherited then it cannot be overridden.
- A subclass within the same package as the instance’s superclass can override any superclass method that is not declared private or final.
- A subclass in a different package can only override the non-final methods declared public or protected.
- An overriding method can throw any uncheck exceptions, regardless of whether the overridden method throws exceptions or not.\
- However the overriding method should not throw checked exceptions that are new or broader than the ones declared by the overridden method. The overriding method can throw narrower or fewer exceptions than the overridden method.
- Constructors cannot be overridden.
- Classic examples of interface is Serializable, Comparable, Comparator

---------------------------


## comparable vs comparator :
-------------------------
https://www.javatpoint.com/difference-between-comparable-and-comparator
https://www.baeldung.com/java-comparator-comparable

```
  class Student implements Comparable<Student>{  
	  public int compareTo(Student st)   {  
	  if(age==st.age)  
	    return 0;  
	  else if(age>st.age)  
	     return 1;
	}

  }
  ```

------------------------------------------------------------------------------
Similarly 

```
class Student implements Comparator
public int compare(Student old_st, Student new_st)   {  
if(old_st.age==new_st.age)  
return 0;  
else if(old_st.age>new_st.age)  
return 1;  }
}
```

When to Use Which:

1. Use Comparable when you want to define the default natural ordering of objects in a class. For example, if there is a clear, intrinsic way to order objects (like sorting integers or strings), implement Comparable.
2. Use Comparator when you want to provide multiple sorting strategies for a class or when you want to sort objects based on criteria that are not intrinsic to the class. This allows you to sort objects in different ways without modifying the class itself.
3. In summary, if you control the class and want to define the default way of sorting its objects, implement Comparable. If you want to provide different ways to sort objects of a class without modifying the class itself, use Comparator.

-------------------------------------------------------
# HashCode and Equals contract

Read it here : https://www.baeldung.com/java-equals-hashcode-contracts

**Note:** Hashcode is not an address of object

-------------------------------------------------------

**Serializable:**
- Serializable is a marker interface (has no data member and method). It is used to "mark" Java classes so that the objects of these classes may get a certain capability. The Cloneable and Remote are also marker interfaces.

- Serialization in Java is a mechanism of writing the state of an object into a byte-stream. It is mainly used in Hibernate, RMI, JPA, EJB and JMS technologies.
The reverse operation of serialization is called deserialization where byte-stream is converted into an object. The serialization and deserialization process is platform-independent, it means you can serialize an object on one platform and deserialize it on a different platform.
- For serializing the object, we call the writeObject() method of ObjectOutputStream class, and for deserialization we call the readObject() method of ObjectInputStream class.
- We must have to implement the Serializable interface for serializing the object.
- The Serializable interface must be implemented by the class whose object needs to be persisted.
- The String class and all the wrapper classes implement the java.io.Serializable interface by default.
- Let's see the example given below: https://www.javatpoint.com/serialization-in-java

-------------------------------------------------------

## Object Clonning
--------------------------------------------
1. When we try to create one object from another using new keyword like as shown below
```
      A a = new A(x, y);
      A b = new A (a);
```   
a clone named 'b' of object 'a' is created using 'new' and this takes a lot of processing time. All of 
this can be reduced object clonning
3. To perform object cloning java.lang has a package has an interface called Cloneable
4. Here Shallow copy is created by default. To make a deep copy, you have to override clone method from object class. 

----------------------------------------------

## Lambdas, Streams and Functional Programming
--------------

1. Lambda functions are oneline functions (short hand functions). Kind of abstract methods that are overridden in your main code. 
2. Lambdas can be performed using Functional interfaces. 
3. Functional Interfaces are those that have only Single Abstract Method (SAM) present in them, besides they can have many default and static methods
4. Default methods are introduced to support backward compatibility. 

  ![img.png](../oops-images/img.png)

------------------------------------------------

## Exception Handling
---------------------------------
The object class is extended by Throwable, which is the root of exception handling.
The Parent of all Exceptions is Exception class
  ![img_1.png](../oops-images/img_1.png)

Error: When programs encounter an issue and terminate. The state of a program cannot be retained after an error is encountered.

Exception: when the normal flow of your code/ program meets with an unexpected hurdle. Which can be handled
1. Exception can be either **Checked Exception** (Compile time exception) or **Unchecked Exception** (Runtime exception).
2. To throw an exception explicitly through a method, you have to mention in the method signature that the method throws Exception.
3. Or you can call the throw wild card to throw an exception if a certain scenario is met. 

```
static void doSomething(int a, int b) throws ArithemeticException {
  }
```

4. To handle this make use of a try-catch block, try block tries to execute a particular operatn and if any error is encountered then it is
   addressed in the catch block. the code written in finally block would execute no matter what. We can put closure of resource, in finally block. 

  ![img_2.png](../oops-images/img_2.png)


| Type | Unchecked exception    | Checked Exceptions         |   |   |
|------|------------------------|----------------------------|---|---|
| List | NullPointerException   | ClassNotFoundException     |   |   |
|      | ClassCastException     | SocketException            |   |   |
|      | ArithmeticException    | SQLException               |   |   |
|      | DateTimeException      | IOException      	     |   |   |
|      | ArrayStoreException    | FileNotFoundException      |   |   |

----------------------------------

###  If a method in parent class does not throw any Checked exception, then is it possible to throw any exceptions from the overriden version of this method in child class ?
  Yes, it is possible for an overridden method in a child class to throw exceptions, even if the parent class method doesn't declare any exceptions in its throws clause.

However, there's a **crucial restriction:**

- The overridden method can **only throw unchecked exceptions** (runtime exceptions and errors). It cannot throw checked exceptions (exceptions derived from Exception class.  but can throw RuntimeException or exceptions derived from RuntimeException class ex: NullPointer, Arithematic its subclasses).
Reasoning:

- Checked exceptions force the calling code to explicitly handle them using try-catch blocks or declare them in the throws clause of the calling method. This ensures that the caller is aware of potential exceptions and takes appropriate actions.
- By not declaring any exceptions in its throws clause, the parent class method implies that it doesn't throw any checked exceptions under normal circumstances. Allowing the child class to introduce new checked exceptions would violate this contract and potentially break existing code that relies on the parent class method not throwing checked exceptions.

Example:

```
class ParentClass {
    public void someMethod() {
        // ... method implementation (doesn't throw any exceptions)
    }
}

class ChildClass extends ParentClass {
    @Override
    public void someMethod() throws NullPointerException { // Child throws unchecked exception (NullPointerException)
        // ... method implementation (might throw NullPointerException)
    }
}

```
In this case:

- ParentClass.someMethod() doesn't declare any exceptions in its throws clause.
- ChildClass.someMethod() overrides it and throws `NullPointerException`, which is an unchecked exception (a subclass of RuntimeException). This is allowed because unchecked exceptions don't require explicit handling in the throws clause of the calling method.
Key Points:

- If the parent class method doesn't throw checked exceptions, the overriding child class can only throw unchecked exceptions.
This restriction maintains exception handling contracts and avoids unexpected behavior in calling code.
Recommendation:

- If the child class method has the potential to throw a checked exception, consider refactoring the parent class method to declare that checked exception in its throws clause. This provides better code clarity and predictability for callers of both the parent and child methods

-----------------------------------------------------------

### SingleTon class:
When only one object of a class can be created.
steps to do this
1. Set the constructor as private
2. initialize a private object of that class, within itself
3. create a static method which can be called from other places that will check if an object exist,  will create if not and will then return the only object created for this class.

---------------------------------------------

**How to make a class immutable in Java**
-----------------------------------------------


To make a class immutable in Java, you need to follow a set of guidelines that prevent the modification of its state once an object is created. Here are the key steps:

**Make the class final:** Prevent the class from being extended to avoid any changes to its behavior in subclasses.


	public final class ImmutableClass {
	    // class implementation
	}
 
**Make fields private and final:** `Encapsulate` the state by making fields private and final. This ensures that they can only be initialized once during object creation.

	public final class ImmutableClass {
	    private final int intValue;
	    private final String stringValue;

	    public ImmutableClass(int intValue, String stringValue) {
	        this.intValue = intValue;
	        this.stringValue = stringValue;
	    }
	
	    // getters for fields, but no setters
	}

**Do not provide setter methods:** Avoid providing methods that can modify the internal state of the object.

**Ensure mutable objects are safely handled:** If your class contains references to mutable objects, ensure that they are not exposed directly. Either make defensive copies or return immutable copies.

```
	public final class ImmutableClass {
	    private final List<String> mutableList;
	
	    public ImmutableClass(List<String> mutableList) {
	        this.mutableList = new ArrayList<>(mutableList); // Defensive copy
	    }
	
	    public List<String> getMutableList() {
	        return new ArrayList<>(mutableList); // Return a copy, not the original list
	    }
	}
 ```

Make the class Serializable (optional): If you want your immutable class to be serializable, implement the Serializable interface. This is optional but can be useful in certain scenarios.


	public final class ImmutableClass implements Serializable {
	    // class implementation
	}
 
By following these guidelines, you ensure that instances of your class cannot be modified once they are created, providing the immutability property.

---------------------------------------------
