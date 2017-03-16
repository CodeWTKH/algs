package algs.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
	private int size;
	private Node first = null;
	private Node last = null;
	private class Node {
		Item item;
		Node lastNode;
		Node nextNode;
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
		if(item == null) throw new NullPointerException();
		Node oldfirst = this.first;
		this.first = new Node();
		this.first.item = item;
		this.first.nextNode = oldfirst;
		if(this.size == 0)
			this.last = this.first;
		else
			oldfirst.lastNode = this.first;
		this.size++;
	}
	public void addLast(Item item) {
		// add the item to the end
		if(item == null) throw new NullPointerException();
		Node oldLast = this.last;
		this.last = new Node();
		this.last.item = item;
		this.last.lastNode = oldLast;
		if(this.size == 0)
			this.first = this.last;
		else
			oldLast.nextNode = this.last;
		this.size++;
	}
	public Item removeFirst() {
		// remove and return the item from the front
		if(this.size == 0) throw new NoSuchElementException();
		Node oldFirst = this.first;
		this.first = this.first.nextNode;
		this.size--;
		return oldFirst.item;
	}
	public Item removeLast() {
		// remove and return the item from the end
		if(this.size == 0) throw new NoSuchElementException();
		Node oldLast = this.last;
		this.last = this.last.lastNode;
		this.size--;
		return oldLast.item;
	}
	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new DequeIterator();
	}
	private class DequeIterator implements Iterator<Item>{
		Node node = first;
		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Node thisNode = node;
			node = node.nextNode;
			return thisNode.item;
		}
		
	}
	public static void main(String[] args) {
		// unit testing (optional)
		Deque<String> deque = new Deque<>();
		deque.addFirst("s");
		System.out.println(deque.removeLast());
		System.out.println(deque.size());
		deque.addFirst("a");
		deque.addLast("b");
		for(String a:deque) {
			System.out.println(a);
		}
	}
}
