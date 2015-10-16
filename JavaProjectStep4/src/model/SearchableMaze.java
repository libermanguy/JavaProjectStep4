package model;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.CommonSearchable;
import general.Position;
import general.State;

// TODO: Auto-generated Javadoc
/**
 * 
 *  * <h1>SearchableMaze</h1>
 * The Maze Demo Class
 * <p>
 * Runs All Searchers For Test
 *
 * @author  Guy Liberman
 * @version 1.0
 * @since   2015-08-22
 */
public class SearchableMaze extends CommonSearchable<Position> {

	/** The _new maze. */
	Maze3d _newMaze;
	
	/**
	 * Instantiates a new searchable maze.
	 *
	 * @param _newMaze the _new maze
	 */
	public SearchableMaze(Maze3d _newMaze) {
		super(new State<Position>(_newMaze.getStartPosition()),new State<Position>(_newMaze.getGoalPosition()));
		this._newMaze = _newMaze;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearchable#getAllPossibleStates(algorithms.search.State)
	 */
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		ArrayList<State<Position>> posStates = new ArrayList<State<Position>>();
		String moves[] = _newMaze.getPossibleMoves(s.getState());
		for(String move : moves){
				posStates.add(new State<Position>(new Position(move),s.getCost()+1,s));
		}
		return posStates;

	}
}
