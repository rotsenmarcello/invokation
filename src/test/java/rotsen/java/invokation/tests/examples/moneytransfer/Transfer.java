package rotsen.java.invokation.tests.examples.moneytransfer;

public class Transfer {
      String fromAccountNumber;
      String toAccountNumber;
      Double amount;

      public Transfer(String fromAccountNumber, String toAccountNumber, Double amount){
         this.amount = amount;
         this.fromAccountNumber = fromAccountNumber;
         this.toAccountNumber = toAccountNumber;
      }
   }