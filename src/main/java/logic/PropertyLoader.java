package logic;

import java.io.IOException;

public class PropertyLoader {
	private static final String PROPERTIES = "config.properties";
	public static final String WINDOW_TITLE = "window.title";
	public static final String WINDOW_WIDTH = "window.width";
	public static final String WINDOW_HEIGHT = "window.height";
	public static final String GRAPHICS_ANTIALIASING = "graphics.antialiasing";
	public static final String WINDOW_RESIZABLE = "window.resizable";

	private static TypedProperties properties;

	public static TypedProperties getProperties() {
		if (properties == null) {
			properties = new TypedProperties();
			try {
				properties.load(PropertyLoader.class.getClassLoader().getResourceAsStream(PropertyLoader.PROPERTIES));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

}