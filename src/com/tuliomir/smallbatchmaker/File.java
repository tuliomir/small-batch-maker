/**
 * 
 */
package com.tuliomir.smallbatchmaker;

/**
 * @author tuliomir
 *
 */
public class File {

	private long fileSize;
	private String fileName;
	private String filePath;
	private boolean isDirectory;
	private Directory directory;
	private java.io.File physicalFile;
	
	public File(java.io.File physicalFile) {
		this.physicalFile = physicalFile;
		this.fileSize = physicalFile.length();
		this.fileName = physicalFile.getName();
		
		String absolutePath = physicalFile.getAbsolutePath();
		this.filePath = absolutePath.
		    substring(0,absolutePath.lastIndexOf(java.io.File.separator));
		
		this.isDirectory = physicalFile.isDirectory();
		if (this.isDirectory) {
			this.directory = Directory.scanDirectory(this.physicalFile);
		}
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	/**
	 * @return the physicalFile
	 */
	public java.io.File getPhysicalFile() {
		return physicalFile;
	}

	/**
	 * @return the directory
	 */
	public Directory getDirectory() {
		return directory;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(Directory directory) {
		this.directory = directory;
	}
}
