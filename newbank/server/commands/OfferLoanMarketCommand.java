package newbank.server.commands;

import newbank.server.*;

import java.util.HashMap;




public class OfferLoanMarketCommand extends Command {

    public final HashMap<String, Customer> customerHashMap;

    public OfferLoanMarketCommand(HashMap<String, Customer> customerHashMap) {
        super("OFFERLOANMARKET",
                "<Account> <Amount> <Period> <Interest Rate> <Credit Score>","");
        this.customerHashMap = customerHashMap;
    }

    LoanMarket market = new LoanMarket();

    @Override
    public String process(Customer customer,  String argument) {

         String[] arguments = argument.split(" ");
        if (arguments.length < 4) {
            return "FAIL";
        }



        // Account from which the loan will be made

        Account fromLoanAccount = customer.getAccount(arguments[0]);
        if (fromLoanAccount == null) {
            return "FAIL Your Account Not found!";
        }

        Currency amount;
        try {
            amount = Currency.FromString(arguments[1]);
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

        if(creditScore < 0 || creditScore > 2000) {
            return "Credit score should be between 1 and 2000.";
        }

        LoanOffer loan = new LoanOffer(customer, fromLoanAccount.getAccountName(), amount, interestRate );
        market.addLoanOffer(loan);

        // Prints out summary of loan offer
        return "Loan offered for  " + amount + " has been  made from  " + fromLoanAccount.getAccountName() +
                " account For a period of " + period +  " months at a  rate of "
                + interestRate +
                " percent. You are prepared to lend at or above a credit score of "
                + creditScore;
    }



}
