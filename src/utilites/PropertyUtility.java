package utilites;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyUtility {

	
	Properties prop = new Properties();
	
	
	public PropertyUtility(String fileName)
	{
		try{
		InputStream input =  null;
		input = new FileInputStream(fileName);
		prop.load(input);				
		
		}
		catch (IOException e) {
			System.out.println("Error "+e);
	   }
	}
	public String getEnvironmentProperty(String propertyKey)
	{
		String propertyValue=null;
		propertyValue=prop.getProperty(propertyKey);
		
		return propertyValue;
		
	}
	
	public int getSizeOfProp()
	{
		return prop.size();
	}

}