import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
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
	private final Digraph digraph;

	// constructor takes a rooted DAG as argument
	public ShortestCommonAncestor(Digraph G) {
		digraph = G;
	}

	// a shortest common ancestor of vertices v and w
	public int ancestor(int v, int w) {
		return getAncestor(getBFDP(v), getBFDP(w));
	}

	// a shortest common ancestor of vertex subsets A and B
	public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
		return getAncestor(getBFDP(subsetA), getBFDP(subsetB));
	}

	private int getAncestor(BreadthFirstDirectedPaths v, BreadthFirstDirectedPaths w) {
		int shortestCommonAncestor = -1;
		int minimumDistance = Integer.MAX_VALUE;
		List<Integer> allAncestors = new ArrayList<>();

		for (int i = 0; i < digraph.V(); i++) {
			if (v.hasPathTo(i) && w.hasPathTo(i)) {
				allAncestors.add(i);
			}
		}

		for (Integer ancestor : allAncestors) {
			int distance = v.distTo(ancestor) + w.distTo(ancestor);
			if (distance < minimumDistance) {
				minimumDistance = distance;
				shortestCommonAncestor = ancestor;
			}
		}

		return shortestCommonAncestor;
	}

	// length of shortest ancestral path between v and w
	public int length(int v, int w) {
		return getLength(getBFDP(v), getBFDP(w));
	}

	// length of shortest ancestral path of vertex subsets A and B
	public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
		return getLength(getBFDP(subsetA), getBFDP(subsetB));
	}

	private int getLength(BreadthFirstDirectedPaths v, BreadthFirstDirectedPaths w) {
		int length = -1;
		int minimumDistance = Integer.MAX_VALUE;

		List<Integer> allAncestors = new ArrayList<>();

		for (int i = 0; i < digraph.V(); i++) {
			if (v.hasPathTo(i) && w.hasPathTo(i)) {
				allAncestors.add(i);
			}
		}

		for (Integer ancestor : allAncestors) {
			int distance = v.distTo(ancestor) + w.distTo(ancestor);
			if (distance < minimumDistance) {
				minimumDistance = distance;
			}
		}

		if (Integer.MAX_VALUE == minimumDistance) {
			length = -1;
		} else {
			length = minimumDistance;
		}
		return length;
	}

	private BreadthFirstDirectedPaths getBFDP(int value) {
		return new BreadthFirstDirectedPaths(digraph, value);
	}

	private BreadthFirstDirectedPaths getBFDP(Iterable<Integer> value) {
		return new BreadthFirstDirectedPaths(digraph, value);
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
