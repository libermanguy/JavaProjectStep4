 package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import general.Position;
import general.Solution;
import io.*;


/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model {

	/** The _mazes. */
	HashMap<String,SearchableMaze> _mazes;
	
	/** The _solutions. */
	HashMap<String,Solution<Position>> _solutions;

	/** The openfiles. */
	int openfiles;
	
	/** The openthreads. */
	int openthreads;
	
	/**
	 * Instantiates a new my model.
	 */
	public MyModel() {
		super();
		this._mazes = new HashMap<String,SearchableMaze>();
		this._solutions = new HashMap<String,Solution<Position>>();
		openfiles=0;
		openthreads=0;
	}

	/* (non-Javadoc)
	 * @see model.Model#generate(java.lang.String, int, int, int)
	 */
	@Override
	public void generate(String name, int x, int y, int z) 
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				openthreads++;
				_mazes.put(name,new SearchableMaze(new MyMaze3dGenerator().generate(x, y, z)));
				setChanged();
				notifyObservers("Display,1,1," + name);
				openthreads--;
			    }
			  }).start();		
	}

	/* (non-Javadoc)
	 * @see model.Model#display(java.lang.String)
	 */
	@Override
	public Object[] display(String name) {
		Object[] arg = {_mazes.get(name)._newMaze.getXLength(),
						_mazes.get(name)._newMaze.getYLength(),
						_mazes.get(name)._newMaze.getZLength(),
						_mazes.get(name)._newMaze.get_maze3d(),
						_mazes.get(name)._newMaze.getStartPosition(),
						_mazes.get(name)._newMaze.getGoalPosition()};
		return arg;
	}

	/* (non-Javadoc)
	 * @see model.Model#getCrossSection(java.lang.String, char, int)
	 */
	@Override
	public Object getCrossSection(String name, char dim, int index) throws ArrayIndexOutOfBoundsException
	{
		switch (dim) {
        case 'x':  return (Object)_mazes.get(name)._newMaze.getCrossSectionByX(index);
        case 'y':  return (Object)_mazes.get(name)._newMaze.getCrossSectionByY(index);
        case 'z':  return (Object)_mazes.get(name)._newMaze.getCrossSectionByZ(index);
        default: return null;
		   }
	}

	/* (non-Javadoc)
	 * @see model.Model#save(java.lang.String, java.lang.String)
	 */
	@Override
	public void save(String name, String path) throws Exception 
	{
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream(path));
		openfiles++;
		out.write(_mazes.get(name)._newMaze.toByteArray());
		out.flush();
		out.close();	
		openfiles--;
		setChanged();
		notifyObservers("Display,3,1,"+ name);
	}

	/* (non-Javadoc)
	 * @see model.Model#load(java.lang.String, java.lang.String)
	 */
	@Override
	public void load(String name, String path) throws Exception {
		InputStream in;
			in = new MyDecompressorInputStream(new FileInputStream(path));
			openfiles++;
			ArrayList<Integer> buff = new ArrayList<Integer>();
			int current = in.read();
			while (current != -1)
			{
			 buff.add(current);
			 current = in.read();
			}
			openfiles--;
			in.close();
			byte fileData[]=new byte[buff.size()];
			for ( int i = 0 ; i < buff.size() ; i ++ )
				fileData[i] = buff.get(i).byteValue();
			_mazes.put(name, new SearchableMaze(new Maze3d(fileData)));
			setChanged();
			notifyObservers("Display,4,1,"+ name);
	

	}

	/* (non-Javadoc)
	 * @see model.Model#mazeSize(java.lang.String)
	 */
	@Override
	public int mazeSize(String name) 
	{
		return _mazes.get(name)._newMaze.size();
	}

	/* (non-Javadoc)
	 * @see model.Model#fileSize(java.lang.String)
	 */
	@Override
	public int fileSize(String name) {
		File f = new File(name);
		return (int) f.length();
	}

	/* (non-Javadoc)
	 * @see model.Model#solve(java.lang.String, java.lang.String)
	 */
	@Override
	public void solve(String name,String alg) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				openthreads++;
				Searcher<Position> searcher = null;
				switch (alg) {
		    case "bfs": 
		    	searcher = new SearcherBFS<Position>();
		    case "man":  
		    	searcher = new SearcherAStar<Position>(new HeuristicManhattan());
		    case "air":  
		    	searcher = new SearcherAStar<Position>(new HeuristicAirLine());
			   }
				try
				{
			_solutions.put(name, searcher.search(_mazes.get(name)));
			setChanged();
			// first int = action , second int status
			notifyObservers("Display,2,1," + name);
			openthreads--;
				}
				catch (Exception e)
				{
					setChanged();
					notifyObservers("Display,2,2," + name);
					openthreads--;
				}
			  }}).start();		

	}

	/* (non-Javadoc)
	 * @see model.Model#displaySolution(java.lang.String)
	 */
	@Override
	public Solution<Position> displaySolution(String name) {
		return _solutions.get(name);
	}

	/* (non-Javadoc)
	 * @see model.Model#exit()
	 */
	@Override
	public void exit() {
		while (openfiles > 0 || openthreads > 0)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				setChanged();
				notifyObservers("Display,Error closing Model");
			}
			setChanged();
			notifyObservers("Display,open files," + openfiles + ",open threads," + openthreads);
		}
		notifyObservers("Display,Finish closing Model");
	}


}
