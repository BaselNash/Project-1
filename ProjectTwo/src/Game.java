//import java.io.*;
import java.util.Scanner;

public class Game {

	// Global

	public static String command;
	public static String choice;
	public static String decision;
	public static boolean stillInTheGame = true;
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
	public static int damage = 0;
	public static Magic magic = new Magic();
	public static Map map = new Map();
	public static boolean validMove;

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

		// instances of items.
		map.StartItems();
		magic.readMagicItem();

	};

	public static void validateMove() {

		if (!validMove) {
			System.out.println("Invalid Move! Try Again");
			System.out.println();

		} else {

			moves = moves + 1;
			points = points + 5;
			AchievementRatio = points / moves;
		}
	}

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

		Monster monster = map.currentLocale.getMonster();

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

		Locale northDirection = map.currentLocale;
		Locale southDirection = map.currentLocale;
		Locale eastDirection = map.currentLocale;
		Locale westDirection = map.currentLocale;
		Locale downDirection = map.currentLocale;
		Locale upDirection = map.currentLocale;

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

		System.out.println(map.currentLocale.getInfo());

		if (map.currentLocale.getId() == 8) {
			magic.readMagicItem();
			PromptUser();
		}

	}

	public static void PromptUser() {

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

		ListItem item = magic.binarySearchArray(targetItem);
		if (item != null) {
			System.out.println(item.toString());

			System.out.println("Would you like to purchase this item?");

			Scanner reader = new Scanner(System.in);
			choice = reader.nextLine();

			if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("Y")) {

				System.out.println("Testing for Purchase");

				if (playerBank > item.getCost()) {

					System.out.println("You can Purchase this item");

					System.out.println(item.getName()
							+ " has been added to your encahnted bag.");

					playerBank = playerBank - item.getCost();

					System.out
							.println(" Press enter to see other Magic Items.");

					enchantedBag[enchantedBagSize] = item;

					enchantedBagSize = enchantedBagSize + 1;

				} else {

					System.out.println("You cannot Purchase this item");
				}

			} else if (choice.equalsIgnoreCase("No")
					|| choice.equalsIgnoreCase("N")) {

				searchOrExit();

			}
		} else {

			searchOrExit();
		}
	}

	private static void typeNavigation() {

		// The Intial position > 0 which starts the position
		// of
		// the game.

		// if statement for the locations and commands

		if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n")) {

			validMove = map.moveNorth();
			validateMove();

		} else if (command.equalsIgnoreCase("south")
				|| command.equalsIgnoreCase("s")) {

			validMove = map.moveSouth();
			validateMove();

		} else if (command.equalsIgnoreCase("east")
				|| command.equalsIgnoreCase("e")) {

			validMove = map.moveEast();
			validateMove();

		} else if (command.equalsIgnoreCase("west")
				|| command.equalsIgnoreCase("w")) {

			validMove = map.moveWest();
			validateMove();

		} else if (command.equalsIgnoreCase("up")
				|| command.equalsIgnoreCase("u")) {

			map.moveUp();
			validateMove();

		} else if (command.equalsIgnoreCase("down")
				|| command.equalsIgnoreCase("d")) {

			validMove = map.moveDown();
			validateMove();

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

	}

	public static void searchOrExit() {

		System.out.println("Would you like to search or Exit? ");

		Scanner reader = new Scanner(System.in);
		decision = reader.nextLine();

		if (decision.equalsIgnoreCase("Exit") || decision.equalsIgnoreCase("E")) {

			System.out.println("You have successfully left the premises");
			validMove = map.moveSouth();
			validateMove();
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

		if (map.currentLocale.getItems().length == 0) {

			System.out.println("There are no items to take");

		} else {

			Item locationItem = map.currentLocale.getItems()[0];

			playerInventory[playerInventorySize] = locationItem;

			playerInventorySize = playerInventorySize + 1;

			map.currentLocale.setItems(new Item[] {});

			System.out.println("you have taken item:" + locationItem);
			System.out.println();
		}

	}

	public static void checkBank() {

		System.out.println("You have " + playerBank + "Sapphires.");
		System.out.println();
	}

	public static void takeMoney() {

		double locationMoney = map.currentLocale.getMoney();

		playerBank = playerBank + locationMoney;

		map.currentLocale.setMoney(0);

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
		System.out
				.println("Do you want to print your adventure backward or forward? ");
		Scanner inputReader = new Scanner(System.in);
		String LastChoice = inputReader.nextLine();

		System.out.println("Locations you have Visited:-");
		System.out.println();

		if (LastChoice.equalsIgnoreCase("Backward")) {
			Locale hasVisited = null;
			try {
				hasVisited = map.getStack().pop();

				while (hasVisited != null) {
					System.out.println(hasVisited.getName());

					hasVisited = map.getStack().pop();

				}
			} catch (Exception ex) {

			}
		}
		if (LastChoice.equalsIgnoreCase("Forward")) {
			Locale hasVisited = null;
			try {
				hasVisited = map.getQueue().dequeue();

				while (hasVisited != null) {

					System.out.println(hasVisited.getName());

					hasVisited = map.getQueue().dequeue();
				}
			} catch (Exception ex) {

			}
		}
		stillInTheGame = false;

	}
};