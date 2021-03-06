package rotsen.java.invokation.lambda;

@FunctionalInterface
public interface Function3<T1, T2, T3, R1> extends InvokationFunction<R1>
{
   R1 applyOriginalFunction( T1 t1, T2 t2, T3 t3 ) throws Throwable;
}