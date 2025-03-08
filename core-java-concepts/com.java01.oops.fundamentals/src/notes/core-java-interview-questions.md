## What is JDK? 
* JDK is a superset of JRE. 
* JDK contains everything that JRE has, along with development tools for developing, debugging, and monitoring Java applications.
* You need JDK when you need to develop Java applications. 
* Same as JREs, JDKs are also platform dependent. 
* So take care when you download the JDK package for your machine. JDK = JRE + Development Tools 

## What is JRE ? 
* JRE (Java Runtime Environment) is a software package that provides Java class libraries, along with Java Virtual Machine (JVM), and other components to run applications written in Java programming. 
* JRE is the superset of JVM. If you need to run Java programs, but not develop them, JRE is what you need. 

## What is JVM ? 
* Java Virtual machine (JVM) is the virtual machine that runs the Java bytecodes. 
* You get this bytecode by compiling the .java files into .class files.
* .class files contain the bytecodes understood by the JVM. 

## What is JIT ? The JIT compiler. 
* The Just-In-Time (JIT) compiler is a component of the runtime environment that improves the performance of Java™ applications by compiling bytecodes to native machine code at run time. 
* When a method has been compiled, the JVM calls the compiled code of that method directly instead of interpreting it.


## Association
* Association refers to the relationship between multiple objects. It refers to how objects are related to each other and how they are using each other's functionality. Composition and aggregation are two types of association.

## Composition
* The composition is the strong type of association. An association is said to composition if an Object owns another object and another object cannot exist without the owner object. Consider the case of Human having a heart. Here Human object contains the heart and heart cannot exist without Human.

## Aggregation
* Aggregation is a weak association. An association is said to be aggregation if both Objects can exist independently. For example, a Team object and a Player object. The team contains multiple players but a player can exist without a team.
* aggregation in java,association in java,is a relationship in java,is a and has a relationship in java,java association,java association aggregation composition,java is a relationship,java has a relationship,association aggregation and composition in java,aggregation and composition in java

## What is Covariant return type in Java ?
* To clarify this with an example, a common case is Object.clone() - which is declared to return a type of Object.
* Java allows for Covariant Return Types, which means you can vary your return type as long you are returning a subclass of your specified return type.
* So in a nutshell Covariant return type in Java is :
      
        Method Overriding in Covariant return type in Java allows a subclass to override the behavior of an existing superclass method and specify a return type that is some subclass of the original return type. 
        It is best practice to use the @Override annotation when overriding a superclass method.
        So this is all about Covariant return type in Java.

## Polymorphism
* Ability of an object to take Many forms.
* Any object in java that passes IS-A test is polymorphic and since all objects extends Object class hence all object are polymorphic.
* Polymorphism in Java has two types: Compile time polymorphism (static binding) and Runtime polymorphism (dynamic binding). Method overloading is an example of static polymorphism, while method overriding is an example of dynamic polymorphism.

* To implement polymorphism in java we have 2 ways :
    * Static can be implemented by overloading
    * Dynamic can be implemented by Overriding

* If child class has same method (with same signature) as defined in parent class is overriding
* A process in which a call to overridden method is resolved at runtime rather than compile time is called method overriding and how this happens is called dynamic method dispatch.
* Methods having same names but different Parameters is method overloading. Eg
    * Add(a,b) and Add(a,b,c)
      Both methods add all arguments

### Static Polymorphism (method overLoading : No associated annotation)
* Methods having same names but different Parameters is method overloading or static polymorphism. Eg
Add(a,b) and Add(a,b,c)
Both methods add all arguments

**Imp**
* Overloading / static polymorphism is not possible by changing the return type only. It gives compile time error due to ambiguity.

**Real-Life-example**
* Frog: Since a frog can behave as an aquatic animal and also as a Terrestrial animal Hence Frog is Polymorphic object.


### Dyanamic Polymorphism (method overriding :  @Override)
* Method overriding is one of the ways in which Java supports Runtime Polymorphism. Dynamic method dispatch is the mechanism by which a call to an overridden method is resolved at run time, rather than compile time.
* if a superclass contains a method that is overridden by a subclass, then when different types of objects are referred to through a superclass reference variable, different versions of the method are executed.
* In this video we find an answer with live example for Runtime Polymorphism or dynamic polymorphism with Data Members
* In Java, we can override methods only, not the variables(data members), so runtime polymorphism or dynamic polymorphism cannot be achieved by instance variables or data members.

