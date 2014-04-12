/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tuliomir.smallbatchmaker.Directory;

/**
 * @author tuliomiranda
 *
 */
public class DirectoryTest {

	private static final String TEST_DIR_NAME = "testDir";
	private java.io.File physicalDir;
	
	@Before
	public void createTestDirectory() {
		this.physicalDir = PhysicalDirectory.create(TEST_DIR_NAME);
	}
	
	@After
	public void destroyTestDirectory() {
		PhysicalDirectory.destroy(TEST_DIR_NAME);
	}

	@Test
	public void shouldFindDirectory() {
		assertEquals(true, physicalDir.isDirectory());
	}
	
	@Test
	public void shouldFindEmptyDirectory() {
		Directory testDir = new Directory(physicalDir);
		
		assertEquals(0l, testDir.getNumberOfFiles());
	}
	
	@Test
	public void shouldFindDirectoryWithOneFile() {
		String fileName = TEST_DIR_NAME + java.io.File.separator + "testFile.txt";
		PhysicalFile.create(fileName);
		Directory testDir = new Directory(physicalDir);
		testDir.scan();
		
		assertEquals(1l, testDir.getNumberOfFiles());
		PhysicalFile.destroy(fileName);
	}

}
