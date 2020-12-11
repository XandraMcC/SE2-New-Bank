package newbank.server;

public class Constants {
    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";
    public static final String CASH = "cash";
    public static final String END = "end";
    public static final String PASSWORDUPDATED = "Password updated";

    /**
     * Fail strings
     */
    public static final String FAIL = "FAIL";
    public static final String FAILPASSWORDNOTUPDATED = "FAIL: Password not updated";
    public static final String FAILNOTENOUGHARGS = "FAIL: Not enough arguments entered";
    public static final String FAILNOTLOGGEDIN = "FAIL: Not logged in";
    public static final String FAILACCOUNTNOTFOUND = "FAIL: Account is not found";
    public static final String FAILACCOUNTEXISTS = "FAIL: Already have an account with that name";
    public static final String FAILPAYMENTNOTFOUND = "FAIL: Payment amount not recognised";
    public static final String FAILPAYEENOTFOUND = "FAIL: Payee not found";
    public static final String FAILPAYEEACCOUNTNOTFOUND = "FAIL: Payee account not found";
    public static final String FAILINSUFFICIENTFUNDS = "FAIL: Insufficient funds available to make payment";
    public static final String FAILPOSTEN = "FAIL: Loan needs a positive tenor";
    public static final String FAILTOOLONG = "FAIL: Loan tenor may not exceed 120 months";
    public static final String FAILIRWRONG = "FAIL: Interest Rate needs to be greater than 0 and less than 20";
    public static final String FAILCREDITSCORE = "FAIL: Credit score should be between 1 and 2000";
}
