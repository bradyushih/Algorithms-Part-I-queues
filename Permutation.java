/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        // StdOut.println(size);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String a = StdIn.readString();
            // System.out.println(a);
            rq.enqueue(a);
        }
        while (size > 0) {
            String a = rq.dequeue();
            StdOut.println(a);
            size--;
        }
    }
}
