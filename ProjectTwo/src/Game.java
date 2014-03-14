import java.util.Scanner;

public class Game {

	// Global

	public static int currentLocale = 0;
	public static String command;
	public static boolean stillInTheGame = true;
	public static Locale[] Locations;
	public static Item[] ITEMS;
	public static int[][] navigationArray;
	public static int moves = 0;
	public static int score = 0;
	public static Item[] PlayerInventory = new Item[5];
	public static int points = 0;
	public static int AchievementRatio = 0;
	public static int playerInventorySize = 0;
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

		Danger location1 = new Danger(1);
		location1.setName("Hall of Keys");
		location1.setDesc("There are keys hovering all around you.");
		location1.setItems(new Item[] {});
		location1.setDangerLevel("25%");

		Danger location2 = new Danger(2);
		location2.setName("Potions Room");
		location2.setDesc("There are racks of potions, some might turn you into a frog.");
		location2.setItems(new Item[] { gameItem3 });
		location2.setDangerLevel("80%");

		Danger location3 = new Danger(3);
		location3.setName("Broom Stick Storage");
		location3.setDesc("A giant closet filled with flying broomsticks.");
		location3.setItems(new Item[] { gameItem2 });
		location3.setDangerLevel("60%");

		Danger location4 = new Danger(4);
		location4.setName("Armory");
		location4.setDesc("So much weaponry, this place is scary under the wrong hands.");
		location4.setItems(new Item[] { gameItem5 });
		location4.setDangerLevel("75%");

		Danger location5 = new Danger(5);
		location5.setName("Dungeon");
		location5.setDesc("A dungeon to keep all the animals and trespassers, so do not get caught.");
		location5.setItems(new Item[] {});
		location5.setDangerLevel("99.99999%");

		Danger location6 = new Danger(6);
		location6.setName("Kitchen");
		location6.setDesc("An elegant and beautiful kitchen, with all the sweats and candy you can eat.");
		location6.setItems(new Item[] {});
		location6.setDangerLevel("0.2%");

		Danger location7 = new Danger(7);
		location7.setName("Cursed Items Room");
		location7.setDesc("A room filled with strange objects, some of them smell funny.");
		location7.setItems(new Item[] { gameItem4 });
		location7.setDangerLevel("90%");

		Danger location8 = new Danger(8);
		location8.setName("Magick Shoppe");
		location8.setDesc("A place to purchase items.");
		location8.setItems(new Item[] {});
		location8.setDangerLevel("0.1%");

		// Items in magick shoope

		Item magickShoppeItem1 = new Item(0);
		magickShoppeItem1.setName("Cloak of Invisibility");
		magickShoppeItem1.setDesc("Hides you from your enemies. 67.00$");

		Item magickShoppeItem2 = new Item(1);
		magickShoppeItem2.setName("Ring of Power");
		magickShoppeItem2.setDesc("Grants you unlimited source of power. 1000.54$");

		Item magickShoppeItem3 = new Item(2);
		magickShoppeItem3.setName("Magic Beans");
		magickShoppeItem3.setDesc("Creates a magical tree. 23.00$");

		Item magickShoppeItem4 = new Item(3);
		magickShoppeItem4.setName("Luminating Sword");
		magickShoppeItem4.setDesc("A sharp sword with the ability to light up a room. 44.00$");

		Item magickShoppeItem5 = new Item(4);
		magickShoppeItem5.setName("Orb of Wisdom");
		magickShoppeItem5.setDesc("A type of crystal ball, used for telling the future. 67.99$");

		Item magickShoppeItem6 = new Item(5);
		magickShoppeItem6.setName("Water Staff");
		magickShoppeItem6.setDesc("Useful for conjuring water from air. 33.00$");

		Item magickShoppeItem7 = new Item(6);
		magickShoppeItem7.setName("Book of Spells");
		magickShoppeItem7.setDesc("All the spells you can ever think of are in the book. 130.00$");

		magickShoppe = new Item[7];
		magickShoppe[0] = magickShoppeItem1;
		magickShoppe[1] = magickShoppeItem2;
		magickShoppe[2] = magickShoppeItem3;
		magickShoppe[3] = magickShoppeItem4;
		magickShoppe[4] = magickShoppeItem5;
		magickShoppe[5] = magickShoppeItem6;
		magickShoppe[6] = magickShoppeItem7;

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
		System.out.println("You are currently in" + Locations[currentLocale]);
		System.out.println();
		System.out.println("Press any key to begin");

