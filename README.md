# SE2-New-Bank
##Getting Started
Note : This process uses IntelliJ IDE to excute the program, if you are using a different executer the process will vary.
1. Unzip the zip folder downloaded above into a location on your desktop.
2. Open IntelliJ IDE and selcet `Open or Import`.
3. Locate the upzipped folder and select it.

### Setting up Intellij IDE
1. Once the project is open in IntelliJ select `File > Project Structure`.
2. Under the `Project Settings` section select `Project` Tab.
3. Select the latest project SDK in the dropdown list labelled `Project SDK`.
4. Next select the programming language level as `SDK Default` in the dropdown list labelled `Project Programming Level`.
5. Select the tab `Modules` located under `Project`.
6. Expand the file explorer to view the project structure.
7. Select the first folder containing the project files.
8. Make the folder as `Sources` by selecting the `Sources` button in the `Mark as` list.
9. Locate the `Tests` Folder and select `Tests` in the `Mark as` list.
10. Locate the `out` Folder and select `Excluded` in the `Mark as` list.
11. Select `Global Libraries` located under `Platform Settings` section.
12. Select the `+` icon to add a global libary and select `from Maven`.
13. Enter `org.junit.jupiter:junit-jupiter-api:5.0.01` in the dialog box and select `Ok`.
14. Apply all changes by selecting `Apply`.
15. IntelliJ is now ready to run the progam code.

##Running the Program
Make sure to follow all the steps above to correctly setup the IntelliJ IDE.
1. Build the project by selecting the green hammer icon or by selecting `Build > Build Project` from the menu bar.
2. Make sure the message `Build completed successfully...` is displayed in the bottom left corner.
3. Locate `NewBankServer.java` under `newbank > server > NewBankServer.java`, right click and select `Run...`.
4. Make sure the command line opens and displays `New Bank Server listening on XXXXX`
5. Locate `ExampleClient.java` under `newbank > client > ExampleClient.java`, right click and select `Run...`.
6. Make sure `Enter Username` is displayed in the command line.
7. Login using the Test account.
8. A list of commands is printed in the command line and its correct usage can be found in protocol.txt.

The test user login details is as below:
-   Username: `Test`
-   Password: `test`

The list of users are as follows:

-   John - Checking: £250

-   Bhagy - Main: £1000

-   Christina - Savings: £1500

-   Test - Main: £60000; Checking: £20250; Savings £10000

-   Manager - Checking: £2000250


 
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
   


