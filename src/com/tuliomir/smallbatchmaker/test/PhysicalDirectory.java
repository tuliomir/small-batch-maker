package com.tuliomir.smallbatchmaker.test;

import com.tuliomir.smallbatchmaker.Directory;
import com.tuliomir.smallbatchmaker.File;

public class PhysicalDirectory {
	
	public static java.io.File create(String directoryName) {
		java.io.File directory = new java.io.File(directoryName);
		boolean success = directory.mkdir();
		if (!success) {
			System.err.println("Unable to create directory.");
		}
		return directory;
	}
	
	public static void destroy(java.io.File physicalDirectory) {
		if (!physicalDirectory.isDirectory()) {
			System.err.println("The file to be deleted is not a directory: " + physicalDirectory.getAbsolutePath());
		}
		if (!physicalDirectory.delete()) {
			System.err.println("Error deleting folder: " + physicalDirectory.getAbsolutePath());
		}
	}
	
	public static void destroy(String directoryName) {
		java.io.File physicalDir = new java.io.File(directoryName);
		destroy(physicalDir);
	}
	
	public static void destroy(Directory directory) {
		for(File fileEntry : directory.getListOfFiles()) {
			PhysicalFile.destroy(fileEntry);
		}
		PhysicalDirectory.destroy(directory.getPhysicalDirectory());
	}
}
