package com.tuliomir.smallbatchmaker.test;

import java.io.FileWriter;
import java.io.IOException;

import com.tuliomir.smallbatchmaker.File;

public class PhysicalFile {
	
	/**
	 * Creates an empty test file
	 * @param fileName Name of the file to be created
	 * @return java.io.File pointing to the empty file
	 */
	public static java.io.File create(String fileName) {
		java.io.File physicalFile = new java.io.File(fileName);
		
		try {
			if (!physicalFile.createNewFile()) {
				physicalFile.delete();
				if (!physicalFile.createNewFile()) {
					System.err.println("Cannot empty file.");
				}
			}
		} catch (IOException e) {
			System.err.println("Error creating file: " + fileName);
			e.printStackTrace();
		}
		return physicalFile;
	}
	
	
	/**
	 * Creates a test file with a specified content
	 * @param fileName Name of the file to be created
	 * @param fileContent Content of the file
	 * @return java.io.File containing the specified content
	 */
	public static java.io.File create(String fileName, String fileContent) {
		java.io.File physicalFile = create(fileName);
		increment(physicalFile, fileContent);
		return physicalFile;
	}
	
	public static void increment(java.io.File physicalFile, String incrementalString) {
		try {
			FileWriter out = new FileWriter(physicalFile);
			out.append(incrementalString);
			out.close();
		} catch (IOException e) {
			System.err.println("Error writing to file: " + physicalFile.getAbsolutePath());
			e.printStackTrace();
		}
	}
	
	public static void destroy(String fileName) {
		java.io.File physicalFile = new java.io.File(fileName);
		physicalFile.delete();
	}
	
	public static void destroy(java.io.File physicalFile) {
		physicalFile.delete();
	}
	
	public static void destroy(File file) {
		if (file.isDirectory()) {
			PhysicalDirectory.destroy(file.getDirectory());
		} else {
			file.getPhysicalFile().delete();
		}
	}
}
