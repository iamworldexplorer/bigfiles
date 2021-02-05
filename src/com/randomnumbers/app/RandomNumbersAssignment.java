package com.randomnumbers.app;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.randomnumbers.app.filehandler.ReadDataFromFile;
import com.randomnumbers.app.filehandler.WriteDataIntoFile;
import com.randomnumbers.app.randomgenerator.RandomGeneratorConfig;

public class RandomNumbersAssignment {

	public static void main(String[] args) throws IOException {
		String[] arr = new String[2];
		String fileName = null;
		String type = null;
		Scanner sc= new Scanner(System.in);
		System.out.print("Please enter a file name and a type of data(Integer, Double, Long): ");  
		int countArgs = 0;
		while(countArgs < 2) {
			arr[countArgs++]= sc.nextLine();
		}
		fileName = !arr[0].isEmpty() ? arr[0] : null;
		type = !arr[1].isEmpty() ? arr[1] : null;
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		RandomGeneratorConfig rgc = new RandomGeneratorConfig();
		List<Number> numbers = rgc.generateRandomNumbers(type);
		System.out.println(numbers);
		
		WriteDataIntoFile writeNumbers = new WriteDataIntoFile(fileName);
		writeNumbers.writeNumbersIntoFile(numbers);
		
		ReadDataFromFile readNumbers = new ReadDataFromFile(fileName, type);
		List<Number> numbersFromFile = readNumbers.getNumbersFromFile();
		System.out.println(numbersFromFile);
		
		
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