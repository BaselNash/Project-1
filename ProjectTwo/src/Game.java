import java.io.*;
import java.util.Scanner;

public class Game {

	// Global

	public static Locale currentLocale;
	public static String command;
	public static String decision;
	public static String choice;
	public static boolean stillInTheGame = true;
	public static Locale[] Locations;
	public static Item[] ITEMS;
	public static int[][] navigationArray;
	public static int moves = 0;
	public static int score = 0;
	public static Item[] PlayerInventory = new Item[671];
	public static int points = 0;
	public static int AchievementRatio = 0;
	public static int playerInventorySize = 0;
	public static int playerBank = 0;
	public static Item[] magickShoppe;

	public static void main(String[] args) {
		intialStart();
		getCommand();
		updateDisplay();

		// while Loop for the game;
		while (stillInTheGame) {
			getCommand();
			typeNavigation();
			updateDisplay();
			directionsYouCanGo();
		}

		System.out.println("Thank you for playing the game.");
	}

	// Private methods, arrays, and such;

	public static void Ifmap() {

		for (int i = 0; i < PlayerInventory.length; i++) {

			Item playerInventoryVariable = PlayerInventory[i];

			if (playerInventoryVariable != null) {

				if (PlayerInventory[i].getId() == 0) {
					// playerInventoryVariable = true;
					map();
					return;
				}
			}
		}
		System.out.println("You do not have the map");
	}

