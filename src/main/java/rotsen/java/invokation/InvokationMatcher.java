package rotsen.java.invokation;

import rotsen.java.invokation.lambda.Function1;
import rotsen.java.invokation.lambda.InvokationFunction;
import rotsen.java.invokation.matchers.ClassMatcherWithLambdaReturnValueProcessor;
import rotsen.java.invokation.matchers.Matcher;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface InvokationMatcher<F extends InvokationFunction<R1>, R1, R2>
{
   Invokation<F, R1, R2> addMatch( Matcher<?, R2> m );

   List<Matcher<?, R2>> getMatches();

   Invokation<F, R1, R2> self();

   default Invokation<F, R1, R2> match( Matcher<?, R2> m )
   {
      return addMatch(m);
   }

   default Invokation<F, R1, R2> matchNull( Supplier<R2> returnValueProcessor )
   {
      Function1<R1, R2> processorWrapper = ( R1 r1 ) -> {
         return returnValueProcessor.get();
      };
      ClassMatcherWithLambdaReturnValueProcessor<R1, R2> m = new ClassMatcherWithLambdaReturnValueProcessor<>(
              ClassMatcherWithLambdaReturnValueProcessor.BY_RETURN_TYPE.NULL_OR_VOID,
              null,
              Optional.empty(),
              processorWrapper);
      this.addMatch(m);
      return self();
   }

   default Invokation<F, R1, R2> match( Class<Void> expectedClass, Supplier<R2> returnValueProcessor )
   {
      Function1<Void, R2> processorWrapper = ( Void r1 ) -> {
         return returnValueProcessor.get();
      };
      ClassMatcherWithLambdaReturnValueProcessor<Void, R2> m = new ClassMatcherWithLambdaReturnValueProcessor<Void, R2>(
              ClassMatcherWithLambdaReturnValueProcessor.BY_RETURN_TYPE.NULL_OR_VOID,
              expectedClass,
              Optional.empty(),
              processorWrapper);
      this.addMatch(m);
      return self();
   }

   default Invokation<F, R1, R2> match( Class<Void> expectedClass, Runnable returnValueProcessor )
   {
      return match(expectedClass,
                   () -> {
                      returnValueProcessor.run();
                      return null;
                   });
   }

   default <C> Invokation<F, R1, R2> match( Class<C> expectedClass, Function<C, R2> returnValueProcessor )
   {
      Function1<C, R2> processorWrapper = ( C c ) -> {
         return returnValueProcessor.apply(c);
      };
      ClassMatcherWithLambdaReturnValueProcessor<C, R2> m =
              new ClassMatcherWithLambdaReturnValueProcessor<C, R2>(
                      ClassMatcherWithLambdaReturnValueProcessor.BY_RETURN_TYPE.CLASS,
                      expectedClass,
                      Optional.empty(),
                      processorWrapper);
      this.addMatch(m);
      return self();
   }

   default <C> Invokation<F, R1, R2> match(
           Class<C> expectedClass,
           Consumer<C> returnValueProcessor )
   {
      return match(expectedClass,
                   ( C c ) -> {
                      returnValueProcessor.accept(c);
                      return null;
                   });
   }

   default <C> Invokation<F, R1, R2> match(
           Class<C> expectedClass,
           Predicate<C> matcher,
           Function<C, R2> returnValueProcessor )
   {
      Function1<C, R2> processorWrapper = ( C c ) -> {
         return returnValueProcessor.apply(c);
      };
      ClassMatcherWithLambdaReturnValueProcessor<C, R2> m = new ClassMatcherWithLambdaReturnValueProcessor<C, R2>(
              ClassMatcherWithLambdaReturnValueProcessor.BY_RETURN_TYPE.CUSTOM,
              expectedClass,
              matcher,
              processorWrapper);
      this.addMatch(m);
      return self();
   }

   default <C extends Object> Invokation<F, R1, R2> match(
           Class<C> expectedClass,
           Predicate<C> matcher,
           Consumer<C> returnValueProcessor )
   {
      return match(expectedClass,
                   matcher,
                   ( C c ) -> {
                      returnValueProcessor.accept(c);
                      return null;
                   });
   }

   default Invokation<F, R1, R2> match(
           Predicate<R1> matcher,
           Function<R1, R2> returnValueProcessor )
   {
      return match(null,matcher,returnValueProcessor);
   }

   default  Invokation<F, R1, R2> match(
           Predicate<R1> matcher,
           Consumer<R1> returnValueProcessor )
   {
      return match(null,matcher,returnValueProcessor);
   }
}