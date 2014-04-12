/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;

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
		FileWriter out;
		try {
			out = new FileWriter(physicalFile);
			out.append("");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return physicalFile;
	}

	@Test
	public void shouldFindFile() {
		File testFile = new File(createTestFile(EXAMPLE_TXT_FILENAME));
		assertEquals(EXAMPLE_TXT_FILENAME,testFile.getFileName());
	}

}
