import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {

	private final Map<Integer, String> synsetIds;
	private final Map<String, Set<Integer>> nounIds;
	private final Digraph digraph;
	private final ShortestCommonAncestor shortestCommonAncestor;

	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {

		if (synsets == null || hypernyms == null) {
			throw new NullPointerException("Error reading input.");
		}

		synsetIds = new HashMap<Integer, String>();
		nounIds = new HashMap<String, Set<Integer>>();

		int synsetCount = readSynsets(synsets);
		digraph = readHypernym(hypernyms, synsetCount);

		DirectedCycle directedCycle = new DirectedCycle(digraph);
		if (directedCycle.hasCycle()) {
			throw new IllegalArgumentException("Cycle Detected.");
		}

		shortestCommonAncestor = new ShortestCommonAncestor(digraph);
	}

	// all WordNet nouns
	public Iterable<String> nouns() {
		return nounIds.keySet();
	}

	/*
	 * Is the word a WordNet noun? Should run in time logarithmic (or better) in
	 * the number of nouns.
	 */
	public boolean isNoun(String word) {
		return word == null ? false : nounIds.containsKey(word);
	}

	/*
	 * A synset (second field of synsets.txt) that is a shortest common ancestor
	 * of noun1 and noun2. should make exactly one call to the length() and
	 * ancestor().
	 */
	public String sca(String noun1, String noun2) {
		return synsetIds.get(shortestCommonAncestor.ancestor(nounIds.get(noun1), nounIds.get(noun2)));
	}

	/*
	 * distance between noun1 and noun2. should make exactly one call to the
	 * length().
	 */
	public int distance(String noun1, String noun2) {
		return shortestCommonAncestor.length(nounIds.get(noun1), nounIds.get(noun2));
	}

	//Reads the synsets.txt file and adds it to the map
	private int readSynsets(String synsets) {
		In synsetsFile = new In(synsets);
		int count = 0;

		while (synsetsFile.hasNextLine()) {
			String[] line = synsetsFile.readLine().split(",");
			Integer id = Integer.valueOf(line[0]);
			String synset = line[1];
			synsetIds.put(id, synset);

			String[] synsetNouns = synset.split(",");
			for (String synsetNoun : synsetNouns) {
				Set<Integer> ids = nounIds.get(synsetNoun);
				if (ids == null) {
					ids = new HashSet<>();
				}

				ids.add(id);
				nounIds.put(synsetNoun, ids);
				count++;
			}
		}
		
		return count;
	}
	
	//Reads the hypernyms.txt file and adds data to the digraph
	private Digraph readHypernym(String hypernyms, int count) {
		In hypernymsFile = new In(hypernyms);
		Digraph digraph = new Digraph(count);

		while (hypernymsFile.hasNextLine()) {
			String[] line = hypernymsFile.readLine().split(",");
			Integer id = Integer.valueOf(line[0]);

			for (int i = 1; i < line.length; i++) {
				int hypernym = Integer.valueOf(line[i]);
				digraph.addEdge(id, hypernym);
			}
		}

		return digraph;
	}

	// do unit testing of this class
	public static void main(String[] args) {
		WordNet wordNet = new WordNet(args[0], args[1]);

		In file = new In(args[0]);
		List<String> nouns1 = new ArrayList<String>();
		List<String> nouns2 = new ArrayList<String>();

		while (file.hasNextLine()) {
			String[] line = file.readLine().split(",");
			String noun = line[1];
			nouns1.add(noun);
			nouns2.add(noun);
		}
		
		//Iterates through every synset and compares with a random other synset.
		long startTime = System.currentTimeMillis();
		int count = 0;
		for (String n1 : nouns1) {
			int randIndex = ThreadLocalRandom.current().nextInt(1, nouns1.size());
			String n2 = nouns2.get(randIndex);

			int distance = wordNet.distance(n1, n2);
			String ancestor = wordNet.sca(n1, n2);

			System.out.println("Distance from " + n1 + " to " + n2 + ": " + distance);
			System.out.println("SCA of " + n1 + " and " + n2 + ": " + ancestor);
			count++;
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(count + " computations in " + totalTime + "ms.");

	}
}
