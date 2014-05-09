import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Magic {

	
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
	
	private ListItem binarySearchArray(ListItem[] items, String targetItem){
		
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
            
        } else {
        	
            System.out.println("Could not find " + targetItem + " in " + counter + " comparisons.");
            
            }
        return retVal;
	}	

	

	// Second method is to read the magic items from the file to the list.

	private void readmagicItemsFromFileToList(String fileName, ListMan lm) {

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
	
	private void selectionSort(ListItem[] items) {
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
