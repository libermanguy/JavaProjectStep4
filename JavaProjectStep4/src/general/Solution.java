package general;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Solution.
 *
 * @param <T> the generic type
 */
public class Solution<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The _steps. */
	public ArrayList<State<T>> _steps;
	
	/**
	 * Instantiates a new solution.
	 */
	public Solution() 
	{
		this._steps = new ArrayList<State<T>>();
	}
	
	/**
	 * Instantiates a new solution.
	 *
	 * @param s the s
	 */
	public Solution(Solution<T> s) 
	{
		this._steps=s._steps;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Solution [Steps = " + _steps + "]";
	}
	
	/**
	 * Gets the _steps.
	 *
	 * @return the _steps
	 */
	public ArrayList<State<T>> get_steps() {
		 for(int i = 0, j = _steps.size() - 1; i < j; i++) {
			 _steps.add(i, _steps.remove(j));
		    }
		return _steps;
	}
	
	/**
	 * Sets the _steps.
	 *
	 * @param _steps the new _steps
	 */
	public void set_steps(ArrayList<State<T>> _steps) {
		this._steps = _steps;
	}
	

}
