package algs.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int size;
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		// construct an empty randomized queue
		this.items = (Item[]) new Object[2];
	}
	private void resize(int newSize) {
		@SuppressWarnings("unchecked")
		Item[] newItems = (Item[]) new Object[newSize];
		int index = 0;
		for (Item item: items) {
			if (item == null) continue;
			newItems[index++] = item;
		}
		items = newItems;
	}
	public boolean isEmpty() {
		// is the queue empty?
		return this.size == 0;
	}
	public int size() {
		// return the number of items on the queue
		return this.size;
	}
	public void enqueue(Item item) {
		// add the item
		if (item == null) throw new NullPointerException();
		items[size++] = item;
		if (items.length == size) resize(2 * items.length);
	}
	public Item dequeue() {
		// remove and return a random item
		if (this.size == 0) throw new NoSuchElementException();
		int n = StdRandom.uniform(size);
		Item item = items[n];
		items[n] = items[size - 1];
		items[--size] = null;
		if (size > 0 && items.length >= 4 * size)
			resize(items.length / 2);
		return item;
	}
	public Item sample() {
		// return (but do not remove) a random item
		if (this.size == 0) throw new NoSuchElementException();
		int n = StdRandom.uniform(size);
		return items[n];
	}
	private class QueueIterator implements Iterator<Item> {
		private int iterSize = size;
		private int[] seq = StdRandom.permutation(size);
		@Override
		public boolean hasNext() {
			return iterSize != 0;
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return items[seq[--size]];
		}
		
	}
	@Override
	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new QueueIterator();
	}
	public static void main(String[] args) {
		// unit testing (optional)
	}
}
