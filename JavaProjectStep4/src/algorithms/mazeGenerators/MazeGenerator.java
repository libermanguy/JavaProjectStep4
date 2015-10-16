package algorithms.mazeGenerators;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeGenerator.
 */
public abstract class MazeGenerator implements Maze3dGenerator {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#generate(int, int, int)
	 */
	@Override
	public abstract Maze3d generate(int x , int y , int z);
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#measureAlgorithmTime(int, int, int)
	 */
	@Override
	public String measureAlgorithmTime(int x , int y , int z) 
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
		long start = System.currentTimeMillis();
		this.generate(x,y,z);
		Date date = new Date(System.currentTimeMillis()-start);
		return sdf.format(date).toString();
		
	}

}
