public class Danger extends Locale {

	public Danger(int id) {
		super(id);
	}

	// Getters and Setters

	public String getDangerLevel() {
		return DangerLevel;
	}

	public void setDangerLevel(String value) {
		this.DangerLevel = value;
	}

	@Override
	public String toString() {
		return super.toString() + " DangerLevel= " + this.DangerLevel;
	}

	//
	// Private
	//
	private String DangerLevel;
}