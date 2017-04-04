package de.glassroom.grt.wf.slide;

import org.jdom2.Element;

import de.glassroom.grt.wf.Slide;
import de.glassroom.grt.wf.SlideController;
import de.glassroom.grt.wf.SlideType;

/**
 * Slide for recording video.
 * @author simon.schwantzer(at)im-c.de
 */
public class RecVideoSlide extends Slide {
	private final String next;

	public RecVideoSlide(String id, SlideController controller, Element properties) {
		super(id, SlideType.RECVIDEO, controller, properties);

		Element nextElement = properties.getChild("next", properties.getNamespace());
		if (nextElement == null) {
			throw new IllegalArgumentException("Missing <next> property for slide " + getId() + ".");
		}
		next = nextElement.getText();
	}

	/**
	 * Returns the succeeding slide.
	 * @return Slide identifier.
	 */
	public String getNext() {
		return next;
	}

}
