package com.randomnumbers.app.filehandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.randomnumbers.app.randomgenerator.RandomGeneratorConstants;

public class ReadDataFromFile {
	private String fileName;
	private String type;

	public ReadDataFromFile() {
		this.fileName = FileHandlerConstants.DEFAULT_FILENAME;
		this.type = RandomGeneratorConstants.RANDOM_GENERATOR_TYPE_INTEGER;
	}

	public ReadDataFromFile(String fileName, String type) {
		this.fileName = fileName == null ? FileHandlerConstants.DEFAULT_FILENAME : fileName;
		this.type = type == null ? RandomGeneratorConstants.RANDOM_GENERATOR_TYPE_INTEGER : type.toUpperCase();
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
		try {
			if (RandomGeneratorConstants.RANDOM_GENERATOR_TYPE_DOUBLE.equals(this.type)) {
				number = Double.valueOf(str);
			} else if (RandomGeneratorConstants.RANDOM_GENERATOR_TYPE_LONG.equals(this.type)) {
				number = Long.valueOf(str);
			} else {
				number = Integer.valueOf(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return number;
	}
}
