/******************************************************************************
 *  Name: Josh Brenden   
 *  NetID: BrendenJ     
 ******************************************************************************/


/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 *****************************************************************************/

I chose to store the information in synsets.txt in two HashMap data structures.
One HashMap, synsetIds, has an integer id and a string. The other HashMap, nounIds has
an string which is the synset and a set of other noins which are related to the synset.
I made this choice because a HashMap is an efficient data structure to add lots of
synsets to and it is also efficient when performing lookups. 

/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 *****************************************************************************/

I chose to store the information in hypernyms.txt in a Digraph data structure.
This data structure has many built in methods, such as the addEdge function,
making it simple to add an edge with an id and a hypernym. The Digraph class is 
well suited for working with the hypernyms.

/******************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithms as a function of the number of vertices V and the
 *  number of edges E in the digraph?
 *****************************************************************************/

Description:

The SCA constructor is passed a Digraph object as an argument, which is the digraph
used in the SCA class. Next each vertex in the graph is iterated though, and ones with
empty adjacency lists are added. Once a vertex is added, it becomes the root. If there 
are multiple roots, an exception is thrown. If we don't find any vertexes with an empty 
adjacency list, then the graph is not acyclic and an exception is also thrown. Using the
DirectedCycle class for detecting cycles, we check for cycles in he digraph.

Order of growth of running time:


/******************************************************************************
 *  Describe concisely your algorithm to compute the shortest common
 *  ancestor in ShortestCommonAncestor. What is the order of growth of
 *  the running time of your methods as a function of the number of
 *  vertices V and the number of edges E in the digraph? What is the
 *  order of growth of the best-case running time?
 *
 *  If you use hashing, you should assume the uniform hashing assumption
 *  so that put() and get() take constant time.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't
 *  forget to count the time needed to initialize the marked[],
 *  edgeTo[], and distTo[] arrays.
 *****************************************************************************/

Description:

The algorithm for computing the shortest common ancestor is done by receiving the 
two nouns, v and w and iterates through the paths where it finds common ancestors.
After this the paths are compared and the shortest one is used. If they are equal
then the higher value is used.