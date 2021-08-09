package dal;

import java.io.IOException;
import java.util.Properties;

public class Settings {

	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.loadFromXML(Settings.class.getResourceAsStream("settings.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		
		String valeur = properties.getProperty(key);
		return valeur;
	}
}
