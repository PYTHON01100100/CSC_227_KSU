
class Node {
	PCB data;
	Node next;

	public Node(PCB data) {
		this.data = data;
		this.next = null;
	}
}

public class PQueue {
	private Node front;

	public PQueue() {
		this.front = null;
	}

	public void enqueue(PCB pcb) {
		Node newNode = new Node(pcb);

		if (front == null || pcb.compareTo(front.data) < 0) {
			newNode.next = front;
			front = newNode;
		} else {
			Node current = front;

			while (current.next != null && pcb.compareTo(current.next.data) >= 0) {
				current = current.next;
			}

			newNode.next = current.next;
			current.next = newNode;
		}
	}

	public PCB dequeue() {
		if (isEmpty()) {
			throw new IllegalStateException("PriorityQueue is empty");
		}

		PCB removedData = front.data;
		front = front.next;
		return removedData;
	}

	public boolean isEmpty() {
		return front == null;
	}

	public void printQueue() {
		Node current = front;

		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}

		System.out.println();
	}

}