	public static void intialStart() {

		// Intializing the intial commands

		command = new String();
		stillInTheGame = true;

		// instances of items

		Item gameItem1 = new Item(0);
		gameItem1.setName("Map");
		gameItem1.setDesc("A Map to help you through the lair");

		Item gameItem2 = new Item(1);
		gameItem2.setName("Broom");
		gameItem2.setDesc("A really cool broom to have, this one can fly");

		Item gameItem3 = new Item(2);
		gameItem3.setName("Poison");
		gameItem3.setDesc("Don\'t drink that!");

		Item gameItem4 = new Item(3);
		gameItem4.setName("Cursed Item");
		gameItem4.setDesc("Don\'t play around with that!");

		Item gameItem5 = new Item(4);
		gameItem5.setName("Sharp Sword");
		gameItem5.setDesc("A really useful thing to have, if the witch comes.");
		
		
		// Create the instance of locations
		
		Danger location0 = new Danger(0);
		location0.setName("Main Entrance");
		location0.setDesc("You are Entering the Witche\'s Lair.");
		location0.setItems(new Item[] { gameItem1 });
		location0.setDangerLevel("10%");
		location0.setMoney(23);

		Danger location1 = new Danger(1);
		location1.setName("Hall of Keys");
		location1.setDesc("There are keys hovering all around you.");
		location1.setItems(new Item[] {});
		location1.setDangerLevel("25%");
		location1.setMoney(143);
		
		Danger location2 = new Danger(2);
		location2.setName("Potions Room");
		location2.setDesc("There are racks of potions, some might turn you into a frog.");
		location2.setItems(new Item[] { gameItem3 });
		location2.setDangerLevel("80%");
		location2.setMoney(652);

		Danger location3 = new Danger(3);
		location3.setName("Broom Stick Storage");
		location3.setDesc("A giant closet filled with flying broomsticks.");
		location3.setItems(new Item[] { gameItem2 });
		location3.setDangerLevel("60%");
		location3.setMoney(54);

		Danger location4 = new Danger(4);
		location4.setName("Armory");
		location4.setDesc("So much weaponry, this place is scary under the wrong hands.");
		location4.setItems(new Item[] { gameItem5 });
		location4.setDangerLevel("75%");
		location4.setMoney(723);

		Danger location5 = new Danger(5);
		location5.setName("Dungeon");
		location5.setDesc("A dungeon to keep all the animals and trespassers, so do not get caught.");
		location5.setItems(new Item[] {});
		location5.setDangerLevel("99.99999%");
		location5.setMoney(450);

		Danger location6 = new Danger(6);
		location6.setName("Kitchen");
		location6.setDesc("An elegant and beautiful kitchen, with all the sweats and candy you can eat.");
		location6.setItems(new Item[] {});
		location6.setDangerLevel("0.2%");
		location6.setMoney(18);

		Danger location7 = new Danger(7);
		location7.setName("Cursed Items Room");
		location7.setDesc("A room filled with strange objects, some of them smell funny.");
		location7.setItems(new Item[] { gameItem4 });
		location7.setDangerLevel("90%");
		location7.setMoney(87);

		Danger location8 = new Danger(8);
		location8.setName("Magick Shoppe");
		location8.setDesc("A place to purchase items.");
		location8.setItems(new Item[] {});
		location8.setDangerLevel("0.1%");
		location8.setMoney(200);
		
		// Location Links 
		
		// Main Entrance
		location0.setNorth(location1);
		
		// Hall of Keys
		location1.setNorth(location4);
		location1.setEast(location3);
		location1.setWest(location2);
		location1.setSouth(location0);
		
		//Broom Stick Storage
		location2.setEast(location1);
		
		//Armory
		location3.setWest(location1);
		
		//Dungeon
		location4.setNorth(location5);
		location4.setSouth(location1);
		
		//Dungeon
		location5.setNorth(location8);
		location5.setEast(location7);
		location5.setWest(location6);
		location5.setSouth(location4);
		
		//Kitchen
		location6.setEast(location5);
		
		//Cursed Items Room
		location7.setWest(location5);
		
		location8.setSouth(location5);
		
		currentLocale = location0;

		// set up the location array.

		Locations = new Locale[9];
		Locations[0] = location0;
		Locations[1] = location1;
		Locations[2] = location2;
		Locations[3] = location3;
		Locations[4] = location4;
		Locations[5] = location5;
		Locations[6] = location6;
		Locations[7] = location7;
		Locations[8] = location8;

		System.out.println();
		System.out.println("										Welcome to the Witche's lair,");
		System.out.println();

		System.out.println("Here are all the game locations:");
		for (int i = 0; i < Locations.length; ++i) {
			System.out.println(i + ":" + Locations[i].toString());
		}

		System.out.println();
		System.out.println("You are currently in" + currentLocale);
		System.out.println();
		System.out.println("Press any key to begin");
	};
	
	
	public static void ReadMagicItemsAndPromptUser(){
		
		// creating the list manager, classified as listMan1
		
		ListMan listMan1 = new ListMan();
		listMan1.setName("Magic Items");
		listMan1.setDesc("Enchanted Items that seem both cool and scary.");
		
		final String fileName = "magicItems.txt";
		
		readmagicItemsFromFileToList(fileName, listMan1);
		
		//Managing the array for to hold the items. The array is called items
		
		ListItem[] items = new ListItem[listMan1.getLength()]; //.getLength method can be found in listMan.
		readMagicItemsFromFileToArray(fileName, items);
		
		// Displaying the items in the array on the command line. Using a for loop
		
		for (int i = 0 ; i < items.length ; i++) {
			if (items[i] != null) {
				System.out.println(items[i].toString());
			}
		}
		 
		// Prompt the user, to select an item.
		
		 Scanner inputReader = new Scanner(System.in);
	        System.out.print("What item would you like? ");
	        String targetItem = new String();
	        targetItem = inputReader.nextLine();
	        System.out.println();

	        ListItem li = new ListItem();
	        li = LinearSearch(listMan1, targetItem);
	        if (li != null) {
	            System.out.println(li.toString());
	        }
		 
	}
	
	//Magic Item methods
	
	//First method is read through the magic Items and find the users choice
	
