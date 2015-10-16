package algorithms.search;
import java.util.Comparator;

import general.State;

// TODO: Auto-generated Javadoc
/**
 * The Class StateComperator.
 *
 * @param <T> the generic type
 */
public class StateComperator<T> implements Comparator<State<T>>
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(State<T> x, State<T> y)
	    {
	        return Double.compare(x.getCost(),y.getCost());
	    }
}