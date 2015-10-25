package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import general.Position;
import model.Model;
import view.View;

public class Presenter implements Observer {

	/** The model. */
	Model model;

	/** The view. */
	View view;
	
	
	public Presenter(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
		HashMap<String, Integer> commands = new HashMap<String,Integer>();
		commands.put("dir", 1);
		commands.put("generate 3d maze", 2);
		commands.put("display", 3);
		commands.put("display cross section", 4);
		commands.put("save maze", 5);
		commands.put("load maze", 6);
		commands.put("maze size", 7);
		commands.put("file size", 8);
		commands.put("solve", 9);
		commands.put("display solution", 10);
		commands.put("exit", 11);
		commands.put("set start", 12);
		commands.put("get next step", 13);
		commands.put("load properties", 14);
		view.setCLI(commands);
	}

	@Override
	public void update(Observable o, Object arg) {

		if (o==view)
		{
			String strArr[] = (String[])arg;
			switch(strArr[0])
			{
				case "1": 
					if (strArr.length == 2)
						view.Dir(strArr[1]);	
						else
							view.displayStr("Wrong number of parameters");
				break;
				case "2": 
					if (strArr.length == 5)
						try
						{
						model.generate(strArr[1], Integer.parseInt(strArr[2]), Integer.parseInt(strArr[3]), Integer.parseInt(strArr[4]));
						}
						catch (Exception e)
						{
							view.displayStr("Error while generating the maze !");
						}
						else
							view.displayStr("Wrong number of parameters");
				break;
				case "3": 
					if (strArr.length == 2)
					{
						try
						{
						view.displayMaze(model.display(strArr[1]));		
						}
						catch (Exception e)
						{
							view.displayStr("Error while displaying the maze !");
						}
					}
					else
						view.displayStr("Wrong number of parameters");
				break;
				case "4": 
					
					if (strArr.length == 6)
					{
						try
						{
						view.displayCrossSection((int[][]) model.getCrossSection(strArr[5], strArr[2].charAt(0), Integer.parseInt(strArr[3])));
						}
						catch (Exception e)
						{
							view.displayStr("Error while displaying the cross section !");
						}
					}
					else 
						view.displayStr("Wrong Number of parameters");
				break;
				case "5": 
						if (strArr.length == 3)
						{
							try {
							model.save(strArr[1], strArr[2]);
						} catch (Exception e) {

							view.displayStr("Can not save to file !");
						}	
						}
						else 
							view.displayStr("Wrong Number of parameters");
				break;
				case "6": 
					if (strArr.length == 3)
					{
					try {
						model.load(strArr[2], strArr[1]);
					} catch (Exception e) {
						view.displayStr("Error while loading file");
					}
					}
					else 
						view.displayStr("Wrong Number of parameters");
				break;
				case "7":
					if (strArr.length == 2)
					{
					try
					{
					view.displayMazeSize(model.mazeSize(strArr[1]));
					}
					catch (Exception e)
					{
						view.displayStr("Error displaying maze size !");
					}
					}
					
					else
						view.displayStr("Wrong number of parameters");
				break;
				case "8": 
					if (strArr.length==2)	
					{
						try
						{
						view.displayFileSize(model.fileSize(strArr[1]));
						}
						catch (Exception e)
						{
							view.displayStr("Error displaying saved file size !");
						}
					}
					else
						view.displayStr("Wrong number of parameters");
				break;
				case "9": 
					if (strArr.length == 3) 
					{
						try
						{
							model.solve(strArr[1], strArr[2]);
						}
						catch (Exception e)
						{
							view.displayStr("Failure while solving the maze");
						}
					}
				else
					view.displayStr("Wrong number of parameters");
				break;
				case "10": 
					if (strArr.length == 2)
					{
						try
						{
							view.displaySolution(model.displaySolution(strArr[1]));
						}
						catch (Exception e)
						{
							view.displayStr("Failure while displaying solution");
						}
					}
					else
						view.displayStr("Wrong number of parameters");
				break;
				case "11":
					model.exit();
					view.displayStr("Program will now exit !");
					System.exit(0);
				break;
				case "12":
					if (strArr.length == 3)
					{
						try
						{
						
							model.setStartPosition(strArr[1], new Position(strArr[2]));
						}
						catch (Exception e)
						{
							view.displayStr("Failure while setting startup position");
						}
					}
				break;
				case "13":
					if (strArr.length == 3)
					{
						try
						{
							model.solve(strArr[1], strArr[2]);
							view.displayNextStep(model.displaySolution(strArr[1]).get_steps().get(0));
						}
						catch (Exception e)
						{
							view.displayStr("Failure while solving the maze");
						}
					}
				break;
				
				case "14":
					if (strArr.length == 2)
					{
						try
						{
							model.setProperties(strArr[1]);
							view.displayStr("Properties Loaded");
						}
						catch (Exception e)
						{
							view.displayStr("Failure while Loading Properties");
						}
					}
				break;
				
			}
		}
		
		
		if (o==model)
		{
			String strArr[] = arg.toString().split(",");
			switch(strArr[0])
			{
				case "Display": 
					view.displayStr(arg.toString());
				break;
			}
		}
	}

}
