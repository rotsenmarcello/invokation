package rotsen.java.invokation.lambda;

@FunctionalInterface
public interface Function4<T1, T2, T3, T4, R1> extends InvokationFunction<R1>
{
   R1 applyOriginalFunction( T1 t1, T2 t2, T3 t3, T4 t4 ) throws Throwable;
}