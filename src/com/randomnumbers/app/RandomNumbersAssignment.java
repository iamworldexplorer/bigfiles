package com.randomnumbers.app;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.randomnumbers.app.filehandler.ReadDataFromFile;
import com.randomnumbers.app.filehandler.WriteDataIntoFile;
import com.randomnumbers.app.randomgenerator.RandomGeneratorConfig;

public class RandomNumbersAssignment {

	public static void main(String[] args) throws IOException {
		String fileNameRandom = null;
		String fileNameSorted = null;
		String type = null;
		Scanner sc= new Scanner(System.in);
		System.out.println("Please enter a type of data(Integer, Double, Long): ");
		type = sc.nextLine();
		System.out.println("Please enter a name of file for storing randomly generated numbers:");
		fileNameRandom = sc.nextLine();
		System.out.println("Please enter a name of file for storing sorted numbers:");
		fileNameSorted = sc.nextLine();
		
		RandomGeneratorConfig rgc = new RandomGeneratorConfig();
		List<Number> numbers = rgc.generateRandomNumbers(type);
		System.out.println(numbers);
		
		WriteDataIntoFile writeNumbers = new WriteDataIntoFile(fileNameRandom);
		writeNumbers.writeNumbersIntoFile(numbers);
		
		ReadDataFromFile readNumbers = new ReadDataFromFile(fileNameRandom);
		List<Number> numbersFromFile = readNumbers.getNumbersFromFile();
		System.out.println(numbersFromFile);
		
		Arrays.sort(numbersFromFile.toArray());
		if(type != null && type.equals("Double")) {
			Collections.sort(numbersFromFile, (a, b) -> Double.compare((double)a, (double)b));
		}
		
		if(fileNameSorted != null) {
			writeNumbers.setFileName(fileNameSorted);
		}
		writeNumbers.writeNumbersIntoFile(numbersFromFile);
//		long start1 = System.currentTimeMillis();
//		ReadDataFromFile readNumbers = new ReadDataFromFile("myfile.txt");
//		List<Integer> numbersFromFile = readNumbers.getNumbersFromFile();
//		Collections.sort(numbersFromFile);
//		WriteDataIntoFile writeNumbers1 = new WriteDataIntoFile("sorted1.txt");
//		writeNumbers1.writeNumbersIntoFile(numbersFromFile);
//		System.out.println("In-memory: " + (System.currentTimeMillis() - start1));
		//System.out.println(numbersFromFile);
		
		
//		Path path = Paths.get("myfile.txt");
//		long start = System.currentTimeMillis();
//		List<File> files = DataSorter.splitAndSortTempFiles(path.toString(), 5);
//		DataSorter.mergeSortedFiles(files, "sorted.txt");
//		System.out.println("With files: " + (System.currentTimeMillis() - start));
	}
}