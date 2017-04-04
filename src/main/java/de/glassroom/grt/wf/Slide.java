package de.glassroom.grt.wf;

import org.jdom2.Element;

/**
 * Interface for slides.
 * 
 * @author simon.schwantzer(at)im-c.de
 */
public abstract class Slide {
	private final String id;
	private final SlideType type;
	private final SlideController controller;
	protected final Element properties;
	
	/**
	 * Creates a new slide.
	 * @param id Identifier for the slide. The identifier should be unique within a workflow.
	 * @param type Type of the slide.
	 * @param controller Controller for the slide.
	 * @param properties Properties for this slide.
	 */
	public Slide(String id, SlideType type, SlideController controller, Element properties) {
		this.id = id;
		this.type = type;
		this.controller = controller;
		this.properties = properties;
	}
	
	/**
	 * Returns the unique identifier for the slide.
	 * @return Unique identifier.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Returns the type of the slide.
	 * @return Slide type.
	 */
	public SlideType getType() {
		return type;
	}
	
	/**
	 * Returns the controller for the slide.
	 * @return Slide controller.
	 */
	public SlideController getController() {
		return controller;
	}
	
	/**
	 * Sets the template for this slide. 
	 * @param template Path of template.
	 * @return Reference for chaining.
	 */
	public Slide setTemplate(String template) {
		return this;
	}
	
	/**
	 * Returns the template of this slide.
	 * @return Path of template or <code>null</code>.
	 */
	public String getTemplatePath() {
		Element templateElement = properties.getChild("template", properties.getNamespace());
		return templateElement != null ? templateElement.getText() : null;
	}
	
	/**
	 * Returns the background of this slide.
	 * @return Path of the background image or <code>null</code>.
	 */
	public String getBackgroundPath() {
		Element backgroundElement = properties.getChild("background", properties.getNamespace());
		return backgroundElement != null ? backgroundElement.getText() : null;
	}
	
	/**
	 * Returns the activity notifier to display. 
	 * @return Path of the activity icon or <code>null</code> if no activity notifier should be displayed.
	 */
	public String getActivityNotifier() {
		Element activityNotifierElement = properties.getChild("activity-notifier", properties.getNamespace());
		return activityNotifierElement != null ? activityNotifierElement.getText() : null;
	}
	
	/**
	 * Returns the raw properties of the slide.
	 * @return Properties as XML element.
	 */
	public Element getRawProperties() {
		return properties;
	}
}
