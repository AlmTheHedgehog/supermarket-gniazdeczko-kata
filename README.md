# Kata - Supermarket Receipt

## Story

Yesterday, you were hired by a local supermarket, "Gniazdeczko". It is a modern
supermarket which has its own digital system for generating receipts. The last
developer retired, so now you are responsible for the whole codebase.

As a first task at your new job, you were asked to check whether all special deals
are calculated correctly. At first glance, it looked like the previous developer
did only manual tests, as there are no written automatic tests. Additionally, you
decided that the code is not readable, and you want to refactor it. However,
you know that the `Teller` class is used elsewhere in another part of the system. 
Hence, the `checksOutArticlesFrom(...)` method must have the same interface.

The supermarket has a catalog with different types of products (rice, apples, 
milk, toothbrushes,...). Each product has a price, and the total price of the 
shopping cart is the total of all the prices of the items. You get a receipt that 
details the items you've bought, the total price and any discounts that were 
applied.

The supermarket runs special deals, e.g.
- Buy two toothbrushes, get one free. Normal toothbrush price is €0.99
- 20% discount on apples, normal price €1.99 per kilo.
- 10% discount on rice, normal price €2.49 per bag
- Five tubes of toothpaste for €7.49, normal price €1.79
- Two boxes of cherry tomatoes for €0.99, normal price €0.69 per box.

These are just examples: the actual products in special deals change each week.












---

refs:
- [Supermarket Receipt - Kata](https://sammancoaching.org/kata_descriptions/supermarket_receipt.html)
- [Refactoring guru - code smells, design patterns](https://refactoring.guru/)
- [Code Smells Catalog](https://luzkan.github.io/smells) 