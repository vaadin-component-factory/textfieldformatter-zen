This branch is a PoC of a migration from cleave.js to `cleavejs-zen`.

Currently only the `CustomCreditCardFieldFormatter` has been tested.
The different configurations have been migrated and should work out of the box.

Not done:
- A different ValueChangeMode for the TextField are not working well since the value is formatted eagerly and updated to the server side automatically. (if it's not updated automatically with the _onChange then the value is wrong on the server side!).
- The cursor position might be an issue to be tested. It doesn't work for all the different cases (with delimiters)
- noImmediatePrefix property is not present in cleave zen (no replacement) - if true, the prefix should not be displayed until the user enters text

Missing from cleave:
- add leading zero
  - .99 -> 0.99
  - Easy on server side if no pre/postfixes or symbols
  - 
- always display decimals
  - 99 -> 99.00
  - Easy on server side if no pre/postfixes or symbols

Questions:
- in the screenshots of use cases, it looks like they are using pre/suffix slots in textfield, is this true?