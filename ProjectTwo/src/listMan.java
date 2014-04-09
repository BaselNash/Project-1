public class listMan {

	// public

	public listMan() {
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

	public ListItem getHead() {
		return head;
	}

	public void setHead(ListItem head) {
		this.head = head;
	}

	public ListItem getTail() {
		return tail;
	}

	public void setTail(ListItem tail) {
		this.tail = tail;
	}

	// methods

	public int getLength() {

		return this.length;
	}

	public void LinkToList(ListItem items) {

		if (this.head == null) {

			this.head = items;
			this.tail = items;

		} else {

			this.tail.setNext(items);
			this.tail = items;
		}
		this.length = this.length + 1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ListMan: name=" + this.name + " desc=" + this.desc
				+ "] List Items:\n");
		ListItem currentItem = this.head;
		while (currentItem != null) {
			sb.append(currentItem.toString());
			sb.append("\n");
			currentItem = currentItem.getNext();
		}
		return sb.toString();
	}

	// Private

	private String name;
	private String desc;
	private int length = 0;
	private ListItem head = null;
	private ListItem tail = null;
}