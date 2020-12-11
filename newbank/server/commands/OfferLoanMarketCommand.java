package newbank.server.commands;

import newbank.server.*;

import java.util.HashMap;




public class OfferLoanMarketCommand extends Command {

    public final HashMap<String, Customer> customerHashMap;

    public OfferLoanMarketCommand(HashMap<String, Customer> customerHashMap) {
        super("OFFERLOANMARKET",
                "<account_name> <amount> <period> <interest_rate> <credit_score>",
                "offer someone a micro loan for a specified amount, time period, interest rate, and credit score ");
        this.customerHashMap = customerHashMap;
    }

    LoanMarket market = new LoanMarket();

    @Override
    public String process(Customer customer,  String argument) {
        String[] arguments = argument.split(" ");
        Currency amount;
        Account fromLoanAccount;
        LoanOffer loan;
        int period;
        int interestRate;
        int creditScore;

        if (arguments.length < 4) {
            return Constants.FAILNOTENOUGHARGS;
        }

        period = Integer.parseInt(arguments[2]);
        interestRate = Integer.parseInt(arguments[3]);
        creditScore =  Integer.parseInt(arguments[4]);

        if (period <= 0) {
            return Constants.FAILPOSTEN;
        }

        if (period > 120) {
            return Constants.FAILTOOLONG;
        }

        // Interest rate entered as an integer between 0 and 20
        if (interestRate <= 0 || interestRate > 20) {
            return Constants.FAILIRWRONG;
        }

        /**
         * Credit score should be between 1 and 2000
         * If the applicant does not have a credit score he can enter 0
         * and will be automatically redirected to a website to obtain one.
         */
        if (creditScore < 0 || creditScore > 2000) {
            return Constants.FAILCREDITSCORE;
        }


        try {
            amount = Currency.FromString(arguments[1]);
            fromLoanAccount = customer.getAccount(arguments[0]);
            loan = new LoanOffer(customer, fromLoanAccount.getAccountName(), amount, interestRate );
            market.addLoanOffer(loan);
        } catch (NumberFormatException e) {
            return Constants.FAIL;
        }

        if (fromLoanAccount == null) {
            return Constants.FAILACCOUNTNOTFOUND;
        }

        // Prints out summary of loan offer
        return "Loan offered for  " + amount + " has been  made from  " + fromLoanAccount.getAccountName() +
                " account For a period of " + period +  " months at a  rate of "
                + interestRate +
                " percent. You are prepared to lend at or above a credit score of "
                + creditScore;
    }



}
