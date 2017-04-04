package de.glassroom.grt.wf;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for navigation on a slide.
 * 
 * @author simon.schwantzer(at)im-c.de
 */
public class SlideController {
	private final List<Command> commands;
	private Workflow workflow;
	
	/**
	 * Creates a new slide controller.
	 */
	public SlideController() {
		this.commands = new ArrayList<Command>();
	}
	
	protected void registerWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	
	/**
	 * Adds a possible command for the slide.
	 * @param command Command to add.
	 * @return This reference for chaining.
	 */
	public SlideController addCommand(Command command) {
		commands.add(command);
		return this;
	}
	
	/**
	 * Returns a list of all registered commands.
	 * @return Ordered list of of commands. May be empty.
	 */
	public List<Command> getCommands() {
		return commands;
	}
	
	/**
	 * Select a command.
	 * This will continue the workflow.
	 * @param key Key of the command to select.
	 * @throws IllegalArgumentException No command registered for the given key.
	 */
	public void select(String key) throws IllegalArgumentException {
		Command command = null;
		for (Command entry : commands) {
			if (entry.getKey().equals(key)) {
				command = entry; 
			}
		}
		if (command == null) throw new IllegalArgumentException("Unknown command.");
		String action = command.getAction();
		if (action != null) {
			performAction(action);
		}
		String target = command.getTarget();
		if (target != null) {
			workflow.setActiveSlide(target);
		}
	}
	
	private void performAction(String action) {
		// TODO Implement me.
	}
}
