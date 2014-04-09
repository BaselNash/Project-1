
public class NavMan {

	public NavMan() {
		
	}
	
	//Public

	public Locale getHead() {
		return head;
	}

	public void setHead(Locale head) {
		this.head = head;
	}

	public Locale getTail() {
		return tail;
	}

	public void setTail(Locale tail) {
		this.tail = tail;
	}
	
	public Locale goSouth (Locale location) {
		
		if (this.head == null) {

			this.head = location;
			this.tail = location;
			
		}else{
			
			this.tail.setNext(location);	
			this.tail = location;
		}
	}
		
 
	public Locale goNorth() {
		
	}
	
	public Locale goWest() {
		
	}
	
	public Locale goEast() {
		
	}
	
	
	
	//Private 
		
	private Locale head = null;
	private Locale tail = null;
	
	
}


