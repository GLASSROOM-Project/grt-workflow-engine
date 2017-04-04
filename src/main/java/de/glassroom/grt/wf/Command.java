package de.glassroom.grt.wf;

import java.util.List;

/**
 * Single command for a slide.
 * 
 * @author simon.schwantzer(at)im-c.de
 */
public class Command {
	private final String label;
	private final String key;
	private final String target;
	private final String action;
	private final List<String> voiceCommands;
	
	/**
	 * Creates a new command
	 * @param key Key unique within all commands of a controller.
	 * @param label Label for a command button. May be <code>null</code>.
	 * @param target Slide to be shown when the command is performed. May be <code>null</code>.
	 * @param action Action to be performed. May be <code>null</code>.
	 * @param voiceCommands List of voice commands. May be empty.
	 */
	public Command(String key, String label, String target, String action, List<String> voiceCommands) {
		this.key = key;
		this.label = label;
		this.target = target;
		this.action = action;
		this.voiceCommands = voiceCommands;
	}
	
	/**
	 * Returns the command key.
	 * @return Key unique within all commands of a controller.
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Returns the label for the command.
	 * @return Label for a command button. May be <code>null</code>.
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Returns the action to be performed.
	 * @return Action identifier. May be <code>null</code>.
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * Returns the target slide. 
	 * @return Slide to be shown when the command is performed. May be <code>null</code>.
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * Returns the available voice commands.
	 * @return List of voice commands. May be empty.
	 */
	public List<String> getVoiceCommands() {
		return voiceCommands;
	}
}