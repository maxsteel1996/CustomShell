package com.commandExecuters;
import static com.Main.currentRelativePath;
import com.customExceptions.CommandException;
public class PwdCommandExcecuter implements CommandExecuter{

	@Override
	public void executeCommand(String[] args) throws CommandException {
		System.out.println(currentRelativePath.toAbsolutePath().toString());		
	}

}
