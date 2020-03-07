package rotsen.java.invokation.tests;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import rotsen.java.invokation.Invokation;
import rotsen.java.invokation.Invoke;
import rotsen.java.invokation.lambda.Function0;
import rotsen.java.invokation.matchers.Matcher;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.*;

import static org.hamcrest.core.Is.is;

public class AsyncInvocationTests
{
   private TestDataFactory m = new TestDataFactory();
   Matcher<TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
           longPropertyIs100 = new Matcher<TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>()
   {
      @Override
      public Boolean matches( Object value )
      {
         return value instanceof TestDataFactory.OriginalRetval &&
                ( (TestDataFactory.OriginalRetval) value ).getLongProperty().equals(100L);
      }

      @Override
      public TestDataFactory.TransformedRetval process( TestDataFactory.OriginalRetval value )
      {
         return new TestDataFactory.TransformedRetval("processed by 100-matcher");
      }
   };
   Matcher<TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
           longPropertyIs250 = new Matcher<TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>()
   {
      @Override
      public Boolean matches( Object value )
      {
         return value instanceof TestDataFactory.OriginalRetval &&
                ( (TestDataFactory.OriginalRetval) value ).getLongProperty().equals(250L);
      }

      @Override
      public TestDataFactory.TransformedRetval process( TestDataFactory.OriginalRetval value )
      {
         return new TestDataFactory.TransformedRetval("processed by 250-matcher");
      }
   };

