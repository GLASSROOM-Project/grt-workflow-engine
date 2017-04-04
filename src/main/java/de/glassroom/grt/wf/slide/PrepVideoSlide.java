package de.glassroom.grt.wf.slide;

import org.jdom2.Element;

import de.glassroom.grt.wf.Slide;
import de.glassroom.grt.wf.SlideController;
import de.glassroom.grt.wf.SlideType;

/**
 * Slide displaying a video in the backgroud to prepare video recordings.
 * @author simon.schwantzer(at)im-c.de
 */
public class PrepVideoSlide extends Slide {

	public PrepVideoSlide(String id, SlideController controller, Element properties) {
		super(id, SlideType.PREPVIDEO, controller, properties);
	}

}
