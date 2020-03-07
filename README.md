# Invokation

This Java library provides a mechanism to "wrap" a method invocation, capture its 
return value or exceptions and route them to a corresponding processor.

Consider the following example:

```
⓵ public Integer stringToInteger(String s){
      return Integer.parseInt(s);
}

⓶ Invokation< ⓷ Function1< ⓸ String, ⓹ Integer>, ⓹ Integer, ⓺ String>
              ivk = Invokation.of( ⓵ this::stringToInteger,  ⓺ String.class);
            
⓻ ivk.match( ⓹ Integer.class, ⓼ i -> i == 4, ⓽ i -> "fire")
      .match( ⓹ Integer.class, ⓼ i -> i == 5, ⓽ i -> "fem")
      .match( ⓼ i -> i == 3, ⓽ i -> "tre")
      .match( ⓾ NumberFormatException.class, i -> "exception");

//use Invoke.asOptional or Invoke.asAsync to run the method and process the return 
System.out.println( ⓵⓵ Invoke.asOptional(ivk, "3").get()); // prints "tre"
System.out.println( ⓵⓵ Invoke.asOptional(ivk, "5").get()); // prints "fem"

// prints "exception" without throwing an Exception
System.out.println( ⓵⓵ Invoke.asOptional(ivk, "not a number").get()); 

```

**Where:**

⓵  the original method to be wrapped

⓶  Invokation instance created by calling `rotsen.java.invokation.Invokation.of()` 

⓷  if you use Java 11 or above you can declare the variable type as `var`, otherwise you will need to declare it 
by using the corresponding `rotsen.java.invokation.lambda.Function`. `Function0` if your method has no input parameters,
`Function1` if it has 1 input parameter and so on and so forth up to 9 parameters

⓸  all the types for the input parameters of the wrapped method are declared here

⓹  return type of the wrapped method

⓺  class of the new (transformed) return value. All value processors are expected to return a value of this type 

⓻  return value and exception matchers
- multiple matchers are declared by chaining `.match` after one another
- the first matcher whose test  method (⓼) returns true will be executed. Any subsequent matchers will not be evaluated
- matchers can be declared in multiple ways:
    - `.match(ReturnValueClass.class, v -> value-test(v), v -> return-value-processor(v))` // v is the value returned by the wrapped method
    - `.match(v -> value-test(v), v -> return-value-processor(v))` // v type is implicitly set to the class type of the original method return value
    - `.match(AnyException.class, e -> value-test(e), e -> exception-value-processor(e))` // e is the instance of the exception thrown by the wrapped method
    - `.match(customerMatcher)` // customerMatcher is an implementation of `rotsen.java.invokation.InvokationMatcher`
     

⓼  matcher test. This lambda expression or method returns true or false to indicate if this matcher's value processor is to
be executed 

⓽ the value processor. It receives the original return value as input and is expected to produce an instance of the
new return value defined in ⓺

⓾ it's also possible to match any of the Exceptions thrown by the original method 

⓵⓵ `Invoke.asOptional` runs the original method and sends its return value (or thrown execution) through the matchers

## Getting started

### Installing

Just add to your pom.xml the following dependency:

```.xml

<dependency>
    <groupId>rotsen.java</groupId>
    <artifactId>invokation</artifactId>
    <version>${invokation.version}</version>
</dependency>

```

## Features and Concept

- Invokation will always produce an instance of `java.lang.Optional` containing the processor return value or `Optional.empty()` if
the processor returns `null`  or when the original value does not get matched 
- It is also possible to perform an asynchronous invocation, by using `Invoke.asAsyncOptional()`   

## Examples and Use Cases

### What if the original method returns `null`

The matcher `.matchNull( processor )` is used to capture `null` as return value

```java

Invokation<Function0<OriginalRetval>, OriginalRetval, TransformedRetval>
  ivk =
  Invokation.of(() -> factory.nullReturnMethodWithoutParametersWithThrowsDeclaration(), TransformedRetval.class)
            .matchNull(() -> new TransformedRetval("property"));

Optional<TransformedRetval> retValue = Invoke.asOptional(ivk);

``` 

### What if the original method has `void` as return type

You need to match a "return value" of type `java.lang.Void.class`

```java

public void voidReturn() throws Exception
{
  // do something
}

Invokation<Function0<Void>, Void, TestDataFactory.TransformedRetval> ivk =
              Invokation.of(() -> this.voidReturn(), TestDataFactory.TransformedRetval.class);
     
      ivk.match(Void.class,
                () -> new TestDataFactory.TransformedRetval("property"));
     
      Optional<TestDataFactory.TransformedRetval> retValue = Invoke.asOptional(ivk);
     
      Assert.assertTrue(retValue.isPresent());
      Assert.assertEquals("property", retValue.get().getStringProperty());

```

### How to run it asynchronously 

- `Invoke.asAsyncOptional()` is used to trigger an asynchronous execution of the original method
- it will either create a new instance of `java.util.concurrent.ExecutorService` or use the one passed
 as a parameter in `Invokation.withExecutorService()` to perform the async invocation
- An instance of `Future<Optional<...>>` is returned by `Invoke.asAsyncOptional()` and can be used to monitor
the task status

```java

Invokation<Function2<Integer, Integer, Double>, Double, String> sumOf2 =
      Invokation.of(( Integer t1, Integer t2 ) -> 1.0 + t1 + t2, String.class);

sumOf2.withExecutorService(Executors.newSingleThreadExecutor())
      .match(Integer.class, d -> "failure")
      .match(Double.class, d -> "=" + d);

Future<Optional<String>> future = Invoke.asAsyncOptional(sumOf2, 2, 3);
Assert.assertEquals("=6.0", future.get().get());

```


## Authors

* **Rotsen Marcello** -  [GitHub](https://github.com/rotsenmarcello) [LinkedIn](https://www.linkedin.com/in/rotsenmarcello) 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

Heavily inspired by F# pattern matching (https://docs.microsoft.com/en-us/dotnet/fsharp/language-reference/pattern-matching)
