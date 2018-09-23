package com.commandExecuters;

import java.io.File;
import static com.Main.currentRelativePath;
import static com.Main.dataToRestore;
import static com.Main.backupPath;
import com.customExceptions.CommandException;

public class RmCommandExcecuter implements CommandExecuter {

	@Override
	public void executeCommand(String[] args) throws CommandException {

		for (int i = 1; i < args.length; i++) {
			String currentpath = "";
			if (args[1].startsWith("/"))
				currentpath = args[1];
			else
				currentpath = currentRelativePath.toAbsolutePath().toString() + "/" + args[1];
			File dir = new File(currentpath);
			dataToRestore.put(dir.getName(), currentRelativePath.toAbsolutePath().toString());
			boolean success = dir.renameTo(new File(backupPath, dir.getName()));
			if (success)
				System.out.println("SUCC: DELETED");
			else
				System.out.println("ERR: Failed to delete the file");
		}
	}

}