### Default methods in interface:
* Before Java 8, interfaces could have only abstract methods.
* The implementation of these methods has to be provided in a separate class.
* So, if a new method is to be added in an interface, then its implementation code has to be provided in the class implementing the same interface.
* To overcome this issue, Java 8 has introduced the concept of default methods which allow the interfaces to have methods with implementation without affecting the classes that implement the interface.

## Diamond Problem (Ambiguity due to default method in Inheritance):
* Diamond problem (in inheritance) is an ambiguity problem that can arise as a consequence of allowing multiple inheritance through default methods that were introduced in java 8.
* But if you have two interfaces having default method with same name, and a class implements both these interfaces, then ambiguity arises. 
* as a result java compiler tells us upfront to override default method from one of the interfaces. 

## Static method in interfaces:
* This came along with default methods in interface as in Java 8.
* Simple reason that the implementing classes should not be allowed to override the method.

## Pass by value and refernece:
* When you use any method and pass a variable as input to it, a copy of the value of that variable is taken into consideration and operations aare performed on this given value, once the method execution is completed if you compare the variable passed and value of the output there may or may not be difference based upon method body, but one thing worth noticing is the memory address of both the vars that we are talking about would be different. this proves java is pass by value and not reference

**IMPORTANT:**
## What happens when you pass object references to a method?..
* If you pass object references to a method the object refernce (values of attributes inside this reference) can be changed.
* Because when we pass object refernce we -> this indicates that we are passing a memory address to a method
* So if any modification is done it is done on top of your obj-ref hence comparing an object before and after a method execution
  would differ based on what task is perform in the method you passed your object to

## Equals and Hashcode
* equals and hashcode contract By default, the Java super class java.lang.Object provides two important methods for comparing objects: equals() and hashcode(). 
* These methods become very useful when implementing interactions between several classes in large projects. In this article, we will talk about the relationship between these methods, their default implementations, and the circumstances that force developers to provide a custom implementation for each of them.
* equals(Object obj): a method provided by java.lang.Object that indicates whether some other object passed as an argument is "equal to" the current instance. The default implementation provided by the JDK is based on memory location --- two objects are equal if and only if they are stored in the same memory address.
* hashcode(): a method provided by java.lang.Object that returns an integer representation of the object memory address. By default, this method returns a random integer that is unique for each instance. This integer might change between several executions of the application and won't stay the same

## How equals and hashcode contract works in java ?
* The default implementation is not enough to satisfy business needs, especially if we're talking about a huge application that considers two objects as equal when some business fact happens. In some business scenarios, developers provide their own implementation in order to force their own equality mechanism regardless the memory addresses.
* As per the Java documentation in perspective of equal and hashcode contract, developers should override both methods in order to achieve a fully working equality mechanism --- it's not enough to just implement the equals() method.

# String interview questions ?

## Q1 are the below two equal ?.
```aiexclude

String s1 = "Hello";
String s2 = new String("Hello");

``` 
* The two strings s1 and s2 in your example are not equal in terms of reference, but they are equal in terms of content. Let me explain this in detail:
* The string "Hello" is a string literal. In Java, string literals are stored in the String Pool (a special memory area in the heap).
* The new String("Hello") explicitly creates a new String object in the heap memory, even if "Hello" already exists in the String Pool. This means s2 points to a different object in memory than s1.
* The == operator checks if s1 and s2 refer to the same object in memory. Which is false
* The .equals() method checks if the content of the strings is the same. which is true

## Q2 are the below two equal ?.
```aiexclude

String s1 = new String("Hello");
String s2 = new String("Hello");

```
* No, s1 and s2 are not equal in terms of reference (s1 == s2 is false) because new String("Hello") creates two separate objects in the heap, so they do not point to the same memory location. 
* However, they are equal in terms of content (s1.equals(s2) is true) because both strings contain the same characters, "Hello".
* Always use .equals() to compare string content rather than ==, which checks reference equality.

 
