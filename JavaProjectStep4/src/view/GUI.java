package view;

import java.util.HashMap;
import java.util.Observable;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;


import general.Position;
import general.Solution;

public class GUI extends Observable implements View {

MazeMaker window;
HashMap<String, SelectionListener> listeners;

	public GUI(MazeMaker window) {
		super();
		this.window = window;
	}

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
		// TODO Auto-generated method stub

	}

	@Override
	public void displayCrossSection(Object[] array) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	
	public void setListeners() {
		listeners = new HashMap<String, SelectionListener>();
		listeners.put("Generate", new SelectionListener() {
			
			@Override
			public void widgetSelected	(SelectionEvent arg0) {
				notifyObservers("2 newmaze 10 10 10");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void setCLI(HashMap<String, Integer> commands) {
		// TODO Auto-generated method stub

	}

}
