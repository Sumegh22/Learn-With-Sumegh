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
* A process in which a call to overridden method is resolved at runtime rather than compile time.
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
* 

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