   @Test
   @SneakyThrows
   public void valueReturnMethodWithoutParametersWithoutThrowsDeclarationWithDelay()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.OriginalRetval>
              ivk =
              Invokation.of(() -> m.valueReturnMethodWithoutParametersWithoutThrowsDeclarationWithDelay());
      ivk.match(TestDataFactory.OriginalRetval.class, v -> v);
      Future<Optional<TestDataFactory.OriginalRetval>> retValue = Invoke.asFutureOptional(ivk);
      Assert.assertFalse(retValue.isDone());
      Thread.sleep(2500);
      Assert.assertTrue(retValue.isDone());
      Assert.assertTrue(retValue.get().isPresent());
      Assert.assertTrue(retValue.get().get().getBooleanProperty());
      Assert.assertEquals(Long.valueOf(100L), retValue.get().get().getLongProperty());
      Assert.assertEquals("A String", retValue.get().get().getStringProperty());
   }

   @Test
   @SneakyThrows
   public void valueReturnMethodWithoutParametersWithThrowsDeclaration()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.OriginalRetval>
              ivk = Invokation.of(() -> m.valueReturnMethodWithoutParametersWithThrowsDeclaration());
      ivk.match(TestDataFactory.OriginalRetval.class, v -> v);
      Future<Optional<TestDataFactory.OriginalRetval>> retValue = Invoke.asAsyncOptional(ivk);
      Assert.assertTrue(retValue.get().isPresent());
      Assert.assertTrue(retValue.get().get().getBooleanProperty());
      Assert.assertEquals(Long.valueOf(100L), retValue.get().get().getLongProperty());
      Assert.assertEquals("A String", retValue.get().get().getStringProperty());
   }

   @Test
   @SneakyThrows
   public void matchClassReturnObjectGenericExecutor()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk = Invokation.of(() -> m.valueReturnMethodWithoutParametersWithThrowsDeclaration());
      ivk.withExecutorService(new ThreadPoolExecutor(1,
                                                     1,
                                                     0L,
                                                     TimeUnit.MILLISECONDS,
                                                     new LinkedBlockingQueue<Runnable>()))
         .match(TestDataFactory.OriginalRetval.class,
                v -> {
                   return new TestDataFactory.TransformedRetval(v.getStringProperty());
                });
      Future<Optional<TestDataFactory.TransformedRetval>> retValue = Invoke.asAsyncOptional(ivk);
      Assert.assertTrue(retValue.get().isPresent());
      Assert.assertEquals("A String", retValue.get().get().getStringProperty());
   }

   @Test
   @SneakyThrows
   public void matchClassReturnObjectUsingCallableInterface()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk = Invokation.of(() -> m.valueReturnMethodWithoutParametersWithThrowsDeclaration());
      ivk.match(TestDataFactory.OriginalRetval.class,
                v -> {
                   return new TestDataFactory.TransformedRetval(v.getStringProperty());
                });
      Future<Optional<TestDataFactory.TransformedRetval>> task = Invoke.asFutureOptional(ivk);
      Optional<TestDataFactory.TransformedRetval> retValue = task.get();
      Assert.assertTrue(retValue.isPresent());
      Assert.assertEquals("A String", retValue.get().getStringProperty());
   }

   @Test
   @SneakyThrows
   public void matchExceptionReturnObject()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk = Invokation.of(() -> m.valueReturnMethodWithoutParametersWithThrowException());
      ivk.match(TestDataFactory.OriginalRetval.class,
                v -> {
                   return new TestDataFactory.TransformedRetval("from retval matcher");
                })
         .match(NumberFormatException.class,
                ( e ) -> {
                   return new TestDataFactory.TransformedRetval("recovered from exception");
                });
      Future<Optional<TestDataFactory.TransformedRetval>> retValue = Invoke.asAsyncOptional(ivk);
      Assert.assertTrue(retValue.get().isPresent());
      Assert.assertEquals("recovered from exception", retValue.get().get().getStringProperty());
   }

   @Test
   @SneakyThrows
   public void matchValueReturnObject()
   {
      Invokation<Function0<String>, String, TestDataFactory.TransformedRetval>
              ivk = Invokation.of(() -> m.stringReturnMethodWithoutParametersWithThrowsDeclaration());
      ivk.match(String.class,
                v -> "should not match 1".equalsIgnoreCase(v),
                v -> {
                   return new TestDataFactory.TransformedRetval(v);
                })
         .match(NumberFormatException.class,
                ( e ) -> {
                   return new TestDataFactory.TransformedRetval("recovered from exception");
                })
         .match(String.class,
                v -> "should not match 3".equalsIgnoreCase(v),
                v -> {
                   return new TestDataFactory.TransformedRetval(v);
                })
         .match(String.class,
                v -> "a random string".equalsIgnoreCase(v),
                v -> {
                   return new TestDataFactory.TransformedRetval(v);
                })
         .match(String.class,
                v -> "should not match 4".equalsIgnoreCase(v),
                v -> {
                   return new TestDataFactory.TransformedRetval(v);
                });
      Future<Optional<TestDataFactory.TransformedRetval>> retValue = Invoke.asAsyncOptional(ivk);
      Assert.assertTrue(retValue.get().isPresent());
      Assert.assertEquals("a random string", retValue.get().get().getStringProperty());
   }

   @Test
   @SneakyThrows
   public void matchValueWithCustomerMatcherReturnObject()
   {
      TestDataFactory.OriginalRetval obj = new TestDataFactory.OriginalRetval();
      obj.setLongProperty(250L);
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk = Invokation.of(() -> m.returnValue(obj));
      ivk.match(this.longPropertyIs100)
         .match(this.longPropertyIs250);
      Future<Optional<TestDataFactory.TransformedRetval>> retval = Invoke.asAsyncOptional(ivk);
      Assert.assertEquals(Long.valueOf(250L), obj.getLongProperty());
      Assert.assertTrue(retval.get().isPresent());
      Assert.assertEquals("processed by 250-matcher", retval.get().get().getStringProperty());
   }

   @Rule
   public ExpectedException exceptionRule = ExpectedException.none();

   @Test
   public void matchedExceptionIsCaptured() throws Throwable
   {
      TestDataFactory.OriginalRetval obj = new TestDataFactory.OriginalRetval();
      obj.setLongProperty(250L);
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk = Invokation.of(() -> m.throwException(new NumberFormatException()));
      ivk.match(IOException.class,
                ( e ) -> {
                   return new TestDataFactory.TransformedRetval("IOException matched");
                })
         .match(NumberFormatException.class,
                ( e ) -> {
                   return new TestDataFactory.TransformedRetval("NumberFormatException matched");
                })
         .match(this.longPropertyIs250);
      Future<Optional<TestDataFactory.TransformedRetval>> retval = Invoke.asAsyncOptional(ivk);
      Assert.assertTrue(retval.get().isPresent());
      Assert.assertEquals("NumberFormatException matched", retval.get().get().getStringProperty());
   }

   @Test
   public void uncaughtExceptionIsRethrow() throws Throwable
   {
      exceptionRule.expect(ExecutionException.class);
      exceptionRule.expectCause(is(NumberFormatException.class));
      TestDataFactory.OriginalRetval obj = new TestDataFactory.OriginalRetval();
      obj.setLongProperty(250L);
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk = Invokation.of(() -> m.throwException(new NumberFormatException()));
      ivk.match(IOException.class,
                v -> {
                   return new TestDataFactory.TransformedRetval(v.toString());
                })
         .match(longPropertyIs250);
      Future<Optional<TestDataFactory.TransformedRetval>> retval = Invoke.asAsyncOptional(ivk);
      retval.get();
   }
}