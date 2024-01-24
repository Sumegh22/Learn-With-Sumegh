# Notes : Funtional Programming

Why Java8
---------

-   Concurrency is a difficult problem. The Stream API was introduced to make parallel processing easier.
-   A result of this change was the introduction of Lambdas to make working with Streams easier.

Lambdas
-------

-   Lambdas are syntactical sugar over annonymous inner classes
-   There is no class associated with the lambda expression. It has a type but no object!
-   Lambda expressions can be used:
    -   to assign a value to a variable: `Callable c = () -> process();`
    -   as a parameter to a method: `myList.removeIf(s -> s.length() % 2 != 0);`
-   A Lambda expression can be used wherever the type is a functional interface.

Functional Intefaces
--------------------

-   A functional interface has the following attributes:
    -   It is an interface
    -   Has only one abstract method
    -   Has the `@FunctionalInterface` annotation. However, the compiler will treat any interface meeting the definition of a functional interface as a functional interface. It does not need to be marked with the `@FunctionalInterface` annotation.
-   Java 8 introduced some new concepts to interfaces:
    -   allow static method
    -   default methods (this allows for multiple inheritance of behaviour for Java).
-   So, an interface can be a functional interface with static and default methods providing it has ONLY ONE abstract method.

Functional Programming
----------------------

-   Using the Stream API and Lambdas requires a new way of thinking: Functional Programming.
    -   A name is only ever associated with one value
    -   The order of execution does not impact the results
    -   There is no fixed execution order
    -   Repeated changes are achieved by nested function calls
    -   New values may be associated with the same name through recursion
    -   Functional programming allows functions to be treated as values
-   External Iteration vs Internal Iteration
    -   External iteration is how I am used to programming in Java. However, there are some issues with this style:
        -   our code controls the iteration
        -   inherently serial: iterate from beginning to end.
        -   not thread safe unless steps are taken to make it so
    -   Internal iteration
        -   The API handles the iteration
        -   Allows for optimization by the library over the data structure (lazy traversal and possible parallel processing)
        -   Thread safe as the client logic is stateless

Example of external iteration:

```
int[] values = {1, 2, 3, 5, 7};
int sum = 0;
for(int num : values) {
    sum += num;
}
System.out.println("The sum is: " + sum);
```

Example of internal iteration:

```
int[] values = {1, 2, 3, 5, 7};
int sum = Arrays.stream(values).sum();
System.out.println("The sum is: " + sum);
```

-   Note with the iternal iteration code that an accumulator is not used by our code. Also, the processing does not need to be done sequentially, it could be done in parallel.

### Type Inference

The compiler is now smarter and can infer type. For example, given the following method definition:

```
static T process(List<T> l, Comparator<T> c)
```

You can use the method as follows:

```
List<String> list = getList();
String r = process(list, (x, y) -> x.length() - y.length())
```

Things to note about the above code:

-   The comparator operation is implemented as a lambda expression.
-   The compiler is able to infer the type of parameters `x` and `y`. It can do so in this case, because `list` was defined as a `String`. Since the `process` method states that both the `List` and `Comparator` parameters are of the same type, `T`, then the compiler can infer the `x` and `y` parameters in the lambda expression MUST be a String.
-   Parameters x and y are still statically typed, the compiler is simplying infering the type.
-   You can also explicitly state the type as follows:

```
String r = process(list, (String x, String y) -> x.length() - y.length())
```

Method References
-----------------

-   Method references let you pass an existing method definition just like a lambda

Example:

```
myList.sort(Integer::compare);
```

The above is shorthand. It calls the sort method on the List object. The compiler expands the above to:

```
myList.sort((x, y) -> Integer.compare(x, y));
```

### 3 Kinds Of Method References

#### Static Method

```
//Given a lambda expression of:
(args) -> ClassName.staticMethod(args)
//It can be written as:
ClassName::staticMethod

//Example:
(String s) -> System.out.println(s)
//It can be written as:
System.out::println
```

### Instance Method Of An Arbitrary Type

```
//Given a lambda expression of:
(arg0, rest) -> arg0.instanceMethod(rest)
//It can be written as:
ClassName::instanceMethod  //Where ClassName is the arg0 type.

//Example:
(String s, int i) -> s.substring(i)
//It can be written as:
String::substring
```

### Instance Method Of An Existing Object

```
//Given a lambda expression of:
(args) -> expr.instanceMethod(args)
//It can written as:
expr::instanceMethod  //The compiler can determine the type of the expr argument.

//Example:
(x) -> myObj.getTransaction(x)
//It can be written as:
myObj::getTransaction
```

### Constructor Reference

Similar to method reference, but you call `new` rather than a method name.

Example:

```
Factory<List<String>> f = () -> return new ArrayList<String>();
//It can be written as:
Factory<List<String>> f = ArrayList<String>::new;
```

Referencing External Variables In Lambda Expressions
----------------------------------------------------

-   The variable must be effectively final
-   effectively final - a variable that is not declared as `final` but respects the `final` requirements (i.e. it is not modified after being set).
-   Annonymous inner classes have rules on how to access variables (any variable you reference must be marked as final)
    -   Effectively a closure on a value
-   Java uses closures on values vs closure on variables
-   What does 'this' mean in a Lambda?
    -   'this' refers to the enclosing object, not the lambda itself
    -   The Lambda is an anonymous function!
        -   It is not assoicated with a class so there CANNOT be a 'this' for the Lambda
    -   WARNING: The compiler will insert a reference to 'this' for you where required.

Elements Of A Stream
--------------------

