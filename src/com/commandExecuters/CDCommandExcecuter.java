package com.commandExecuters;

import java.io.File;
import java.nio.file.Paths;
import static com.Main.currentRelativePath;

import com.customExceptions.CommandException;

public class CDCommandExcecuter implements CommandExecuter {

	@Override
	public void executeCommand(String[] args) throws CommandException {
		if (args.length == 1) {
			currentRelativePath = Paths.get("/");
			System.out.println("SUCC: REACHED");
		} else if (args.length == 2) {
			if (args[1] == "..") {
				currentRelativePath = currentRelativePath.getParent();
				System.out.println("SUCC: REACHED");
			}
			String currentpath = "";
			if (args[1].startsWith("/")) {
				currentpath = args[1];
			} else {
				currentpath = currentRelativePath.toAbsolutePath().toString() + "/" + args[1];
			}
			if (new File(currentpath).isDirectory()) {
				currentRelativePath = Paths.get(currentpath);
				System.out.println("SUCC: REACHED");
			} else {
				throw new CommandException("INVALID PATH");
			}
		} else {
			throw new CommandException("INVALID PATH");
		}
	}

}
