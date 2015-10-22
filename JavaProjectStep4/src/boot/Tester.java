package boot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import general.Position;
import general.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import model.SearchableMaze;

public class Tester {

	public static void main(String[] args) throws Exception {
		Maze3d maze = new MyMaze3dGenerator().generate(3, 3, 3);
		Maze3d maze2 = maze;
		HashMap<Maze3d, Solution<Position>> map = new HashMap<Maze3d, Solution<Position>>();
		map.put(maze, null);
		System.out.println(map.containsKey(maze2));
		System.out.println(maze.get_maze3d().hashCode());
		System.out.println(maze2.get_maze3d().hashCode());
		
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream("c:\\temp\\new.maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();	
	
		
		
		InputStream in;
		in = new MyDecompressorInputStream(new FileInputStream("c:\\temp\\new.maz"));
		ArrayList<Integer> buff = new ArrayList<Integer>();
		int current = in.read();
		while (current != -1)
		{
		 buff.add(current);
		 current = in.read();
		}
		in.close();
		byte fileData[]=new byte[buff.size()];
		for ( int i = 0 ; i < buff.size() ; i ++ )
			fileData[i] = buff.get(i).byteValue();
		maze2 = new Maze3d(fileData);
		System.out.println(maze2.get_maze3d().hashCode());
		System.out.println(map.containsKey(maze2));
		maze.print();
		maze2.print();

	}

}
