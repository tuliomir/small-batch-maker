/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tuliomir.smallbatchmaker.File;
import com.tuliomir.smallbatchmaker.Util;

/**
 * @author tuliomiranda
 *
 */
public class FileTest {
	
	private static final String EXAMPLE_TXT_FILENAME = "example.txt";
	private java.io.File physicalFile;

	@Before
	public void createTestFile() {
		physicalFile = PhysicalFile.create(EXAMPLE_TXT_FILENAME);
	}
	
	@After
	public void destroyTestFile() {
		PhysicalFile.destroy(physicalFile);
	}

	@Test
	public void shouldFindFile() {
		File testFile = new File(physicalFile);
		assertEquals(EXAMPLE_TXT_FILENAME,testFile.getFileName());
	}
	
	@Test
	public void shouldReturnZeroSize() {
		File testFile = new File(physicalFile);
		assertEquals(0l,testFile.getFileSize());
	}
	
	@Test
	public void shouldReturnSizeOfOne() {
		PhysicalFile.increment(physicalFile,"a");
		File testFile = new File(physicalFile);
		
		assertEquals(1l,testFile.getFileSize());
	}
	
	@Test
	public void shouldReturnSizeCorrectly() {
		int stringLength = Util.randomInt(1, 100);
		
		File testFile = new File(PhysicalFile.createFileWithSize(EXAMPLE_TXT_FILENAME, stringLength));
		
		assertEquals(stringLength, testFile.getFileSize());
	}

}
