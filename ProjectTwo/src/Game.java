import java.util.Scanner;

public class Game {
	
	
	//Global
	
	public static int currentLocale = 0;
	public static String command;
	public static boolean stillInTheGame = true;
	public static Locale[] Locations;           
	public static int[][] navigationArray;
	public static int moves = 0;
	public static int score = 0;
	public static Item[] PlayerInventory;
	public static int points = 0;
	public static int AchievementRatio = 0;
	
	public static void main(String[] args){
		intialStart();
		getCommand();
		updateDisplay();
		
		// while Loop for the game; 
		while (stillInTheGame) {
			getCommand();
			updateDisplay();
			typeNavigation();
		}
		
		System.out.println("Thank you for playing the game.");
	}
	
	//Private methods, arrays, and such;
	
public static void intialStart() {
		
		//Intializing the intial commands
		
		command = new String();
		stillInTheGame = true;
		
		//Create the instance of locations
		
		Locale location0 = new Locale(0);
		location0.setName("Main Entrance");
		location0.setDesc("You are currently Entering the Witche\'s Lair.");
		//location0.setDangerLevel("10%");
		
		Locale location1 = new Locale(1);
		location1.setName("Hall of Keys");
		location1.setDesc("There are keys hovering all around you.");
		//location1.setDangerLevel("25%");
		
		Locale location2 = new Locale(2);
		location2.setName("Potions Room");
		location2.setDesc("There are racks of potions, some might turn you into a frog.");
		//location2.setDangerLevel("80%");
		
		Locale location3 = new Locale(3);
		location3.setName("Broom Stick Storage");
		location3.setDesc("A giant closet filled with flying broomsticks.");
		//location3.setDangerLevel("60%");
		
		Locale location4 = new Locale(4);
		location4.setName("Armory");
		location4.setDesc("So much weaponry, this place is scary under the wrong hands.");
		//location4.setDangerLevel("75%");
		
		Locale location5 = new Locale(5);
		location5.setName("Dungeon");
		location5.setDesc("A dungeon to keep all the animals and trespassers, so do not get caught.");
		//location5.setDangerLevel("99.99999%");
		
		Locale location6 = new Locale(6);
		location6.setName("Kitchen");
		location6.setDesc("An elegant and beautiful kitchen, with all the sweats and candy you can eat.");
		//location6.setDangerLevel("0.2%");
		
		Locale location7 = new Locale(7);
		location7.setName("Cursed Items Room");
		location7.setDesc("A room filled with strange objects, some of them smell funny.");
		//location7.setDangerLevel("90%");
		
		Locale location8 = new Locale(8);
		location8.setName("Magick Shoppe");
		location8.setDesc("A place to purchase items.");
		//location8.setDangerLevel("0.1%");
		
		//set up the location array.
		
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
		
		System.out.println("All game locations:");
	      for (int i = 0; i <Locations.length; ++i) {
	         System.out.println(i + ":" + Locations[i].toString());
	      };
		
	//2D array for the game, 	
		
	navigationArray = new int[][] {
				 
				    /*   N	S  E  W*/
	/*Location 0*/		{1,-1,-1,-1},
	/*Location 1*/		{4,0,3,2},
	/*Location 2*/		{-1,-1,1,-1},
	/*Location 3*/		{-1,-1,-1,1},
	/*Location 4*/		{5,1,-1,-1},
	/*Location 5*/		{8,4,7,6},
	/*Location 6*/		{-1,-1,5,-1},
	/*Location 7*/		{-1,-1,-1,5},
	/*Location 8*/		{-1,5,-1,-1}
	 
		 };
	};
	
	private static void typeNavigation(){
		
		int dir = -1; // The Intial position > 0 which starts the position of the game.
			
			// if statement for the locations
		
		if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n") ){
			dir = 0;
		}else if (command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s") ){
			dir = 1;
		}else if (command.equalsIgnoreCase("east") || command.equalsIgnoreCase("e") ){
			dir = 2;
		}else if (command.equalsIgnoreCase("west") || command.equalsIgnoreCase("w") ){
			dir = 3;
		}else if(command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h") ){
			help();	
		}else if(command.equalsIgnoreCase("map") || command.equalsIgnoreCase("m") ){
			map();	
		}else if(command.equalsIgnoreCase("inventory") || command.equalsIgnoreCase("i") ){
			playerInventory();	
		}else if(command.equalsIgnoreCase("take") || command.equalsIgnoreCase("t") ){
			help();	
		}else if(command.equalsIgnoreCase("Magick Shoppe") || command.equalsIgnoreCase("ms") ){
			help();	
		}else if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q") ){
			quit();	
		}else{
			System.out.println("The command that you have just placed is incorrect");
			 help();
		}
		
		// if statement for the NewLocation
		
		if (dir > -1) {
			
			int NewLocation = navigationArray[currentLocale][dir];
			if (NewLocation == -1){
				System.out.println("Invalid Move! Try Again");
			}else{
				currentLocale = NewLocation;
                moves = moves + 1;
                points = points + 5;
                AchievementRatio = points/moves;
			}	
		}
	}
	
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
	
	public static void updateDisplay(){
	
	System.out.println(Locations[currentLocale].getInfo());
	
	}
	
	public static void map(){
		
		System.out.println("    [8]");
		System.out.println("[6] [5]	[7]");
		System.out.println("    [4]	");
		System.out.println("[2] [1]	[3]");
		System.out.println("    [0]");
		
		}
	
	 private static void getCommand() {
		 System.out.println("[ " + moves + "moves, score " + score + "AchievementRatio " + AchievementRatio +"] ");
	     Scanner inputReader = new Scanner(System.in);
	     command = inputReader.nextLine();
	     
	}
	 
	 private static void playerInventory() {
		 System.out.println("Player Inventory");
	      for (int i = 0; i <PlayerInventory.length; ++i) {
	         System.out.println(i + ":" + PlayerInventory[i]);
	      };
	}
	 
	 private static void DisplayMagickShoppeItems() {
		 
	}
	 
	 private static void quit() {
		 
		 stillInTheGame = false;
		 
		}
	 
	 
};
