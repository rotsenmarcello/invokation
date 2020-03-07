package rotsen.java.invokation.sandbox;

import lombok.Data;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

class Service
{
   public Integer stringToInteger( String id ) throws NumberFormatException
   {
      return Integer.parseInt(id);
   }
}

@Data
public class Sandbox<T>
{
   private final T methodObject;
   private String name;
   Class<? extends T> dynamicType;

   public Sandbox( T methodObject )
   {
      this.methodObject = methodObject;
   }

   private void run()
   {
      dynamicType = new ByteBuddy()
              .subclass((Class<T>) this.methodObject.getClass())
              .method(ElementMatchers.named("toString"))
              .intercept(FixedValue.value("Hello World!"))
              .make()
              .load(getClass().getClassLoader())
              .getLoaded();
   }

   public static <T> Sandbox<T> of( T methodObject )
   {
      return new Sandbox<>(methodObject);
   }

   public void main()
   {
      Service s = new Service();
   }
}