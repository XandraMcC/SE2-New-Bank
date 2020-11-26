package newbank.server.commands;

import newbank.server.*;

import java.util.HashMap;

public class LoanMarketCommand extends Command {

    private final HashMap<String, Customer> customerHashMap;

    public LoanMarketCommand(HashMap<String, Customer> customerHashMap) {
        super("LOANMARKET",
                "<Account> <Amount> <Period> <Interest Rate> <Credit Score> <Category>",
                "");
        this.customerHashMap = customerHashMap;
    }

    @Override
    public String process(Customer customer, String argument) {

        String[] arguments = argument.split(" ");
        if (arguments.length < 5) {
            return "FAIL";
        }

        // Account to which the loan will be made

        Account toLoanAccount = customer.getAccount(arguments[0]);
        if (toLoanAccount == null) {
            return "FAIL Your Account Not found!";
        }

        Currency amount;
        try {
            amount = new Currency(arguments[1]);
        } catch (NumberFormatException e) {
            return "FAIL";
        }

        //Loan Period should be greater than 0 and not more than 120 months
        int period = Integer.parseInt(arguments[2]);
        if (period <= 0) {
            return "FAIL Loan needs a positive tenor";
        }
        else if(period > 120){
            return "Loan tenor may not exceed 120 months";
        }

        // Interest rate entered as an integer between 0 and 20
        int interestRate = Integer.parseInt(arguments[3]);
        if(interestRate <= 0 || interestRate > 20){
            return "FAIL Interest Rate needs to be greater than 0 and less than 20";
        }

        /*Credit score should be between 1 and 2000
        If the applicant does not have a credit score he can enter 0
        and will be automatically redirected to a website to obtain one.
         */
        int  creditScore =  Integer.parseInt(arguments[4]);

        /*return statement triggers the auto redirect to the credit score website
        handled by the client handler class */

        if(creditScore == 0){
            return "You do not appear to have a credit score.";
        }
        else if(creditScore < 0 || creditScore > 2000) {
            return "Credit score should be between 1 and 2000. If you do not have a" +
                    "credit score enter 0";
        }

        // The reason for the loan application, what the funds are required for.

        String category = "";

        try{category = arguments[5];
            }
        catch (Exception e){
            return "Enter a valid reason for the Loan Request";
        }

        // Prints out summary of loan request
        return "Loan Request for  " + amount + " was made for  " + toLoanAccount.getAccountName() +
                " account For a period of " + period +  " months at a  rate of " + interestRate +
                " percent. You supplied a Credit Score of " + creditScore + " and gave the reason for the loan as "
                + category;
    }
}

