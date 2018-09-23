package com.commandExecuters;

import com.customExceptions.CommandException;

public interface CommandExecuter {
	public void executeCommand(String args[]) throws CommandException;
}
