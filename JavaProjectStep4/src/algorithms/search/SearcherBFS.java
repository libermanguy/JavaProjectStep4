package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import general.Solution;
import general.State;

// TODO: Auto-generated Javadoc
/**
 * The Class SearcherBFS.
 *
 * @param <T> the generic type
 */
public class SearcherBFS<T> extends CommonSearcher<T>{

	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
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
		    	  if(closedSet.contains(state))
		    			  System.out.println(closedSet);
		        state.setCameFrom(n);
		        addToOpenList(state);
		      } 
		      else
		      {
		    	  State<T> openState = getState(state);
		    	  if (openList.contains(state) && state.getCost() < openState.getCost())
		    	  { 
		    		  openList.remove(state);
		    		  addToOpenList(state);
		    	  }
		    	  else
		    	  {
		    		  for (Iterator<State<T>> it = closedSet.iterator(); it.hasNext(); ) {
		    				State<T> f = it.next();
		    		        if (f.equals(state) && state.getCost() < f.getCost())
		    		            {
		    		        	closedSet.remove(state);
		    		        	openList.add(state);
		    		        	break;
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
