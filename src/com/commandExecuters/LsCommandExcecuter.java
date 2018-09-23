package com.commandExecuters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static com.Main.currentRelativePath;

import com.customExceptions.CommandException;

public class LsCommandExcecuter implements CommandExecuter{

	@Override
	public void executeCommand(String[] args) throws CommandException {
		File folder = new File(currentRelativePath.toAbsolutePath().toString());
		File[] listOfFiles = folder.listFiles();
		List<String> files = new ArrayList<>();
		List<String> folders = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				folders.add(listOfFiles[i].getName());
			}
		}
		System.out.print("DIRS: ");
		for (String dir : folders)
			System.out.print(dir + " ");
		System.out.println();
		System.out.print("FILES: ");
		for (String fileName : files)
			System.out.print(fileName + " ");
		System.out.println();
		
	}

}
