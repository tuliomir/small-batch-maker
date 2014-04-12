package com.tuliomir.smallbatchmaker.test;

import java.io.File;

public class PhysicalDirectory {
	
	public static java.io.File create(String directoryName) {
		java.io.File directory = new File(directoryName);
		boolean success = directory.mkdir();
		if (!success) {
			System.err.println("Unable to create directory.");
		}
		return directory;
	}
	
	public static void destroy(String directoryName) {
		java.io.File physicalDir = new java.io.File(directoryName);
		if (!physicalDir.isDirectory()) { 
			System.err.println("The file to be deleted is not a directory: " + physicalDir.getAbsolutePath());
		}
		physicalDir.delete();
	}
}
