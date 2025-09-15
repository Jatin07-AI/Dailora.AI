package fileUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility {
	
	public String toGetDataFromPropertiesFile(String key) throws Throwable{
		
		FileInputStream fis = new FileInputStream("./src/test/resources/Commondata.properties");
		
		Properties pp = new Properties();
		
		pp.load(fis);
		
		String value = pp.getProperty(key);
		
		return value;
	}

}
