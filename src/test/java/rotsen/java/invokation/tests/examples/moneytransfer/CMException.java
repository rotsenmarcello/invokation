package rotsen.java.invokation.tests.examples.moneytransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CMException extends Exception
{
   public static class CustomerAlreadyRegistered extends CMException
   {}
   public static class AccountAlreadyRegistered extends CMException
   {}

   @Data
   @NoArgsConstructor
   public static class InsufficientInitialDeposit extends CMException
   {
      private Integer deposit;

      private Integer minimum;


      public InsufficientInitialDeposit( Integer deposit, Integer minimum )
      {
         super();
         this.deposit = deposit;
         this.minimum = minimum;
      }
   }
}
