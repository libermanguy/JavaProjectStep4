package boot;

import presenter.Presenter;

import java.io.IOException;

import model.MyModel;
import view.MazeWindow;
import view.MyView;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * <h1>Maze Demo</h1>
 * The Maze Demo Class
 * <p>
 * Runs All Searchers For Test
 *
 * @author  Guy Liberman & Omri Polinikviat
 * @version 1.0
 * @since   2015-10-16
 */
public class Demo {
	public static void main(String[] args) {
		
		
		MyModel mymodel=new MyModel();
		Presenter presenter=null;
		int option = 2;
		
		if (option == 1)
		{
			MyView myview=new MyView();
			presenter=new Presenter(mymodel, myview);
			myview.startCLI();
		}
		if (option == 2)
		{
			MazeWindow myview=new MazeWindow("Window", 1000, 500);
			presenter=new Presenter(mymodel, myview);
			myview.addObserver(presenter);
			mymodel.addObserver(presenter);
			myview.run();
		}
		
		
		
		
		
		
		
		
		
	}

}

/*
***********test commands******************
dir c:\temp
generate 3d maze testmaze 10 10 10
save maze testmaze c:\temp\tm.maz
load maze c:\temp\tm.maz testmaze2
display cross section by x 3 for testmaze
maze size testmaze
maze size testmaze2
file size c:\temp\tm.maz
solve testmaze man
solve testmaze2 air
display solution testmaze
display testmaze
*/