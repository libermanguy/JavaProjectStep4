package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;


// this is (1) the common type, and (2) a type of widget
// (1) we can switch among different MazeDisplayers
// (2) other programmers can use it naturally
public abstract class MazeDisplayer extends Canvas{
	
	// just as a stub...
	int[][] mazeData={
			{0},
		};

	
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
	}

	public void setMazeData(int[][] mazeData){
		this.mazeData=mazeData;
	}
	
	public abstract  void setCharacterPosition(int row,int col);

	public abstract  void setExitPosition(int row,int col);
	
	public abstract void moveUp();

	public abstract  void moveDown();

	public abstract  void moveLeft();

	public  abstract void moveRight();

	public abstract int[] getCharacterPosition();
	
	public abstract void setCharacterFloor(int floor);
	
	public abstract void setExitFloor(int floor);
	
	public abstract boolean isFinished();
	
}