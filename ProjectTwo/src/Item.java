public class Item {

	public Item(int id) {
		this.id = id;
	}

	// Getters and Setters

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	// Methods

	public String toString() {
		return "id=" + this.id + " name= " + this.name + " desc= " + this.desc
				+ " ";
	}

	// Private

	private int id;
	private String name;
	private String desc;

}