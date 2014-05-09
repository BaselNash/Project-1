//import java.io.*;
import java.util.Scanner;

public class Game {

	// Global

	public static Locale currentLocale;
	public static Locale newLocale;
	public static String command;
	public static String choice;
	public static String decision;
	public static boolean stillInTheGame = true;
	public static Locale[] Locations;
	public static Item[] localeItems;
	public static int[][] navigationArray;
	public static int moves = 0;
	public static int score = 0;
	public static Item[] playerInventory = new Item[7];
	public static ListItem[] enchantedBag = new ListItem[666];
	public static int enchantedBagSize = 0;
	public static int points = 0;
	public static int AchievementRatio = 0;
	public static int playerInventorySize = 0;
	public static double playerBank = 0;
	public static Item[] magickShoppe;
	public static int playerHealth = 125;
	public static Item healthPotion;
	public static int attackPower = 15;
	public static Stack myStack = new Stack();
	public static Queue myQueue = new Queue();
	public static int damage = 0;
	public static Magic magic = new Magic();

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

	public static void IfMap() {

		for (int i = 0; i < playerInventory.length; i++) {

			Item playerInventoryVariable = playerInventory[i];

			if (playerInventoryVariable != null) {

				if (playerInventory[i].getId() == 0) {
					// playerInventoryVariable = true;
					map();

				}
			}
		}
		System.out.println("You do not have the map");
	}

	public static void IfPotion() {

		for (int i = 0; i < playerInventory.length; i++) {
			Item playerInventoryVariable = playerInventory[i];
			if (playerInventoryVariable != null) {
				if (playerInventory[i].getId() == 2) {
					// playerInventoryVariable = true;
					playerHealth = playerHealth + 125;
					System.out.println("Your current health is now: "
							+ playerHealth);
					System.out.println();
				}
			}
		}
		System.out.println("You do not have any health potions");
	}

