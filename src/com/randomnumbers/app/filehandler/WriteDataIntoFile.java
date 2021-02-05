package com.randomnumbers.app.filehandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WriteDataIntoFile {
	
	private Logger logger = Logger.getLogger(WriteDataIntoFile.class.getName());
	
	private String fileName;
	
	public WriteDataIntoFile() {
		this.fileName = FileHandlerConstants.DEFAULT_FILENAME;
	}
	
	public WriteDataIntoFile(String filename) {
		this.fileName = filename == null ? FileHandlerConstants.DEFAULT_FILENAME : filename;
	}
	
	public void writeNumbersIntoFile(List<Number> numbers) {
		Path path = Paths.get(this.fileName);
		try {
			Files.write(path, numbers.stream().map(num -> num.toString()).collect(Collectors.toList()));
		} catch (IOException e) {
			logger.warning(e.getLocalizedMessage());//TODO
		}
	}
}


