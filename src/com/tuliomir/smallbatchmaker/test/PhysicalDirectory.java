package com.tuliomir.smallbatchmaker.test;

import com.tuliomir.smallbatchmaker.Directory;
import com.tuliomir.smallbatchmaker.File;

/**
 * Creates, destroys and manipulates physical directories, associating them with
 * logical representations when appropriate.
 * @author tuliomir
 *
 */
public class PhysicalDirectory {
	
	/**
	 * Creates a directory on the designated path and returns its logical
	 * representation.
	 * @param directoryName Directory name
	 * @return Logical representation of the directory
	 */
	public static File create(String directoryName) {
		File logicalFile = new File(createPhysicalDirectory(directoryName));
		return logicalFile;
	}
	
	/**
	 * Creates a physical directory on the designated path.
	 * @param directoryName Directory name
	 * @return The physical representation of the created directory
	 */
	private static java.io.File createPhysicalDirectory(String directoryName) {
		java.io.File physicalDirectory = new java.io.File(directoryName);
		boolean success = physicalDirectory.mkdir();
		
		if (!success) {
			System.err.println("Unable to create directory.");
		}
		
		return physicalDirectory;
	}
	
	/**
	 * Destroys a physical directory
	 * @param physicalDirectory Physical directory
	 */
	public static void destroy(java.io.File physicalDirectory) {
		if (!physicalDirectory.isDirectory()) {
			System.err.println("The file to be deleted is not a directory: " + physicalDirectory.getAbsolutePath());
		}
		if (!physicalDirectory.delete()) {
			System.err.println("Error deleting folder: " + physicalDirectory.getAbsolutePath());
		}
	}
	
	/**
	 * Destroys a physical directory with the associated name.<br>
	 * <em>WARNING:</em> Be careful about the path of the filename! It may be relative
	 * to where the program is running.
	 * @param directoryName Name of the file to be deleted
	 */
	public static void destroy(String directoryName) {
		java.io.File physicalDir = new java.io.File(directoryName);
		destroy(physicalDir);
	}
	
	/**
	 * Destroys a physical directory associated with the logical directory passed.
	 * @param directory Logical directory
	 */
	public static void destroy(Directory directory) {
		for(File fileEntry : directory.getListOfFiles()) {
			PhysicalFile.destroy(fileEntry);
		}
		PhysicalDirectory.destroy(directory.getPhysicalDirectory());
	}
	
	/**
	 * Creates a sub directory within a directory.
	 * This method eases the operations with File Separator names. 
	 * @param directoryName Directory which will have the new sub directory
	 * @param newDirName Name of the new directory
	 * @return Physical file reference to the new directory
	 */
	public static java.io.File createPhysicalSubDirectory(String directoryName, String newDirName) {
		String dirName = directoryName + java.io.File.separator + newDirName;
		
		return createPhysicalDirectory(dirName);
	}
}
