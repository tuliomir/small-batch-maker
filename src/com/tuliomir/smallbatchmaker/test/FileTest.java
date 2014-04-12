/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import com.tuliomir.smallbatchmaker.File;

/**
 * @author tuliomiranda
 *
 */
public class FileTest {
	
	private static final String EXAMPLE_TXT_FILENAME = "example.txt";

	private java.io.File createTestFile(String fileName) {
		java.io.File physicalFile = new java.io.File(fileName);
		
		try {
			if (!physicalFile.createNewFile()) {
				physicalFile.delete();
				if (!physicalFile.createNewFile()) {
					throw new Exception("Cannot empty file.");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return physicalFile;
	}
	
	private void incrementPhysicalFile(java.io.File physicalFile, String incrementalString) {
		try {
			FileWriter out = new FileWriter(physicalFile);
			out.append(incrementalString);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void destroyTestFile(String fileName) {
		java.io.File physicalFile = new java.io.File(fileName);
		physicalFile.delete();
	}

	@Test
	public void shouldFindFile() {
		File testFile = new File(createTestFile(EXAMPLE_TXT_FILENAME));
		assertEquals(EXAMPLE_TXT_FILENAME,testFile.getFileName());
		destroyTestFile(EXAMPLE_TXT_FILENAME);
	}
	
	@Test
	public void shouldReturnZeroSize() {
		File testFile = new File(createTestFile(EXAMPLE_TXT_FILENAME));
		assertEquals(0l,testFile.getFileSize());
		destroyTestFile(EXAMPLE_TXT_FILENAME);
	}
	
	@Test
	public void shouldReturnSizeOfOne() {
		java.io.File physicalFile = createTestFile(EXAMPLE_TXT_FILENAME);
		incrementPhysicalFile(physicalFile, "a");
		File testFile = new File(physicalFile);
		
		assertEquals(1l,testFile.getFileSize());
		
		destroyTestFile(EXAMPLE_TXT_FILENAME);
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
		java.io.File physicalFile = createTestFile(EXAMPLE_TXT_FILENAME);
		incrementPhysicalFile(physicalFile, testString);
		File testFile = new File(physicalFile);
		
		assertEquals(testString.length(),testFile.getFileSize());
		
		destroyTestFile(EXAMPLE_TXT_FILENAME);
	}

}
