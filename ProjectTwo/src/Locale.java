	
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
	    
	    public String getInfo(){
	    	
	    	return this.name + "/n" + this.desc;
	    }

	    public String getName() {
	        return this.name;
	    }
	    public void setName(String value) {
	        this.name = value;
	    }
	    
	    public Item getItem() {
	    	return this.item[];
	    }
	    
	    public void setItem(Item value) {
	        this.item[] = value;
	    }
	    
	    public String getDesc() {
	        return this.desc;
	    }
	    public void setDesc(String value) {
	        this.desc = value;
	    }
	    // Other methods
	    public String toString() {
	        return "[Locale object: id=" 
	        		+ this.id 
	        		+ " name="
	        		+ this.name 
	        		+ " desc=" 
	        		+ this.desc 
	        		+ " items="
	        		+ this.item +
	        		"] ";
	    }

	    //
	    // -- PRIVATE --
	    //
	    private int     id;
	    private Item[] item;
	    private String  name;
	    private String  desc;
	}
