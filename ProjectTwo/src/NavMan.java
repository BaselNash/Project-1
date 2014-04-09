
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
	
	//Private 
		
	private Locale head = null;
	private Locale tail = null;
	
	
}


