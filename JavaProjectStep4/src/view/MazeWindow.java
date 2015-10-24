package view;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import general.Position;
import general.Solution;
import general.State;

public class MazeWindow extends BasicWindow implements View{

	Timer timer;
	TimerTask task;
	MazeDisplayer maze;
	int currentFloor;
	int futureFloor;
	int topFloor;
	String currentMaze;
	Label floorText;
	
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
		currentFloor = 0;
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2,false));
		
		Menu menu = new Menu(shell,SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem file = new MenuItem(menu, SWT.CASCADE);
		file.setText("&File");
		
		Menu filemenu = new Menu(shell,SWT.DROP_DOWN);
		file.setMenu(filemenu);

		MenuItem properties = new MenuItem(filemenu, SWT.CASCADE);
		properties.setText("&Properties");
		
		MenuItem exit = new MenuItem(filemenu, SWT.CASCADE);
		exit.setText("&Exit");

		Button generateButton=new Button(shell, SWT.PUSH);
		generateButton.setText("Generate");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
			
		
		//MazeDisplayer maze=new Maze2D(shell, SWT.BORDER);		
		maze=new Maze3D(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,4));
		
		Button displayButton=new Button(shell, SWT.PUSH);
		displayButton.setText("Display");
		displayButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
				
		
		Button solveButton=new Button(shell, SWT.PUSH);
		solveButton.setText("Solve");
		solveButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		
		Button hintButton=new Button(shell, SWT.PUSH);
		hintButton.setText("Hint");
		hintButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		
		floorText=new Label(shell, SWT.NONE);
		floorText.setText("Floor " + currentFloor);
		Font newfont = new Font(floorText.getDisplay(), new FontData("Ariel",40,SWT.BOLD));
		floorText.setFont(newfont);
		floorText.setLayoutData(new GridData(SWT.CENTER, SWT.None, false, false, 2, 1));
		
		
		
		generateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateDialog diag = new GenerateDialog(shell);
				String args = "2 " + diag.open();
				String[] splited = args.split(" ");
				setChanged();
				notifyObservers(splited);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		displayButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DisplayDialog diag = new DisplayDialog(shell);
				String mazename = diag.open();
				String args = "3 " + mazename;
				String[] splited = args.split(" ");
				currentMaze = mazename; 
				setChanged();
				notifyObservers(splited);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		
		solveButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DisplayDialog diag = new DisplayDialog(shell);
				String mazename = diag.open(); 
				String args = "9 " + mazename + " air";
				String[] splited = args.split(" ");
				setChanged();
				notifyObservers(splited);
				args = "10 " + mazename;
				splited = args.split(" ");
				setChanged();
				notifyObservers(splited);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
			
		
			hintButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String pos = currentFloor + "," + maze.getCharacterPosition()[1]+","+maze.getCharacterPosition()[0];
				String args = "12 " + currentMaze + " "  + pos;
				String[] splited = args.split(" ");
				setChanged();
				notifyObservers(splited);
				args = "9 " + currentMaze + " air";
				splited = args.split(" ");
				setChanged();
				notifyObservers(splited);
				args = "10 " + currentMaze;
				splited = args.split(" ");
				setChanged();
				notifyObservers(splited);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});


		
		
	
		exit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				String arg[] = new String[1];
				arg[0] = "11";
				notifyObservers(arg);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
	
		shell.addKeyListener((new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode==16777218)
				{
					maze.moveDown();
				}
				if(arg0.keyCode==16777217)
				{
					maze.moveUp();
				}
				if(arg0.keyCode==16777220)
				{
					maze.moveRight();
				}
				if(arg0.keyCode==16777219)
				{
					maze.moveLeft();
				}
				if(arg0.keyCode==SWT.PAGE_UP)
				{
					if (currentFloor < topFloor)
					{
						futureFloor = currentFloor+1;
						String args = "4 by x " + futureFloor + " for " + currentMaze;
						String[] splited = args.split(" ");
						setChanged();
						notifyObservers(splited);
						maze.update();
						maze.redraw();
					}
					else
					{
						displayStr("you're on top of the world again !");
					}
				}
				if(arg0.keyCode==SWT.PAGE_DOWN)
				{ 
					if (currentFloor > 0)
					{ 
						futureFloor = currentFloor-1;
						String args = "4 by x " + futureFloor + " for " + currentMaze;
						String[] splited = args.split(" ");
						setChanged();
						notifyObservers(splited);
						maze.update();
						maze.redraw();
					}
					else
					{
						displayStr("you can't get any lower than that ;)");
					}
				}
				
			}
		}
		));

		
		
		
		
		
		
	}
	
	/*
	public static void main(String[] args) {
		MazeWindow win=new MazeWindow("maze example", 500, 300);
		win.run();
	}
*/
	@Override
	public void display(Object[] arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Dir(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Object[] obj) {
		Position start = (Position)obj[4];
		Position end = (Position)obj[5];
		int [][][] maze3d = (int[][][])obj[3];
		int[][] maze2d = maze3d[currentFloor];
		maze.setMazeData(maze2d);
		maze.setCharacterPosition(start.getY(), start.getZ());
		maze.setCharacterFloor(currentFloor);
		maze.setExitPosition(end.getY(), end.getZ());
		maze.setExitFloor(end.getX());
		topFloor = maze3d.length-1;
		maze.redraw();
	}

	@Override
	public void displayCrossSection(Object[] array) {
		int[][] newmaze = (int[][])array;
		int[] pos = maze.getCharacterPosition();
		if (newmaze [pos[1]][pos[0]] == 0 )
		{
			
			currentFloor=futureFloor;
			maze.setCharacterFloor(currentFloor);
			maze.setMazeData((int[][])array);
			floorText.setText("Floor " + currentFloor);
			maze.update();
			maze.redraw();	
		}
		else
		{
			System.out.println(newmaze [pos[1]][pos[0]]);
			displayStr("no where to go");
		}
	}

	@Override
	public void displayMazeSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayFileSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution<Position> solution) {
		for (State<Position> step : solution.get_steps()) {
			Position pos = step.getState();
			futureFloor = pos.getX();
			if (futureFloor != currentFloor)
			{
				
				currentFloor = futureFloor;
				maze.setCharacterFloor(currentFloor);
				setChanged();
				String args = "4 by x " + currentFloor + " for " + currentMaze;
				String[] splited = args.split(" ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setChanged();
				notifyObservers(splited);
				maze.update();
				maze.redraw();
				floorText.setText("Floor " + currentFloor);
			}
			else
			{
				maze.setCharacterPosition(pos.getY(), pos.getZ());
				maze.update();
			}
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	@Override
	public void displayStr(String arg) {
		MessageBox box = new MessageBox(shell);
		box.setMessage(arg);
		box.open();
		
	}

	@Override
	public void setCLI(HashMap<String, Integer> commands) {
		// TODO Auto-generated method stub
		
	}
	
	

}
