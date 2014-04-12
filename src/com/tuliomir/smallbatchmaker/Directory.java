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
	private String directoryName;
	private ArrayList<File> listOfFiles;
	private java.io.File physicalDirectory;

	public Directory(java.io.File physicalDirectory) {
		this.physicalDirectory = physicalDirectory;
		if (!this.physicalDirectory.isDirectory()) {
			System.err.println("Invalid directory: " + physicalDirectory.getAbsolutePath());
			return;
		}
		this.directoryName = physicalDirectory.getName();
		this.listOfFiles = new ArrayList<File>();
	}
	
	public long getNumberOfFiles() {
		return this.listOfFiles.size();
	}
	
	public void scan() {
		for (final java.io.File fileEntry : this.physicalDirectory.listFiles()) {
	        this.listOfFiles.add(new File(fileEntry));
	    }
	}

	/**
	 * @return the directoryName
	 */
	public String getDirectoryName() {
		return directoryName;
	}

	/**
	 * @return the listOfFiles
	 */
	public ArrayList<File> getListOfFiles() {
		return listOfFiles;
	}

}
