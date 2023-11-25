# Maven

## What is Maven
1. Maven is a powerful project management tool that is based on POM (project object model). It is used for projects build, dependency and documentation. 
2. It simplifies the build process like ANT. But it is too much advanced than ANT. 
3. Current version of Maven is 3.

In other words:
It downloads the dependencies underlying and compiles the code. With the resultant code .java files are converted to .class files and then the whole code is packaged in to a .war or .jar file now days only .jar files are created.
 

## Understanding the problem without Maven
There are many problems that we face during the project development. They are discussed below:

1) Adding set of Jars in each project: In case of struts, spring, hibernate frameworks, we need to add set of jar files in each project. It must include all the dependencies of jars also.

2) Creating the right project structure: We must create the right project structure in servlet, struts etc, otherwise it will not be executed.

3) Building and Deploying the project: We must have to build and deploy the project so that it may work.


## What it does?
Maven simplifies the above mentioned problems. It does mainly following tasks.

It makes a project easy to build
It provides uniform build process (maven project can be shared by all the maven projects)
It provides project information (log document, cross referenced sources, mailing list, dependency list, unit test reports etc.)
It is easy to migrate for new features of Maven

## How ? 
By performing all these tasks automatically !.. 

1. Downloads dependencies in bckg
2. Putting additional jars in class path, compiling source code into byte code
3. Running tests
4. packaging compiled code into deployable artifact such as .war pr .ja
5. Deploying these artifacts to an application server or respository

Apache Maven helps to manage :
* Builds
* Documentation
* Reporing
* SCMs
* Releases
* Distribution

## What is Build Tool
build tool takes care of everything for building a process. It does following:

* Generates source code (if auto-generated code is used)
* Generates documentation from source code
* Compiles source code
* Packages compiled code into JAR of ZIP file
* Installs the packaged code in local repository, server repository, or central repository

## Maven pom.xml file
* POM is an acronym for Project Object Model. The pom.xml file contains information of project and configuration information for the maven to build the project such as dependencies, build directory, source directory, test source directory, plugin, goals etc.

* Maven reads the pom.xml file, then executes the goal.

* Before maven 2, it was named as project.xml file. But, since maven 2 (also in maven 3), it is renamed as pom.xml.

##  Elements of maven pom.xml file
For creating the simple pom.xml file, you need to have following elements:

| Element      | Description                                                                                                                                                                                                                                                              |
|--------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| project      | It is the root element of pom.xml file.                                                                                                                                                                                                                                  |
| modelVersion | It is the sub element of project. It specifies the modelVersion. It should be set to 4.0.0.                                                                                                                                                                              |
| groupId      | It is the sub element of project. It specifies the id for the project group.                                                                                                                                                                                             |
| artifactId   | It is the sub element of project. It specifies the id for the artifact (project). An artifact is something that is either produced or used by a project. Examples of artifacts produced by Maven for a project include: JARs, source and binary distributions, and WARs. |
| version      | It is the sub element of project. It specifies the version of the artifact under given group.                                                                                                                                                                            |                                                                                                                                                                                                                                                             |   |   |   |
                                                                                                                                                                       |   |   |   |

File: pom.xml

        <project xmlns="http://maven.apache.org/POM/4.0.0"   
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0   
        http://maven.apache.org/xsd/maven-4.0.0.xsd">
        
        <modelVersion>4.0.0</modelVersion>  
        <groupId>com.javatpoint.application1</groupId>  
        <artifactId>my-app</artifactId>  
        <version>1</version>
        
        </project> 

## Maven pom.xml file with additional elements

Here, we are going to add other elements in pom.xml file such as:

| Element      | Description                                                                                  |
|--------------|----------------------------------------------------------------------------------------------|
| packaging    | defines packaging type such as jar, war etc.                                                 |
| name         | defines name of the maven project.                                                           |
| url          | defines url of the project.                                                                  |
| dependencies | defines dependencies for this project.                                                       |
| dependency   | defines a dependency. It is used inside dependencies.                                        |
| scope        | defines scope for this maven project. It can be compile, provided, runtime, test and system. |

File: pom.xml

        <project xmlns="http://maven.apache.org/POM/4.0.0"   
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0   
        http://maven.apache.org/xsd/maven-4.0.0.xsd">
        
        <modelVersion>4.0.0</modelVersion>
        
        <groupId>com.javatpoint.application1</groupId>  
        <artifactId>my-application1</artifactId>  
        <version>1.0</version>  
        <packaging>jar</packaging>
        
        <name>Maven Quick Start Archetype</name>  
        <url>http://maven.apache.org</url>
        
          <dependencies>  
            <dependency>  
              <groupId>junit</groupId>  
              <artifactId>junit</artifactId>  
              <version>4.8.2</version>  
              <scope>test</scope>  
            </dependency>  
          </dependencies>  
        
        </project> 