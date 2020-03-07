package rotsen.java.invokation.tests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

public class TestDataFactory
{
   public void voidReturnMethodWithoutParametersWithThrowsDeclaration() throws Exception
   {
      System.out.println("called voidReturnMethodWithoutParametersWithThrowsDeclaration");
   }

   public void voidReturnMethodWithoutParametersWithoutThrowsDeclaration()
   {
      System.out.println("called voidReturnMethodWithoutParametersWithoutThrowsDeclaration");
   }

   public OriginalRetval valueReturnMethodWithoutParametersWithThrowsDeclaration() throws Exception
   {
      System.out.println("called valueReturnMethodWithoutParametersWithThrowsDeclaration");
      return new OriginalRetval();
   }

   public OriginalRetval valueReturnMethodWithoutParametersWithThrowException() throws NumberFormatException
   {
      System.out.println("called valueReturnMethodWithoutParametersWithThrowException");
      throw new NumberFormatException("bla bla bla");
   }

   public String stringReturnMethodWithoutParametersWithThrowsDeclaration() throws NumberFormatException
   {
      return "a random string";
   }

   public OriginalRetval valueReturnMethodWithoutParametersWithoutThrowsDeclaration()
   {
      System.out.println("called valueReturnMethodWithoutParametersWithoutThrowsDeclaration");
      return new OriginalRetval();
   }

   @SneakyThrows
   public OriginalRetval valueReturnMethodWithoutParametersWithoutThrowsDeclarationWithDelay()
   {
      Thread.sleep(2000);
      return valueReturnMethodWithoutParametersWithoutThrowsDeclaration();
   }

   public OriginalRetval nullReturnMethodWithoutParametersWithThrowsDeclaration() throws Exception
   {
      System.out.println("called nullReturnMethodWithoutParametersWithThrowsDeclaration");
      return null;
   }

   public OriginalRetval nullReturnMethodWithoutParametersWithoutThrowsDeclaration()
   {
      System.out.println("called nullReturnMethodWithoutParametersWithoutThrowsDeclaration");
      return null;
   }

   public OriginalRetval valueReturnMethodWithParametersWithThrowsDeclaration( OriginalRetval object1 ) throws Exception
   {
      System.out.println("called valueReturnMethodWithParametersWithThrowsDeclaration");
      return object1;
   }

   public OriginalRetval valueReturnMethodWithParametersWithoutThrowsDeclaration( OriginalRetval object1 )
   {
      System.out.println("called valueReturnMethodWithParametersWithoutThrowsDeclaration");
      return object1;
   }

   public OriginalRetval returnValue( OriginalRetval obj )
   {
      return obj;
   }

   public OriginalRetval throwException( Throwable t ) throws Throwable
   {
      throw t;
   }

   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public static class OriginalRetval
   {
      private Boolean booleanProperty = true;
      private String stringProperty = "A String";
      private Long longProperty = 100L;
   }

   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public static class TransformedRetval
   {
      private String stringProperty;
   }
}