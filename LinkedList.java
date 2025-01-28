/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		//// Replace the following statement with your code
		Node currentNode = this.first;
		for (int i=0;i<index;i++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		//// Write your code here
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node newNode = new Node(block);
		if (index==0) {
			newNode.next = this.first;
			this.first = newNode; 
			if (this.size==0) {
				newNode.next = null;
			}
		}
		if (index==this.size) {
			if(this.last!=null) {
				this.last.next = newNode;
			}
			this.last = newNode;
		}
		else {
			Node currentNode = this.first;
			for (int i=0;i<index-1;i++) {
				currentNode = currentNode.next;
			}
			newNode.next = currentNode.next;
			currentNode.next = newNode;
			if (index == this.size) {
				this.last = newNode;
			}
		}
		this.size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		//// Write your code here
		Node newNode  = new Node(block);
		if(this.size==0) {
			this.first = newNode;
		} else  {
				this.last.next=newNode; 
		}
		this.last=newNode;;
		this.size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		//// Write your code here
		Node newNode  = new Node(block);
		if(this.size==0) {
			this.first = newNode; 
			this.last = newNode;
		}
		else {
			newNode.next = this.first;
			this.first = newNode;
		}
		this.size++;
		if (this.size == 1) {
			this.last = newNode;
		}
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		//// Replace the following statement with your code
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node currentNode = this.first;
		for (int i=0;i<index;i++) {
			currentNode = currentNode.next;
		}
		return currentNode.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		//// Replace the following statement with your code
		Node currentNode = this.first;
		for (int i=0; i<this.size;i++) {
			if(currentNode.block.equals(block)){
				return i;
			}
			else currentNode=currentNode.next;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		//// Write your code here
		if(this.first==null) {
			throw new IllegalArgumentException(" NullPointerException!");
		}
		if(this.first.equals(node)) {
			this.first = this.first.next;
			this.size--;
			if (this.first==null) {
				this.last=null;
			}
		}
		else {
			Node beforeNode = this.first;
			Node currentNode = this.first.next;
			while (currentNode!=null) {
				if(currentNode.equals(node)){
					beforeNode.next = currentNode.next;
					this.size--;
					if (currentNode == this.last) {
						this.last = beforeNode;
					}
					break;
				}
				else {
					beforeNode=currentNode;
					currentNode=currentNode.next;
				}
			}
		}
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		//// Write your code here
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (this.first == null) {
			return;
		}
		if(index==0) {
			this.first = this.first.next;
			this.size--;
			return;
		}
		Node currentNode = this.first;
		for (int i=0;i<index-1;i++) {
			currentNode=currentNode.next;
		}
		if (currentNode.next == null) {
			return;
		}
		currentNode.next=currentNode.next.next;
		this.size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		//// Write your code here
		if(this.first==null) {
			throw new IllegalArgumentException(" IllegalArgumentException: index must be between 0 and size");
		}
		if (this.first.block.equals(block)) {
			this.first = this.first.next;
			this.size--;
			if (this.first == null) {
				this.last = null;
			}
		}
		else {
			Node beforeNode = this.first;
			Node currentNode = this.first.next;
			while (currentNode!=null)
				if(currentNode.block.equals(block)){
					beforeNode.next=currentNode.next;
					this.size--;
					if (currentNode == this.last) {
						this.last = beforeNode;
					}
					break;
				}
				beforeNode = currentNode;
				currentNode=currentNode.next;
				}
			}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		//// Replace the following statement with your code
		String s1 = "{";
		Node currentNode = this.first;
		for(int i =0;i<this.size-1;i++) {
			s1 = s1 + currentNode.block + ",";
			currentNode = currentNode.next;
		}
		if (currentNode != null) { 
			s1 = s1 + currentNode.block;
		}
		s1 = s1 + "}";
		return s1;
	}
}