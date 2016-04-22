import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/* An ancestral path between two vertices v and w in a rooted DAG
 * is a directed path from v to a common ancestor x, 
 * together with a directed path from w to the same ancestor x. 
 * A shortest ancestral path is an ancestral path of minimum total length. 
 * We refer to the common ancestor in a shortest ancestral path as a shortest common ancestor. 
 * Note that a shortest common ancestor always exists because the root is an ancestor of every vertex.
 * Note also that an ancestral path is a path, but not a directed path.
 * */

public class ShortestCommonAncestor {
	// constructor takes a rooted DAG as argument
	public ShortestCommonAncestor(Digraph G) {

	}

	// length of shortest ancestral path between v and w
	public int length(int v, int w) {
		return 0;
	}

	// a shortest common ancestor of vertices v and w
	public int ancestor(int v, int w) {
		return 0;
	}

	// length of shortest ancestral path of vertex subsets A and B
	public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
		return 0;
	}

	// a shortest common ancestor of vertex subsets A and B
	public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
		return 0;
	}

	// do unit testing of this class
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sca.length(v, w);
			int ancestor = sca.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}
}
