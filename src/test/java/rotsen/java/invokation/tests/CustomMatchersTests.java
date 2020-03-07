package rotsen.java.invokation.tests;

import org.junit.Assert;
import org.junit.Test;
import rotsen.java.invokation.Invokation;
import rotsen.java.invokation.Invoke;
import rotsen.java.invokation.lambda.Function1;

import java.util.function.Predicate;

public class CustomMatchersTests
{
   @Test
   public void noMatcher()
   {
      Invokation<Function1<String, Integer>, Integer, String>
              ivk = Invokation.of(( String s ) -> Integer.parseInt(s));
      Assert.assertFalse(Invoke.asOptional(ivk, "2").isPresent());
   }

   public Integer stringToInteger(String s){
      return Integer.parseInt(s);
   }

   @Test
   public void lambdaValueMatcher()
   {
      Invokation<Function1<String, Integer>, Integer, String>
              ivk = Invokation.of(this::stringToInteger, String.class);
      ivk.match(Integer.class, i -> i == 4, i -> "fire")
         .match(Integer.class, i -> i == 5, i -> "fem")
         .match(Integer.class, i -> i == 3, i -> "tre")
         .match(NumberFormatException.class, i -> "exception");
      Assert.assertEquals("tre", Invoke.asOptional(ivk, "3").get());
      Assert.assertEquals("fire", Invoke.asOptional(ivk, "4").get());
      Assert.assertEquals("exception", Invoke.asOptional(ivk, "not a number").get());
      Assert.assertFalse( Invoke.asOptional(ivk, "0").isPresent());
   }
}
