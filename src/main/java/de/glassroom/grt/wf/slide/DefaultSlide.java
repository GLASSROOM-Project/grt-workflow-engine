package de.glassroom.grt.wf.slide;

import org.jdom2.Element;

import de.glassroom.grt.wf.Slide;
import de.glassroom.grt.wf.SlideController;
import de.glassroom.grt.wf.SlideType;

/**
 * Default slide type.
 * @author simon.schwantzer(at)im-c.de
 */
public class DefaultSlide extends Slide {
	
	/**
	 * Creates a new slide.
	 * @param id Identifier for the slide.
	 * @param controller Slide controller.
	 * @param properties Property container.
	 */
	public DefaultSlide(String id, SlideController controller, Element properties) {
		super(id, SlideType.DEFAULT, controller, properties);
	}
}
