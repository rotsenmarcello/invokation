package rotsen.java.invokation.matchers;

public interface Matcher<M, U>
{
   Boolean matches( Object value );

   U process( M value );
}