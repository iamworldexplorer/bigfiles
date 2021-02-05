package com.randomnumbers.app.filehandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadDataFromFile {
	private String fileName;

	public ReadDataFromFile() {
		this.fileName = FileHandlerConstants.DEFAULT_FILENAME;
	}

	public ReadDataFromFile(String fileName) {
		this.fileName = fileName == null ? FileHandlerConstants.DEFAULT_FILENAME : fileName;
	}

	public List<Number> getNumbersFromFile() {
		List<Number> numbers = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(this.fileName));
			numbers = lines.stream()
					.map(str -> getNumberFromString(str))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numbers;
	}

	private Number getNumberFromString(String str) {
		Number number = null;
		if(str == null || str.isEmpty()) {
			return null;
		}
		try {
			if (str.contains(".")) {
				number = Double.valueOf(str);
			} else {
				number = Long.valueOf(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return number;
	}
}