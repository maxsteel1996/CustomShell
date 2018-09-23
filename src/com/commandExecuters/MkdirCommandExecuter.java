package com.commandExecuters;

import java.io.File;

import static com.Main.dataToRemove;
import com.customExceptions.CommandException;
import static com.Main.currentRelativePath;
public class MkdirCommandExecuter implements CommandExecuter {

	@Override
	public void executeCommand(String[] args) throws CommandException {
		for (int i = 1; i < args.length; i++) {
			String currentpath = "";
			if (args[1].startsWith("/"))
				currentpath = args[1];
			else
				currentpath = currentRelativePath.toAbsolutePath().toString() + "/" + args[1];
			File dir = new File(currentpath);
			boolean successful = dir.mkdir();
			if (successful) {
				dataToRemove.add(currentpath.toString());
				System.out.println("SUCC: CREATED");
			} else
				throw new CommandException("ALREADY EXISTS");
		}

	}

}