		// 2D array for the game,

		navigationArray = new int[][] {

		/* N S E W */
		/* Location 0 */{ 1, -1, -1, -1 },
		/* Location 1 */{ 4, 0, 3, 2 },
		/* Location 2 */{ -1, -1, 1, -1 },
		/* Location 3 */{ -1, -1, -1, 1 },
		/* Location 4 */{ 5, 1, -1, -1 },
		/* Location 5 */{ 8, 4, 7, 6 },
		/* Location 6 */{ -1, -1, 5, -1 },
		/* Location 7 */{ -1, -1, -1, 5 },
		/* Location 8 */{ -1, 5, -1, -1 } };
	};

	private static void help() {
		System.out.println("Theses are the commands that you can type in:-");
		System.out.println("   North or N");
		System.out.println("   South or S");
		System.out.println("   West or W");
		System.out.println("   East or E");
		System.out.println("   Help or H");
		System.out.println("   Inventory or I");
		System.out.println("   Map or M");
		System.out.println("   Quit or Q");
	}

	public static void directionsYouCanGo() {

		int northDirection = navigationArray[currentLocale][0];
		int southDirection = navigationArray[currentLocale][1];
		int eastDirection = navigationArray[currentLocale][2];
		int westDirection = navigationArray[currentLocale][3];

		System.out.print("You can move ");

		if (northDirection != -1) {

			System.out.print("North, ");

		}
		if (southDirection != -1) {

			System.out.print("South, ");

		}
		if (eastDirection != -1) {

			System.out.print("East, ");

		}
		if (westDirection != -1) {

			System.out.print("West, ");

		}

		System.out.println();
	}

	public static void updateDisplay() {

		System.out.println(Locations[currentLocale].getInfo());

		if (currentLocale == 8) {

			DisplayMagickShoppeItems();
		}

	}

	private static void typeNavigation() {

		int direction = -1; // The Intial position > 0 which starts the position
							// of
		// the game.

		// if statement for the locations

		if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n")) {
			direction = 0;
		} else if (command.equalsIgnoreCase("south")
				|| command.equalsIgnoreCase("s")) {
			direction = 1;
		} else if (command.equalsIgnoreCase("east")
				|| command.equalsIgnoreCase("e")) {
			direction = 2;
		} else if (command.equalsIgnoreCase("west")
				|| command.equalsIgnoreCase("w")) {
			direction = 3;
		} else if (command.equalsIgnoreCase("help")
				|| command.equalsIgnoreCase("h")) {
			help();
		} else if (command.equalsIgnoreCase("map")
				|| command.equalsIgnoreCase("m")) {
			Ifmap();
		} else if (command.equalsIgnoreCase("inventory")
				|| command.equalsIgnoreCase("i")) {
			playerInventory();
		} else if (command.equalsIgnoreCase("take")
				|| command.equalsIgnoreCase("t")) {
			takeItem();
		} else if (command.equalsIgnoreCase("Magick Shoppe")
				|| command.equalsIgnoreCase("ms")) {
			DisplayMagickShoppeItems();
		} else if (command.equalsIgnoreCase("quit")
				|| command.equalsIgnoreCase("q")) {
			quit();
		} else {
			System.out
					.println("The command that you have just placed is incorrect");
			help();
		}

		// if statement for the NewLocation

		if (direction > -1) {

			int NewLocation = navigationArray[currentLocale][direction];
			if (NewLocation == -1) {
				System.out.println("Invalid Move! Try Again");

			} else {
				currentLocale = NewLocation;
				moves = moves + 1;
				points = points + 5;
				AchievementRatio = points / moves;
			}
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

	public static void takeItem() {

		Locale currentLocation = Locations[currentLocale];

		if (currentLocation.getItems().length == 0) {

			System.out.println("There are no items to take");

		} else {

			Item locationItem = currentLocation.getItems()[0];

			PlayerInventory[playerInventorySize] = locationItem;

			playerInventorySize = playerInventorySize + 1;

			currentLocation.setItems(new Item[] {});

			System.out.println("you have taken item:" + locationItem);
		}

	}

	private static void DisplayMagickShoppeItems() {

		for (int item = 0; item < magickShoppe.length; item++) {

			System.out.println(magickShoppe[item].toString());

		}
	}

	private static void quit() {

		stillInTheGame = false;

	}
};
