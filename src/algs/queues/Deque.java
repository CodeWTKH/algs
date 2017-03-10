package algs.queues;

import java.util.NoSuchElementException;

public class Deque<Item> {
	private int size;
	private Node first = null;
	private Node last = null;
	private class Node<Item> {
		Item item;
		Node last;
		Node next;
	}
	public Deque() {
		// construct an empty deque
		size = 0;
	}
	public boolean isEmpty() {
		// is the deque empty?
		return this.size() == 0;
	}
	public int size() {
		// return the number of items on the deque
		return this.size;
	}
	public void addFirst(Item item) {
		// add the item to the front
		if(item == null)
			throw new NullPointerException();
		Node<Item> oldfirst = this.first;
		this.first = new Node();
		this.first.item = item;
		this.first.next = oldfirst;
		if(this.size == 0)
			this.last = this.first;
		this.size++;
	}
	public void addLast(Item item) {
		// add the item to the end
		if(item == null)
			throw new NullPointerException();
		Node<Item> oldLast = this.last;
		this.last = new Node();
		this.last.item = item;
		this.last.last = oldLast;
		if(this.size == 0)
			this.first = this.last;
		this.size++;
	}
	public Item removeFirst() {
		// remove and return the item from the front
		if(this.size == 0)
			throw new NoSuchElementException();
		Node<Item> oldFirst = this.first;
		this.first = this.first.next;
		return oldFirst.item;
	}
	public Item removeLast() {
		// remove and return the item from the end
		Node<Item> oldLast = this.last;
		this.last = this.last.last;
		return oldLast.item;
	}
//	public Iterator<Item> iterator() {
//		// return an iterator over items in order from front to end
//	}
	public static void main(String[] args) {
		// unit testing (optional)
	}
}
