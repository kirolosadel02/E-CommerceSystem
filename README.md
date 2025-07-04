# E-Commerce System

I have implemented all the required functionalities in the project requirements.

## Features

* Define products with name, price, and quantity.
* Some products can expire, like cheese and biscuits, while others do not expire, like TVs and mobiles.
* Some products require shipping, like cheese and TVs, while others, like mobile scratch cards, do not. Every shippable item provides its weight.
* Customers can add products to the cart with a specific quantity, but not more than the available quantity.
* Customers can checkout with items in the cart. During checkout:

  * Print to console the order subtotal (sum of all items’ prices), shipping fees, paid amount (subtotal + shipping fees), and customer current balance after payment.
  * Give an error if:
    * The cart is empty.
    * Customer balance is insufficient.
    * One product is out of stock or expired.
  * If applicable, collect all items that need to be shipped and send them to a ShippingService that accepts a list of objects implementing an interface with `getName()` and `getWeight()` methods.

## Implementation Notes

* The solution is implemented using Java.
* All edge cases are covered, and test cases are included to handle and verify them.
* Assumptions are documented in the code where needed.

## Screenshots

Code output example 1:
![Code Output 1](CodeOutput/FawryNoProblems.png)

Code output example 2:
![Code Output 2](CodeOutput/FawryOutOfStock.png)

Code output example 3:
![Code Output 3](CodeOutput/FawryInsufficientBalance.png)

Code output example 4:
![Code Output 4](CodeOutput/FawryProductExpired.png)

---

## Test Cases

Test cases are included to cover all edge cases, including:

* Adding items exceeding available quantity.
* Checking out with insufficient balance.
* Checking out with an empty cart.
* Expired or out-of-stock products.