	public static void IfCheatMessage() {

		for (int i = 0; i < playerInventory.length; i++) {
			Item playerInventoryVariable = playerInventory[i];
			if (playerInventoryVariable != null) {
				if (playerInventory[i].getId() == 5) {
					System.out
							.println("                      How to Become Immortal");
					System.out
							.println("You cannot defeat the Witch with low health, you need health potions."
									+ "\n"
									+ "Go to the potion Room, and steal the container full of health potions. Each potion gives a 125 boost."
									+ "\n" + "Press P to Drink the potion.");
					System.out.println();
					System.out
							.println("                      Secrets of attacking Monsters");
					System.out
							.println("You cannot attack anything unless you have a weapon, there is a sharp sword in the armory"
									+ "\n"
									+ "Aquire it so you may kill some monsters. The sword's power will increase with each strike by 10 points"
									+ "\n"
									+ "Hint1: If you kill the ghoul in the dungeon, your strength damage will increase by 25 points"
									+ "\n"
									+ "Hint2: If you kill the giant cat in the cellar below the dungeon your strength damage will increase by 75"
									+ "\n"
									+ "Hint3: Kill The witch to win the Game"
									+ "\n" + "Good Luck on your mission!");
					System.out.println();
				}
			}
		}
		System.out.println("You do not have the cheat sheet.");
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
		gameItem3.setName("Container of Health Potion");
		gameItem3.setDesc("take the potion and drink it to increase health!");

		Item gameItem4 = new Item(3);
		gameItem4.setName("Cursed Item");
		gameItem4.setDesc("Don\'t play around with that!");

		Item gameItem5 = new Item(4);
		gameItem5.setName("Sharp Sword");
		gameItem5.setDesc("A really useful thing to have, if the witch comes.");

		Item gameItem6 = new Item(5);
		gameItem6.setName("Cheat message 1");
		gameItem6.setDesc("Important to get through the game!!.");

		Item gameItem7 = new Item(6);
		gameItem7.setName("Cheat message 2");
		gameItem7.setDesc("Important to get through the game!!.");

		// Create the instance of monsters

		Monster ghoul = new Monster(100);
		ghoul.setName("Mr. Ghoul");
		ghoul.setDesc("Scary Ghoul, attack at will. ");

		Monster giantCat = new Monster(510);
		giantCat.setName("Giant Cat");
		giantCat.setDesc("It's a giant cat that is about to maul you to death");

		Monster witch = new Monster(1230);
		witch.setName("Evil Witch");
		witch.setDesc("Defeat the witch, and win the game.");

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
		location1.setItems(new Item[] { gameItem6 });
		location1.setDangerLevel("25%");
		location1.setMoney(143);

		Danger location2 = new Danger(2);
		location2.setName("Potions Room");
		location2
				.setDesc("There are racks of potions, some might turn you into a frog.");
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
		location4
				.setDesc("So much weaponry, this place is scary under the wrong hands.");
		location4.setItems(new Item[] { gameItem5 });
		location4.setDangerLevel("75%");
		location4.setMoney(723);

		Danger location5 = new Danger(5);
		location5.setName("Dungeon");
		location5
				.setDesc("A dungeon to keep all the animals and trespassers, so do not get caught. There's a trap door leading to the abyss.");
		location5.setItems(new Item[] {});
		location5.setDangerLevel("95%");
		location5.setMoney(450);
		location5.setMonster(ghoul);

		Danger location6 = new Danger(6);
		location6.setName("Kitchen");
		location6
				.setDesc("An elegant and beautiful kitchen, with all the sweats and candy you can eat.");
		location6.setItems(new Item[] { gameItem7 });
		location6.setDangerLevel("0.2%");
		location6.setMoney(18);

		Danger location7 = new Danger(7);
		location7.setName("Cursed Items Room");
		location7
				.setDesc("A room filled with strange objects, some of them smell funny.");
		location7.setItems(new Item[] { gameItem4 });
		location7.setDangerLevel("90%");
		location7.setMoney(87);

		Danger location8 = new Danger(8);
		location8.setName("Magick Shoppe");
		location8.setDesc("A place to purchase items.");
		location8.setItems(new Item[] {});
		location8.setDangerLevel("0.1%");
		location8.setMoney(200);

		Danger location9 = new Danger(9);
		location9.setName("Cellar");
		location9.setDesc("Entrance towards the Danger.");
		location9.setItems(new Item[] {});
		location9.setDangerLevel("60%");
		location9.setMoney(300);

		Danger location10 = new Danger(10);
		location10.setName("Cat Room");
		location10
				.setDesc("Room full of Cats, one cat is particularly evil, and you must destroy it.");
		location10.setItems(new Item[] {});
		location10.setDangerLevel("98%");
		location10.setMoney(900);
		location10.setMonster(giantCat);

		Danger location11 = new Danger(11);
		location11.setName("Witch's Bedroom");
		location11
				.setDesc("The most dangerous room of all, prepare to battle the witch.");
		location11.setItems(new Item[] {});
		location11.setDangerLevel("100%");
		location11.setMoney(5000);
		location11.setMonster(witch);

		// Location Links

		// Main Entrance
		location0.setNorth(location1);

		// Hall of Keys
		location1.setNorth(location4);
		location1.setEast(location3);
		location1.setWest(location2);
		location1.setSouth(location0);

		// Potion's Room
		location2.setEast(location1);

		// Broom Stick Storage
		location3.setWest(location1);

		// Armory
		location4.setNorth(location5);
		location4.setSouth(location1);

		// Dungeon
		location5.setNorth(location8);
		location5.setEast(location7);
		location5.setWest(location6);
		location5.setSouth(location4);
		location5.setDown(location9);

		// Kitchen
		location6.setEast(location5);

		// Cursed Items Room
		location7.setWest(location5);

		// Magic Shop
		location8.setSouth(location5);

		// Cellar
		location9.setSouth(location10);
		location9.setUp(location5);

		location10.setSouth(location11);
		location10.setNorth(location9);

		location11.setNorth(location10);

		currentLocale = location0;

		try {
			myStack.push(currentLocale);
		} catch (Exception ex) {
			System.out.println("Stack is full");
		}

		try {
			myQueue.enqueue(currentLocale);
		} catch (Exception ex) {
			System.out.println("Queue is full");
		}

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
		
		magic.readMagicItem();

	};

