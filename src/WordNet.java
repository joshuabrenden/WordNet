import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class WordNet {

    private final Map<Integer, String> synsetIds;
    private final Map<String, Set<Integer>> nounIds;
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		synsetIds = new HashMap<Integer,String>();
		nounIds = new HashMap<String, Set<Integer>>();
		
		
		readSynsets(synsets);
	}

	// all WordNet nouns
	public Iterable<String> nouns() {
		return null;
	}

	/*
	 * Is the word a WordNet noun? Should run in time logarithmic (or better) in
	 * the number of nouns.
	 */
	public boolean isNoun(String word) {
		return true;
	}

	/*
	 * A synset (second field of synsets.txt) that is a shortest common ancestor
	 * of noun1 and noun2. should make exactly one call to the length() and
	 * ancestor().
	 */
	public String sca(String noun1, String noun2) {
		return null;
	}

	/*
	 * distance between noun1 and noun2. should make exactly one call to the
	 * length().
	 */
	public int distance(String noun1, String noun2) {
		return 0;
	}

	public Digraph readHypernyms(String hypernyms) {

		return null;
	}

	private int readSynsets(String synsets) {
		In synsetsFile = new In(synsets);
		
		//Might be over-complicating this.
		List<String> allLines = new ArrayList<String>(Arrays.asList(synsetsFile.readAllLines()));

		allLines.stream() //
				.forEach(line -> synsetIds.put(Integer.valueOf(line.split(",")[0]), line.split(",")[1]));
		
		for(int i = 0; i < synsetIds.size(); i++){
			System.out.println(synsetIds.get(i).toString());
		}
		
		return 0;
	}

	// do unit testing of this class
	public static void main(String[] args) {
		WordNet wordNet = new WordNet(args[0], args[1]);
	}
}
