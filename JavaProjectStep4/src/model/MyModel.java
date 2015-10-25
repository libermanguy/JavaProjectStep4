 package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
import general.Position;
import general.Properties;
import general.Solution;
import io.*;

/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model {

	/** The _mazes. */
	HashMap<String,Future<SearchableMaze>> _mazes;
	
	/** The _solutions. */
	HashMap<String,Future<Solution<Position>>> _solutions;

	HashMap<Maze3d, Solution<Position>> _solutionscache;
	
	/** The openfiles. */
	int openfiles;
	
	/** The openthreads. */
	int openthreads;
	
	Properties prop;
	
	ExecutorService executer;
	
	/**
	 * Instantiates a new my model.
	 */
	public MyModel() {
		super();
		this._mazes = new HashMap<String,Future<SearchableMaze>>();
		this._solutions = new HashMap<String,Future<Solution<Position>>>();
		try{
			loadCache("c:\\temp\\cache.zip");
			}
		catch (Exception e) {
			notifyObservers("Cache doesn`t exist");
		}
		
		
		
		
		executer = Executors.newFixedThreadPool(20); // change later
		openfiles=0;
		openthreads=0;
	}

	/* (non-Javadoc)
	 * @see model.Model#generate(java.lang.String, int, int, int)
	 */
	@Override
	public void generate(String name, int x, int y, int z) 
	{
		/*new Thread(new Runnable() {
			@Override
			public void run() {*/
				openthreads++;
				_mazes.put(name, executer.submit(new Callable<SearchableMaze>() {
					@Override
					public SearchableMaze call() throws Exception {
						openthreads++;
						Maze3d maz = new MyMaze3dGenerator().generate(x, y, z);
						openthreads--;
						
						return new SearchableMaze(maz);
					}
				}));
			//	setChanged();
			//	notifyObservers("Display,1,1," + name);
				openthreads--;
			/*    }
			  }).start();		*/
				
				
	}

	/* (non-Javadoc)
	 * @see model.Model#display(java.lang.String)
	 */
	@Override
	public Object[] display(String name) throws Exception {
		try {
		Object[] arg = {
						
					_mazes.get(name).get()._newMaze.getXLength(),
					_mazes.get(name).get()._newMaze.getYLength(),
					_mazes.get(name).get()._newMaze.getZLength(),
					_mazes.get(name).get()._newMaze.get_maze3d(),
					_mazes.get(name).get()._newMaze.getStartPosition(),
					_mazes.get(name).get()._newMaze.getGoalPosition()};
		
		
		return arg;
		}
		catch (Exception e)
		{
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see model.Model#getCrossSection(java.lang.String, char, int)
	 */
	@Override
	public Object getCrossSection(String name, char dim, int index) throws Exception
	{
		try 
		{
		switch (dim) {
        case 'x':  return (Object)_mazes.get(name).get()._newMaze.getCrossSectionByX(index);
        case 'y':  return (Object)_mazes.get(name).get()._newMaze.getCrossSectionByY(index);
        case 'z':  return (Object)_mazes.get(name).get()._newMaze.getCrossSectionByZ(index);
        default: return null;
		   }
		}
		catch (Exception e)
		{
			throw e;
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
		out.write(_mazes.get(name).get()._newMaze.toByteArray());
		out.flush();
		out.close();	
		openfiles--;
	//	setChanged();
	//	notifyObservers("Display,3,1,"+ name);
	}

	/* (non-Javadoc)
	 * @see model.Model#load(java.lang.String, java.lang.String)
	 */
	
	@SuppressWarnings("unchecked")
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
			
			
			_mazes.put(name,executer.submit(new Callable<SearchableMaze>() {
				@Override
				public SearchableMaze call() throws Exception {
					return new SearchableMaze(new Maze3d(fileData));
				}
			}));
	//		setChanged();
	//		notifyObservers("Display,4,1,"+ name);
	

	}

	/* (non-Javadoc)
	 * @see model.Model#mazeSize(java.lang.String)
	 */
	@Override
	public int mazeSize(String name) throws Exception
	{
			try
			{
				return _mazes.get(name).get()._newMaze.size();
			}
			catch (Exception e)
			{
				throw e;
			}
	}

	/* (non-Javadoc)
	 * @see model.Model#fileSize(java.lang.String)
	 */
	@Override
	public int fileSize(String name) {
		File f = new File(name);
		return (int) f.length();
	}

	public void saveCache(String file) throws Exception
	{
		OutputStream fileOut=new FileOutputStream(file);
		openfiles++;
		GZIPOutputStream zip = new GZIPOutputStream(fileOut);
		ObjectOutputStream out = new ObjectOutputStream(zip);
		out.writeObject(_solutionscache);
        out.close();
        zip.close();
		fileOut.close();	
		openfiles--;
	//	setChanged();
	//	notifyObservers("finish saving caching");
	}
	
	@SuppressWarnings("unchecked")
	
	public void loadCache(String file) throws Exception
	{
		this._solutionscache = new HashMap<Maze3d,Solution<Position>>();
		InputStream fileIn = new FileInputStream(file);
		openfiles++;
		GZIPInputStream zip = new GZIPInputStream(fileIn);
		ObjectInputStream in = new ObjectInputStream(zip);
		_solutionscache = (HashMap<Maze3d,Solution<Position>>) in.readObject();
		in.close();
		zip.close();
        fileIn.close();
		openfiles--;
	//	setChanged();
	//	notifyObservers("finish loading caching");
	}
	
	/* (non-Javadoc)
	 * @see model.Model#solve(java.lang.String, java.lang.String)
	 */
	@Override
	public void solve(String name,String alg)
	{
		try {
			if (_solutionscache.get(_mazes.get(name).get()._newMaze) == null)
			{		
			try
					{
				_solutions.put(name, executer.submit(new Callable<Solution<Position>>() {
					@Override
					public Solution<Position> call() throws Exception {
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
						Solution<Position> sol = searcher.search(_mazes.get(name).get());
						_solutionscache.put(_mazes.get(name).get()._newMaze, sol);
						return sol;
					}
				}));
			//	setChanged();
			//	notifyObservers("Display,2,1," + name);
				openthreads--;
					}
					catch (Exception e)
					{
						setChanged();
						notifyObservers("Error Solving Maze " + name);
						openthreads--;
					}
			}
			else 
			{
			//	setChanged();
			//	notifyObservers("Display,2,2,solution exists" + name);
				openthreads--;
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			setChanged();
			notifyObservers("Error Solving Maze " + name);
			openthreads--;
		}
	}

	
	
	
	
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see model.Model#displaySolution(java.lang.String)
	 */
	@Override
	public Solution<Position> displaySolution(String name) throws Exception 
	{
		if (_solutions.get(name) != null)
		{
		try {
			return _solutions.get(name).get();
			} 
		catch (Exception e) {
				throw e;
			}
		}
		else
		{
			solve(name, "air");
			return _solutions.get(name).get();
		}
		
	}

	/* (non-Javadoc)
	 * @see model.Model#exit()
	 */
	@Override
	public void exit() {
		try{
			saveCache("c:\\temp\\cache.zip");
			}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error cache save");
		}
		
		
		while (openfiles > 0 || openthreads > 0)
		{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				setChanged();
			//	notifyObservers("Display,Error closing Model");
			//	notifyObservers();
			}
			setChanged();
			notifyObservers("Still there are open files: " + openfiles + " open threads:" + openthreads);
		}
		notifyObservers("Finish closing Model");
	}

	@Override
	public void setStartPosition(String name, Position newpos) throws Exception{
		SearchableMaze currentmaze = _mazes.get(name).get();
		_mazes.put(name,executer.submit(new Callable<SearchableMaze>() {
			@Override
			public SearchableMaze call() throws Exception {
				currentmaze._newMaze.setStartPosition(newpos);
				SearchableMaze newmaze = new SearchableMaze(currentmaze._newMaze);
				return newmaze;
				
			}
		})	
		);
	}
	
	public void setProperties(String file) throws Exception{
		this.prop = new Properties();
		prop.loadProp(file);
	}


}
