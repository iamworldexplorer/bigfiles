//package com.randomnumbers.app.datasorter;
//
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class DataSorter {
//	
//	public static List<File> splitAndSortTempFiles(final String fileName,
//			final int noOfSplits) throws IOException {
//		List<File> files = new ArrayList<>();
//		RandomAccessFile raf = new RandomAccessFile(fileName, "r");
//		long sourceSize = raf.length();
//		long bytesPerSplit = sourceSize / noOfSplits;
//		long remainingBytes = sourceSize % noOfSplits;
//		int maxReadBufferSize = 8 * 1024; // 8KB
//		int fileCounter = 1;
//		for (int i = 1; i <= noOfSplits; i++) {
//			File file = new File("temp-file-" + fileCounter + ".txt");
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//			if (bytesPerSplit > maxReadBufferSize) {
//				long numReads = bytesPerSplit / maxReadBufferSize;
//				long numRemainingRead = bytesPerSplit % maxReadBufferSize;
//				for (int j = 0; j < numReads; j++) {
//					readWrite(raf, bos, maxReadBufferSize);
//				}
//				if (numRemainingRead > 0) {
//					readWrite(raf, bos, numRemainingRead);
//				}
//			} else {
//				readWrite(raf, bos, bytesPerSplit);
//			}
//			file = sortFileContent(file);
//			files.add(file);
//			fileCounter++;
//			bos.close();
//		}
//		if (remainingBytes > 0) {
//			File file = new File("temp-file-" + fileCounter + ".txt");
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//			readWrite(raf, bos, remainingBytes);
//			file = sortFileContent(file);
//			files.add(file);
//			bos.close();
//		}
//		return files;
//	}
//	private static void readWrite(RandomAccessFile raf, BufferedOutputStream bos, long numBytes) throws IOException {
//		byte[] buf = new byte[(int) numBytes];
//		int val = raf.read(buf);
//		if (val != -1) {
//			bos.write(buf);
//			bos.flush();
//		}
//	}
//	/**
//	 * Sort file content
//	 *
//	 * @param file
//	 * @return file
//	 * @throws IOException
//	 */
//	private static File sortFileContent(File file) throws IOException {
//		List<Integer> lines = new ArrayList<>();
//		try (Stream<String> ln = Files.lines(file.toPath())) {
//			lines = ln.filter(str -> !str.isEmpty())
//					.map(str -> Integer.valueOf(str))
//					.collect(Collectors.toList());
//		}
//		Collections.sort(lines);
//		try (BufferedWriter bw = Files.newBufferedWriter(file.toPath())) {
//			for (Integer line : lines) {
//				bw.write(line.toString());
//				bw.write("\r\n");
//			}
//		}
//		return file;
//	}
//	
//	public static void mergeSortedFiles(final List<File> files, final String outputFile)
//			throws IOException {
//		List<BufferedReader> brReaders = new ArrayList<>();
//		TreeMap<Integer, BufferedReader> map = new TreeMap<>();
//		File f = new File(outputFile);
//		if (f.exists()) {
//			f.delete();
//		}
//		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true));
//		try {
//			for (File file : files) {
//				BufferedReader br = new BufferedReader(new FileReader(file));
//				brReaders.add(br);
//				String line = br.readLine();
//				if(line != null) {
//					Integer mapKey = Integer.valueOf(line);
//					map.put(mapKey, br);
//				}
//			}
//			while (!map.isEmpty()) {
//				Map.Entry<Integer, BufferedReader> nextToGo = map.pollFirstEntry();
//				bw.write(nextToGo.getKey());
//				bw.write("\r\n");
//				String line = nextToGo.getValue().readLine();
//				if (line != null) {
//					Integer mapKey = Integer.valueOf(line);
//					map.put(mapKey, nextToGo.getValue());
//				}
//			}
//		} finally {
//			if (brReaders != null) {
//				for (BufferedReader br : brReaders) {
//					br.close();
//				}
//				for (File file : files) {
//					file.delete();
//				}
//			}
//			if (bw != null) {
//				bw.close();
//			}
//		}
//	}
//}
