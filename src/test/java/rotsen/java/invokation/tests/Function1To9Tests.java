package rotsen.java.invokation.tests;

import org.junit.Assert;
import org.junit.Test;
import rotsen.java.invokation.Invokation;
import rotsen.java.invokation.Invoke;
import rotsen.java.invokation.lambda.*;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Function1To9Tests
{
   Function5<Integer, Integer, Integer, Integer, Integer, Double> SUM_OF_5 =
           ( Integer t1, Integer t2, Integer t3, Integer t4, Integer t5 )
                   -> 1.0 + t1 + t2 + t3 + t4 + t5;

   Function6<Integer, Integer, Integer, Integer, Integer, Integer, Double> SUM_OF_6 =
           ( Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6 )
                   -> 1.0 + t1 + t2 + t3 + t4 + t5 + t6;

   Function7<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Double> SUM_OF_7 =
           ( Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7 )
                   -> 1.0 + t1 + t2 + t3 + t4 + t5 + t6 + t7;

   Function8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Double> SUM_OF_8 =
           ( Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8 )
                   -> 1.0 + t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8;

   Function9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Double> SUM_OF_9 =
           ( Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9 )
                   -> 1.0 + t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9;

   @Test
   public void testFunction1() throws Throwable
   {
      Invokation<Function1<Integer, Double>, Double, String> times2 =
              Invokation.of(( Integer i ) -> 2.0 * i, String.class)
                        .match(Integer.class, d -> "failure")
                        .match(Double.class, d -> "=" + d);
      Assert.assertEquals("=4.0", Invoke.asAsyncOptional(times2, 2).get().get());
   }

   @Test
   public void testFunction2() throws Throwable
   {
      Invokation<Function2<Integer, Integer, Double>, Double, String> sumOf2 =
              Invokation.of(( Integer t1, Integer t2 ) -> 1.0 + t1 + t2, String.class);

      sumOf2.withExecutorService(Executors.newSingleThreadExecutor())
            .match(Integer.class, d -> "failure")
            .match(Double.class, d -> "=" + d);

      Future<Optional<String>> future = Invoke.asAsyncOptional(sumOf2, 2, 3);
      Assert.assertEquals("=6.0", future.get().get());
   }

   @Test
   public void testFunction3() throws Throwable
   {
      Invokation<Function3<Integer, Integer, Integer, Double>, Double, String> sum =
              Invokation.of(( Integer t1, Integer t2, Integer t3 ) -> 1.0 + t1 + t2 + t3, String.class)
                        .match(Integer.class, d -> "failure")
                        .match(Double.class, d -> "=" + d);
      Assert.assertEquals("=10.0", Invoke.asAsyncOptional(sum, 2, 3, 4).get().get());
   }

   @Test
   public void testFunction4() throws Throwable
   {
      Invokation<Function4<Integer, Integer, Integer, Integer, Double>, Double, String> sum =
              Invokation.of(( Integer t1, Integer t2, Integer t3, Integer t4 ) -> 1.0 + t1 + t2 + t3 + t4, String.class)
                        .match(Integer.class, d -> "failure")
                        .match(Double.class, d -> "=" + d);
      Assert.assertEquals("=15.0", Invoke.asAsyncOptional(sum, 2, 3, 4, 5).get().get());
   }

   @Test
   public void testFunction5() throws Throwable
   {
      Invokation<Function5<Integer, Integer, Integer, Integer, Integer, Double>, Double, String> sum =
              Invokation.of(SUM_OF_5, String.class)
                        .match(Integer.class, d -> "failure")
                        .match(Double.class, d -> "=" + d);
      Assert.assertEquals("=21.0", Invoke.asAsyncOptional(sum, 2, 3, 4, 5, 6).get().get());
   }

   @Test
   public void testFunction6() throws Throwable
   {
      Invokation<Function6<Integer, Integer, Integer, Integer, Integer, Integer, Double>, Double, String> sum =
              Invokation.of(SUM_OF_6, String.class)
                        .match(Integer.class, d -> "failure")
                        .match(Double.class, d -> "=" + d);
      Assert.assertEquals("=28.0",
                          Invoke.asAsyncOptional(sum, 2, 3, 4, 5, 6, 7).get().get());
   }

   @Test
   public void testFunction7() throws Throwable
   {
      Invokation<Function7<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Double>, Double, String> sum =
              Invokation.of(SUM_OF_7, String.class)
                        .match(Integer.class, d -> "failure")
                        .match(Double.class, d -> "=" + d);
      Assert.assertEquals("=36.0",
                          Invoke.asAsyncOptional(sum, 2, 3, 4, 5, 6, 7, 8).get().get());
   }

   @Test
   public void testFunction8() throws Throwable
   {
      Invokation<
              Function8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Double>,
              Double, String> sum = Invokation.of(SUM_OF_8);
      sum.match(Integer.class, d -> "failure")
         .match(Double.class, d -> "=" + d);
      Assert.assertEquals("=45.0",
                          Invoke.asAsyncOptional(sum, 2, 3, 4, 5, 6, 7, 8, 9).get().get());
   }

   @Test
   public void testFunction9() throws Throwable
   {
      Invokation<
              Function9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Double>,
              Double, String
              > sum = Invokation.of(SUM_OF_9, String.class)
                                .match(Integer.class, d -> "failure")
                                .match(Double.class, d -> "=" + d);
      Assert.assertEquals("=55.0",
                          Invoke.asAsyncOptional(sum, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                                .get().get());
   }
}
