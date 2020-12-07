import newbank.server.*;
import org.junit.jupiter.api.*;

class LoanMarketTest {

  LoanMarket loanMarket;

  /**
   * Setup an empty loan market for the tests
   */
  @BeforeEach
  void setUp() {
    loanMarket = new LoanMarket();
  }

  @Test
  void addLoanOffer() {
    int initialLength = loanMarket.getLoanOffers().size();
    Customer offerer = new Customer("bob", "bob");
    LoanOffer loanOffer = new LoanOffer(offerer, "Test", Currency.FromInteger(100), 0.05f);
    loanMarket.addLoanOffer(loanOffer);
    Assertions.assertEquals(initialLength + 1, loanMarket.getLoanOffers().size());
  }

  @Test
  void removeLoanOffer() {
    int initialLength = loanMarket.getLoanOffers().size();
    Customer offerer = new Customer("bob", "bob");
    LoanOffer loanOffer = new LoanOffer(offerer, "Test", Currency.FromInteger(100), 0.05f);
    loanMarket.addLoanOffer(loanOffer);
    Assertions.assertEquals(initialLength + 1, loanMarket.getLoanOffers().size());
    Assertions.assertDoesNotThrow(() -> loanMarket.removeLoanOffer(loanOffer));
    Assertions.assertEquals(initialLength, loanMarket.getLoanOffers().size());
  }

  @Test
  void getLoanOffers() {
    Customer offerer = new Customer("bob", "bob");
    Assertions.assertTrue(loanMarket.getLoanOffers().isEmpty());
    LoanOffer loanOffer = new LoanOffer(offerer, "Test", Currency.FromInteger(100), 0.05f);
    loanMarket.addLoanOffer(loanOffer);
    Assertions.assertEquals(loanOffer, loanMarket.getLoanOffers().get(0));
  }
}