package rotsen.java.invokation.tests;

import org.junit.Assert;
import org.junit.Test;
import rotsen.java.invokation.Invokation;
import rotsen.java.invokation.Invoke;
import rotsen.java.invokation.lambda.Function0;

import java.util.Optional;
import java.util.function.Function;

public class SyncInvocationTests
{
   private TestDataFactory factory = new TestDataFactory();

   @Test
   public void voidReturnMethodWithoutParametersWithThrowsDeclaration()
   {
      Invokation<Function0<Void>, Void, Void>
              ivk =
              Invokation.of(factory::voidReturnMethodWithoutParametersWithThrowsDeclaration);
      Optional<Void> retValue = Invoke.asOptional(ivk);
      Assert.assertFalse(retValue.isPresent());
   }

   @Test
   public void voidReturnMethodWithoutParametersWithoutThrowsDeclaration()
   {
      Invokation<Function0<Void>, Void, Void>
              ivk =
              Invokation.of(factory::voidReturnMethodWithoutParametersWithoutThrowsDeclaration);
      Optional<Void> retValue = Invoke.asOptional(ivk);
      Assert.assertFalse(retValue.isPresent());
   }

   @Test
   public void valueReturnMethodWithoutParametersWithoutThrowsDeclaration()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.OriginalRetval>
              ivk =
              Invokation.of(factory::valueReturnMethodWithoutParametersWithoutThrowsDeclaration,
                            TestDataFactory.OriginalRetval.class);
      ivk.match(TestDataFactory.OriginalRetval.class, v -> v);
      Optional<TestDataFactory.OriginalRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertTrue(retValue.isPresent());
      Assert.assertTrue(retValue.get().getBooleanProperty());
      Assert.assertEquals(Long.valueOf(100L), retValue.get().getLongProperty());
      Assert.assertEquals("A String", retValue.get().getStringProperty());
   }

   @Test
   public void valueReturnMethodWithoutParametersWithThrowsDeclaration()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.OriginalRetval>
              ivk =
              Invokation.of(factory::valueReturnMethodWithoutParametersWithThrowsDeclaration);
      ivk.match(TestDataFactory.OriginalRetval.class, v -> v);
      Optional<TestDataFactory.OriginalRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertTrue(retValue.isPresent());
      Assert.assertTrue(retValue.get().getBooleanProperty());
      Assert.assertEquals(Long.valueOf(100L), retValue.get().getLongProperty());
      Assert.assertEquals("A String", retValue.get().getStringProperty());
   }

   @Test
   public void nullReturnMethodWithoutParametersWithoutThrowsDeclaration()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.OriginalRetval>
              ivk =
              Invokation.of(() -> factory.nullReturnMethodWithoutParametersWithoutThrowsDeclaration());
      Optional<TestDataFactory.OriginalRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertFalse(retValue.isPresent());
   }

   @Test
   public void nullReturnMethodWithoutParametersWithThrowsDeclaration()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.OriginalRetval>
              ivk =
              Invokation.of(() -> factory.nullReturnMethodWithoutParametersWithThrowsDeclaration());
      Optional<TestDataFactory.OriginalRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertFalse(retValue.isPresent());
   }

   @Test
   public void matchNull()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk =
              Invokation.of(() -> {return (TestDataFactory.OriginalRetval) null;}, TestDataFactory.TransformedRetval.class)
                        .matchNull(() -> new TestDataFactory.TransformedRetval("property"));
      Optional<TestDataFactory.TransformedRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertTrue(retValue.isPresent());
      Assert.assertEquals("property", retValue.get().getStringProperty());
   }

   @Test
   public void matchVoidReturnObject()
   {
      Invokation<Function0<Void>, Void, TestDataFactory.TransformedRetval> ivk =
              Invokation.of(() -> factory.voidReturnMethodWithoutParametersWithThrowsDeclaration(),
                            TestDataFactory.TransformedRetval.class);
      ivk.match(Void.class,
                () -> new TestDataFactory.TransformedRetval("property"));
      Optional<TestDataFactory.TransformedRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertTrue(retValue.isPresent());
      Assert.assertEquals("property", retValue.get().getStringProperty());
   }

   @Test
   public void matchVoidReturnVoid()
   {
      StringBuffer buf = new StringBuffer();
      Invokation<Function0<Void>, Void, Void>
              ivk =
              Invokation.<Void, Void>of(() -> factory.voidReturnMethodWithoutParametersWithThrowsDeclaration())
                      .match(Void.class,
                             () -> buf.append("from matcher"));
      Optional<Void> retValue = Invoke.asOptional(ivk);
      Assert.assertFalse(retValue.isPresent());
      Assert.assertEquals("from matcher", buf.toString());
   }

   @Test
   public void matchClassReturnObject()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk =
              Invokation.of(() -> factory.valueReturnMethodWithoutParametersWithThrowsDeclaration(),
                            TestDataFactory.TransformedRetval.class)
                        .match(TestDataFactory.OriginalRetval.class,
                               ( v ) -> {
                                  return new TestDataFactory.TransformedRetval(v.getStringProperty());
                               });
      Optional<TestDataFactory.TransformedRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertTrue(retValue.isPresent());
      Assert.assertEquals("A String", retValue.get().getStringProperty());
   }

   @Test
   public void matchClassReturnNullAsOptionalEmpty()
   {
      StringBuffer buf = new StringBuffer();
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk =
              Invokation.of(() -> factory.valueReturnMethodWithoutParametersWithThrowsDeclaration(),
                            TestDataFactory.TransformedRetval.class)
                        .match(TestDataFactory.OriginalRetval.class,
                               ( v ) -> {
                                  buf.append("from matcher");
                                  return null;
                               });
      Optional<TestDataFactory.TransformedRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertEquals("from matcher", buf.toString());
      Assert.assertFalse(retValue.isPresent());
   }

   @Test
   public void matchClassReturnVoidAsOptionalEmpty()
   {
      StringBuffer buf = new StringBuffer();
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk =
              Invokation.of(() -> factory.valueReturnMethodWithoutParametersWithThrowsDeclaration(),
                            TestDataFactory.TransformedRetval.class)
                        .match(TestDataFactory.OriginalRetval.class,
                               ( v ) -> {
                                  buf.append("from matcher");
                               });
      Optional<TestDataFactory.TransformedRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertEquals("from matcher", buf.toString());
      Assert.assertFalse(retValue.isPresent());
   }

   @Test
   public void matchValueReturnVoidAsOptionalEmpty()
   {
      StringBuffer buf = new StringBuffer();
      Invokation<Function0<String>, String, Void> ivk =
              Invokation.of(() -> factory.stringReturnMethodWithoutParametersWithThrowsDeclaration(), Void.class)
                        .match(String.class,
                               v -> "a random string".equalsIgnoreCase(v),
                               v -> {
                                  buf.append("from matcher");
                               });
      Optional<Void> retValue = Invoke.asOptional(ivk);
      Assert.assertEquals("from matcher", buf.toString());
      Assert.assertFalse(retValue.isPresent());
   }

   @Test
   public void matchExceptionReturnNullAsOptionalEmpty()
   {
      StringBuffer buf = new StringBuffer();
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk =
              Invokation.of(() -> factory.valueReturnMethodWithoutParametersWithThrowException(),
                            TestDataFactory.TransformedRetval.class)
                        .match(TestDataFactory.OriginalRetval.class,
                               ( v ) -> {
                                  buf.append("from retval matcher");
                                  return new TestDataFactory.TransformedRetval();
                               })
                        .match(NumberFormatException.class,
                               ( t ) -> {
                                  buf.append("from exception matcher");
                                  return null;
                               });
      Optional<TestDataFactory.TransformedRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertEquals("from exception matcher", buf.toString());
      Assert.assertFalse(retValue.isPresent());
   }

   @Test
   public void matchExceptionReturnObject()
   {
      Invokation<Function0<TestDataFactory.OriginalRetval>, TestDataFactory.OriginalRetval, TestDataFactory.TransformedRetval>
              ivk =
              Invokation.of(() -> factory.valueReturnMethodWithoutParametersWithThrowException(),
                            TestDataFactory.TransformedRetval.class)
                        .match(TestDataFactory.OriginalRetval.class,
                               ( v ) -> {
                                  return new TestDataFactory.TransformedRetval("from retval matcher");
                               })
                        .match(NumberFormatException.class,
                               ( t ) -> {
                                  return new TestDataFactory.TransformedRetval("recovered from exception");
                               });
      Optional<TestDataFactory.TransformedRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertTrue(retValue.isPresent());
      Assert.assertEquals("recovered from exception", retValue.get().getStringProperty());
   }

   @Test
   public void matchValueReturnObject()
   {
      Invokation<Function0<String>, String, TestDataFactory.TransformedRetval>
              ivk =
              Invokation.<String, TestDataFactory.TransformedRetval>of(() -> factory.stringReturnMethodWithoutParametersWithThrowsDeclaration())
                      .match(String.class,
                             v -> "should not match 1".equalsIgnoreCase(v),
                             (Function<String, TestDataFactory.TransformedRetval>) TestDataFactory.TransformedRetval::new)
                      .match(NumberFormatException.class, ( t ) -> {
                         return new TestDataFactory.TransformedRetval("recovered from exception");
                      })
                      .match(String.class,
                             v -> "should not match 3".equalsIgnoreCase(v),
                             (Function<String, TestDataFactory.TransformedRetval>) TestDataFactory.TransformedRetval::new)
                      .match(String.class,
                             v -> "a random string".equalsIgnoreCase(v),
                             (Function<String, TestDataFactory.TransformedRetval>) TestDataFactory.TransformedRetval::new)
                      .match(String.class,
                             v -> "should not match 4".equalsIgnoreCase(v),
                             (Function<String, TestDataFactory.TransformedRetval>) TestDataFactory.TransformedRetval::new);
      Optional<TestDataFactory.TransformedRetval> retValue = Invoke.asOptional(ivk);
      Assert.assertTrue(retValue.isPresent());
      Assert.assertEquals("a random string", retValue.get().getStringProperty());
   }
}