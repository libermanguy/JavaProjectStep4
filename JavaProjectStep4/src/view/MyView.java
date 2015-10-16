package view;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Controller;
import controller.MyController;
import general.Position;
import general.Solution;
import general.State;

// TODO: Auto-generated Javadoc
/**
 * The Class MyView.
 */
public class MyView implements View
{
	
	/** The cli. */
	CLI cli;
	
	/** The controller. */
	Controller controller;
	
	/**
	 * Sets the cli.
	 *
	 * @param cmdsHM the cmds hm
	 */
	public void setCLI(HashMap<String,Command> cmdsHM) {
		cli=new CLI(new BufferedReader(new InputStreamReader(System.in)), 
				new PrintWriter(new OutputStreamWriter(System.out)), cmdsHM);
		
	}
	
	/**
	 * Sets the controller.
	 *
	 * @param c the new controller
	 */
	public void setController(Controller c){
		controller=c;
	}


	/* (non-Javadoc)
	 * @see view.View#Dir(java.lang.String)
	 */
	@Override
	public void Dir(String path) {
	File currentFolder=new File(path);
		try{
			for (File obj :currentFolder.listFiles()){
				System.out.println(obj.getName());
			
			}
		}
		catch (NullPointerException e) {
			System.out.println("Path is not exist");
		}
	}
	
	/* (non-Javadoc)
	 * @see view.View#display(java.lang.Object[])
	 */
	@Override
	public void display(Object[] arg){
		System.out.println(arg);
	}

	/* (non-Javadoc)
	 * @see view.View#displayStr(java.lang.String)
	 */
	public void displayStr(String arg){
		System.out.println(arg);
	}
	
	/* (non-Javadoc)
	 * @see view.View#displayMaze(java.lang.Object[])
	 */
	@Override
	public void displayMaze(Object[] obj){
		System.out.println("Maze dimensions { X = " + obj[0] + " , Y = " + obj[1] + " , Z = " + obj[2] + " }");
		Position p = (Position)obj[4];
		System.out.println("Maze start position : " + p.toString());
		p = (Position)obj[5];
		System.out.println("Maze end position : " + p.toString());
		for (int[][] dimension: (int[][][])obj[3])
		{
			for (int[] row: dimension)
				System.out.println(java.util.Arrays.toString(row));
				System.out.println();
		}

	}
	
	/* (non-Javadoc)
	 * @see view.View#displayCrossSection(java.lang.Object[])
	 */
	@Override
	public void displayCrossSection(Object[] array){
			for (int[] row: (int[][])array)
				System.out.println(java.util.Arrays.toString(row));
				System.out.println();
	}
	
	/* (non-Javadoc)
	 * @see view.View#displayMazeSize(int)
	 */
	@Override
	public void displayMazeSize(int size){
		System.out.println("Maze size is : " + size);
	}
	
	/* (non-Javadoc)
	 * @see view.View#displayFileSize(int)
	 */
	@Override
	public void displayFileSize(int size){
		System.out.println("File size is : " + size);
	}
	
	/* (non-Javadoc)
	 * @see view.View#displaySolution(general.Solution)
	 */
	@Override
	public void displaySolution(Solution<Position> solution){
		System.out.println(solution.toString());
	}
	
	/**
	 * Start cli.
	 */
	public void startCLI(){
		cli.start();
	}
		
			
	
	}

