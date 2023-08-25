/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int n; // size of the deque
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node before;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
        if (n == 1) {
            last = first;
        }
        else if (n == 2) {
            last = oldFirst;
            last.before = first;
        }
        else {
            oldFirst.before = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.before = oldLast;
        n++;
        if (n == 1) {
            first = last;
        }
        else if (n == 2) {
            first = oldLast;
            first.next = last;
        }
        else {
            oldLast.next = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("no such ele");
        Item it = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return it;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("no such ele");
        Item it = last.item;
        last = last.before;
        n--;
        if (isEmpty()) first = null;
        return it;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            if (item == null) throw new NoSuchElementException();
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Deque<String> dq = new Deque<>();
        // Map<Integer, Boolean> map = new HashMap<>();
        // System.out.println(map);
        // int i = 500;
        // while (i > 0) {
        //     double[] probabilities = { 0.6, 0.1, 0.1, 0.1, 0.1, 0.0, 0.0 };
        //     for (int k = 0; k < probabilities.length; k++) {
        //         map.put(k, StdRandom.bernoulli(probabilities[k]));
        //     }
        //     System.out.println(i);
        //     int j = 0;
        //     if (map.get(j++)) {
        //         System.out.println("addFirst");
        //         dq.addFirst("addFirst");
        //     }
        //     if (map.get(j++)) {
        //         System.out.println("addLast");
        //         dq.addLast("addLast");
        //     }
        //     if (map.get(j++)) {
        //         System.out.println("removeFirst");
        //         dq.removeFirst();
        //     }
        //     if (map.get(j++)) {
        //         System.out.println("removeLast");
        //         dq.removeLast();
        //     }
        //     if (map.get(j++)) {
        //         System.out.println("isEmpty");
        //         dq.isEmpty();
        //     }
        //     if (map.get(j++)) {
        //         System.out.println("size");
        //         dq.size();
        //     }
        //     if (map.get(j++)) {
        //         System.out.println("iterator");
        //         dq.iterator();
        //     }
        //     i--;
        // }
    }

}
