# SE2-New-Bank

## Architecture

The server application adopts a layered architecture.  This is split into three layers:

```dita

  ┌───────────┐
  │  Command  │
  └───────────┘
  ┌───────────┐
  │   Logic   │
  └───────────┘
  ┌───────────┐
  │    Data   │
  └───────────┘

```

The **Command** layer processes input from the client application and returns 
the result of these operations. This includes a class for each command.

The **Logic** layer implements the application objects which are interacted with
from the **Command** layer.  This includes classes such as Customer, Account, Loan etc. 

The **Data** layer has not yet been implemented.  This will interface with a 
database providing persistent storage for the application.

## Automated Tests
Directory `./newbank/test` contains tests.  These use the JUnit framework.

IntelliJ IDEA requires setting up to use these tests.
 
1. **The test root needs to be set**.  To do this select `File` → `Project 
   Structure`, then choose `Project Settings` → `Modules` and right click on 
   the test folder and change its type to test.  This folder now appears green.

2. **The junit library needs to be installed**.  To do this select `File` → 
   `Project Structure`, then choose `Platform Settings` → `Global Libraries`.  
   Click the + `New Global Library` and choose `from maven...`.  Enter 
   `org.junit.jupiter:junit-jupiter-api:5.0.0` in the search box and tick to 
   download entering somewhere appropriate e.g. `SE2-New-Bank/lib`

3. To run the tests right click the test folder in the project explorer window
   and select run tests.

Notes

* In intelliJ tests can be created automatically, to do this right click a 
  class or function and select show `Context Actions`, then `Create Tests`.
   


