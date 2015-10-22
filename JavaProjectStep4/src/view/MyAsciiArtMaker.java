package view;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;

public class MyAsciiArtMaker extends BasicWindow{

	public MyAsciiArtMaker(int width, int height){
		super(width, height);
	}
	
	@Override
	public void initWidgets(){
		shell.setLayout(new GridLayout(2,false));
		Button b1 = new Button(shell,SWT.PUSH);
		b1.setText("Open image file");
		b1.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		Text t =new Text(shell,SWT.MULTI | SWT.BORDER);
		t.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,2));
		b1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected	(SelectionEvent arg0) {
			   FileDialog file= new FileDialog(shell);
			   file.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
	
		
		Button b2 = new Button(shell,SWT.PUSH);
		b2.setText("Convert to ASCII art");
		
		b2.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		
		
	}
	

}
