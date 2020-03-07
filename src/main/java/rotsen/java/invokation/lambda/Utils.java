package rotsen.java.invokation.lambda;

public class Utils
{
   public static <R1> Function0<R1> runnable0ToFunction0( Runnable0 runnable0 )
   {
      return () -> {
         runnable0.run();
         return null;
      };
   }
}