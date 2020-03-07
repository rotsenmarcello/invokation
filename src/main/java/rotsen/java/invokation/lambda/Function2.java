package rotsen.java.invokation.lambda;

@FunctionalInterface
public interface Function2<T1, T2, R1> extends InvokationFunction<R1>
{
   R1 applyOriginalFunction( T1 t1, T2 t2 ) throws Throwable;
}