## Description ##
* This is a terminal application that manages items in inventories. You can add, update, delete, and view an item in two locations of warehouses.
* The output is stored as a CSV file.

## How to Run the Project ##
* Requirement: Java in the system.
* Click on code, download ZIP.
* On a terminal, move to the src directory.
* <how to compile>In terminal, enter 'javac ./*.java' to compile.
* <how to run>Enter java InventoryTracking to run.

## Features ##
* It gives errors
    * when it prompts a character to proceed and you do not enter an appropriate character
    * when you enter an item name of an already existing item to create one 
    * when the quantity of the item you have entered are not numeric values
    * when you enter a non-existing item name to edit or delete
* When it prompts a character to proceed, it is not case-sensitive.
* The data is persistent in CSV file.
* When you move an item to another warehouse and the item matches an existing item in the warehouse, it adds the quantity.
* When you add an item which matches an existing item in the warehouse, it asks if want to increase the quantity of the item.
 
## Tests ##
![Inventory1](https://user-images.githubusercontent.com/96569864/150097990-268329d4-7039-401a-8624-7f5c15685ec7.png)
<img width="263" alt="Inventory2" src="https://user-images.githubusercontent.com/96569864/150098002-e50502ec-e1b7-4d86-8fe7-a6ad81ce70a3.png">
