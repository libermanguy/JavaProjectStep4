package algorithms.search;

import general.Solution;

// TODO: Auto-generated Javadoc
/**
 * The Interface Searcher.
 *
 * @param <T> the generic type
 */
public interface Searcher<T>
{
    
    /**
     * Search.
     *
     * @param s the s
     * @return the solution
     */
    public Solution<T> search(Searchable<T> s);
    
    /**
     * Gets the number of nodes evaluated.
     *
     * @return the number of nodes evaluated
     */
    public int getNumberOfNodesEvaluated();
    
}
