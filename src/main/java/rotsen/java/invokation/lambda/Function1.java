package rotsen.java.invokation.lambda;

@FunctionalInterface
public interface Function1<T1, R1> extends InvokationFunction<R1>
{
   R1 applyOriginalFunction( T1 t1 ) throws Throwable;
}