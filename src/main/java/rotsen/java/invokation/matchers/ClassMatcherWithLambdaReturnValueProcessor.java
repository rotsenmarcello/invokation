package rotsen.java.invokation.matchers;

import lombok.Data;
import lombok.SneakyThrows;
import rotsen.java.invokation.lambda.Function1;

import java.util.Optional;
import java.util.function.Predicate;

@Data
public class ClassMatcherWithLambdaReturnValueProcessor<M, U> implements Matcher<M, U>
{
   public enum BY_RETURN_TYPE
   {CLASS, VALUE, CUSTOM, NULL_OR_VOID}

   private final BY_RETURN_TYPE matchBy;
   private final Class<M> expectedClass;
   private final Optional<M> expectedValue;
   private final Predicate<M> customMatcher;
   private final Function1<M, U> processor;

   public ClassMatcherWithLambdaReturnValueProcessor(
           BY_RETURN_TYPE matchBy,
           Class<M> expectedClass,
           Optional<M> expectedValue,
           Function1<M, U> processor )
   {
      this.matchBy = matchBy;
      this.processor = processor;
      this.expectedClass = expectedClass;
      this.expectedValue = expectedValue;
      this.customMatcher = null;
   }

   public ClassMatcherWithLambdaReturnValueProcessor(
           BY_RETURN_TYPE matchBy,
           Class<M> expectedClass,
           Predicate<M> customMatcher,
           Function1<M, U> processor )
   {
      this.matchBy = matchBy;
      this.processor = processor;
      this.expectedClass = expectedClass;
      this.expectedValue = null;
      this.customMatcher = customMatcher;
   }

   public Boolean matches( Object value )
   {
      switch (matchBy) {
         case NULL_OR_VOID:
            return value == null || value == Void.class;
         case CLASS:
            return expectedClass.isInstance(value);
         case CUSTOM:
            try{
               return customMatcher.test( (M) value);
            }
            catch(Throwable t) {
               return false;
            }
         case VALUE:
            return expectedValue.map(v -> v.equals(value)).orElse(false);
      }
      return false;
   }

   @SneakyThrows
   public U process( M value )
   {
      return processor.applyOriginalFunction(value);
   }
}