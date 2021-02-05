package com.randomnumbers.app.filehandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class WriteDataIntoFile {
	
	private String fileName;
	
	public WriteDataIntoFile() {
		this.fileName = FileHandlerConstants.DEFAULT_FILENAME;
	}
	
	public WriteDataIntoFile(String filename) {
		this.fileName = filename == null ? FileHandlerConstants.DEFAULT_FILENAME : filename;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void writeNumbersIntoFile(List<Number> numbers) {
		Path path = Paths.get(this.fileName);
		try {
			Files.write(path, numbers.stream().map(num -> num.toString()).collect(Collectors.toList()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

