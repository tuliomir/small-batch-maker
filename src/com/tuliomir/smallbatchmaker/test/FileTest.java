/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import com.tuliomir.smallbatchmaker.File;

/**
 * @author tuliomiranda
 *
 */
public class FileTest {
	
	private static final String EXAMPLE_TXT_FILENAME = "example.txt";

	

	@Test
	public void shouldFindFile() {
		File testFile = new File(PhysicalFile.createTestFile(EXAMPLE_TXT_FILENAME));
		assertEquals(EXAMPLE_TXT_FILENAME,testFile.getFileName());
		PhysicalFile.destroyTestFile(EXAMPLE_TXT_FILENAME);
	}
	
	@Test
	public void shouldReturnZeroSize() {
		File testFile = new File(PhysicalFile.createTestFile(EXAMPLE_TXT_FILENAME));
		assertEquals(0l,testFile.getFileSize());
		PhysicalFile.destroyTestFile(EXAMPLE_TXT_FILENAME);
	}
	
	@Test
	public void shouldReturnSizeOfOne() {
		java.io.File physicalFile = PhysicalFile.createTestFile(EXAMPLE_TXT_FILENAME,"a");
		File testFile = new File(physicalFile);
		
		assertEquals(1l,testFile.getFileSize());
		
		PhysicalFile.destroyTestFile(EXAMPLE_TXT_FILENAME);
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
		java.io.File physicalFile = PhysicalFile.createTestFile(EXAMPLE_TXT_FILENAME, testString);
		File testFile = new File(physicalFile);
		
		assertEquals(testString.length(),testFile.getFileSize());
		
		PhysicalFile.destroyTestFile(EXAMPLE_TXT_FILENAME);
	}

}
