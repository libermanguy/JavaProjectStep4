package algorithms.mazeGenerators;

import java.util.Random;

import general.Position;

import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * The Class MyMaze3dGenerator.
 */
public class MyMaze3dGenerator extends MazeGenerator {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.MazeGenerator#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int x, int y, int z) 
	{
		//identify when to define the end of the maze
		boolean endefined = false;
		Maze3d newMaze = new Maze3d(x,y,z);
		//size of cells only (not walls)
		int size = 0; 						
		//unvisited cells
		ArrayList<Position> unVisitedCells = new ArrayList<Position>(); 	
		//create a wall grid
		for (int i = 0; i < newMaze.size(); i++)
		{
			Position p = newMaze.getPositionByID(i);
			if (p.getX() % 2 == 0 && p.getY() % 2 == 0 && p.getZ() % 2 == 0)
			{
				newMaze.setStatus(p, 0);
				size++;
				unVisitedCells.add(p);
			}
			else
				newMaze.setStatus(p, 1);
		}
		
		Random rn = new Random();
		newMaze.setStartPosition(new Position(0,rn.nextInt(y/2)*2,rn.nextInt(z/2)*2)); 
		Position current = newMaze.getStartPosition();
		//visited cells
		ArrayList<Position> visitedCells = new ArrayList<Position>();
		//Stack for back trace
		ArrayList<Position> cellStack = new ArrayList<Position>();
		visitedCells.add(current);
		unVisitedCells.remove(current);
			
		while(visitedCells.size() < size)
		{
			ArrayList<Position> unVisitedNeighbours = getUnvisitedNeighbours(x, y, z, current, visitedCells);
			if (!unVisitedNeighbours.isEmpty())
			{
				int chosenID = rn.nextInt(unVisitedNeighbours.size());
				cellStack.add(current);
				//Making the wall between them a path
				newMaze.setStatus(newMaze.getPositionByID((newMaze.getPositionID(current)+newMaze.getPositionID(unVisitedNeighbours.get(chosenID)))/2), 0);
				current = unVisitedNeighbours.get(chosenID);
				newMaze.setStatus(current, 0);
				visitedCells.add(current);
				unVisitedCells.remove(current);
			}
			else if (!cellStack.isEmpty())
			{
				//Defining the end if not already defined
				if (!endefined)
				{
					endefined=true;
					newMaze.setGoalPosition(current);
				}
				current = cellStack.get(cellStack.size()-1);
				cellStack.remove(cellStack.size()-1);
			}
			else
			{
				current = unVisitedCells.get(rn.nextInt(unVisitedCells.size()));
	    		unVisitedCells.remove(current);
	    		visitedCells.add(current);
			}
		}
		if (!endefined)
		{
			endefined=true;
			newMaze.setGoalPosition(current);
		}
		return newMaze;
	}
	
	/**
	 * Gets the unvisited neighbours.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @param p the p
	 * @param visitedgrid the visitedgrid
	 * @return the unvisited neighbours
	 */
	//Get all unvisited neighbours for a specific point - neighbour - difference of 2 cells in one of the dimensions
	public ArrayList<Position> getUnvisitedNeighbours(int x,int y,int z,final Position p,final ArrayList<Position> visitedgrid)
	{
		ArrayList<Position> positions = new ArrayList<Position>();
		if (p.getX() > 1 && !visitedgrid.contains(new Position(p.getX()-2,p.getY(),p.getZ())))
			positions.add(new Position(p.getX()-2,p.getY(),p.getZ()));
		if (p.getX() < x-2 && !visitedgrid.contains(new Position(p.getX()+2,p.getY(),p.getZ())))
			positions.add(new Position(p.getX()+2,p.getY(),p.getZ()));
		if (p.getY() > 1 && !visitedgrid.contains(new Position(p.getX(),p.getY()-2,p.getZ())))
			positions.add(new Position(p.getX(),p.getY()-2,p.getZ()));
		if (p.getY() < y-2 && !visitedgrid.contains(new Position(p.getX(),p.getY()+2,p.getZ())))
			positions.add(new Position(p.getX(),p.getY()+2,p.getZ()));
		if (p.getZ() > 1 && !visitedgrid.contains(new Position(p.getX(),p.getY(),p.getZ()-2)))
			positions.add(new Position(p.getX(),p.getY(),p.getZ()-2));
		if (p.getZ() < z-2 && !visitedgrid.contains(new Position(p.getX(),p.getY(),p.getZ()+2)))
			positions.add(new Position(p.getX(),p.getY(),p.getZ()+2));
		return positions;
		}

}

       