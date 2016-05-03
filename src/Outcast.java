import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
	private final WordNet wordnet;
	
	// constructor takes a WordNet object
	public Outcast(WordNet wordnet){
		this.wordnet = wordnet;
	}         
	
	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns){
		String outcast = null;
		if(nouns == null){
			throw new NullPointerException("Noun array is null.");
		} else {
			outcast = getOutcast(nouns);
		}
		
		return outcast;
	}
	
	private String getOutcast(String[] nouns){
		String outcast =null;
		int maximum = 0;
		
		for(String noun1 : nouns){
			int distance = 0;
			for(String noun2: nouns){
				if(noun1 != noun2){
					distance +=getDistance(noun1, noun2);
				}
			}
		}
		
		return outcast;
	}
	
	private int getDistance(String noun1, String noun2){
		int distance = 0;
		if(noun1 != null && noun2 != null){
			distance = wordnet.distance(noun1, noun2);
		}
		return distance;
	}
	
	// see test client below
	public static void main(String[] args){
		WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}  
}
