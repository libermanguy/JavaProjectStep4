	package model;
import general.Position;
import general.Solution;

// TODO: Auto-generated Javadoc
/**
 * The Interface Model.
 */
public interface Model {
	
	/**
	 * Generate.
	 *
	 * @param name the name
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public void generate(String name,int x,int y,int z);
	
	/**
	 * Display.
	 *
	 * @param name the name
	 * @return the object[]
	 * @throws Exception 
	 */
	public Object[] display(String name) throws Exception;
	
	/**
	 * Gets the cross section.
	 *
	 * @param name the name
	 * @param dim the dim
	 * @param index the index
	 * @return the cross section
	 * @throws Exception 
	 */
	public Object getCrossSection(String name,char dim,int index) throws Exception;
	
	/**
	 * Save.
	 *
	 * @param name the name
	 * @param path the path
	 * @throws Exception the exception
	 */
	public void save(String name, String path) throws Exception;
	
	/**
	 * Load.
	 *
	 * @param name the name
	 * @param path the path
	 * @throws Exception the exception
	 */
	public void load(String name,String path) throws Exception;
	
	/**
	 * Maze size.
	 *
	 * @param name the name
	 * @return the int
	 * @throws Exception 
	 */
	public int mazeSize(String name) throws Exception;
	
	/**
	 * File size.
	 *
	 * @param name the name
	 * @return the int
	 */
	public int fileSize(String name);
	
	/**
	 * Solve.
	 *
	 * @param name the name
	 * @param alg the alg
	 */
	public void solve(String name,String alg);
	
	/**
	 * Display solution.
	 *
	 * @param name the name
	 * @return the solution
	 * @throws Exception 
	 */
	public Solution<Position> displaySolution(String name) throws Exception;
	
	/**
	 * Exit.
	 */
	
	public void setStartPosition(String name, Position newpos) throws Exception;
	public void exit();
	
	public void setProperties(String file) throws Exception;
}
