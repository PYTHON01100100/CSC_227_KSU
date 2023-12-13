
public class Queue {
	private Node front; // front of the queue
	private Node rear; // rear of the queue

	public Queue() {
		this.front = this.rear = null;
	}

	// Method to add an item to the queue
	public void enqueue(PCB data) {
		Node newNode = new Node(data);

		// If the queue is empty, both front and rear are the new node
		if (isEmpty()) {
			front = rear = newNode;
			return;
		}

		// Add the new node at the end of the queue and update rear
		rear.next = newNode;
		rear = newNode;
	}

	// Method to remove an item from the queue
	public PCB dequeue() {
		// If the queue is empty, return -1 (or throw an exception)
		if (isEmpty()) {
			System.out.print("");
			return null;
		}

		// Remove the front node and update front
		PCB data = front.data;
		front = front.next;

		// If front becomes null, update rear to null as well
		if (front == null) {
			rear = null;
		}

		return data;
	}

	// Method to check if the queue is empty
	public boolean isEmpty() {
		return front == null;
	}

	// Method to peek at the front element of the queue without removing it
	public PCB peek() {
		// If the queue is empty, return -1 (or throw an exception)
		if (isEmpty()) {
			System.out.print("");
			return null;
		}

		return front.data;
	}

	// Method to print the elements of the queue
	public void printQueue() {
		Node current = front;
		if (isEmpty()) {
			System.out.print("");
			return;
		}
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println();
	}

}