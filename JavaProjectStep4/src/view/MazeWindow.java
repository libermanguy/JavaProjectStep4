package view;
import org.eclipse.swt.widgets.Label;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.hamcrest.DiagnosingMatcher;

import general.Position;
import general.Solution;

public class MazeWindow extends BasicWindow implements View{

	Timer timer;
	TimerTask task;
	MazeDisplayer maze;
	int currentFloor;
	
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
		currentFloor = 0;
	}

	private void randomWalk(MazeDisplayer maze){
		Random r=new Random();
		boolean b1,b2;
		b1=r.nextBoolean();
		b2=r.nextBoolean();
		if(b1&&b2)
			maze.moveUp();
		if(b1&&!b2)
			maze.moveDown();
		if(!b1&&b2)
			maze.moveRight();
		if(!b1&&!b2)
			maze.moveLeft();
		
		maze.redraw();
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
		
		Label floorText=new Label(shell, SWT.NONE);
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
				String args = "4 by x 2 for " + diag.open();
				System.out.println(args);
				String[] splited = args.split(" ");
				setChanged();
				notifyObservers(splited);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		
		solveButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				task.cancel();
				timer.cancel();
				solveButton.setEnabled(true);
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
	}

	@Override
	public void displayCrossSection(Object[] array) {
		maze.setMazeData((int[][])array);
		maze.redraw();	
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
		// TODO Auto-generated method stub
		
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
