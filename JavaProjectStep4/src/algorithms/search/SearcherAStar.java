package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import general.Solution;
import general.State;

// TODO: Auto-generated Javadoc
/**
 * The Class SearcherAStar.
 *
 * @param <T> the generic type
 */
public class SearcherAStar<T> extends CommonSearcher<T> implements Searcher<T> {

	/** The _heuristic. */
	private Heuristic<T> _heuristic;
	
	/**
	 * Instantiates a new searcher a star.
	 *
	 * @param _heuristic the _heuristic
	 */
	public SearcherAStar(Heuristic<T> _heuristic) {
		super();
		this._heuristic = _heuristic;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		if (s != null)
		{
		  addToOpenList(s.getStartState());
		  HashSet<State<T>> closedSet=new HashSet<State<T>>();

		  while(openList.size()>0){
		    State<T> n=popOpenList();// dequeue
		    closedSet.add(n);
		    if(n.equals(s.getGoalState()))
		      return backTrace(n, s.getStartState()); 
		      // private method, back traces through the parents
		    ArrayList<State<T>> successors=s.getAllPossibleStates(n); //however it is implemented 
		    for(State<T> state : successors)
		    {
		      if(!closedSet.contains(state) && !openListContains(state))
		      {
		        state.setCameFrom(n);
		        state.setCost(state.getCost()+_heuristic.estimation(state,s.getGoalState()));
		        addToOpenList(state);
		      } 
		      else
		      {
		    	  State<T> openState = getState(state);
		    	  if (openList.contains(state) && state.getCost()+_heuristic.estimation(state,s.getGoalState()) < openState.getCost())
		    	  { 
		    		  state.setCost(state.getCost()+_heuristic.estimation(state,s.getGoalState()));
		    		  openList.remove(state);
		    		  addToOpenList(state);
		    	  }
		    	  else
		    	  {
		    		  for (Iterator<State<T>> it = closedSet.iterator(); it.hasNext(); ) {
		    				State<T> f = it.next();
		    		        if (f.equals(state) && state.getCost()+_heuristic.estimation(state,s.getGoalState()) < f.getCost())
		    		            {
		    		        	state.setCost(state.getCost()+_heuristic.estimation(state,s.getGoalState()));
		    		        	closedSet.remove(state);
		    		        	openList.add(state);
		    		        	break;
		    		            }
		    	  }
		      }
		      }
		    }
		  }
		}
		  return null;
	}

  /**
   * Back trace.
   *
   * @param goal the goal
   * @param start the start
   * @return the solution
   */
  private Solution<T> backTrace(State<T> goal, State<T> start)
  {
  	Solution<T> sol = new Solution<T>();
  	sol._steps.add(goal);
  	State<T> current = new State<T>(goal.getCameFrom());
  	do{

  		sol._steps.add(current);
  		current = current.getCameFrom();
  	} while(!current.equals(start));
  	return sol;
  }

	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#getNumberOfNodesEvaluated()
	 */
	@Override
	public int getNumberOfNodesEvaluated() {
		// TODO Auto-generated method stub
		return evaluatedNodes;
	}

}
