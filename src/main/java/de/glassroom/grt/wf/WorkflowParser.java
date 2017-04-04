package de.glassroom.grt.wf;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import de.glassroom.grt.wf.slide.DefaultSlide;
import de.glassroom.grt.wf.slide.InfoSlide;
import de.glassroom.grt.wf.slide.ListSlide;
import de.glassroom.grt.wf.slide.PrepVideoSlide;
import de.glassroom.grt.wf.slide.RecAudioSlide;
import de.glassroom.grt.wf.slide.RecPictureSlide;
import de.glassroom.grt.wf.slide.RecVideoSlide;
import de.glassroom.grt.wf.slide.SplashSlide;

public final class WorkflowParser {
	private static SAXBuilder saxBuilder;
	static {
		saxBuilder = new SAXBuilder();
	}
	
	private WorkflowParser() {}
	
	/**
	 * Parses a workflow and returns a workflow instance.
	 * @param workflow Workflow in XML representation.
	 * @return Workflow manager instance.
	 * @throws IllegalArgumentException The given string is no valid workflow XML.
	 */
	public static Workflow parseWorkflow(String workflow) throws IllegalArgumentException {
		Element workflowElement;
		try {
			Document doc = saxBuilder.build(new StringReader(workflow));
			workflowElement = doc.getRootElement();
			if (!"workflow".equals(workflowElement.getName())) {
				throw new IllegalArgumentException("Invalid workflow: Route element does not match \"process\".");
			}
		} catch (JDOMException|IOException e) {
			throw new IllegalArgumentException("Failed to parse workflow.", e);
		}
		
		String beginWith = workflowElement.getAttributeValue("beginWith");
		if (beginWith == null) {
			throw new IllegalArgumentException("Missing [beginWith] attribute with slide identifier.");
		}
	
		Map<String, Slide> slides = new HashMap<String, Slide>();
		
		for (Element slideElement : workflowElement.getChildren("slide", workflowElement.getNamespace())) {
			Slide slide = parseSlide(slideElement);
			if (slides.containsKey(slide.getId())) {
				throw new IllegalArgumentException("Two slides with same identifier: " + slide.getId());
			}
			slides.put(slide.getId(), slide);
		}
		
		if (!slides.containsKey(beginWith)) {
			throw new IllegalArgumentException("The slide " + beginWith + " does not exists. No slide available to start with.");
		}
		
		return new Workflow(slides, beginWith);
	}
	
	private static Slide parseSlide(Element slideElement) throws IllegalArgumentException {
		Namespace ns = slideElement.getNamespace();
		String id = slideElement.getAttributeValue("id");
		if (id == null) {
			throw new IllegalArgumentException("Missing required attribute for slide: id");
		}
		
		String typeString = slideElement.getAttributeValue("type", "<na>");
		SlideType type;
		try {
			type = SlideType.valueOf(typeString.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Missing or invalid slide type: " + typeString , e);
		}
		SlideController controller = new SlideController();
		Element commandsElement = slideElement.getChild("commands", ns);
		if (commandsElement != null) for (Element commandElement : commandsElement.getChildren("command", ns)) {
			Command command = parseCommand(commandElement);
			controller.addCommand(command);
		}
		
		Element propertiesElement = slideElement.getChild("properties", ns);
		
		Slide slide;
		switch (type) {
		case DEFAULT:
			slide = new DefaultSlide(id, controller, propertiesElement);
			break;
		case INFO:
			slide = new InfoSlide(id, controller, propertiesElement);
			break;
		case LIST:
			slide = new ListSlide(id, controller, propertiesElement);
			break;
		case PREPVIDEO:
			slide = new PrepVideoSlide(id, controller, propertiesElement);
			break;
		case RECAUDIO:
			slide = new RecAudioSlide(id, controller, propertiesElement);
			break;
		case RECPICTURE:
			slide = new RecPictureSlide(id, controller, propertiesElement);
			break;
		case RECVIDEO:
			slide = new RecVideoSlide(id, controller, propertiesElement);
			break;
		case SPLASH:
			slide = new SplashSlide(id, controller, propertiesElement);
			break;
		default:
			throw new IllegalArgumentException("Unknown slide type: " + typeString);
		}
		
		return slide;
	}
	
	private static Command parseCommand(Element commandElement) throws IllegalArgumentException {
		String key = commandElement.getAttributeValue("key");
		String target = commandElement.getAttributeValue("target");
		String action = commandElement.getAttributeValue("action");
		String label = commandElement.getAttributeValue("label");
		if (key == null || (target == null && action == null)) {
			throw new IllegalArgumentException("A [key] and either a [target] or an [action] are required attributes of a command.");
		}
		
		List<String> voiceCommands;
		String voiceCommandsString = commandElement.getAttributeValue("voiceCommands");
		if (voiceCommandsString != null) {
			voiceCommands = new ArrayList<String>(Arrays.asList(voiceCommandsString.split(";")));
		} else {
			voiceCommands = new ArrayList<String>();
		}
		Command command =  new Command(key, label, target, action, voiceCommands);
		return command;
	}
}