	public static void attack() {

		for (int i = 0; i < playerInventory.length; i++) {
			Item playerInventoryVariable = playerInventory[i];
			if (playerInventoryVariable != null) {
				if (playerInventory[i].getId() == 4) {
					damage = damage + 10;
				}
			}
		}

		// write the code for attacking.

		Monster monster = currentLocale.getMonster();

		if (monster != null) {
			if (monster.getHealth() > 0) {
				playerHealth = playerHealth - 25;
				System.out.println();
				System.out.println("Aghh you have been injured " + 25
						+ " points your health is now " + playerHealth);
				System.out.println();
				monster.attack(damage);
				System.out.println("You have Damaged the " + monster.getName()
						+ "by " + damage + " it's health is now "
						+ monster.getHealth());
				System.out.println();
			} else {
				monster.attack(0);
				monster.dead();
				System.out.println(monster.getName()
						+ "Is Dead with health of " + monster.getHealth());

				if (monster.getName() == "Evil Witch") {
					if (monster.getHealth() < 1) {
						System.out
								.println("Congratulations You have won the Game!!");
						System.out.println();
						quit();
					}
				}

				if (monster.getName() == "Giant Cat") {
					if (monster.getHealth() < 1) {
						damage = damage + 200;
						System.out
								.println("Your strength damage has been increased to 75");
					}
				}

				if (monster.getName() == "Mr. Ghoul") {
					if (monster.getHealth() < 1) {
						damage = damage + 100;
						System.out
								.println("Your strength damage has been increased to 25");
					}
				}
			}
		} else {
			System.out.println("The are no monsters present in the area.");
			System.out.println();
		}

		// Write conditions for wining, increasing damage and so.

		if (playerHealth < 0) {
			System.out.println("The " + monster.getName()
					+ " has killed you, You have lost the game");
			System.out.println();
			quit();
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
		System.out.println("   Bank or B");
		System.out.println("   Enchanted Bag or EN");

	}

	public static void directionsYouCanGo() {

		Locale northDirection = currentLocale.getNorth();
		Locale southDirection = currentLocale.getSouth();
		Locale eastDirection = currentLocale.getEast();
		Locale westDirection = currentLocale.getWest();
		Locale downDirection = currentLocale.getDown();
		Locale upDirection = currentLocale.getUp();

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
		if (downDirection != null) {

			System.out.print("Down, ");

		}
		if (upDirection != null) {

			System.out.print("Up, ");

		}

		System.out.println();
	}

	public static void updateDisplay() {

		System.out.println(currentLocale.getInfo());

		if (currentLocale.getId() == 8) {
			magic.readMagicItem();
			// promptUser();
		}

	}

	public void PromptUser(){
		
		selectionSort(magic.getItems());
		
        for (int i = 0; i < magic.getItems().length; i++) {
            if (magic.getItems()[i] != null) {
                System.out.println(magic.getItems()[i].toString());
            }
        }
		
		// Prompt the user, to select an item.

		Scanner inputReader = new Scanner(System.in);
		System.out.print("What item would you like? ");
		String targetItem = new String();
		targetItem = inputReader.nextLine();
		System.out.println();

		ListItem item = binarySearchArray();
		if (item != null) {
			System.out.println(item.toString());
		}
	}
	
	private static void typeNavigation() {

		// The Intial position > 0 which starts the position
		// of
		// the game.

		// if statement for the locations and commands

		if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n")) {

			newLocale = currentLocale.getNorth();

		} else if (command.equalsIgnoreCase("south")
				|| command.equalsIgnoreCase("s")) {

			newLocale = currentLocale.getSouth();

		} else if (command.equalsIgnoreCase("east")
				|| command.equalsIgnoreCase("e")) {

			newLocale = currentLocale.getEast();

		} else if (command.equalsIgnoreCase("west")
				|| command.equalsIgnoreCase("w")) {

			newLocale = currentLocale.getWest();

		} else if (command.equalsIgnoreCase("up")
				|| command.equalsIgnoreCase("u")) {

			newLocale = currentLocale.getUp();

		} else if (command.equalsIgnoreCase("down")
				|| command.equalsIgnoreCase("d")) {

			newLocale = currentLocale.getDown();

		} else if (command.equalsIgnoreCase("help")
				|| command.equalsIgnoreCase("h")) {
			help();
		} else if (command.equalsIgnoreCase("read")
				|| command.equalsIgnoreCase("r")) {
			IfCheatMessage();
		} else if (command.equalsIgnoreCase("Display Health")
				|| command.equalsIgnoreCase("DH")) {
			System.out.println("Your current health is" + playerHealth);
			System.out.println();
		} else if (command.equalsIgnoreCase("attack")
				|| command.equalsIgnoreCase("a")) {
			attack();
		} else if (command.equalsIgnoreCase("Map")
				|| command.equalsIgnoreCase("m")) {
			IfMap();
		} else if (command.equalsIgnoreCase("Cheat")
				|| command.equalsIgnoreCase("Ch")) {
			IfCheatMessage();
		} else if (command.equalsIgnoreCase("Potion")
				|| command.equalsIgnoreCase("P")) {
			IfPotion();
		} else if (command.equalsIgnoreCase("inventory")
				|| command.equalsIgnoreCase("i")) {
			playerInventory();
		} else if (command.equalsIgnoreCase("enchanted Bag")
				|| command.equalsIgnoreCase("En")) {
			enchantedBag();
		} else if (command.equalsIgnoreCase("take item")
				|| command.equalsIgnoreCase("ti")) {
			takeItem();
		} else if (command.equalsIgnoreCase("take money")
				|| command.equalsIgnoreCase("tm")) {
			takeMoney();
		} else if (command.equalsIgnoreCase("quit")
				|| command.equalsIgnoreCase("q")) {
			quit();
		} else if (command.equalsIgnoreCase("Bank")
				|| command.equalsIgnoreCase("b")) {
			checkBank();
		} else {
			System.out
					.println("The command that you have just placed is incorrect");
			help();
		}

		// if statement for the NewLocation

		if (newLocale == null) {
			System.out.println("Invalid Move! Try Again");
			System.out.println();

		} else {

			currentLocale = newLocale;

			try {
				myStack.push(currentLocale);
			} catch (Exception ex) {
				System.out.println("Stack is full");
			}

			try {
				myQueue.enqueue(currentLocale);
			} catch (Exception ex) {
				System.out.println("Queue is full");
			}

			moves = moves + 1;
			points = points + 5;
			AchievementRatio = points / moves;

		}
		
		ListItem item = Magic.binarySearchArray();
		if (item != null) { 
			System.out.println("Would you like to purchase this item?");
            
            Scanner reader = new Scanner(System.in);
            choice = reader.nextLine();
    		
    		if(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("Y")){
    			
    			System.out.println("Testing for Purchase");
    			
    				if(playerBank > item.getCost()){
    					
    					System.out.println("You can Purchase this item");
    					
    					System.out.println(item.getName()
    							+ " has been added to your encahnted bag.");

    					playerBank = playerBank - item.getCost();

    					System.out.println(" Press enter to see other Magic Items.");

    					enchantedBag[enchantedBagSize] = item;

    					enchantedBagSize = enchantedBagSize + 1;
    					
    				}else{
    					
    					System.out.println("You cannot Purchase this item");
    				}
    			
    		} else if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("N")){
    				
    			 searchOrExit();
    			
    		}
		} else {
			
			searchOrExit();
		}
	}
	
	public static void searchOrExit(){
		
		System.out.println("Would you like to search or Exit? ");
		
		Scanner reader = new Scanner(System.in);
        decision = reader.nextLine();
        
        	if(decision.equalsIgnoreCase("Exit") || decision.equalsIgnoreCase("E")){
        		
        		System.out.println("You have successfully left the premises");
        		newLocale = currentLocale.getSouth();
        	}
		
	}

	public static void map() {

		System.out.println("Here is a map to guide you around the Lair.");
		System.out
				.println("Use the id from the list of locations to determine the exact name of your location");
		System.out.println("First Floor:- ");
		System.out.println("    [8]");
		System.out.println("[6] [5]	[7]");
		System.out.println("    [4]	");
		System.out.println("[2] [1]	[3]");
		System.out.println("    [0]");
		System.out.println();
		System.out.println("Second Floor:- ");
		System.out.println("    [9]");
		System.out.println("   [10]");
		System.out.println("   [11]");
		System.out.println();
		System.out
				.println("Hint there is a trap door in the Dungeon, whic you can use to enter the witch's bedroom.");
		System.out.println();

	}

	private static void getCommand() {
		System.out.println("[ " + moves + " moves, score, " + points
				+ " AchievementRatio " + AchievementRatio + "] ");
		Scanner inputReader = new Scanner(System.in);
		command = inputReader.nextLine();
		System.out.println();

	}

	private static void playerInventory() {

		System.out.println("Player Inventory");

		for (int i = 0; i < playerInventory.length; ++i) {

			if (playerInventory[i] != null) {

				System.out.println(i + ":" + playerInventory[i]);
			}
		}
	}

	private static void enchantedBag() {

		System.out.println("enchantedBag");

		for (int i = 0; i < enchantedBag.length; ++i) {

			if (enchantedBag[i] != null) {

				System.out.println(i + ":" + enchantedBag[i]);
			}
		}
	}

	public static void takeItem() {

		if (currentLocale.getItems().length == 0) {

			System.out.println("There are no items to take");

		} else {

			Item locationItem = currentLocale.getItems()[0];

			playerInventory[playerInventorySize] = locationItem;

			playerInventorySize = playerInventorySize + 1;

			currentLocale.setItems(new Item[] {});

			System.out.println("you have taken item:" + locationItem);
			System.out.println();
		}

	}

	public static void checkBank() {

		System.out.println("You have " + playerBank + "Sapphires.");
		System.out.println();
	}

	public static void takeMoney() {

		double locationMoney = currentLocale.getMoney();

		playerBank = playerBank + locationMoney;

		currentLocale.setMoney(0);

		System.out.println("You have taken:" + locationMoney
				+ " Sapphires. Your bank Contains: " + playerBank
				+ " Sapphires.");
		System.out.println();

	}

	private static void quit() {

		System.out.println("Your statistics are:- ");
		System.out.println("moves: " + moves);
		System.out.println("money: " + playerBank);
		System.out.println("score: " + points);
		System.out.println("strength " + damage);
		System.out.println("health: " + playerHealth);
		System.out.println("Skill Ratio: " + AchievementRatio + "%");
		System.out.println();
		System.out.println("Do you want to print your adventure backward or forward? ");
		Scanner inputReader = new Scanner(System.in);
		String LastChoice = inputReader.nextLine();

		System.out.println("Locations you have Visited:-");
		System.out.println();

		if (LastChoice.equalsIgnoreCase("Backward")) {
			Locale hasVisited = null;
			try {
				hasVisited = myStack.pop();

				while (hasVisited != null) {
					System.out.println(hasVisited.getName());

					hasVisited = myStack.pop();

				}
			} catch (Exception ex) {

			}
		}
		if (LastChoice.equalsIgnoreCase("Forward")) {
			Locale hasVisited = null;
			try {
				hasVisited = myQueue.dequeue();

				while (hasVisited != null) {

					System.out.println(hasVisited.getName());

					hasVisited = myQueue.dequeue();
				}
			} catch (Exception ex) {

			}
		}
		stillInTheGame = false;

	}
};