package algorithms.search;

import general.State;

// TODO: Auto-generated Javadoc
/**
 * The Interface Heuristic.
 *
 * @param <T> the generic type
 */
public interface Heuristic<T> {
	
	/**
	 * Estimation.
	 *
	 * @param current the current
	 * @param goal the goal
	 * @return the double
	 */
	double estimation(State<T> current,State<T> goal);
}
