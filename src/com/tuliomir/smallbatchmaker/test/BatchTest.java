/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author tuliomiranda
 *
 */
public class BatchTest {

	/**
	 * Creates data to be analysed by the batch maker
	 */
	private void createSampleData1() {
		String ROOT_DIR_NAME = "sampleData";
		java.io.File subDirectory;
		
		// Directory /sampleData/
		PhysicalDirectory.createDirectoryWithFiles(ROOT_DIR_NAME, "", 5, 4);
		
		// Directory /sampleData/sub10
		PhysicalDirectory.createDirectoryWithFiles(ROOT_DIR_NAME, "sub10", 10, 10);
		
		// Directory /sampleData/sub20
		subDirectory = PhysicalDirectory.createDirectoryWithFiles(ROOT_DIR_NAME, "sub20", 20, 10);
		
		// Directory /sampleData/sub20/sub2030
		PhysicalDirectory.createDirectoryWithFiles(subDirectory.getAbsolutePath(), "sub2030", 30, 30);
	}
	
	@Test
	public void shouldMakeBatchOfOneFile() {
		createSampleData1();
		//Batch.create("/", 1, 0);
		
		// How is the internal output going to work?
		fail("Not implemented.");
	}

}
