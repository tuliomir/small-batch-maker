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
		String dirName = pathToDirectory(directoryName, newDirName);

		return createPhysicalDirectory(dirName);
	}
	
	/**
	 * Returns the path to a new file, with the correct separator names.<br>
	 * Ex.: On a linux OS, the invocation <code>pathToDirectory("testDir", "myFile.txt")</code>
	 * will result in <code>testDir/myFile.txt</code>
	 * @param path Directory
	 * @param newFileName Destination file/directory
	 * @return String concatenating the file with the path.
	 */
	public static String pathToDirectory(String path, String newFileName){
		return (newFileName.isEmpty()) ? 
				path : 
				path + java.io.File.separator + newFileName;
	}
	
	/**
	 * Creates a directory with files inside it.<br>
	 * All files will be created with the filename format: <code>folderName-size-index.txt</code>
	 * @param subDirectoryName Name of the new directory
	 * @param fileSize Size of each file to be created
	 * @param numberOfFiles Number of files to be created
	 * @return Physical <code>java.io.File</code> representation of the new directory
	 */
	public static java.io.File createDirectoryWithFiles(String pathToDirectory, String subDirectoryName, int fileSize, int numberOfFiles) {
		final String TEST_FILE_TXT = "%s-%03d-%03d.txt"; // Format: folderName-size-index.txt
		final String newFilesPath = pathToDirectory(pathToDirectory, subDirectoryName);
		java.io.File directoryWithFiles = createPhysicalDirectory(newFilesPath);
		
		
		for (int i=0; i<numberOfFiles; i++) {
			PhysicalFile.createFileWithinDirectory(newFilesPath,
					String.format(TEST_FILE_TXT, subDirectoryName, fileSize, i), fileSize);
		}
		return directoryWithFiles;
	}
}
