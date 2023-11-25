# Spring Framework

## Description:
The Spring Framework provides a comprehensive programming and configuration model for modern Java-based enterprise applications - on any kind of deployment platform.
A key element of Spring is infrastructural support at the application level: Spring focuses on the "plumbing" of enterprise applications so that teams can focus on application-level business logic, without unnecessary ties to specific deployment environments.

## Overview:
It is a framework that helps developers to work on their application rather than, worrying about the non-functional code.
So that the dev can focus upon the apps business logic instead of the non-fucntional requirement. 
In simple terms in minimizes boiler plate code for devs
It is a dependency injection framework which  is a pattern that allows one to create / biild decoupled systems. 

**Support Policy and Migration**
For information about minimum requirements, guidance on upgrading from earlier versions and support policies, please check out the official Spring Framework wiki page

**Features**
* Core technologies: dependency injection, events, resources, i18n, validation, data binding, type conversion, SpEL, AOP.
* Testing: mock objects, TestContext framework, Spring MVC Test, WebTestClient.
* Data Access: transactions, DAO support, JDBC, ORM, Marshalling XML.
* Spring MVC and Spring WebFlux web frameworks.
* Integration: remoting, JMS, JCA, JMX, email, tasks, scheduling, cache and observability.
* Languages: Kotlin, Groovy, dynamic languages.

==========================================================================================================

# Why do we use Spring Framework

This part of the reference documentation covers all the technologies that are absolutely integral to the Spring Framework.

Foremost amongst these is the Spring Framework’s Inversion of Control (IoC) container. A thorough treatment of the Spring Framework’s IoC container is closely followed by comprehensive coverage of Spring’s Aspect-Oriented Programming (AOP) technologies. The Spring Framework has its own AOP framework, which is conceptually easy to understand and which successfully addresses the 80% sweet spot of AOP requirements in Java enterprise programming.

Coverage of Spring’s integration with AspectJ (currently the richest — in terms of features — and certainly most mature AOP implementation in the Java enterprise space) is also provided.

AOT processing can be used to optimize your application ahead-of-time. It is typically used for native image deployment using GraalVM.

==========================================================================================================

# Core Technologies

## Introduction to the Spring IoC Container and Beans

This chapter covers the Spring Framework implementation of the Inversion of Control (IoC) principle. IoC is also known as dependency injection (DI). It is a process whereby objects define their dependencies (that is, the other objects they work with) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. The container then injects those dependencies when it creates the bean. This process is fundamentally the inverse (hence the name, Inversion of Control) of the bean itself controlling the instantiation or location of its dependencies by using direct construction of classes or a mechanism such as the Service Locator pattern.

The org.springframework.beans and org.springframework.context packages are the basis for Spring Framework’s IoC container. The BeanFactory interface provides an advanced configuration mechanism capable of managing any type of object. ApplicationContext is a sub-interface of BeanFactory. It adds:

* Easier integration with Spring’s AOP features

* Message resource handling (for use in internationalization)
 
* Event publication
 
* Application-layer specific contexts such as the WebApplicationContext for use in web applications.

In short, the BeanFactory provides the configuration framework and basic functionality, and the ApplicationContext adds more enterprise-specific functionality. The ApplicationContext is a complete superset of the BeanFactory and is used exclusively in this chapter in descriptions of Spring’s IoC container. For more information on using the BeanFactory instead of the ApplicationContext, see the section covering the BeanFactory API.
In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans. A bean is an object that is instantiated, assembled, and managed by a Spring IoC container. Otherwise, a bean is simply one of many objects in your application. Beans, and the dependencies among them, are reflected in the configuration metadata used by a container.

## Summary:
1. In simple words we need spring/ boot to ease out development by externalizing maintaining the lifecycle of objects 
2. IOC container is responsible to instantiate, configure and assemble the objects. 
3. The IoC container gets this information through configuration.
4. We can configure the IOC container using an .xml, annotations or by Java configuration class


## Types of IOC
**A. Bean Factory:**
1. It is the most basic version of IOC
2. Bean Factory must be used only when memory consumption is critical

**B. Application context:**
1. Application context extends features of Bean Factory.
2. IoC of type Application context is the most widely used, it has advanced features, that are developed considering enterprise applications in mind.

**Process:** 
* Code calls to application context, context calls -> .xml configuration. 
* xml consists of beans tag, inside which individual bean(s) are te be defined. bean tag takes two arguments
* **<bean> id = "myVehicle" class="fully_qualified_name ex: com..." </bean>**
* Any required property can be set up within this bean tag using property tag. 

        <bean id ="" class="" scope="prototype">
        <property name="" ref""></property>
        </bean>
