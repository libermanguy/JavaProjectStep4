package view;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
public abstract class BasicWindow implements Runnable{
	
	Display display;
	Shell shell;
	
	abstract void initWidgets();
	
	 public BasicWindow(int width, int height) {
		display=new Display();
		shell=new Shell(display);
		shell.setSize(width, height);
	}
	
	@Override
	public void run(){
		
		initWidgets();
		shell.open();

		// run the event loop as long as the window is open
		while (!shell.isDisposed()) {
		    // read the next OS event queue and transfer it to a SWT event 
		  if (!display.readAndDispatch())
		   {
		  // if there are currently no other OS event to process
		  // sleep until the next OS event is available 
		    display.sleep();
		   }
		}

		// disposes all associated windows and their components
		display.dispose(); 
		
	}

}