	private static ListItem LinearSearch(ListMan lm, String targetItem){
		
		ListItem retVal = null;
        System.out.println("Searching for " + targetItem + ".");
        int magicItemCounter = 0; //Counts the number of searches through each item. 
        ListItem currentItem = new ListItem();
        currentItem = lm.getHead();
        boolean isFound = false;
        while ( (!isFound) && (currentItem != null) ) {
        	magicItemCounter = magicItemCounter +1;
            if (currentItem.getName().equalsIgnoreCase(targetItem)) {
                // We found it!
                isFound = true;
                retVal = currentItem;
            } else {
                // Keep looking.
                currentItem = currentItem.getNext();
            }
        }
        if (isFound) {
            System.out.println("Found " + targetItem + " after " + magicItemCounter + " comparisons.");
            	Scanner inputReader = new Scanner(System.in);
            	decision = inputReader.nextLine();
            	System.out.println("Would you like to purchase this item? Y or N");
            		//if(decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("yes") && playerBank > currentItem.getCost() ) {
            			
            			//PlayerInventory[playerInventorySize] = ;

            			//playerInventorySize = playerInventorySize + 1;
            		//}
            return  currentItem;
           
            
        } else {
            System.out.println("Could not find " + targetItem + " in " + magicItemCounter + " comparisons.");
            System.out.println("Would you like to search again or exit?");
            Scanner inputReader = new Scanner(System.in);
        	choice = inputReader.nextLine();
        		if(choice.equalsIgnoreCase("Search") || choice.equalsIgnoreCase("S")) { 
        			
        			ReadMagicItemsAndPromptUser();
        			
        		} if (choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("E")) {
        			
        			currentLocale = currentLocale.getSouth();
        			updateDisplay();
        		}
        }

        return retVal;
	}
	
	//Second method is to read the magic items from the file to the list.
	
	private static void readmagicItemsFromFileToList(String fileName, ListMan lm){
		
		File myFile = new File(fileName);
        try {
            Scanner input = new Scanner(myFile);
            while (input.hasNext()) {
                // Read a line from the file.
                String itemName = input.nextLine();

                // Construct a new list item and set its attributes.
                ListItem fileItem = new ListItem();
                fileItem.setName(itemName);
                fileItem.setCost(Math.random() * 100); // random pricing.
                fileItem.setNext(null);

                // Add the newly constructed item to the list.
                lm.add(fileItem);
            }
	            // Closing the magicItems file 
	            input.close();
		 } catch (FileNotFoundException ex) {
			 System.out.println("File not found. " + ex.toString());
		 }
		 
	}
	
	//Third method is to read the magic items from the file to the list.
	private static void readMagicItemsFromFileToArray(String fileName, ListItem[] items) {
		
		  File myFile = new File(fileName);
	        try {
	            int itemCount = 0;
	            Scanner input = new Scanner(myFile);

	            while (input.hasNext() && itemCount < items.length) {
	                // Read a line from the file.
	                String itemName = input.nextLine();

	                // Construct a new list item and set its attributes.
	                ListItem fileItem = new ListItem();
	                fileItem.setName(itemName);
	                fileItem.setCost(Math.random() * 100); // random pricing.
	                fileItem.setNext(null);

	                // Add the newly constructed item to the array.
	                items[itemCount] = fileItem;
	                itemCount = itemCount + 1;
	            }
	            // Close the file.
	            input.close();
	        } catch (FileNotFoundException ex) {
	            System.out.println("File not found. " + ex.toString());
	        }
	}

	private static void help() {
		System.out.println("Theses are the commands that you can type in:-");
		System.out.println("   North or N");
		System.out.println("   South or S");
		System.out.println("   West or W");
		System.out.println("   East or E");
		System.out.println("   Take Item or TI");
		System.out.println("   Take Money or TM");
		System.out.println("   Inventory or I");
		System.out.println("   Map or M");
		System.out.println("   Quit or Q");
	}

	public static void directionsYouCanGo() {

		Locale northDirection = currentLocale.getNorth();
		Locale southDirection = currentLocale.getSouth();
		Locale eastDirection = currentLocale.getEast();
		Locale westDirection = currentLocale.getWest();

		System.out.print("You can move ");

		if (northDirection != null) {

			System.out.print("North, ");

		}
		if (southDirection != null) {

			System.out.print("South, ");

		}
		if (eastDirection != null) {

			System.out.print("East, ");

		}
		if (westDirection != null) {

			System.out.print("West, ");

		}

		System.out.println();
	}

	public static void updateDisplay() {

		System.out.println(currentLocale.getInfo());

		if (currentLocale.getId() == 8) {

			ReadMagicItemsAndPromptUser();
		}

	}

