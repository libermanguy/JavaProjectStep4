package boot;

import presenter.Presenter;
import model.MyModel;
import view.MyView;

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
		MyView myview=new MyView();
		MyModel mymodel=new MyModel();
		Presenter presenter=new Presenter(mymodel, myview);
		myview.addObserver(presenter);
		mymodel.addObserver(presenter);
		myview.startCLI();
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