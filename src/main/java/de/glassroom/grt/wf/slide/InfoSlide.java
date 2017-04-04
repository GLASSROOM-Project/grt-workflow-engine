package de.glassroom.grt.wf.slide;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import de.glassroom.grt.wf.Slide;
import de.glassroom.grt.wf.SlideController;
import de.glassroom.grt.wf.SlideType;

/**
 * Type for info (popup) slides.
 * @author simon.schwantzer(at)im-c.de
 */
public class InfoSlide extends Slide {
	private final String target;
	private final int delay;
	
	public InfoSlide(String id, SlideController controller, Element properties) throws IllegalArgumentException {
		super(id, SlideType.INFO, controller, properties);
		
		Element forwardElement = properties.getChild("forward", properties.getNamespace());
		if (forwardElement == null) {
			throw new IllegalArgumentException("Missing <forward> property.");
		}
		target = forwardElement.getAttributeValue("target");
		try {
			delay = forwardElement.getAttribute("delay").getIntValue();
		} catch (DataConversionException e) {
			throw new IllegalArgumentException("Failed to parse delay.", e);
		}
	}
	
	/**
	 * Returns the next slide to be shown.
	 * @return Slide identifier.
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * Returns the delay until the forward should be executed.
	 * @return Delay in seconds.
	 */
	public int getDelay() {
		return delay;
	}
}
