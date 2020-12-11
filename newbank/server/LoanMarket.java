package newbank.server;

import java.util.ArrayList;
import java.util.List;

public class LoanMarket {


  public static ArrayList<LoanOffer> loanOffers;

  /**
   * Constructor
   */
  public LoanMarket() {
    loanOffers = new ArrayList<>();
  }

  /**
   * Adds a loan offer to the market
   * @param loanOffer the new loan offer
   */
  public static void  addLoanOffer(LoanOffer loanOffer) {
    LoanMarket.getLoanOffers().add(loanOffer);
  }


  /**
   * Removes a loan offer from the market
   * @param loanOffer to remove
   * @throws Exception if the offer was not on the market
   */
  public static void removeLoanOffer(LoanOffer loanOffer) throws Exception {
    if (!LoanMarket.getLoanOffers().contains(loanOffer)) {
      throw new Exception("LoanOffer not in market");
    }
    LoanMarket.getLoanOffers().remove(loanOffer);
  }

  /**
   * @return a list of all the loan offers on the market
   */
  public static ArrayList<LoanOffer> getLoanOffers() {
    return loanOffers;
  }



}
