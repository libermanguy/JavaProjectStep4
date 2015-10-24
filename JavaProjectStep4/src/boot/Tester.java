package boot;

import general.Properties;

public class Tester {

	public static void main(String[] args) throws Exception {
			Properties prop = new Properties();
			
			prop.saveProp("c:\\temp\\test23.xml");
			
			prop.loadProp("c:\\temp\\test23.xml");
	}

}
