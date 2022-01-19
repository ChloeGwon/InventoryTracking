## Description ##
* This is a terminal application that manages items in inventories. You can add an item, update, delete, and view in two locations of warehouses.
* The output retrieves into a CSV file.

## How to Run the Project ##
* Download Visual Studio Code.
* In Visual Studio Code, click on Extensions and download Debugger for Java.
* Click on View, Command Palette, search Git: Clone, and paste https://github.com/ChloeGwon96/InventoryTracking
* Under src folder, click on InventoryTacking.java to open.
* The memory is permanent.

## Features ##
* It gives errors
    * when it prompts a character to proceed and you do not enter the appropriate characters
    * when the item name you have entered matches an existing product list in the warehouse you choose
    * when the quantity of the item you have entered are not numeric values
    * when you edit and enter an item that does not match an existing product list
* When it prompts a character to proceed, it is not case sensitive.
* The memory is volatile in CSV file.
* When you move an item to another warehouse and the item matches an existing product list in the warehouse, it adds the quantity.
* When you add an item which matches an existing product list in the warehouse, it asks you to add the quantity or not.
 
## Tests ##
![Inventory1](https://user-images.githubusercontent.com/96569864/150097990-268329d4-7039-401a-8624-7f5c15685ec7.png)
<img width="263" alt="Inventory2" src="https://user-images.githubusercontent.com/96569864/150098002-e50502ec-e1b7-4d86-8fe7-a6ad81ce70a3.png">
