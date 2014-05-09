public class Currency {

	// Public

	public static String currencyName = " enchantedSapphires";

	public Currency(int id) {
		this.id = id;
	}

	// Getters and Setters

	public int getId() {
		return this.id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	// methods

	public String toString() {
		return "There are " + this.value + currencyName;

	}

	// Private

	private int id;
	private int value;
}
