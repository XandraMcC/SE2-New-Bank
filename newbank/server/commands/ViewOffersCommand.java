package newbank.server.commands;
import newbank.server.*;

import java.util.ArrayList;

public class ViewOffersCommand extends Command {

    public ViewOffersCommand(){
        super("VIEWLOANOFFERS",
                "",
                "Show all Loan Offers.");
    }

    @Override
    public String process(Customer customer, String argument){
        String heading1 = "Lender";
        String heading2 = "Amount (GBP)";
        String heading3 = "Interest Rate";
        String allLoans;
        allLoans = String.format("| %-15s | %-15s | %-15s |\n-------------------------------------------------------\n", heading1, heading2, heading3);
        String currentLoanOffer;

        for (LoanOffer loanOffer : loans.loanOffers) {
            String LenderName = loanOffer.getLender().getName();
            String Amount = loanOffer.getAmount().toString();
            String InterestRate = String.valueOf(loanOffer.getInterestRate());
            currentLoanOffer = String.format("| %-15s | %-15s | %-15s |\n-------------------------------------------------------", LenderName, Amount, InterestRate);
            allLoans += currentLoanOffer + "\n";
        }
        return allLoans;
    }
}
