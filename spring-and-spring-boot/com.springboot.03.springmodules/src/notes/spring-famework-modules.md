## Modules in Spring Framework: 


## SpringBoot 

Spring Bean Lifecycle

1. In Spring, a bean is an object managed by the IoC container, which controls its creation, initialization, usage, and destruction. Understanding the bean lifecycle ensures efficient resource management and predictable application behavior.

2. A bean typically goes through these core phases:

3. Instantiation – The container creates the bean instance based on its definition (XML, Java Config, or annotations).

4. Dependency Injection – Required dependencies are injected via constructor, setters, or field injection.

5. Initialization – Custom logic runs after properties are set, using: @PostConstruct InitializingBean.afterPropertiesSet() Custom init-method in XML or initMethod in @Bean

6. Ready for Use – The bean is fully initialized and participates in application logic.

7. Destruction – Cleanup logic executes before removal, using: @PreDestroy DisposableBean.destroy() Custom destroy-method in XML or destroyMethod in @Bean

Example using annotations (modern and preferred):

```
package beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class HelloWorld {

@PostConstruct
public void init() {
System.out.println("Bean initialized using annotations");
}

@PreDestroy
public void destroy() {
System.out.println("Bean destroyed using annotations");
}
}
```

spring.xml:
```
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:context="http://www.springframework.org/schema/context"
xsi:schemaLocation="
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd">

<context:annotation-config/>
<bean id="hw" class="beans.HelloWorld"/>
</beans>
```

Driver:
```
ConfigurableApplicationContext context =
new ClassPathXmlApplicationContext("spring.xml");
context.close();
```

Alternative approaches:
```
Programmatic: Implement InitializingBean and DisposableBean for lifecycle hooks.

XML Config: Define init-method and destroy-method attributes in <bean>.
```

Best practices:

* Prefer @PostConstruct and @PreDestroy for clean, decoupled code.

* Always release heavy resources in destruction callbacks.

* Use BeanPostProcessor for cross-cutting initialization logic.

* mixing multiple lifecycle mechanisms in the same bean to prevent conflicts.

* By mastering these mechanisms, you can customize bean behavior, ensure resource safety, and maintain scalable Spring applications.



### Spring MVC
1. Spring Model View Controller is a framework in Spring, built for Java enterprise and Web Application, 
2. It supports IOC container and dependency injection.
3. It models the data and returns a view (Front end ). 
