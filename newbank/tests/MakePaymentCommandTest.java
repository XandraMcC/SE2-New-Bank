package newbank.tests;

import newbank.server.*;
import newbank.server.commands.MakePaymentCommand;
import org.junit.jupiter.api.*;
import java.util.HashMap;

class MakePaymentCommandTest {
  static String PAYEE_NAME = "Bob";
  static String PAYER_NAME = "John";
  static String PAYEE_ACCOUNT = "business";
  static String PAYER_ACCOUNT = "checking";
  static int OPENING_BALANCE = 100;
  static int AMOUNT = 50;
  static String COMMAND_PARAMETERS = PAYER_ACCOUNT + " " + AMOUNT + " " + PAYEE_NAME + " " + PAYEE_ACCOUNT;

  Customer payee;
  Account payeeAccount;
  Customer payer;
  Account payerAccount;
  HashMap<String, Customer> customerHashMap;
  MakePaymentCommand command;

  @BeforeEach
  void setUp() {
    customerHashMap = new HashMap<>();

    payee = new Customer(PAYEE_NAME, PAYEE_NAME);
    payeeAccount = new Account(PAYEE_ACCOUNT, Currency.FromInteger(OPENING_BALANCE));
    payee.addAccount(payeeAccount);
    customerHashMap.put(PAYEE_NAME, payee);

    payer = new Customer(PAYER_NAME, PAYER_NAME);
    payerAccount = new Account(PAYER_ACCOUNT, Currency.FromInteger(OPENING_BALANCE));
    payer.addAccount(payerAccount);
    customerHashMap.put(PAYER_NAME, payer);

    command = new MakePaymentCommand(customerHashMap);
  }

  @Test
  void makePayment() {
    String result = command.process(payer, COMMAND_PARAMETERS);
    Assertions.assertFalse(result.contains("FAIL"));

    //Check money removed from payer
    int newBalancePennies = (OPENING_BALANCE- AMOUNT) * 100;
    Assertions.assertEquals(newBalancePennies, payerAccount.getBalance().getValue());

    //Check money paid to payee
    newBalancePennies = (int)Math.floor(OPENING_BALANCE*100) + (int)Math.floor(AMOUNT*100);
    Assertions.assertEquals(newBalancePennies, payeeAccount.getBalance().getValue());
  }
}