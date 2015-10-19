package general;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class State.
 *
 * @param <T> the generic type
 */
public class State<T> implements Comparable<State<T>>,Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The state. */
    private T state;    // the state represented by a string
    
    /** The cost. */
    private double cost;     // cost to reach this state
    
    /** The came from. */
    private State<T> cameFrom;  // the state we came from to this state

    /**
     * Instantiates a new state.
     *
     * @param state the state
     * @param cost the cost
     * @param cameFrom the came from
     */
    public State(T state, double cost, State<T> cameFrom) {
		super();
		this.state = state;
		this.cost = cost;
		this.cameFrom = cameFrom;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() 
    {
        return  state.hashCode();
    }
    
	/**
	 * Instantiates a new state.
	 *
	 * @param state the state
	 */
	public State(T state){    // CTOR    
        this.state = state;
        this.cost = 0;
        this.cameFrom = null;
    }
	
	/**
	 * Instantiates a new state.
	 *
	 * @param s the s
	 */
	public State(State<T> s) {
		this.state = s.state;
		this.cost = s.cost;
		this.cameFrom = s.cameFrom;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public T getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(T state) {
		this.state = state;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Gets the came from.
	 *
	 * @return the came from
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}

	/**
	 * Sets the came from.
	 *
	 * @param cameFrom the new came from
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj){ // we override Object's equals method
        return state.equals(((State<T>)obj).state);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "State [state=" + state + ", cost=" + cost + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(State<T> obj) {
		if (obj.state == state)
				return 0;
		else
			return 1;
	} 
}