-   Similar to Unix/Linux where streams of data are passed between programs. (i.e. `cat file1 file2 | sort`)
-   Streams are NOT a data structure
-   The Stream can be infinite
    -   No concept of breaking out out of a Stream as there is not concept of loops
-   Can think of a Stream as a pipeline (although this is not how it is implemented internally)
-   The pipeline conists of 3 things:
    -   A source of elements we are going to process
    -   Intermediate Operation(s)
        -   The output of one operation can be the input to another operation
    -   A terminal operation
        -   A terminal operation does not produce a stream. It's result is one of two things:
            -   An explicit result (i.e. an integer value)
            -   A side effect (i.e. printing a message)
-   The pipeline is only evaluated when the terminal operation is called
-   Internal to the library are stream characteristics to help identify optimisations (i.e. identifying streams with distinct (unique) elements)

Example of a stream pipeline:

```
int values[] = {1, 87, -56, 3, 2, 23, 14};
int[] evenValues = Arrays.stream(values)
        .filter(num -> num % 2 == 0)
        .sorted()
        .toArray();	
```

-   Numerous places to get stream sources
    -   Examples:
        -   Collection Interface
        -   Arrays Class
        -   Files Class
        -   Random Numbers
            -   Random
            -   ThreadLocalRandom
            -   SplittableRandom
        -   JarFile/ZipFile
        -   BufferedReader
        -   Pattern
        -   CharSequence
        -   BitSet
        -   Static Methods on the Stream interface
-   Intermediate operations
    -   Operations must not modify the stream
    -   They are typically stateless
    -   Streams may be changed from sequential to parallel (and vice-versa)
        -   But you can't do both. The processing must be done either sequentially or parallel
        -   Last call wins (so if you have declared both sequential and parallel in your pipeline, the last call will determine how it is processed.)
    -   See the [Streams API](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) for the intermediate operations available.
-   Terminal Operations
    -   Terminates the pipeline of operations on the stream
    -   Only at this point is any processing performed
        -   This allows for optimisation of the pipeline
            -   Lazy evaluation
            -   Merged/fused operations
            -   Elimination of redundant operations
            -   Parallel execution
    -   Generates an explicit result or a side effect
    -   See the [Streams API](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) for the terminal operations available.

Reductions
----------

-   Reduce is a terminal opertaion
-   Reduction take a stream and reduces it to a single value
    -   Like a recursive approach, but without the resource overhead
-   Requires you to think differently to an imperative, loop based approach

Example of a reduction:

```
//Find the longest word in the words List
List<String> words = Arrays.asList("take", "the", "light", "bottle", "echo");
String longestWord = words.stream()
        .max(Comparator.comparingInt(String::length))
        .get();
```

Collectors
----------

-   collect is a terminal operation
-   You can collect into a Collection (Set, List or Map)
-   You can group results using `groupingBy`
-   You can join string results using `joining`
-   There are many numeric collectors such as `summarizingInt` and `maxBy(Comparator)`
-   See <https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html> for more details
-   You can also create your own Collector

Example of a collector

```
//Join all the elements of the List into a pipe delimited String
List<String> items = Arrays.asList("item1", "item2", "item3", "item4");
String allItems = items.stream().collect(Collectors.joining("|"));
```

forEach
-------

-   When to use forEach
    -   when using an infinite stream (forEach consumes elements from the stream, but does not terminate it)
    -   No state is being modified
    -   Simplified iteration (e.g. printing values from the stream)
    -   May be made parallel if order is not important
-   When not to use forEach
    -   If you are thinking of using forEach(), stop!
    -   Can it be replaced with a combination of mapping and reduction? If so, it is unlikely to be the right approach to be functional

Parallel Streams
----------------

-   Implemented internally using the fork-join framework
-   Will default to as many threads for the pool as the OS reports processors
    -   Which may not be what you want System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "32767"); //32767 is the max number you can set it to
-   Parallel streams require more overhead. You will need to test for your situation whether it is faster or not to use a sequential or parallel stream.
-   Stream can be made parallel or sequential at any point by using the `parallel()` or `sequential()` methods
-   The last call wins
    -   Whole stream is either sequential or parallel

Debugging
---------

-   Use peek(Consumer c) to sbserve stream elements as they go past
    -   peek(Consumer c) - Returns an output stream that is identical to the input stream
    -   Useful for debugging and doing more than one thing with a stream
-   It is difficult to set breakpoints in a pipeline. Try extracting lambda code into a seperate method and replace the lambda with a method reference to the new method. The breakpoint will need to be set in the new method.

The Optional Class
------------------

-   Checking for `null` leads to very verbose code. Lots of `if (obj != null) { ... } `statements
-   Optional
    -   Container for an object reference (null, or real object)
    -   Think of it like a stream of 0 or 1 elements. 0 elements = null, 1 element = reference to object
    -   Guaranteed that the Optional reference returned will not be null

Example:

```
//old way
if (x != null) {
  print(x);
}

//using Optional
opt.ifPresent(this::print);
```

Logger Class Changes
--------------------

-   Performance issue with logging class:
    -   Log String is passed as parameter to logging method (`logger.finest logger.info` etc). However, that message may never be logged if log level is set at higher level (i.e. calling `logger.finest("some message")` but the log level is set to INFO).
-   New methods takes a `Supplier` as an argument
-   Now passing a Lambda expression to the method rather than the String itself.
    -   Passing how to create the message rather than the message itself
    -   This means if the log level is higher than the logging method it will not bother executing the lambda
    -   Remember this for own coding. Can do similar pattern when usage of data is conditional

Example of new logging technique:

```
logger.fine(() -> createComplexLogMsg());	
```

Ref : https://github.com/MonicaG/oracle_java8_mooc

-------