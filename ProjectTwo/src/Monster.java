public class Monster {

	public Monster(int health) {	
		this.health = health;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	//Methods
	
	@Override
    public String toString() {
        return "[Monster name= " + this.name
                + " health= " + this.health
                + " Desc= " + this.desc + "]";
    }
	
	public void attack(int damage) {
		this.health =  this.health - damage; 
	}	
	
	

	//Private 
	
	private String name;
	private int health;
	private String desc;
	
}

