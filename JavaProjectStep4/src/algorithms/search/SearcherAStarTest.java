package algorithms.search;


import static org.junit.Assert.*;

import model.*;

import org.junit.Test;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import general.Position;


public class SearcherAStarTest {

	@Test
	public void testGetNumberOfNodesEvaluated() {
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d newmaze  = mg.generate(10,10,10);	
		SearchableMaze searchable = new SearchableMaze(newmaze);
		Searcher<Position> searcher = new SearcherAStar<Position>(new HeuristicAirLine());
		assertEquals(0, searcher.getNumberOfNodesEvaluated());
	}

	@Test
	public void testSearcherAStar() {
		Searcher<Position> searcher = new SearcherAStar<Position>(null);
	}

	@Test
	public void testSearch() {
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d newmaze  = mg.generate(10,10,10);	
		SearchableMaze searchable = new SearchableMaze(newmaze);
		Searcher<Position> searcher = new SearcherAStar<Position>(new HeuristicAirLine());
		searcher.search(null);
		searcher = new SearcherAStar<Position>(new HeuristicManhattan());
		searcher.search(null);
	}
}
