package view;

import java.util.HashMap;

import general.Position;
import general.Solution;
import general.State;

// TODO: Auto-generated Javadoc
/**
 * The Interface View.
 */
public interface View {
	
	/**
	 * Display.
	 *
	 * @param arg the arg
	 */
	public void display(Object[] arg);
	
	/**
	 * Dir.
	 *
	 * @param path the path
	 */
	public void Dir(String path);
	
	/**
	 * Display maze.
	 *
	 * @param obj the obj
	 */
	public void displayMaze(Object[] obj);
	
	/**
	 * Display cross section.
	 *
	 * @param array the array
	 */
	public void displayCrossSection(Object[] array);
	
	/**
	 * Display maze size.
	 *
	 * @param size the size
	 */
	public void displayMazeSize(int size);
	
	/**
	 * Display file size.
	 *
	 * @param size the size
	 */
	public void displayFileSize(int size);
	
	/**
	 * Display solution.
	 *
	 * @param solution the solution
	 */
	public void displaySolution(Solution<Position> solution);
	
	/**
	 * Display str.
	 *
	 * @param arg the arg
	 */
	public void displayStr(String arg);

	public void setCLI(HashMap<String, Integer> commands);

	public void displayNextStep(State<Position> state);

	
}
