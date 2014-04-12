/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tuliomir.smallbatchmaker.File;

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
		PhysicalFile.destroy(EXAMPLE_TXT_FILENAME);
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
		String testString;
		int stringLength;
		Random randomGenerator = new Random();
		
		// Generating random string
		stringLength = 1 + randomGenerator.nextInt(100);
		StringBuffer outputBuffer = new StringBuffer(stringLength);
		
		for (int i=0; i<stringLength; i++) {
			outputBuffer.append("a");
		}
		testString = outputBuffer.toString();
		
		// Pushing the string into the file
		PhysicalFile.increment(physicalFile,testString);
		File testFile = new File(physicalFile);
		
		assertEquals(testString.length(),testFile.getFileSize());
	}

}
