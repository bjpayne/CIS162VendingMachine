/*****************************************************************
 Vending Machine class that simulates the IO of the machines on
 the GVSU campus.

 @author Ben Payne
 @version 2017.02.12
 *****************************************************************/
public class VendingMachineTest {
    private static int errors = 0;

    public static void main(String args[]){
        // Get two instances of the the VendingMachine class.
        // The first using the default constructor, the second
        // using the overloaded constructor with a set inventory.
        VendingMachine vendingMachine1 = new VendingMachine();
        VendingMachine vendingMachine2 = new VendingMachine(20);

        initialInventoryShouldBeSet(vendingMachine1, vendingMachine2);

        changeInsertedShouldMatchCredit(vendingMachine1);

        dollarsInsertedShouldMatchCredit(vendingMachine1);

        itemShouldNotDispenseIfInsufficientCredit(vendingMachine1);

        itemShouldDispenseIfSufficientCredit(vendingMachine1);

        additionalFundsShouldNotBeAppliedToCredit(vendingMachine1);

        onlyDollarBillsShouldBeAccepted(vendingMachine1);

        System.out.println("Error count: " + errors);
    }

    /******************************************************************
     Test that the inventory in each machine is properly set, both internally
     and externally via the overloaded constructor.
     *****************************************************************/
    private static void initialInventoryShouldBeSet(
        VendingMachine vendingMachine1,
        VendingMachine vendingMachine2
    ) {
        boolean testPass = true;

        testStart();

        testActions("check inventory");

        if (! assertInventory(
            vendingMachine1,
            vendingMachine1.getInventory()
        )) {
            System.out.println(
                "ERROR! Inventory should be " + vendingMachine1.getInventory()
            );

            testPass = false;

            errors++;
        }

        if (! assertInventory(vendingMachine2, 20)) {
            System.out.println("ERROR! Inventory should be 10");

            testPass = false;

            errors++;
        }

        System.out.println("Vending Machine 1 inventory: " +
            vendingMachine1.getInventory()
        );

        System.out.println("Vending Machine 2 inventory: " +
            vendingMachine2.getInventory()
        );

        testEnd(testPass);
    }

    /*****************************************************************
     Test the amount inserted in change and the resulting credit for
     each machine
    *****************************************************************/
    private static void changeInsertedShouldMatchCredit(
        VendingMachine vendingMachine
    ) {
        boolean testPass = true;

        testStart();

        testActions("insert $0.25 | insert $0.25 | cancel sale");

        vendingMachine.insertCoin(25).insertCoin(25);

        if (! assertCredit(vendingMachine, 50)) {
            System.out.println("ERROR! Credit should be 50");

            testPass = false;

            errors++;
        }

        vendingMachine.cancelSale();

        testEnd(testPass);
    }

    /*****************************************************************
     Test the amount inserted in dollars and resulting credit
     for each machine
    *****************************************************************/
    private static void dollarsInsertedShouldMatchCredit(
        VendingMachine vendingMachine
    ) {
        boolean testPass = true;

        testStart();

        testActions("insert $1.00 | insert $1.00 | cancel sale");

        vendingMachine.insertDollar(1).insertDollar(1);

        if (! assertCredit(vendingMachine, 100)) {
            System.out.println(
                "ERROR! Credit should be $1.00 after inserting a dollar."
            );

            testPass = false;

            errors++;
        }

        vendingMachine.cancelSale();

        testEnd(testPass);
    }

    /*****************************************************************
     Test that a item will not be dispensed if insufficient funds are
     provided
    *****************************************************************/
    private static void itemShouldNotDispenseIfInsufficientCredit(
        VendingMachine vendingMachine
    ) {
        boolean testPass = true;

        testStart();

        testActions(
            "insert $1.00 | insert $0.05 | make selection | cancel sale"
        );

        vendingMachine.insertDollar(1).insertCoin(5).makeSelection();

        if(! assertInventory(vendingMachine, 10)) {
            System.out.println("Error: Item should not have been dispensed.");

            testPass = false;

            errors++;
        }

        vendingMachine.cancelSale();

        testEnd(testPass);
    }

    /*****************************************************************
     Test that a item will dispensed if sufficient funds are
     provided
    *****************************************************************/
    private static void itemShouldDispenseIfSufficientCredit(
        VendingMachine vendingMachine
    ) {
        boolean testPass = true;

        testStart();

        testActions(
            "insert $1.00 | insert $0.25 | insert $0.25 | make selection"
        );

        vendingMachine
            .insertDollar(1)
            .insertCoin(25)
            .insertCoin(25)
            .makeSelection();

        if(! assertInventory(vendingMachine, 9)) {
            System.out.println("Error: Item should have been dispensed.");

            testPass = false;

            errors++;
        }

        if (! assertCredit(vendingMachine, 0)) {
            System.out.println("Error: Credit should be 0");

            testPass = false;

            errors++;
        }

        testEnd(testPass);
    }

    /*****************************************************************
     Test that additional funds are returned to the customer once
     enough credit has been established
    *****************************************************************/
    private static void additionalFundsShouldNotBeAppliedToCredit(
        VendingMachine vendingMachine
    ) {
        boolean testPass = true;

        testStart();

        testActions(
            "insert $1.00 | insert $0.25 | insert $0.25 | " +
                "insert $0.25 | cancel sale"
        );

        vendingMachine
            .insertDollar(1)
            .insertCoin(25)
            .insertCoin(25)
            .insertCoin(25);

        if (! assertCredit(vendingMachine, 150)) {
            System.out.println("Error: Credit should not exceed price.");

            testPass = false;

            errors++;
        }

        vendingMachine.cancelSale();

        testEnd(testPass);
    }

    private static void onlyDollarBillsShouldBeAccepted(
        VendingMachine vendingMachine
    ) {
        boolean testPass = true;

        testStart();

        testActions("insert $5 dollar bill");

        vendingMachine.insertDollar(5);

        if (! assertCredit(vendingMachine, 0)) {
            testPass = false;

            System.out.println("ERROR: Only $1 bills should be accepted");
        }

        testEnd(testPass);
    }

    private static boolean assertInventory(
        final VendingMachine vendingMachine,
        final int inventory
    ) {
        return !!(vendingMachine.getInventory() == inventory);
    }

    private static boolean assertCredit(
        final VendingMachine vendingMachine,
        final int credit
    ) {
        return !!(vendingMachine.getCredit() == credit);
    }

    private static void testStart() {
        String test = getCurrentMethod();

        System.out.println("Test " + test + ": Start");
    }

    private static void testEnd(boolean result) {
        String test = getCurrentMethod();

        System.out.print("Test " + test + ": " );
        System.out.print((result) ? "Passed" : "Failed");
        System.out.print(System.lineSeparator());
        System.out.println("Test " + test + ": End");
        System.out.println(testSeparator());
    }

    private static String testSeparator() {
        return new String(new char[20]).replace("\0", "-");
    }

    private static void testActions(final String action) {
        System.out.println("(" + action + ")");
    }

    private static String getCurrentMethod() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }
}
