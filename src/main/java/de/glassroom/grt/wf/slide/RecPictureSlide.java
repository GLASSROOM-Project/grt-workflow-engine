package de.glassroom.grt.wf.slide;

import org.jdom2.Element;

import de.glassroom.grt.wf.Slide;
import de.glassroom.grt.wf.SlideController;
import de.glassroom.grt.wf.SlideType;

/**
 * Slide for taking a picture.
 * @author simon.schwantzer(at)im-c.de
 */
public class RecPictureSlide extends Slide {

	public RecPictureSlide(String id, SlideController controller, Element properties) {
		super(id, SlideType.RECPICTURE, controller, properties);
	}

}
