import java.text.NumberFormat;
import java.util.*;

/*****************************************************************
 Vending Machine class that simulates the IO of the machines on
 the GVSU campus.

 @author Ben Payne
 @version 2017.02.12
 *****************************************************************/
class VendingMachine {
    private int creditBalance;

    private int numberOfBottles;

    private int totalSales;

    private static final Set<Integer> COINS_ACCEPTED = new HashSet<>(
        Arrays.asList(new Integer[]{5, 10, 25, 100})
    );

    /*****************************************************************
     Constructor initializes a new VendingMachine object with
     default property.
     *****************************************************************/
    public VendingMachine() {
        this.creditBalance = 0;

        this.totalSales = 0;

        this.numberOfBottles = 10;
    }

    /*****************************************************************
     Overloaded constructor initializes a new VendingMachine object with
     default properties except for the the supplied units.
     @param units the number of bottles to insert
     *****************************************************************/
    public VendingMachine(final int units) {
        this.numberOfBottles = units;
    }

    /*****************************************************************
     Display the machines greeting message accounting for the machines
     stock.
     *****************************************************************/
    public void displayGreeting() {
        if (this.getInventory() > 0) {
            System.out.println(
                "Ice cold drinks!" + System.lineSeparator() +
                    "Price: " + this.formatDollarAmount(this.getPrice())
            );

            System.out.println("(5¢, 10¢, 25¢, $1)");
        } else {
            System.out.println(
                "Out of stock." + System.lineSeparator() +
                    "Please try again later."
            );
        }
    }

    /*****************************************************************
     Restock the machine with a specified number of bottles
     @param units the number of bottles to insert
     *****************************************************************/
    public void restock(final int units) {
        if (units > 0) {
            this.numberOfBottles += units;
        } else {
            System.out.println("Invalid number of bottles.");
        }
    }

    /*****************************************************************
     Cancel the current sale and dispense the inserted coins.
     *****************************************************************/
    public void cancelSale() {
        this.creditBalance = 0;

        System.out.println("Sale cancelled");
        System.out.println("Credit: " +
            this.formatDollarAmount(this.getCredit())
        );

        this.displayGreeting();
    }


    /*****************************************************************
     Insert a coin and display the appropriate message.
     @param amount the coins values
     @return VendingMachine allow method chaining for a fluent API
     *****************************************************************/
    public VendingMachine insertCoin(final int amount) {
        if (this.numberOfBottles == 0) {
            this.displayGreeting();

            return this;
        }

        if (! COINS_ACCEPTED.contains(amount)) {
            System.out.println(
                "Invalid coin." + System.lineSeparator() + "Please try again!"
            );

            return this;
        }

        if (this.getCredit() == this.getPrice()) {
            System.out.println(
                "Please..." + System.lineSeparator() + "Make a selection"
            );
        } else if (this.getCredit() < this.getPrice()) {
            this.addCredit(amount);

            System.out.println(
                "Credit: " + this.formatDollarAmount(this.getCredit()) +
                System.lineSeparator() +
                "Price: " + this.formatDollarAmount(this.getPrice())
            );
        } else {
            System.out.println("Please try again.");
        }

        return this;
    }

    /*****************************************************************
     Cancel the current sale and dispense the inserted coins.
     @param amount the dollar value inserted.
     @return VendingMachine allow method chaining for a fluent API
     *****************************************************************/
    public VendingMachine insertDollar(final int amount) {
        if (amount > 1) {
            System.out.println("Only $1 dollar bills are accepted.");
        } else {
            this.insertCoin(100);
        }

        return this;
    }

    /*****************************************************************
     Make a drink selection. Check the inventory and the credit balance
     prior to dispensing. Display an appropriate message.
     *****************************************************************/
    public void makeSelection() {
        if (this.numberOfBottles == 0) {
            this.displayGreeting();

            return;
        }

        if (this.getPrice() > this.getCredit()) {
            this.displayGreeting();

            return;
        }

        this.dispense();
    }

    private void dispense() {
        this.numberOfBottles = this.numberOfBottles - 1;

        this.creditBalance = 0;

        System.out.println(
            "Dispensing your ice cold " + this.getProduct() + "!"
        );
    }

    /*****************************************************************
     Get the current balance.
     *****************************************************************/
    public int getCredit() {
        return creditBalance;
    }

    /*****************************************************************
     Set the current balance.
     @param creditBalance the amount to set the credit balance to.
     *****************************************************************/
    public void setCreditBalance(final int creditBalance) {
        this.creditBalance = creditBalance;
    }

    /*****************************************************************
     Add credit to the current credit balance.
     @param amount the amount to add to the credit balance to.
     *****************************************************************/
    public void addCredit(final int amount) {
        int newBalance = this.getCredit() + amount;

        if (newBalance <= this.getPrice()) {
            this.setCreditBalance(newBalance);
        }
    }


    /*****************************************************************
     Return the total sales for the machine
     *****************************************************************/
    public int getTotalSales() {
        return totalSales;
    }

    /*****************************************************************
     Set the total sales of the machine
     @param totalSales the number of sales for the machine.
     *****************************************************************/
    public void setTotalSales(final int totalSales) {
        this.totalSales = totalSales;
    }

    /*****************************************************************
     Return the current price per bottle.
     *****************************************************************/
    private int getPrice() {
        return 150;
    }

    /*****************************************************************
     Set the vending machines number of bottles
     @param numberOfBottles the number of bottles to add to the machine
     *****************************************************************/
    public void setInventory(final int numberOfBottles) {
        this.numberOfBottles = numberOfBottles;
    }

    /*****************************************************************
     Return the current inventory.
     *****************************************************************/
    public int getInventory() {
        return this.numberOfBottles;
    }

    /*****************************************************************
     Custom dollar amount formatter
     @param amount the dollar amount to format
     *****************************************************************/
    @org.jetbrains.annotations.NotNull
    private String formatDollarAmount(final int amount) {
        double dollarAmount = amount / 100.00;

        return "$" + String.format("%-4s", dollarAmount).replace(' ', '0');
    }

    /*****************************************************************
     Display a status report of the Vending Machines current state.
     *****************************************************************/
    public void displayStatus() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        String status = "";

        status += "Inventory : ";
        status += this.getInventory();
        status += System.lineSeparator();
        status += "Total Sales: ";
        status += formatter.format(this.getTotalSales());

        System.out.println(status);
    }

    /*****************************************************************
     Simulate sales of the machine
     @param sales the number of sales to loop through
     *****************************************************************/
    public void simulateSales (final int sales) {
        this.restock(sales);

        for (int i = 0; i < sales; i++) {
            this.insertDollar(1);

            this.makeSelection();
        }
    }

    public String getProduct() {
        return "Pepsi";
    }
}
