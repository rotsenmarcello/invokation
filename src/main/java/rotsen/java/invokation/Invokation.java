package rotsen.java.invokation;

import lombok.SneakyThrows;
import rotsen.java.invokation.lambda.*;
import rotsen.java.invokation.matchers.Matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Invokation<F extends InvokationFunction<R1>, R1, R2>
        implements InvokationConfig<F, R1, R2>, InvokationMatcher<F, R1, R2>
{
   private F method;
   private Optional<ExecutorService> executorService = Optional.empty();
   private ArrayList<Matcher<?, R2>> matches = new ArrayList<>();

   @Override
   public Invokation<F, R1, R2> addMatch( Matcher<?, R2> m )
   {
      this.matches.add(m);
      return this;
   }

   @Override
   public List<Matcher<?, R2>> getMatches()
   {
      return this.matches;
   }

   @Override
   public Invokation<F, R1, R2> self()
   {
      return this;
   }

   protected F method()
   {
      return this.method;
   }

   protected ExecutorService getExecutor()
   {
      if(!executorService.isPresent())
      {
         executorService = Optional.of(Executors.newSingleThreadExecutor());
      }
      return executorService.get();
   }

   protected Invokation( F method )
   {
      this.method = method;
   }

   public Invokation<F, R1, R2> withExecutorService( ExecutorService executorService )
   {
      this.executorService = Optional.of(executorService);
      return this;
   }

   protected Optional<R2> processMatchers( Optional<R1> methodRetval )
   {
      if(this.matches.isEmpty())
      {
         return Optional.empty();
      }
      R1 against = methodRetval.orElse(null);
      Optional<Matcher<?, R2>> matcher = this.matches.stream().filter(m -> m.matches(against)).findFirst();
      Optional<R2> ret = matcher.map(m -> this.processMatch(( (Matcher<R1, R2>) m ), methodRetval.orElse(null)));
      return ret;
   }

   @SneakyThrows
   protected Optional<R2> processException( Throwable t )
   {
      Optional<Matcher<?, R2>> matcher = this.matches.stream().filter(m -> m.matches(t)).findFirst();
      if(!matcher.isPresent())
      {
         throw t;
      }
      return matcher.map(m -> this.processMatch(( (Matcher<Throwable, R2>) m ), t));
   }

   private <V> R2 processMatch( Matcher<V, R2> m, V value )
   {
      return m.process(value);
   }

   /**
    * builders
    */
   public static <R1, R2>
   Invokation<Function0<R1>, R1, R2>
   of( Function0<R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <R1, R2>
   Invokation<Function0<R1>, R1, R2>
   of( Function0<R1> lambda, Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }

   public static <R1, R2>
   Invokation<Function0<R1>, R1, R2>
   of( Runnable0 lambda )
   {
      return new Invokation<>(Utils.runnable0ToFunction0(lambda));
   }

   public static <R1, R2>
   Invokation<Function0<R1>, R1, R2>
   of( Runnable0 lambda, Class<R2> newReturnValueClass )
   {
      return new Invokation<>(Utils.runnable0ToFunction0(lambda));
   }

   public static <T1, R1, R2>
   Invokation<Function1<T1, R1>, R1, R2>
   of( Function1<T1, R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, R1, R2>
   Invokation<Function1<T1, R1>, R1, R2>
   of(
           Function1<T1, R1> lambda,
           Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, R1, R2>
   Invokation<Function2<T1, T2, R1>, R1, R2>
   of( Function2<T1, T2, R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, R1, R2>
   Invokation<Function2<T1, T2, R1>, R1, R2>
   of(
           Function2<T1, T2, R1> lambda,
           Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, R1, R2>
   Invokation<Function3<T1, T2, T3, R1>, R1, R2>
   of( Function3<T1, T2, T3, R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, R1, R2>
   Invokation<Function3<T1, T2, T3, R1>, R1, R2>
   of(
           Function3<T1, T2, T3, R1> lambda,
           Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, R1, R2>
   Invokation<Function4<T1, T2, T3, T4, R1>, R1, R2>
   of( Function4<T1, T2, T3, T4, R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, R1, R2>
   Invokation<Function4<T1, T2, T3, T4, R1>, R1, R2>
   of(
           Function4<T1, T2, T3, T4, R1> lambda,
           Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, R1, R2>
   Invokation<Function5<T1, T2, T3, T4, T5, R1>, R1, R2>
   of( Function5<T1, T2, T3, T4, T5, R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, R1, R2>
   Invokation<Function5<T1, T2, T3, T4, T5, R1>, R1, R2>
   of(
           Function5<T1, T2, T3, T4, T5, R1> lambda,
           Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, T6, R1, R2>
   Invokation<Function6<T1, T2, T3, T4, T5, T6, R1>, R1, R2>
   of(
           Function6<T1, T2, T3, T4, T5, T6, R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, T6, R1, R2>
   Invokation<Function6<T1, T2, T3, T4, T5, T6, R1>, R1, R2>
   of(
           Function6<T1, T2, T3, T4, T5, T6, R1> lambda,
           Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, T6, T7, R1, R2>
   Invokation<Function7<T1, T2, T3, T4, T5, T6, T7, R1>, R1, R2>
   of(
           Function7<T1, T2, T3, T4, T5, T6, T7, R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, T6, T7, R1, R2>
   Invokation<Function7<T1, T2, T3, T4, T5, T6, T7, R1>, R1, R2>
   of(
           Function7<T1, T2, T3, T4, T5, T6, T7, R1> lambda,
           Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, T6, T7, T8, R1, R2>
   Invokation<Function8<T1, T2, T3, T4, T5, T6, T7, T8, R1>, R1, R2>
   of(
           Function8<T1, T2, T3, T4, T5, T6, T7, T8, R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, T6, T7, T8, R1, R2>
   Invokation<Function8<T1, T2, T3, T4, T5, T6, T7, T8, R1>, R1, R2>
   of(
           Function8<T1, T2, T3, T4, T5, T6, T7, T8, R1> lambda,
           Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R1, R2>
   Invokation<Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R1>, R1, R2>
   of(
           Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R1> lambda )
   {
      return new Invokation<>(lambda);
   }

   public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R1, R2>
   Invokation<Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R1>, R1, R2>
   of(
           Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R1> lambda,
           Class<R2> newReturnValueClass )
   {
      return new Invokation<>(lambda);
   }
}  