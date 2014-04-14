package com.tuliomir.smallbatchmaker.test;

import java.io.FileWriter;
import java.io.IOException;

import com.tuliomir.smallbatchmaker.File;

/**
 * Creates, destroys and manipulates physical files, associating them with
 * logical Files when appropriate.
 * @author tuliomir
 *
 */
public class PhysicalFile {
	
	/**
	 * Creates an empty file
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
	
	/**
	 * Increments the size of a file by appending a string to it.
	 * @param physicalFile Physical file to be incremented.
	 * @param incrementalString String to be appended.
	 */
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
	
	/**
	 * Destroys a physical file with the associated name.<br>
	 * <em>WARNING:</em> Be careful about the path of the filename! It may be relative
	 * to where the program is running.
	 * @param fileName Name of the file to be deleted
	 */
	public static void destroy(String fileName) {
		java.io.File physicalFile = new java.io.File(fileName);
		physicalFile.delete();
	}
	
	/**
	 * Destroys a physical file
	 * @param physicalFile Physical file to be destroyed
	 */
	public static void destroy(java.io.File physicalFile) {
		physicalFile.delete();
	}
	
	/**
	 * Destroys the physical file associated to a logical file
	 * @param file Logical file which will have its physical file deleted
	 */
	public static void destroy(File file) {
		if (file.isDirectory()) {
			PhysicalDirectory.destroy(file.getDirectory());
		} else {
			file.getPhysicalFile().delete();
		}
	}
	

	/**
	 * Creates a Logical file with a specific size
	 * @param fileName Name of the file
	 * @param fileSize Size of the file in bytes
	 * @return Logical encapsulation of the physical file created 
	 */
	public static java.io.File createFileWithSize(String fileName, int fileSize){
		String testString;
		
		// Generating random string
		StringBuffer outputBuffer = new StringBuffer(fileSize);
		
		for (int i=0; i<fileSize; i++) {
			outputBuffer.append("a");
		}
		testString = outputBuffer.toString();
		
		// Pushing the string into the file
		java.io.File result = create(fileName, testString);
		
		return result;
	}
	
	/**
	 * Creates a file within a directory.
	 * This method eases the operations with File Separator names. 
	 * @param directoryName Directory which will have the new file
	 * @param newFileName Name of the new file
	 * @return Physical file reference to the new file
	 */
	public static java.io.File createFileWithinDirectory(String directoryName, String newFileName) {
		String fileName = directoryName + java.io.File.separator + newFileName;
		
		return PhysicalFile.create(fileName);
	}
	
	/**
	 * Creates a file with a specific size within a directory.
	 * This method eases the operations with File Separator names. 
	 * @param directoryName Directory which will have the new file
	 * @param newFileName Name of the new file
	 * @param fileSize Size of the new file
	 * @return Physical file reference to the new file
	 */
	public static java.io.File createFileWithinDirectory(String directoryName, String newFileName, int fileSize) {
		String fileName = directoryName + java.io.File.separator + newFileName;
		
		return PhysicalFile.createFileWithSize(fileName, fileSize);
	}
}
