package de.glassroom.grt.wf.slide;

import org.jdom2.Element;

import de.glassroom.grt.wf.Slide;
import de.glassroom.grt.wf.SlideController;
import de.glassroom.grt.wf.SlideType;

/**
 * Slide containing a list to select an item from.
 * @author simon.schwantzer(at)im-c.de
 *
 */
public class ListSlide extends Slide {
	
	public ListSlide(String id, SlideController controller, Element properties) {
		super(id, SlideType.LIST, controller, properties);
	}

}
