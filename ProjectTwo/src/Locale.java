import java.util.Arrays;

public class Locale {

	// Public

	// Constructor
	public Locale(int id) {
		this.id = id;
	}

	// Getters and Setters
	public int getId() {
		return this.id;
	}

	public String getInfo() {

		return this.name + "\n" + this.desc + "\n" + "Here are the items: "
				+ Arrays.toString(this.items);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public Item[] getItems() {
		return this.items;
	}

	public void setItems(Item[] value) {
		this.items = value;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String value) {
		this.desc = value;
	}

	// Other methods
	public String toString() {
		return "[Locale object: id= " + this.id + " name= " + this.name
				+ " desc= " + this.desc + " items= "
				+ Arrays.toString(this.items) + "] ";
	}

	//
	// -- PRIVATE --
	//
	private int id;
	private Item[] items;
	private String name;
	private String desc;
}
