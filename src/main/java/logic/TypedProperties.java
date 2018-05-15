package logic;

import java.util.Properties;

public class TypedProperties extends Properties {

	public Boolean getBoolean(String key) {
		return Boolean.parseBoolean(getProperty(key));
	}

	public Integer getInteger(String key) {
		return Integer.parseInt(getProperty(key));
	}

}