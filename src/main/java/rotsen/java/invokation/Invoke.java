package rotsen.java.invokation;

import rotsen.java.invokation.lambda.Function0;
import rotsen.java.invokation.lambda.Function1;
import rotsen.java.invokation.lambda.Function2;
import rotsen.java.invokation.lambda.Function3;
import rotsen.java.invokation.lambda.Function4;
import rotsen.java.invokation.lambda.Function5;
import rotsen.java.invokation.lambda.Function6;
import rotsen.java.invokation.lambda.Function7;
import rotsen.java.invokation.lambda.Function8;
import rotsen.java.invokation.lambda.Function9;

import java.util.Optional;
import java.util.concurrent.Future;

public class Invoke
{
   /**
    * Function0
    */
   public static <R1, R2> Future<Optional<R2>>
   asFutureOptional( Invokation<Function0<R1>, R1, R2> ivk )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk));
   }

   public static <R1, R2> Future<Optional<R2>>
   asAsyncOptional( Invokation<Function0<R1>, R1, R2> ivk )
   {
      return asFutureOptional(ivk);
   }

   public static <R1, R2> Optional<R2>
   asOptional( Invokation<Function0<R1>, R1, R2> ivk )
   {
      try
      {
         Optional<R1> codeRetval = Optional.ofNullable(ivk.method().applyOriginalFunction());
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }

   /**
    * Function1
    */
   public static <T1, R1, R2> Future<Optional<R2>>
   asFutureOptional(
           Invokation<Function1<T1, R1>, R1, R2> ivk,
           T1 t1 )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk, t1));
   }

   public static <T1, R1, R2> Future<Optional<R2>>
   asAsyncOptional(
           Invokation<Function1<T1, R1>, R1, R2> ivk,
           T1 t1 )
   {
      return asFutureOptional(ivk, t1);
   }

   public static <T1, R1, R2> Optional<R2>
   asOptional(
           Invokation<Function1<T1, R1>, R1, R2> ivk,
           T1 t1 )
   {
      try
      {
         Optional<R1> codeRetval = Optional.ofNullable(ivk.method().applyOriginalFunction(t1));
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }


   /**
    * Function2
    */
   public static <T1, T2, R1, R2> Future<Optional<R2>>
   asFutureOptional(
           Invokation<Function2<T1, T2, R1>, R1, R2> ivk,
           T1 t1, T2 t2 )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk, t1, t2));
   }

   public static <T1, T2, R1, R2> Future<Optional<R2>>
   asAsyncOptional(
           Invokation<Function2<T1, T2, R1>, R1, R2> ivk,
           T1 t1, T2 t2 )
   {
      return asFutureOptional(ivk, t1, t2);
   }

   public static <T1, T2, R1, R2> Optional<R2>
   asOptional(
           Invokation<Function2<T1, T2, R1>, R1, R2> ivk,
           T1 t1, T2 t2 )
   {
      try
      {
         Optional<R1> codeRetval =
                 Optional.ofNullable(
                         ivk.method().applyOriginalFunction(t1, t2)
                 );
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }

   /**
    * Function3
    */
   public static <T1, T2, T3, R1, R2> Future<Optional<R2>>
   asFutureOptional(
           Invokation<Function3<T1, T2, T3, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3 )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk, t1, t2, t3));
   }

   public static <T1, T2, T3, R1, R2> Future<Optional<R2>>
   asAsyncOptional(
           Invokation<Function3<T1, T2, T3, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3 )
   {
      return asFutureOptional(ivk, t1, t2, t3);
   }

   public static <T1, T2, T3, R1, R2> Optional<R2>
   asOptional(
           Invokation<Function3<T1, T2, T3, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3 )
   {
      try
      {
         Optional<R1> codeRetval =
                 Optional.ofNullable(
                         ivk.method().applyOriginalFunction(t1, t2, t3)
                 );
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }

   /**
    * Function4
    */
   public static <T1, T2, T3, T4, R1, R2> Future<Optional<R2>>
   asFutureOptional(
           Invokation<Function4<T1, T2, T3, T4, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4 )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk, t1, t2, t3, t4));
   }

   public static <T1, T2, T3, T4, R1, R2> Future<Optional<R2>>
   asAsyncOptional(
           Invokation<Function4<T1, T2, T3, T4, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4 )
   {
      return asFutureOptional(ivk, t1, t2, t3, t4);
   }

   public static <T1, T2, T3, T4, R1, R2> Optional<R2>
   asOptional(
           Invokation<Function4<T1, T2, T3, T4, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4 )
   {
      try
      {
         Optional<R1> codeRetval =
                 Optional.ofNullable(
                         ivk.method().applyOriginalFunction(t1, t2, t3, t4)
                 );
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }

   /**
    * Function5
    */
   public static <T1, T2, T3, T4, T5, R1, R2> Future<Optional<R2>>
   asFutureOptional(
           Invokation<Function5<T1, T2, T3, T4, T5, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5 )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk, t1, t2, t3, t4, t5));
   }

   public static <T1, T2, T3, T4, T5, R1, R2> Future<Optional<R2>>
   asAsyncOptional(
           Invokation<Function5<T1, T2, T3, T4, T5, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5 )
   {
      return asFutureOptional(ivk, t1, t2, t3, t4, t5);
   }

   public static <T1, T2, T3, T4, T5, R1, R2> Optional<R2>
   asOptional(
           Invokation<Function5<T1, T2, T3, T4, T5, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5 )
   {
      try
      {
         Optional<R1> codeRetval =
                 Optional.ofNullable(
                         ivk.method().applyOriginalFunction(t1, t2, t3, t4, t5)
                 );
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }

   /**
    * Function6
    */
   public static <T1, T2, T3, T4, T5, T6, R1, R2> Future<Optional<R2>>
   asFutureOptional(
           Invokation<Function6<T1, T2, T3, T4, T5, T6, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6 )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk, t1, t2, t3, t4, t5, t6));
   }

   public static <T1, T2, T3, T4, T5, T6, R1, R2> Future<Optional<R2>>
   asAsyncOptional(
           Invokation<Function6<T1, T2, T3, T4, T5, T6, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6 )
   {
      return asFutureOptional(ivk, t1, t2, t3, t4, t5, t6);
   }

   public static <T1, T2, T3, T4, T5, T6, R1, R2> Optional<R2>
   asOptional(
           Invokation<Function6<T1, T2, T3, T4, T5, T6, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6 )
   {
      try
      {
         Optional<R1> codeRetval =
                 Optional.ofNullable(
                         ivk.method().applyOriginalFunction(t1, t2, t3, t4, t5, t6)
                 );
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }

   /**
    * Function7
    */
   public static <T1, T2, T3, T4, T5, T6, T7, R1, R2> Future<Optional<R2>>
   asFutureOptional(
           Invokation<Function7<T1, T2, T3, T4, T5, T6, T7, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7 )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk, t1, t2, t3, t4, t5, t6, t7));
   }

   public static <T1, T2, T3, T4, T5, T6, T7, R1, R2> Future<Optional<R2>>
   asAsyncOptional(
           Invokation<Function7<T1, T2, T3, T4, T5, T6, T7, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7 )
   {
      return asFutureOptional(ivk, t1, t2, t3, t4, t5, t6, t7);
   }

   public static <T1, T2, T3, T4, T5, T6, T7, R1, R2> Optional<R2>
   asOptional(
           Invokation<Function7<T1, T2, T3, T4, T5, T6, T7, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7 )
   {
      try
      {
         Optional<R1> codeRetval =
                 Optional.ofNullable(
                         ivk.method().applyOriginalFunction(t1, t2, t3, t4, t5, t6, t7)
                 );
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }

   /**
    * Function8
    */
   public static <T1, T2, T3, T4, T5, T6, T7, T8, R1, R2> Future<Optional<R2>>
   asFutureOptional(
           Invokation<Function8<T1, T2, T3, T4, T5, T6, T7, T8, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8 )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk, t1, t2, t3, t4, t5, t6, t7, t8));
   }

   public static <T1, T2, T3, T4, T5, T6, T7, T8, R1, R2> Future<Optional<R2>>
   asAsyncOptional(
           Invokation<Function8<T1, T2, T3, T4, T5, T6, T7, T8, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8 )
   {
      return asFutureOptional(ivk, t1, t2, t3, t4, t5, t6, t7, t8);
   }

   public static <T1, T2, T3, T4, T5, T6, T7, T8, R1, R2> Optional<R2>
   asOptional(
           Invokation<Function8<T1, T2, T3, T4, T5, T6, T7, T8, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8 )
   {
      try
      {
         Optional<R1> codeRetval =
                 Optional.ofNullable(
                         ivk.method().applyOriginalFunction(t1, t2, t3, t4, t5, t6, t7, t8)
                 );
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }

   /**
    * Function9
    */
   public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R1, R2> Future<Optional<R2>>
   asFutureOptional(
           Invokation<Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9 )
   {
      return ivk.getExecutor().submit(() -> asOptional(ivk, t1, t2, t3, t4, t5, t6, t7, t8, t9));
   }

   public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R1, R2> Future<Optional<R2>>
   asAsyncOptional(
           Invokation<Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9 )
   {
      return asFutureOptional(ivk, t1, t2, t3, t4, t5, t6, t7, t8, t9);
   }

   public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R1, R2> Optional<R2>
   asOptional(
           Invokation<Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R1>, R1, R2> ivk,
           T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9 )
   {
      try
      {
         Optional<R1> codeRetval =
                 Optional.ofNullable(
                         ivk.method().applyOriginalFunction(t1, t2, t3, t4, t5, t6, t7, t8, t9)
                 );
         return ivk.processMatchers(codeRetval);
      }
      catch (Throwable t)
      {
         return ivk.processException(t);
      }
   }
}