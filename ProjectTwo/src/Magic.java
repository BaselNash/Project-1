import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Magic {
	
	public static ListItem[] enchantedBag = new ListItem[666];
	public static int enchantedBagSize = 0;
	public static double playerBank = 0;
	public static Locale currentLocale;
	public static Locale newLocale;
	public static String choice;
	public static String decision;
	
	public void readMagicItem(){

		// creating the list manager, classified as listMan1

		ListMan listMan1 = new ListMan();
		listMan1.setName("Magic Items");
		listMan1.setDesc("Enchanted Items that seem both cool and scary.");

		final String fileName = "magicItems.txt";

		readmagicItemsFromFileToList(fileName, listMan1);

		// Managing the array for to hold the items. The array is called items

		ListItem[] items = new ListItem[listMan1.getLength()]; // .getLength
																// method can be
																// found in
																// listMan.
		readMagicItemsFromFileToArray(fileName, items);

		// Displaying the items in the array on the command line. Using a for
		// loop
		
		selectionSort(items);
		
        for (int i = 0; i < items.length; i++) {
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
		li = binarySearchArray(items, targetItem);
		if (li != null) {
			System.out.println(li.toString());
		}

	}

	// Magic Item methods

	// First method is read through the magic Items and find the users choice
	
	public static void searchOrExit(){
		
		System.out.println("Would you like to search or Exit? ");
		
		Scanner reader = new Scanner(System.in);
        decision = reader.nextLine();
        
        	if(decision.equalsIgnoreCase("Exit") || decision.equalsIgnoreCase("E")){
        		
        		System.out.println("You have successfully left the premises");
        		
        		
        		newLocale = currentLocale.getSouth();
        	}
		
	}
	
	private static ListItem binarySearchArray(ListItem[] items, String targetItem){
		
		ListItem retVal = null;
		System.out.println("Binary Search for " + targetItem + ".");
		ListItem currentItem = new ListItem();
		boolean isFound = false;
		int counter = 0;
		int low = 0;
		int high = items.length-1;
		while ( (!isFound) && (low <= high)) {
            int mid = Math.round((high + low) / 2);
            currentItem = items[mid];
            if (currentItem.getName().equalsIgnoreCase(targetItem)) {
                // We found it!
                isFound = true;
                retVal = currentItem;
            } else {
                // Keep looking.
                counter++;
                if (currentItem.getName().compareToIgnoreCase(targetItem) > 0) {
                    // target is higher in the list than the currentItem (at mid)
                    high = mid - 1;
                } else {
                    // target is lower in the list than the currentItem (at mid)
                    low = mid + 1;
                }
            }
		}
        if (isFound) {
            System.out.println("Found " + targetItem + " after " + counter + " comparisons.");
            
            System.out.println("Would you like to purchase this item?");
            
            Scanner reader = new Scanner(System.in);
            choice = reader.nextLine();
    		
    		if(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("Y")){
    			
    			System.out.println("Testing for Purchase");
    			
    				if(playerBank > currentItem.getCost()){
    					
    					System.out.println("You can Purchase this item");
    					
    					System.out.println(currentItem.getName()
    							+ " has been added to your encahnted bag.");

    					playerBank = playerBank - currentItem.getCost();

    					System.out.println(" Press enter to see other Magic Items.");

    					enchantedBag[enchantedBagSize] = currentItem;

    					enchantedBagSize = enchantedBagSize + 1;
    					
    				}else{
    					
    					System.out.println("You cannot Purchase this item");
    				}
    			
    		} else if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("N")){
    				
    			 searchOrExit();
    			
    		} else {
    			
    			System.out.println("Invalid type, try again.");
    			
    		}
            
        } else {
        	
            System.out.println("Could not find " + targetItem + " in " + counter + " comparisons.");
            
            System.out.println("Would you like to search or Exit? ");
			
            Scanner reader = new Scanner(System.in);
            decision = reader.nextLine();
            
            	if(decision.equalsIgnoreCase("Exit") || decision.equalsIgnoreCase("E")){
            		
            		System.out.println("You have successfully left the premises");
            		currentLocale = currentLocale.getSouth();
            		System.out.println(currentLocale.getInfo());
            		
            	} 
            
            }
        return retVal;
	}	

	/*private static ListItem LinearSearch(ListMan lm, String targetItem) {

		ListItem retVal = null;
		System.out.println("Searching for " + targetItem + ".");
		int magicItemCounter = 0; // Counts the number of searches through each
									// item.
		ListItem currentItem = new ListItem();
		currentItem = lm.getHead();
		boolean isFound = false;
		while ((!isFound) && (currentItem != null)) {
			magicItemCounter = magicItemCounter + 1;
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
			System.out.println("Found " + targetItem + " after "
					+ magicItemCounter + " comparisons.");
			System.out.println("Would you like to purchase this item? Y or N");
			Scanner inputReader = new Scanner(System.in);
			decision = inputReader.nextLine();
			if (decision.equalsIgnoreCase("Y")
					&& playerBank > currentItem.getCost()) {

				System.out.println(currentItem.getName()
						+ " has been added to your encahnted bag.");

				playerBank = playerBank - currentItem.getCost();

				System.out.println(" Press enter to see other Magic Items.");

				enchantedBag[enchantedBagSize] = currentItem;

				enchantedBagSize = enchantedBagSize + 1;

			} else if (decision.equalsIgnoreCase("Y")
					&& playerBank < currentItem.getCost()) {

				System.out.println("you can't afford it. ");
				System.out.println(" Press enter to see other Magic Items.");

			} else {

				ReadMagicItemsAndPromptUser();

			}
			return currentItem;

		} else {
			System.out.println("Could not find " + targetItem + " in "
					+ magicItemCounter + " comparisons.");
			System.out.println("Would you like to search again or exit?");
			Scanner inputReader = new Scanner(System.in);
			choice = inputReader.nextLine();
			if (choice.equalsIgnoreCase("Search")
					|| choice.equalsIgnoreCase("S")) {

				ReadMagicItemsAndPromptUser();

			}
			if (choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("E")) {

				System.out.println();
				currentLocale = currentLocale.getSouth();
				updateDisplay();
			} else {

				System.out.println("Invalid Command.");
				System.out.println("Would you like to search again or exit?");
			}
		}

		return retVal;
	}*/

	// Second method is to read the magic items from the file to the list.

	private static void readmagicItemsFromFileToList(String fileName, ListMan lm) {

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

	// Third method is to read the magic items from the file to the list.
	private static void readMagicItemsFromFileToArray(String fileName,
			ListItem[] items) {

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
	
	private static void selectionSort(ListItem[] items) {
        for (int pass = 0; pass < items.length-1; pass++) {
            // System.out.println(pass + "-" + items[pass]);
            int indexOfTarget = pass;
            int indexOfSmallest = indexOfTarget;
            for (int j = indexOfTarget+1; j < items.length; j++) {
                if (items[j].getName().compareToIgnoreCase(items[indexOfSmallest].getName()) < 0) {
                    indexOfSmallest = j;
                }
            }
            ListItem temp = items[indexOfTarget];
            items[indexOfTarget] = items[indexOfSmallest];
            items[indexOfSmallest] = temp;
        }
    }

}
