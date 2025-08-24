package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	Properties properties;
	FileInputStream ip;

	public ReadConfig() throws IOException {
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\config.properties";
		ip = new FileInputStream(filepath);

		properties = new Properties();
		properties.load(ip);
	}

	public String getProperty(String property) {
		return properties.getProperty(property);
	}
}
