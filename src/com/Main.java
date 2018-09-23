package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.commandExecuters.CDCommandExcecuter;
import com.commandExecuters.CommandExecuter;
import com.commandExecuters.LsCommandExcecuter;
import com.commandExecuters.MkdirCommandExecuter;
import com.commandExecuters.PwdCommandExcecuter;
import com.commandExecuters.RmCommandExcecuter;

public class Main {
	// Get the current path where application is running
	public static Path currentRelativePath = Paths.get("");
	// List to remove the program generated files folders
	public static List<String> dataToRemove = new ArrayList<>();
	// map to keep track of files which will be removed by rm
	public static Map<String, String> dataToRestore = new HashMap<>();
	// a backup path where remove data will be saved to restore on clear session, by
	// deafult it will be in the application path
	public static String backupPath = currentRelativePath.toAbsolutePath().toString() + "\\backup";

	public static void main(String[] args) throws java.io.IOException {
		String commandLine;
		File dir = new File(backupPath);
		// creating the backup folder
		dir.mkdir();

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("$> <Starting your application...>");
		while (true) {
			System.out.print("$> ");
			commandLine = console.readLine();
			String inputArry[] = commandLine.trim().split(" ");
			CommandExecuter commandExecuter = null;
			switch (inputArry[0]) {
			case "cd":
				commandExecuter = new CDCommandExcecuter();
				break;
			case "mkdir":
				commandExecuter = new MkdirCommandExecuter();
				break;
			case "rm":
				commandExecuter = new RmCommandExcecuter();
				break;
			case "pwd":
				commandExecuter = new PwdCommandExcecuter();
				break;
			case "ls":
				commandExecuter = new LsCommandExcecuter();
				break;
			case "session":
				if (inputArry[1].equals("clear")) {

					currentRelativePath = Paths.get("");
					for (Map.Entry<String, String> entry : dataToRestore.entrySet()) {
						File file = new File(backupPath + "\\" + entry.getKey());
						file.renameTo(new File(entry.getValue(), entry.getKey()));

					}
					dataToRestore.clear();
					for (String data : dataToRemove) {
						File file = new File(data);
						file.delete();
					}
					dataToRemove.clear();

					System.out.println("SUCC: CLEARED: RESET TO ROOT");
				} else {
					System.out.println("ERR: CANNOT RECOGNIZE INPUT.");
				}
				break;
			case "exit":
				System.out.println("Bye");
				System.exit(0);
				break;
			default:
				System.out.println("ERR: CANNOT RECOGNIZE INPUT.");
				break;
			}
			if (commandExecuter == null)
				continue;
			try {
				commandExecuter.executeCommand(inputArry);
			} catch (Exception e) {
				System.out.println("ERR: " + e.getMessage());
			}
		}

	}
}
