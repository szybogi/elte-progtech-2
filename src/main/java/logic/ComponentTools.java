package logic;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComponentTools {

	public static <T> List<T> findComponents(final Container container, final Class<T> componentType) {
		return Stream.concat(Arrays.stream(container.getComponents())
						.filter(componentType::isInstance)
						.map(componentType::cast),
				Arrays.stream(container.getComponents())
						.filter(Container.class::isInstance)
						.map(Container.class::cast)
						.flatMap(c -> findComponents(c, componentType).stream())
		).collect(Collectors.toList());
	}

}