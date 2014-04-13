package com.tuliomir.smallbatchmaker.test;

import com.tuliomir.smallbatchmaker.Directory;
import com.tuliomir.smallbatchmaker.File;

public class PhysicalDirectory {
	
	public static File create(String directoryName) {
		java.io.File physicalDirectory = new java.io.File(directoryName);
		boolean success = physicalDirectory.mkdir();
		if (!success) {
			System.err.println("Unable to create directory.");
		}
		File logicalFile = new File(physicalDirectory);
		return logicalFile;
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
