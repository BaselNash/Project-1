public class Map {

	public Locale currentLocale;
	public Locale newLocale;
	public Locale[] Locations;
	public Item[] localeItems;

	public void StartItems() {

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
		Locations[9] = location9;
		Locations[10] = location10;
		Locations[11] = location11;

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
		System.out
				.println("Hint: First, in order to get through the game you need to find the map, to see where you are."
						+ "\n"
						+ "and you need to find the cheat message Otherwise you wouldn't be able to win the game. It contains important secrets."
						+ "\n"
						+ "Your objective is to find a kill the witch. She has minions around differnt locations kill them to increase your strength."
						+ "\n"
						+ "Press H or Help For navigation details."
						+ "\n" + "Good Luck!");
		System.out.println("Press enter to begin");

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
	}
}
