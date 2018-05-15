package logic;

import java.awt.*;

public interface ResizeableElement {

	void onResize();

	default Dimension currentSize() {
		return new Dimension(PropertyLoader.getProperties().getInteger(PropertyLoader.WINDOW_WIDTH),
				PropertyLoader.getProperties().getInteger(PropertyLoader.WINDOW_HEIGHT));
	}
}