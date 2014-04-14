/**
 * 
 */
package com.tuliomir.smallbatchmaker;

import java.util.ArrayList;

/**
 * @author tuliomiranda
 *
 */
public class Directory {
	private File logicalFile;
	private ArrayList<File> listOfLocalFiles;
	private long numTotalFiles = 0;
	private long localSize = 0;

	public static Directory scanDirectory(File logicalFile) {
		Directory result = new Directory(logicalFile);
		result.scan();
		return result;
	}
	
	private Directory(File logicalFile) {
		this.logicalFile = logicalFile;
		if (!this.logicalFile.isDirectory()) {
			System.err.println("Invalid directory: " + logicalFile.getPhysicalFile().getAbsolutePath());
			return;
		}
		this.listOfLocalFiles = new ArrayList<File>();
	}
	
	private void scan() {
		for (final java.io.File fileEntry : this.logicalFile.getPhysicalFile().listFiles()) {
			File newFile = new File(fileEntry, this);
			this.numTotalFiles++;
			this.localSize += newFile.getFileSize();
	        this.listOfLocalFiles.add(newFile);
	    }
		
		// Incrementing parent directory number of files
		if (null != this.logicalFile.getParentDirectory()) {
			this.logicalFile.getParentDirectory().addToTotalFiles(getNumLocalFiles());
		}
	}
	
	/**
	 * Empties the local info and rescans the directory and all its sub-directories.
	 */
	public void reScan() {
		this.numTotalFiles = 0;
		this.listOfLocalFiles.clear();
		this.scan();
	}
	
	protected void addToTotalFiles(long amountOfNewFiles) {
		this.numTotalFiles = this.numTotalFiles + amountOfNewFiles;
	}
	
	public long getNumLocalFiles() {
		return this.listOfLocalFiles.size();
	}

	/**
	 * @return the directoryName
	 */
	public String getDirectoryName() {
		return this.logicalFile.getFileName();
	}

	/**
	 * @return the listOfFiles
	 */
	public ArrayList<File> getListOfFiles() {
		return listOfLocalFiles;
	}

	/**
	 * @return the physicalDirectory
	 */
	public java.io.File getPhysicalDirectory() {
		return this.logicalFile.getPhysicalFile();
	}

	/**
	 * @return the numTotalFiles
	 */
	public long getNumTotalFiles() {
		return numTotalFiles;
	}

	public long getLocalSize() {
		return this.localSize;
	}

}
