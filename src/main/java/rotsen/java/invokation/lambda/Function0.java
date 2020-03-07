package rotsen.java.invokation.lambda;

@FunctionalInterface
public interface Function0<R1> extends InvokationFunction<R1>
{
   R1 applyOriginalFunction() throws Throwable;
}