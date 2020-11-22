import newbank.server.CustomerID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerIDTest {

  @Test
  void getKey() {
    CustomerID customerID = new CustomerID("Test");
    Assertions.assertEquals("Test", customerID.getKey());
  }
}