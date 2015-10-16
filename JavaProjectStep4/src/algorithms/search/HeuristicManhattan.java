package algorithms.search;

import general.Position;
import general.State;

// TODO: Auto-generated Javadoc
/**
 * The Class HeuristicManhattan.
 */
public class HeuristicManhattan implements Heuristic<Position> {

	/* (non-Javadoc)
	 * @see algorithms.search.Heuristic#estimation(algorithms.search.State, algorithms.search.State)
	 */
	@Override
	public double estimation(State<Position> current, State<Position> goal) {
		Position c = new Position(current.getState());
		Position g = new Position(goal.getState());
		double cost = Math.abs(c.getX()-g.getX())+Math.abs(c.getY()-g.getY())+Math.abs(c.getZ()-g.getZ());
		//System.out.println(c.getX()+","+g.getX()+","+c.getY()+","+g.getY()+","+c.getZ()+","+g.getZ());
		//System.out.println(cost);
		return cost;
	}

}
