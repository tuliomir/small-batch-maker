/**
 * 
 */
package com.tuliomir.smallbatchmaker;

import java.util.ArrayList;

import com.tuliomir.smallbatchmaker.test.Util;

/**
 * Logical directory manipulation
 * <b>Important</b>: For the sake of this application, a folder is counted as a file,
 * with size zero.
 * @author tuliomiranda
 *
 */
public class Directory {
	private File logicalFile;
	private ArrayList<File> listOfLocalFiles;
	private long totalFiles = 0;
	private long localSize = 0;
	private long totalSize = 0;  

	/**
	 * Public constructor for the Directory class.<br>
	 * Reads a directory and scans it for files.
	 * @param logicalFile Logical file that has just been discovered as a directory
	 * @return Logical directory
	 */
	public static Directory scanDirectory(File logicalFile) {
		Directory result = new Directory(logicalFile);
		result.scan();
		return result;
	}
	
	/**
	 * Private constructor for the Directory class.
	 * Does not scan the contents of the directory.
	 * @param logicalFile Logical file containing the folder
	 */
	private Directory(File logicalFile) {
		this.logicalFile = logicalFile;
		if (!this.logicalFile.isDirectory()) {
			System.err.println("Invalid directory: " + logicalFile.getPhysicalFile().getAbsolutePath());
			return;
		}
		this.listOfLocalFiles = new ArrayList<File>();
	}
	
	/**
	 * Scans the directory for total files and size.
	 */
	private void scan() {
		for (final java.io.File fileEntry : this.logicalFile.getPhysicalFile().listFiles()) {
			File newFile = new File(fileEntry, this);
			this.totalFiles++;
			if (!newFile.isDirectory()) {
				this.localSize += newFile.getFileSize();
				this.totalSize += newFile.getFileSize();
			}
	        this.listOfLocalFiles.add(newFile);
	    }
		
		// Incrementing parent directory number of files
		if (null != this.logicalFile.getParentDirectory()) {
			this.logicalFile.getParentDirectory().addToTotalFiles(getNumLocalFiles());
			this.logicalFile.getParentDirectory().addToTotalSize(getLocalSize());
		}
	}

	/**
	 * Empties the local info and rescans the directory and all its sub-directories.
	 */
	public void reScan() {
		totalFiles = 0;
		localSize = 0;
		totalSize = 0;
		this.listOfLocalFiles.clear();
		this.scan();
	}
	
	/**
	 * A child directory calls this to inform the parent directory about its
	 * files.
	 * @param subFolderFiles Amount of files within the folder.
	 */
	protected void addToTotalFiles(long subFolderFiles) {
		this.totalFiles += subFolderFiles;
	}

	/**
	 * A child directory calls this to inform the parent directory about its
	 * files total size.
	 * @param subFolderSize Total size of files within the directory.
	 */
	protected void addToTotalSize(long subFolderSize) {
		this.totalSize += subFolderSize;
	}
	
	/**
	 * Returns the number of local files within the directory
	 * @return
	 */
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
		return totalFiles;
	}

	/**
	 * @return the local size
	 */
	public long getLocalSize() {
		return this.localSize;
	}

	/**
	 * @return the total size
	 */
	public long getTotalSize() {
		return this.totalSize ;
	}

}
