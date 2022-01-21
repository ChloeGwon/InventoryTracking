import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryTracking {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int itemCount = 0;
		String name;
		String category;
		String quantity;
		String warehouseLocation;
		
		Item item = new Item();
		ArrayList<String> warehouseArrList = new ArrayList<String>();
		
		//If there is no file, make one. If there is, add values into the array list.
		itemCount(itemCount, warehouseArrList);
		
		Scanner input = new Scanner(System.in);
		System.out.println("Do you want to Add, Edit, Delete or View an item to the warehouse?");
		System.out.println("Enter 'A' to add, 'E' to edit, 'D' to delete, 'V' to view or 'Q' to quit");
		char option = input.nextLine().charAt(0);
		
		while (option != 'Q' || option != 'q') {
			
			if (option == 'A' || option == 'a') {
				System.out.println("Enter the name of the item");
				name = input.nextLine();
				System.out.println("Enter the category of the item");
				category = input.nextLine();
				System.out.println("Enter the quantity of the item");
				quantity = input.nextLine();
				//Accept only digits
				String QTT = onlyDigits(input, quantity);
				System.out.println("In which location do you want to save? Enter 'O' for Ottawa, 'T' for Toronto");
				char location = input.nextLine().charAt(0);
				while (!(location == 'O' || location == 'o' || location == 'T' || location == 't')) {
					System.out.println("Invalid.");
					System.out.println("In which location do you want to save? Enter 'O' for Ottawa, 'T' for Toronto");
					location = input.nextLine().charAt(0);
				}
				
				if (location == 'O' || location == 'o') {
					warehouseLocation = "Ottawa";
					//First, check if there is already same item in the warehouse. If not, add the item to the warehouse.
					itemExist(input, name, warehouseLocation, QTT, category, warehouseArrList);
					if (!itemExist(input, name, warehouseLocation, QTT, category, warehouseArrList)) {
						addToInventory(name, category, QTT, warehouseLocation, item, warehouseArrList);
					}
				}
				
				else if (location == 'T' || location == 't') {
					warehouseLocation = "Toronto";
					itemExist(input, name, warehouseLocation, QTT, category, warehouseArrList);
					if (!itemExist(input, name, warehouseLocation, QTT, category, warehouseArrList)) {
						addToInventory(name, category, QTT, warehouseLocation, item, warehouseArrList);
					}
				}
			}
			else if (option == 'E' || option == 'e') {
				System.out.println("In which location do you want to search? Enter 'O' for Ottawa, 'T' for Toronto");
				char location = input.nextLine().charAt(0);
				while (!(location == 'O' || location == 'o' || location == 'T' || location == 't')) {
					System.out.println("Invalid.");
					System.out.println("In which location do you want to search? Enter 'O' for Ottawa, 'T' for Toronto");
					location = input.nextLine().charAt(0);
				}
				
				String searchLocation = "";
				if (location == 'O' || location == 'o') {
					searchLocation = "Ottawa";
				}
				else if (location == 'T' || location == 't') {
					searchLocation = "Toronto";
				}
				
				System.out.println("Enter the name of the item you want to edit");
				name = input.nextLine();
				updateToInventory(input, name, warehouseArrList, searchLocation);
				
			}
			else if (option == 'D' || option == 'd') {
				System.out.println("In which location do you want to delete? Enter 'O' for Ottawa, 'T' for Toronto");
				char location = input.nextLine().charAt(0);
				while (!(location == 'O' || location == 'o' || location == 'T' || location == 't')) {
					System.out.println("Invalid.");
					System.out.println("In which location do you want to delete? Enter 'O' for Ottawa, 'T' for Toronto");
					location = input.nextLine().charAt(0);
				}
				
				String searchLocation = "";
				if (location == 'O' || location == 'o') {
					searchLocation = "Ottawa";
				}
				else if (location == 'T' || location == 't') {
					searchLocation = "Toronto";
				}
				
				System.out.println("Enter the name of the item you want to delete");
				name = input.nextLine();
				deleteFromInventory(input, name, warehouseArrList, searchLocation);
			}
			else if (option == 'V' || option == 'v') {
				if (warehouseArrList.size() == 0)
					System.out.println("The warehouse is empty.");
				else
					viewInventory(warehouseArrList);
			}
			else if (option == 'Q' || option == 'q') {
				System.out.println("Thank you!");
				System.exit(0);
			}
			
			System.out.println("Do you want to Add, Edit, Delete or View an item to the warehouse?");
			System.out.println("Enter 'A' to add, 'E' to edit, 'D' to delete, 'V' to view or 'Q' to quit");
			option = input.nextLine().charAt(0);
		}
	}
	
	public static String onlyDigits(Scanner input, String quantity) {
		
		boolean isDigit = true;
		for (int i=0; i<quantity.length(); i++) {
			if (!Character.isDigit(quantity.charAt(i)))
				isDigit = false;
			else if (Integer.parseInt(quantity) == 0)
				isDigit = false;
		}
		
		while (!isDigit) {
			System.out.println("It should be only digits and positive number");
			System.out.println("Enter the quantity of the item");
			quantity = input.nextLine();
			
			isDigit = true;
			for (int i=0; i<quantity.length(); i++) {
				if (!Character.isDigit(quantity.charAt(i)))
					isDigit = false;
				else if (Integer.parseInt(quantity) == 0)
					isDigit = false;
			}
		}
		
		//Parse to integer and parse to string in order to remove any leading zero
		int QTT = Integer.parseInt(quantity);
		return String.valueOf(QTT);
	}
	
	public static void itemCount(int count, ArrayList<String> warehouseArrList) throws IOException {
		
		//Make a file. Just in case, it is append mode.
		FileWriter file = new FileWriter("Inventory.csv", true);
		PrintWriter outFile = new PrintWriter(file);
		
		//Read the file.
		File fileForInput = new File("Inventory.csv");
		Scanner inputFile = new Scanner(fileForInput);
		String line;
		
		while (inputFile.hasNext()) {
			line = inputFile.nextLine();
			warehouseArrList.add(line);
			count++;
		}
	}
	
	public static void addToInventory(String name, String category, String QTT, String warehouseLocation, Item item, 
			ArrayList<String> warehouseArrList) throws IOException {
	
		item = new Item(name, category, QTT, warehouseLocation);
		warehouseArrList.add(item.toString());
		
		try {
			FileWriter file = new FileWriter("Inventory.csv", true);
			PrintWriter outFile = new PrintWriter(file);
			outFile.println(item.toString());
			System.out.println("Successfully added");
			outFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static boolean itemExist(Scanner input, String name, String warehouseLocation, String QTT, 
			String category, ArrayList<String> warehouseArrList) throws IOException {
		
		//If the name and category is the same, it is considered as a same item.
		boolean itemExist = false;

		int index = 0;
		String[] itemValues = new String[4];
		
		for (int i=0; i<warehouseArrList.size(); i++) {
			String[] arr = warehouseArrList.get(i).split(",");
			
			if (arr[3].equals(warehouseLocation)) {
				if (arr[0].equals(name) && arr[1].equals(category)) {
					itemExist = true;
					index = i;
					itemValues[0] = arr[0];
					itemValues[1] = arr[1];
					itemValues[2] = arr[2];
					itemValues[3] = arr[3];
				}
			}
		}
		
		if (itemExist) {
			System.out.println("The name and category of the item you have entered is already existed in " + warehouseLocation + " warehouse.");
			System.out.println("Do you want to add the quantity? Enter 'Y' for yes, 'N' for no");
			String add = input.nextLine();
			
			while (!(add.equals("Y") || add.equals("y") || add.equals("N") || add.equals("n"))) {
				System.out.println("Invalid.");
				System.out.println("Do you want to add the quantity? Enter 'Y' for yes, 'N' for no");
				add = input.nextLine();
			}
			
			if (add.equals("Y") || add.equals("y")) {
				warehouseArrList.set(index, itemValues[0]+","+itemValues[1]+","+(Integer.parseInt(itemValues[2]) + Integer.parseInt(QTT))+","+itemValues[3]);
				try {
					PrintWriter outFile = new PrintWriter("Inventory.csv");
					for (int i=0; i<warehouseArrList.size(); i++) {
						outFile.println(warehouseArrList.get(i));
					}
					System.out.println("Successfully updated");
					outFile.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		return itemExist;
	}
	
	public static void updateToInventory(Scanner input, String name, ArrayList<String> warehouseArrList, String searchLocation) throws IOException {
		
		boolean itemExist = false;
		
		int index = 0;
		String[] itemValues = new String[4];
		
		for (int i=0; i<warehouseArrList.size(); i++) {
			String[] arr = warehouseArrList.get(i).split(",");
			if (arr[3].equals(searchLocation)) {
				if (arr[0].equals(name)) {
					itemExist = true;
					index = i;
					itemValues[0] = arr[0];
					itemValues[1] = arr[1];
					itemValues[2] = arr[2];
					itemValues[3] = arr[3];
				}
			}
		}
		
		while (!itemExist) {
			System.out.println("The name of the item you have entered does not existed in "+searchLocation+" warehouse.");
			System.out.println("Enter the name of the item you want to edit");
			name = input.nextLine();
			
			for (int i=0; i<warehouseArrList.size(); i++) {
				String[] arr = warehouseArrList.get(i).split(",");
				
				if (arr[3].equals(searchLocation)) {
					if (arr[0].equals(name)) {
						itemExist = true;
						index = i;
						itemValues[0] = arr[0];
						itemValues[1] = arr[1];
						itemValues[2] = arr[2];
						itemValues[3] = arr[3];
					}
				}
			}
		}
		
		System.out.println("What do you want to edit? Enter 'N' for name, 'C' for category, 'Q' for quantity or 'L' for location.");
		String option = input.nextLine();
		
		while (!(option.equals("N") || option.equals("n") || option.equals("C") || option.equals("c") || 
				option.equals("Q") || option.equals("q") || option.equals("L") || option.equals("l"))) {
			System.out.println("Invalid.");
			System.out.println("What do you want to edit? Enter 'N' for name, 'C' for category, 'Q' for quantity or 'L' for location.");
			option = input.nextLine();
		}
		

		if (option.equals("N") || option.equals("n")) {
			System.out.println("Enter a new name");
			name = input.nextLine();
			warehouseArrList.set(index, name+","+itemValues[1]+","+itemValues[2]+","+itemValues[3]);
		}
		else if (option.equals("C") || option.equals("c")) {
			System.out.println("Enter a new category");
			String category = input.nextLine();
			warehouseArrList.set(index, itemValues[0]+","+category+","+itemValues[2]+","+itemValues[3]);
		}
		else if (option.equals("Q") || option.equals("q")) {
			System.out.println("Enter a new quantity");
			String quantity = input.nextLine();
			//only numbers
			String QTT = onlyDigits(input, quantity);
			warehouseArrList.set(index, itemValues[0]+","+itemValues[1]+","+QTT+","+itemValues[3]);
		}
		else if (option.equals("L") || option.equals("l")) {
			String location;
			if (itemValues[3].equals("Ottawa")) 
				location = "Toronto";
			else
				location = "Ottawa";
			
			System.out.println("Do you want to move this item to "+location+" warehouse? Enter 'Y' for yes, 'N' for no");
			option = input.nextLine();
			while (!(option.equals("Y") || option.equals("y") || option.equals("N") || option.equals("n"))) {
				System.out.println("Invalid.");
				System.out.println("Do you want to move this item to "+location+" warehouse? Enter 'Y' for yes, 'N' for no");
				option = input.nextLine();
			}
			
			//When an item is moved to another location warehouse, we need to check if there is the same item.
			if (option.equals("Y") || option.equals("y")) {
				
				boolean existItem = false;
				String existItemInNewLocation = "";
				int indexInNewLocation = 0;
				
				for (int i=0; i<warehouseArrList.size(); i++) {
					String[] arr = warehouseArrList.get(i).split(",");
					
					if (arr[3].equals(location)) {
						if (arr[0].equals(name) && arr[1].equals(itemValues[1])) {
							existItem = true;
							indexInNewLocation = i;
							existItemInNewLocation = arr[2];
						}
					}
				}
				
				if (existItem) {
					warehouseArrList.remove(index);
					warehouseArrList.set(indexInNewLocation, itemValues[0]+","+itemValues[1]+","+
					String.valueOf((Integer.parseInt(itemValues[2]) + Integer.parseInt(existItemInNewLocation)))+","+location);
					System.out.println("Since there is a same item, quantity is added.");
				}
				
				else {
					warehouseArrList.remove(index);
					warehouseArrList.set(indexInNewLocation, itemValues[0]+","+itemValues[1]+","+itemValues[2]+","+location);
				}
			}
		}
		
		try {
			PrintWriter outFile = new PrintWriter("Inventory.csv");
			for (int i=0; i<warehouseArrList.size(); i++) {
				outFile.println(warehouseArrList.get(i));
			}
			System.out.println("Successfully updated");
			outFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void deleteFromInventory(Scanner input, String name, ArrayList<String> warehouseArrList, String searchLocation) throws IOException {
		
		boolean itemExist = false;
		
		int index = 0;
		String[] itemValues = new String[4];
		
		for (int i=0; i<warehouseArrList.size(); i++) {
			String[] arr = warehouseArrList.get(i).split(",");
			if (arr[3].equals(searchLocation)) {
				if (arr[0].equals(name)) {
					itemExist = true;
					index = i;
					itemValues[0] = arr[0];
					itemValues[1] = arr[1];
					itemValues[2] = arr[2];
					itemValues[3] = arr[3];
				}
			}
		}
		
		while (!itemExist) {
			System.out.println("The name of the item you have entered does not existed in "+searchLocation+" warehouse.");
			System.out.println("Enter the name of the item you want to edit");
			name = input.nextLine();
			
			for (int i=0; i<warehouseArrList.size(); i++) {
				String[] arr = warehouseArrList.get(i).split(",");
				
				if (arr[3].equals(searchLocation)) {
					if (arr[0].equals(name)) {
						itemExist = true;
						index = i;
						itemValues[0] = arr[0];
						itemValues[1] = arr[1];
						itemValues[2] = arr[2];
						itemValues[3] = arr[3];
					}
				}
			}
		}
		
		warehouseArrList.remove(index);
		
		try {
			PrintWriter outFile = new PrintWriter("Inventory.csv");
			for (int i=0; i<warehouseArrList.size(); i++) {
				outFile.println(warehouseArrList.get(i));
			}
			System.out.println("Successfully deleted");
			outFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void viewInventory(ArrayList<String> warehouseArrList) {
		
		ArrayList<String> OTT = new ArrayList<String>();
		ArrayList<String> TOR = new ArrayList<String>();

		for (int i=0; i<warehouseArrList.size(); i++) {
			String[] arr = warehouseArrList.get(i).split(",");
			if (arr[3].equals("Ottawa"))
				OTT.add(arr[0]+"\t\t"+arr[1]+"\t"+arr[2]);
			else 
				TOR.add(arr[0]+"\t\t"+arr[1]+"\t"+arr[2]);
		}
		
		if (OTT.size() > 0) {
			System.out.println("\nOttawa warehouse");
			System.out.println("No.\tName\t\tCategory\tQuantity");
			for (int i=0; i<OTT.size(); i++) {
				System.out.println((i+1)+"\t"+OTT.get(i));
			}
		}
		
		if (TOR.size() > 0) {
			System.out.println("\nToronto warehouse");
			System.out.println("No.\tName\t\tCategory\tQuantity");
			for (int i=0; i<TOR.size(); i++) {
				System.out.println((i+1)+"\t"+TOR.get(i));
			}
		}
		
		System.out.println();
	}
	
}