	private static void typeNavigation() {

							// The Intial position > 0 which starts the position
							// of
							// the game.

		// if statement for the locations and commands

		if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n")) {
			
			if (currentLocale.getNorth() == null) {
				System.out.println("Invalid Move! Try Again");
				
			}else{
				
				currentLocale = currentLocale.getNorth();
				moves = moves + 1;
				points = points + 5;
				AchievementRatio = points / moves;
			}
			
		} else if (command.equalsIgnoreCase("south")
				|| command.equalsIgnoreCase("s")) {
			
			if (currentLocale.getSouth() == null) {
				System.out.println("Invalid Move! Try Again");
				
			}else{
			
				currentLocale = currentLocale.getSouth();
				moves = moves + 1;
				points = points + 5;
				AchievementRatio = points / moves;
			
			}
			
		} else if (command.equalsIgnoreCase("east")
				|| command.equalsIgnoreCase("e")) {
			
			if (currentLocale.getEast() == null) {
				System.out.println("Invalid Move! Try Again");
				
			}else{
			
				currentLocale = currentLocale.getEast();
				moves = moves + 1;
				points = points + 5;
				AchievementRatio = points / moves;
			
			}
			
		} else if (command.equalsIgnoreCase("west")
				|| command.equalsIgnoreCase("w")) {
			
			if (currentLocale.getWest() == null) {
				System.out.println("Invalid Move! Try Again");
				
			}else{
			
				currentLocale = currentLocale.getWest();
				moves = moves + 1;
				points = points + 5;
				AchievementRatio = points / moves;
			
			}
			
		} else if (command.equalsIgnoreCase("help")
				|| command.equalsIgnoreCase("h")) {
			help();
		} else if (command.equalsIgnoreCase("map")
				|| command.equalsIgnoreCase("m")) {
			Ifmap();
		} else if (command.equalsIgnoreCase("inventory")
				|| command.equalsIgnoreCase("i")) {
			playerInventory();
		} else if (command.equalsIgnoreCase("take item")
				|| command.equalsIgnoreCase("ti")) {
			takeItem();
		} else if (command.equalsIgnoreCase("take money")
				|| command.equalsIgnoreCase("tm")) {
			takeMoney();
		}else if (command.equalsIgnoreCase("quit")
				|| command.equalsIgnoreCase("q")) {
			quit();
		} else {
			System.out
					.println("The command that you have just placed is incorrect");
			help();
		}
	}

	public static void map() {

		System.out.println("Here is a map to guide you around the Lair.");
		System.out
				.println("Use the id from the list of locations to determine the exact name of your location");
		System.out.println("    [8]");
		System.out.println("[6] [5]	[7]");
		System.out.println("    [4]	");
		System.out.println("[2] [1]	[3]");
		System.out.println("    [0]");

	}

	private static void getCommand() {
		System.out.println("[ " + moves + "moves, score, " + points
				+ " AchievementRatio " + AchievementRatio + "] ");
		Scanner inputReader = new Scanner(System.in);
		command = inputReader.nextLine();

	}

	private static void playerInventory() {

		System.out.println("Player Inventory");

		for (int i = 0; i < PlayerInventory.length; ++i) {

			if (PlayerInventory[i] != null) {

				System.out.println(i + ":" + PlayerInventory[i]);
			}
		}
	}
	
	private static void takeMoney() {
		
		int locationMoney = currentLocale.getMoney();
		
		playerBank = playerBank + locationMoney;
		
		System.out.println("You have taken:" + locationMoney + " Sapphires. Your bank Contains" + playerBank + "Sapphires.");
		
	}

	public static void takeItem() {

		if (currentLocale.getItems().length == 0) {

			System.out.println("There are no items to take");

		} else {

			Item locationItem = currentLocale.getItems()[0];

			PlayerInventory[playerInventorySize] = locationItem;

			playerInventorySize = playerInventorySize + 1;

			currentLocale.setItems(new Item[] {});

			System.out.println("you have taken item:" + locationItem);
		}

	}

	private static void quit() {

		stillInTheGame = false;

	}
};