============================================================================================

## Dependency Injection:

When one class say class A is dependent on object of another class say B then this object without which class A cannot complete its task is called a dependency
and class A is called dependent class. 
So For the sake of class A to function properly we have to ensure that we inject an object of class B in it, this process of inection of object
with the help of spring is called dependency injection. And it can be achieved in following ways
1. **Constructor injection :** Injecting beans right when constructor of dependent class is invoked.
2. **Setter/Property injection :** using setter methods to set value of objects
3. **Field injection:** using properties to set value of a dependency.

============================================================================================
## Spring Bean Scopes:
Scope means lifecycle of a bean, and its types are as follows 
1. **Singleton :** default lifecycle of a bean created by, meaning, only one instance of an object (dependency) will be created. And whenever invoked, the call will be made to the same object, the object is shared. For example, if a bean of type Vehicle is created by Spring IoC then

        Vehicle vehicle1 = context.getBean("myVehicle", Vehicle.class);
        Vehicle vehicle2 = context.getBean("myVehicle", Vehicle.class); 
 will returm the same object


2. **Prototype :** 



        <bean id ="" class="" scope="prototype">
        <property name="" ref""></property>
        </bean>

3. Request : used in web-app
4. Session : used in web-app
5. Global-session : used in web-app

============================================================================================
## Annotations:

Dependency injection in spring can be done via three methods, Constructor injection, Setter injection and FIeld Injection
All these three injection can be achived by Annotation. 
However, Filed injection cannot be achived by .xml application context, but the other two methods const and setter injection can be done using .xml

### Constructor-injection vs Field-injection vs Setter-injection:
1. In Constructor-injection, the @Autowired annotation has to be applied on top of constructor and the DI is perfomed by injecting bean using constructor.
2. In Setter-injection, the @Autowired annotation has to be applied on top of setter method which is setting value of the property (bean) which is a component or dependency for current class
3. Field-injection, the easiest of 'em in this case, apply @Autowired annotation, right on top of the field or variable declaration. and Spring will handle the rest



### @Component: or @Component("bean_id")
1. When you apply a @Component annotation on declaration of any class, it tells spring framework that this class is now a spring bean and its lifecycle has to be managed by Spring.
2. by-default the Bean name (id) is used in camelCased classname.

### @Autowired:
1. Annotation used to inject the @Component beans. 
2. Using this annotation, spring starts to scan the classpath application context to look for a suitable bean that is to be injected

### @Qualifier:
1. When multiple beans of same type are available then, Spring framework gets confused a bit and throws an exception (UnsatisfiedDependencyException). It needs an explicit instruction, about which bean is to be injected when needed.
2. Qualifier annotation can be used in all three methods, with field injection, setter injection and with constructor injection

   | Filed injection                         | Setter injection                                              | Constructor Injection                                                        |
   |-----------------------------------------|---------------------------------------------------------------|------------------------------------------------------------------------------|
   | @Autowired                              | @Autowired                                                    | @Autowired                                                                   |
   | @Qualifier                              | @Qualifier                                                    | public Vehicle ( @Qualifier ("noDiscount") DiscountService discountService){ |
   | private DiscountService discountService | public setDiscountService (DiscountService discountService ){ | this.discountService = discountService }                                     |
   |                                         | this.discountService = discountService}                       |                                                                              |

### @Scope:
1. The @Scope annotation can be used to define scope of a bean, this has to be attached to a class in same fashion as the @Component annotation
2. Like in the declaration of class name
    
       @Component
       @Scope("prototype")
       public class Fuel {...}

### @Configuration:
1. This annotation tells spring to ignore the .xml file for configuration and instead take configurations (beans instantiation etc.) from class with @Configuration annotations

### @ComponentScan: 
1. @ComponentScan("base.package.path") : This annotation tells spring to look for the beans in the given base package
   When used this annotation, to get the application context, use the below class object
   
       AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ClassNameWhichHasConfigAnnotation.class")
       context.getBean("beanName", Vehicle.class);

### PropertySource("NameOfPropertyFile.properties")
1.  To give location of property file

### @Value:
1. Using @Value annotation we can inject values to properties from the application.properties file using placeholders

        @Value("${customer.propuserName}")
        private String userName

### @Bean:
1. Create a method and annotate it as @Bean, then a bean of the return type will be created
2. Whatever is the name of method, that will be considered as bean id


============================================================================================

