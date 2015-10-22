package algorithms.mazeGenerators;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import general.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class Maze3d.
 */
public class Maze3d implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The _maze3d. */
	int[][][] _maze3d;
	
	/** The _start. */
	Position _start;
	
	/** The _end. */
	Position _end;

	/**
	 * Instantiates a new maze3d.
	 *
	 * @param x_length the x_length
	 * @param y_length the y_length
	 * @param z_length the z_length
	 */
	public Maze3d( int x_length , int y_length , int z_length ) 
	{
		this._maze3d = new int[x_length][y_length][z_length];
	}
	
	
	/**
	 * Instantiates a new maze3d.
	 *
	 * @param b the b
	 */
	public Maze3d(byte[] b) 
	{
		this._maze3d = new int[b[6]][b[7]][b[8]];
		setStartPosition(new Position(b[0],b[1],b[2]));
		setGoalPosition(new Position(b[3],b[4],b[5]));
		int current = 9;
	       for (int i = 0 ; i < getXLength() ; i++)
	    	   for (int j = 0 ; j < getYLength() ; j++)
	    		   for (int k = 0 ; k < getZLength() ; k++)
	    			   _maze3d[i][j][k] = b[current++];
	}
	
	/**
	 * Pos in maze.
	 *
	 * @param p the p
	 * @return true, if successful
	 */
	public boolean posInMaze(Position p)
	{
		return (p.getX() > -1 && p.getY() > -1 && p.getZ() > -1 && p.getX() < getXLength() && p.getY() < getYLength() && p.getZ() < getZLength());
	}
	
	/**
	 * Sets the start position.
	 *
	 * @param p the new start position
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public void setStartPosition(Position p) throws IndexOutOfBoundsException
	{
		if (!posInMaze(p))
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		_start=p;
	}
	
	/**
	 * Gets the start position.
	 *
	 * @return the start position
	 */
	public Position getStartPosition()
	{
		return _start;
	}	
	
	/**
	 * Gets the _maze3d.
	 *
	 * @return the _maze3d
	 */
	public int[][][] get_maze3d() {
		return _maze3d;
	}


	/**
	 * Gets the next positions.
	 *
	 * @param p the p
	 * @return the next positions
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public ArrayList<Position> getNextPositions(Position p) throws IndexOutOfBoundsException
	{
		if (!posInMaze(p))
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		ArrayList<Position> positions = new ArrayList<Position>();
		if (p.getX() > 0)
			positions.add(new Position(p.getX()-1,p.getY(),p.getZ()));
		if (p.getX() < this.getXLength()-1)
			positions.add(new Position(p.getX()+1,p.getY(),p.getZ()));
		if (p.getY() > 0)
			positions.add(new Position(p.getX(),p.getY()-1,p.getZ()));
		if (p.getY() < this.getYLength()-1)
			positions.add(new Position(p.getX(),p.getY()+1,p.getZ()));
		if (p.getZ() > 0)
			positions.add(new Position(p.getX(),p.getY(),p.getZ()-1));
		if (p.getZ() < this.getZLength()-1)
			positions.add(new Position(p.getX(),p.getY(),p.getZ()+1));
		return positions;
	}
	
	/**
	 * Gets the possible moves.
	 *
	 * @param p the p
	 * @return the possible moves
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public String[] getPossibleMoves(Position p) throws IndexOutOfBoundsException
	{
		if (!posInMaze(p))
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		ArrayList<String> moves = new ArrayList<String>();
		ArrayList<Position> positions = this.getNextPositions(p);
		for(Position item : positions)
		{
			if (getStatus(item) == 0)
				moves.add(item.toString());
		}		
		return (moves.toArray(new String[moves.size()]));
	}
	
	/**
	 * Gets the position id.
	 *
	 * @param p the p
	 * @return the position id
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int getPositionID(Position p) throws IndexOutOfBoundsException
	{
		if (!posInMaze(p))
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
	return (p.getX()*this.getYLength()*this.getZLength()+p.getY()*this.getZLength()+p.getZ());
	}
	
	/**
	 * Gets the position by id.
	 *
	 * @param index the index
	 * @return the position by id
	 */
	public Position getPositionByID(int index)
	{
	return new Position(index / (this.getYLength()*this.getZLength()),index % (this.getYLength()*this.getZLength()) / this.getZLength(),index % (this.getYLength()*this.getZLength()) % this.getZLength());
	}
	
	/**
	 * Sets the goal position.
	 *
	 * @param p the new goal position
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public void setGoalPosition(Position p) throws IndexOutOfBoundsException
	{
		if (!posInMaze(p))
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		_end=p;
	}
	
	/**
	 * Gets the goal position.
	 *
	 * @return the goal position
	 */
	public Position getGoalPosition()
	{
		return _end;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param p the p
	 * @param status the status
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public void setStatus(Position p,int status)throws IndexOutOfBoundsException
	{
		if (!posInMaze(p))
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		_maze3d[p.getX()][p.getY()][p.getZ()] = status;
	}
	
	/**
	 * Gets the status.
	 *
	 * @param p the p
	 * @return the status
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int getStatus(Position p) throws IndexOutOfBoundsException
	{
		if (!posInMaze(p))
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		return _maze3d[p.getX()][p.getY()][p.getZ()];
	}
	
	/**
	 * Gets the cross section by x.
	 *
	 * @param x the x
	 * @return the cross section by x
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByX(int x) throws IndexOutOfBoundsException
	{
		if ( x > this._maze3d.length-1 || x < 0)
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		int[][] maze2d= new int[this._maze3d[0].length][this._maze3d[0][0].length];
		for (int i = 0; i < this._maze3d[0].length;i++)
		{
			for (int j = 0; j < this._maze3d[0][0].length;j++)
			{
				maze2d[i][j]=_maze3d[x][i][j];
			}
		}
		return maze2d;
	}
	
	/**
	 * Gets the cross section by y.
	 *
	 * @param y the y
	 * @return the cross section by y
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByY(int y) throws IndexOutOfBoundsException
	{
		if ( y > this._maze3d[0].length-1 || y < 0)
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		int[][] maze2d= new int[this._maze3d.length][this._maze3d[0][0].length];
		for (int i = 0; i < this._maze3d.length;i++)
		{
			for (int j = 0; j < this._maze3d[0][0].length;j++)
			{
				maze2d[i][j]=_maze3d[i][y][j];
			}
		}
		return maze2d;
	}
	
	/**
	 * Gets the cross section by z.
	 *
	 * @param z the z
	 * @return the cross section by z
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByZ(int z) throws IndexOutOfBoundsException
	{
		if ( z > this._maze3d[0][0].length-1 || z < 0)
		{
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		int[][] maze2d= new int[this._maze3d.length][this._maze3d[0].length];
		for (int i = 0; i < this._maze3d.length;i++)
		{
			for (int j = 0; j < this._maze3d[0].length;j++)
			{
				maze2d[i][j]=_maze3d[i][j][z];
			}
		}
		return maze2d;
	}
	
	/**
	 * Prints the.
	 */
	public void print()
	{
		for (int[][] dimension: this._maze3d)
		{
			for (int[] row: dimension)
				System.out.println(java.util.Arrays.toString(row));
				System.out.println();
		}
	}
	
	/**
	 * Gets the x length.
	 *
	 * @return the x length
	 */
	public int getXLength()
	{
		return this._maze3d.length;
	}
	
	/**
	 * Gets the y length.
	 *
	 * @return the y length
	 */
	public int getYLength()
	{
		return this._maze3d[0].length;
	}
	
	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size()
	{
		return this.getXLength()*this.getYLength()*this.getZLength();
	}
	
	/**
	 * Gets the z length.
	 *
	 * @return the z length
	 */
	public int getZLength()
	{
		return this._maze3d[0][0].length;
	}
	
	/**
	 * To byte array.
	 *
	 * @return the byte[]
	 */
	public byte[] toByteArray()
	{
	       ByteArrayOutputStream bos = new ByteArrayOutputStream();
	       bos.write(_start.getX());
	       bos.write(_start.getY());
	       bos.write(_start.getZ());
	       bos.write(_end.getX());
	       bos.write(_end.getY());
	       bos.write(_end.getZ());
	       bos.write(getXLength());
	       bos.write(getYLength());
	       bos.write(getZLength());
	       for (int i = 0 ; i < getXLength() ; i++)
	    	   for (int j = 0 ; j < getYLength() ; j++)
	    		   for (int k = 0 ; k < getZLength() ; k++)
	    			   bos.write(_maze3d[i][j][k]);
	        return bos.toByteArray();
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (_start.equals(((Maze3d)obj).getStartPosition()) && _end.equals(((Maze3d)obj).getGoalPosition()) 
				&& getXLength() == ((Maze3d)obj).getXLength() && getYLength() == ((Maze3d)obj).getYLength()&& getZLength() == (((Maze3d)obj).getZLength()))
		{
			for (int i = 0 ; i < getXLength() ; i++)
		    	   for (int j = 0 ; j < getYLength() ; j++)
		    		   for (int k = 0 ; k < getZLength() ; k++)
		    			   if (_maze3d[i][j][k] != ((Maze3d)obj).getStatus(new Position(i,j,k)))
								return false;
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int hashCode() 
	{
		int x=0;
		for (int[][] dim : _maze3d) 
			for (int[] arr : dim) 
				for (int cell : arr) 
				{
					if (cell == 1)
						x++;
				}
		int hash = this._start.hashCode()+this._end.hashCode()+x;
	    return hash;
	}
}
