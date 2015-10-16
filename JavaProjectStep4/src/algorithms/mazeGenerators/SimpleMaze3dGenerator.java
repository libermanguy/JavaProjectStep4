package algorithms.mazeGenerators;

import java.util.Random;

import general.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleMaze3dGenerator.
 */
public class SimpleMaze3dGenerator extends MazeGenerator {

	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.MazeGenerator#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int x , int y , int z)
	{
		Maze3d newMaze = new Maze3d(x,y,z);
		
		//Fill maze with walls
		for (int[][] dimension: newMaze._maze3d)
			for (int[] row: dimension)
				java.util.Arrays.fill(row, 1);
		
		//Creates start & end
		Random rn = new Random();
		newMaze.setStartPosition(new Position(0,rn.nextInt(y),rn.nextInt(z)));
		newMaze.setGoalPosition(new Position(x-1,rn.nextInt(y),rn.nextInt(z)));		
		
		//Mark 50% off the maze as path
		for (int i = 1; i < newMaze.size()/2; i++)
		{
			newMaze.setStatus(newMaze.getPositionByID(rn.nextInt(i)*2), 0);
		}
		
		//Build path
		Position p = new Position(newMaze.getStartPosition());
		int dim;
		while (!p.equals(newMaze.getGoalPosition()))
		{
			newMaze.setStatus(p,0);
			dim = rn.nextInt(3)+1;
			switch (dim) {
            case 1:  
            	if (p.getX() != newMaze.getGoalPosition().getX())
            		if(p.getX() > newMaze.getGoalPosition().getX())
            			p.setX(p.getX()-1);
            		else
            			p.setX(p.getX()+1);
                break;
            case 2: 
            	if (p.getY() != newMaze.getGoalPosition().getY())
            		if(p.getY() > newMaze.getGoalPosition().getY())
            			p.setY(p.getY()-1);
            		else
            			p.setY(p.getY()+1);
                     break;
            case 3: 
            	if (p.getZ() != newMaze.getGoalPosition().getZ())
            		if(p.getZ() > newMaze.getGoalPosition().getZ())
            			p.setZ(p.getZ()-1);
            		else
            			p.setZ(p.getZ()+1);
                     break;
					}
		}
		newMaze.setStatus(newMaze.getGoalPosition(),0);
		return newMaze;
	}
}
