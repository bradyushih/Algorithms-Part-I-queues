/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 1;
    private Item[] randomizedQueue;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        randomizedQueue = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        if (n == randomizedQueue.length) {
            resize(randomizedQueue.length * 2);
        }
        randomizedQueue[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("No element");
        int randomIndex = StdRandom.uniformInt(n);
        Item it = randomizedQueue[randomIndex];

        randomizedQueue[randomIndex] = randomizedQueue[n - 1];
        randomizedQueue[n - 1] = null;
        n--;
        if (n > 0 && n == randomizedQueue.length / 4)
            resize(randomizedQueue.length / 2);
        return it;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("No element");
        return randomizedQueue[StdRandom.uniformInt(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int ci = 0;
        for (int i = 0; i < n; i++) {
            if (randomizedQueue[i] != null) {
                copy[ci++] = randomizedQueue[i];
            }
        }
        randomizedQueue = copy;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;

        public RandomizedQueueIterator() {
            i = n - 1;
            StdRandom.shuffle(randomizedQueue);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item it = randomizedQueue[i--];
            if (it == null) throw new NoSuchElementException();
            return it;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 8;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }

}
