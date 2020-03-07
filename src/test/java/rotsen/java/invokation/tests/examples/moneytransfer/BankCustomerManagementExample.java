package rotsen.java.invokation.tests.examples.moneytransfer;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import rotsen.java.invokation.Invokation;
import rotsen.java.invokation.Invoke;
import rotsen.java.invokation.lambda.Function3;
import rotsen.java.invokation.matchers.Matcher;

import java.util.Optional;

public class BankCustomerManagementExample
{

   public static class StandardCustomerMatcher implements Matcher<LegacyCustomerManagementService.Customer, String>
   {
      @Override
      public Boolean matches( Object value )
      {
         return LegacyCustomerManagementService.Customer.class.isInstance(value)
                && ((LegacyCustomerManagementService.Customer) value).getProfile() == LegacyCustomerManagementService.CustomerProfile.STANDARD;
      }

      @Override
      public String process( LegacyCustomerManagementService.Customer value )
      {
         return "redirect: standard welcome page";
      }
   }

   private static LegacyCustomerManagementService cmService = new LegacyCustomerManagementService();

   private static Invokation<Function3<String, String, Integer, LegacyCustomerManagementService.Customer>, LegacyCustomerManagementService.Customer, String>
           createCustomer = Invokation.of(cmService::createCustomerAndAccount, String.class);

   private static  Matcher<LegacyCustomerManagementService.Customer, String> standardCustomers = new StandardCustomerMatcher();

   @BeforeClass
   public static void setupMatches(){
      createCustomer
              .matchNull(() -> "error: it returned null")
              .match(CMException.AccountAlreadyRegistered.class, e -> "error: account already exists")
              .match(CMException.CustomerAlreadyRegistered.class, e -> "error: customer already exists")
              .match(CMException.InsufficientInitialDeposit.class,
                     e -> e.getDeposit() > 5000.00,
                     e -> "redirect: manual evaluation")
              .match(CMException.InsufficientInitialDeposit.class,
                     e -> e.getDeposit() <= 5000.00,
                     e -> { return String.format("error: a minimum deposit of %d is required", e.getMinimum()); })
              .match(LegacyCustomerManagementService.Customer.class,
                     customer -> customer.getProfile() == LegacyCustomerManagementService.CustomerProfile.EXPOSED_PERSON,
                     customer ->  "redirect: exposed person form")
              .match(customer -> customer.getProfile() == LegacyCustomerManagementService.CustomerProfile.HIGH_INCOME,
                     customer -> "redirect: vip welcome page")
              .match(standardCustomers)
              ;
   }

   @Test
   public void standardCustomerCustomMatcher()
   {
      Optional<String> msg = Invoke.asOptional(createCustomer, "customer 5", "55555", 15000);
      Assert.assertTrue(msg.isPresent());
      Assert.assertEquals("redirect: standard welcome page", msg.get());
   }

   @Test
   public void multipleStandardCustomerCustomMatcher()
   {
      standardCustomerCustomMatcher();
      standardCustomerCustomMatcher();
      standardCustomerCustomMatcher();
      standardCustomerCustomMatcher();
   }

   @Test
   public void nullMatcher()
   {
      Optional<String> msg = Invoke.asOptional(createCustomer, null, "55555", 15000);
      Assert.assertTrue(msg.isPresent());
      Assert.assertEquals("error: it returned null", msg.get());
   }

   @Test
   public void exceptionMatchedByClass()
   {
      Optional<String> msg = Invoke.asOptional(createCustomer, "customer 1", "55555", 15000);
      Assert.assertTrue(msg.isPresent());
      Assert.assertEquals("error: customer already exists", msg.get());

      msg = Invoke.asOptional(createCustomer, "customer 5", "11111", 15000);
      Assert.assertTrue(msg.isPresent());
      Assert.assertEquals("error: account already exists", msg.get());
   }

   @Test
   public void exceptionMatchedByClassAndExceptionInstanceAttributeValue()
   {
      Optional<String> msg = Invoke.asOptional(createCustomer, "customer 5", "55555", 4000);
      Assert.assertTrue(msg.isPresent());
      Assert.assertEquals("error: a minimum deposit of 10000 is required", msg.get());

      msg = Invoke.asOptional(createCustomer, "customer 6", "66666", 6000);
      Assert.assertTrue(msg.isPresent());
      Assert.assertEquals("redirect: manual evaluation", msg.get());
   }

   @Test
   public void originalReturnValueMatchedByClassAndAttributeValue()
   {
      Optional<String> msg = Invoke.asOptional(createCustomer, "exposed customer", "55555", 14000);
      Assert.assertTrue(msg.isPresent());
      Assert.assertEquals("redirect: exposed person form", msg.get());
   }

   @Test
   public void originalReturnValueMatchedByImplicitClassAndAttributeValue()
   {
      Optional<String> msg = Invoke.asOptional(createCustomer, "customer 6", "55555", 140000);
      Assert.assertTrue(msg.isPresent());
      Assert.assertEquals("redirect: vip welcome page", msg.get());
   }

}