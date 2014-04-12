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
	
	public File(java.io.File physicalFile) {
		this.fileSize = physicalFile.length();
		this.fileName = physicalFile.getName();
		
		String absolutePath = physicalFile.getAbsolutePath();
		this.filePath = absolutePath.
		    substring(0,absolutePath.lastIndexOf(java.io.File.separator));
	}

	public File(long fileSize, String fileName, String filePath) {
		super();
		this.fileSize = fileSize;
		this.fileName = fileName;
		this.filePath = filePath;
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
	
	
	
}
