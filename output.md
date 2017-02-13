Test initialInventoryShouldBeSet: Start
(check inventory)
Vending Machine 1 inventory: 10
Vending Machine 2 inventory: 20
Test initialInventoryShouldBeSet: Passed
Test initialInventoryShouldBeSet: End
--------------------
Test changeInsertedShouldMatchCredit: Start
(insert $0.25 | insert $0.25 | cancel sale)
Credit: $0.25
Price: $1.50
Credit: $0.50
Price: $1.50
Sale cancelled
Credit: $0.00
Ice cold drinks!
Price: $1.50
(5¢, 10¢, 25¢, $1)
Test changeInsertedShouldMatchCredit: Passed
Test changeInsertedShouldMatchCredit: End
--------------------
Test dollarsInsertedShouldMatchCredit: Start
(insert $1.00 | insert $1.00 | cancel sale)
Credit: $1.00
Price: $1.50
Credit: $1.00
Price: $1.50
Sale cancelled
Credit: $0.00
Ice cold drinks!
Price: $1.50
(5¢, 10¢, 25¢, $1)
Test dollarsInsertedShouldMatchCredit: Passed
Test dollarsInsertedShouldMatchCredit: End
--------------------
Test itemShouldNotDispenseIfInsufficientCredit: Start
(insert $1.00 | insert $0.05 | make selection | cancel sale)
Credit: $1.00
Price: $1.50
Credit: $1.05
Price: $1.50
Ice cold drinks!
Price: $1.50
(5¢, 10¢, 25¢, $1)
Sale cancelled
Credit: $0.00
Ice cold drinks!
Price: $1.50
(5¢, 10¢, 25¢, $1)
Test itemShouldNotDispenseIfInsufficientCredit: Passed
Test itemShouldNotDispenseIfInsufficientCredit: End
--------------------
Test itemShouldDispenseIfSufficientCredit: Start
(insert $1.00 | insert $0.25 | insert $0.25 | make selection)
Credit: $1.00
Price: $1.50
Credit: $1.25
Price: $1.50
Credit: $1.50
Price: $1.50
Dispensing your ice cold Pepsi!
Test itemShouldDispenseIfSufficientCredit: Passed
Test itemShouldDispenseIfSufficientCredit: End
--------------------
Test additionalFundsShouldNotBeAppliedToCredit: Start
(insert $1.00 | insert $0.25 | insert $0.25 | insert $0.25 | cancel sale)
Credit: $1.00
Price: $1.50
Credit: $1.25
Price: $1.50
Credit: $1.50
Price: $1.50
Please...
Make a selection
Sale cancelled
Credit: $0.00
Ice cold drinks!
Price: $1.50
(5¢, 10¢, 25¢, $1)
Test additionalFundsShouldNotBeAppliedToCredit: Passed
Test additionalFundsShouldNotBeAppliedToCredit: End
--------------------
Test onlyDollarBillsShouldBeAccepted: Start
(insert $5 dollar bill)
Only $1 dollar bills are accepted.
Test onlyDollarBillsShouldBeAccepted: Passed
Test onlyDollarBillsShouldBeAccepted: End
--------------------
Test onlyNickelsDimesAndQuartersAreAccepted: Start
(insert $.01)
Test onlyNickelsDimesAndQuartersAreAccepted: Passed
Test onlyNickelsDimesAndQuartersAreAccepted: End
--------------------
Error count: 0