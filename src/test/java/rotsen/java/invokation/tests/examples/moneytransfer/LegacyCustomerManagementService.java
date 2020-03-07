package rotsen.java.invokation.tests.examples.moneytransfer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class LegacyCustomerManagementService
{

   private static final Double HIGH_INCOME_INITIAL_DEPOSIT = 100000.00 ;

   public static enum CustomerProfile {
      HIGH_INCOME,
      EXPOSED_PERSON,
      STANDARD
   }

   @Data
   @NoArgsConstructor
   public static  class Customer {
      private CustomerProfile profile;
      private UUID id;
      private String name;
      private String accountNumber;

      public Customer( CustomerProfile profile, UUID id, String name, String accountNumber )
      {
         this.profile = profile;
         this.id = id;
         this.name = name;
         this.accountNumber = accountNumber;
      }
   }

   private static Integer MINIMUM_INITIAL_DEPOSIT = 10000;
   private static List<String> EXISTING_ACCOUNTS =    Arrays.asList("11111","22222","33333","44444");
   private static List<String> EXISTING_CUSTOMERS =   Arrays.asList("customer 1","customer 2","customer 3","customer 4");
   private static List<String> EXPOSED_PERSON_CUSTOMERS =  Arrays.asList("exposed customer");

   public Customer createCustomerAndAccount(String name, String accountNumber, Integer initialDeposit)
      throws CMException
   {
      if(name == null || accountNumber == null || initialDeposit == null){
         return null;
      }
      if(EXISTING_CUSTOMERS.contains(name)){
         throw new CMException.CustomerAlreadyRegistered();
      }
      if(EXISTING_ACCOUNTS.contains(accountNumber)){
         throw new CMException.AccountAlreadyRegistered();
      }
      if(initialDeposit<MINIMUM_INITIAL_DEPOSIT){
         throw new CMException.InsufficientInitialDeposit(initialDeposit,MINIMUM_INITIAL_DEPOSIT);
      }
      CustomerProfile profile = CustomerProfile.STANDARD;
      if(EXPOSED_PERSON_CUSTOMERS.contains(name)){
         profile = CustomerProfile.EXPOSED_PERSON;
      }
      else if(initialDeposit > HIGH_INCOME_INITIAL_DEPOSIT){
         profile = CustomerProfile.HIGH_INCOME;
      }
      return new Customer(profile,UUID.randomUUID(),name,accountNumber);
   }
}
