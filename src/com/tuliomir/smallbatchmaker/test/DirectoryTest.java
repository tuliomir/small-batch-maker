/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tuliomir.smallbatchmaker.Directory;
import com.tuliomir.smallbatchmaker.File;

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
		if (physicalDir.exists()) {
			Directory testDir = Directory.scanDirectory(physicalDir);
			PhysicalDirectory.destroy(testDir);
		}
	}

	@Test
	public void shouldFindDirectory() {
		assertEquals(true, physicalDir.isDirectory());
	}
	
	@Test
	public void shouldFindEmptyDirectory() {
		File testDir = new File(physicalDir);
		
		assertEquals(0l, testDir.getDirectory().getNumberOfFiles());
	}
	
	@Test
	public void shouldFindDirectoryWithOneFile() {
		String fileName = TEST_DIR_NAME + java.io.File.separator + "testFile.txt";
		PhysicalFile.create(fileName);
		File testDir = new File(physicalDir);
		
		assertEquals(1l, testDir.getDirectory().getNumberOfFiles());
	}
	
	@Test
	public void shouldFindDirectoryWithManyFiles() {
		int amountOfFiles = Util.randomInt(2, 10);
		String fileName;
		
		for (int i=0; i<amountOfFiles; i++) {
			fileName = TEST_DIR_NAME + java.io.File.separator + "testFile" + i + ".txt";
			PhysicalFile.create(fileName);	
		}
		
		File testDir = new File(physicalDir);
		
		assertEquals(amountOfFiles, testDir.getDirectory().getNumberOfFiles());
	}
	
	@Test
	public void shouldFindSubDirectoryWithin() {
		String fileName = TEST_DIR_NAME + java.io.File.separator + "testFile.txt";
		String subDirName = TEST_DIR_NAME + java.io.File.separator + TEST_DIR_NAME + "subDir";
		
		PhysicalFile.create(fileName);
		PhysicalDirectory.create(subDirName);
		
		File testDir = new File(physicalDir);
		
		assertEquals(2l, testDir.getDirectory().getNumberOfFiles());
	}
	
	@Test
	public void shouldFindFileWithinSubDirectory() {
		String fileName = TEST_DIR_NAME + java.io.File.separator + "testFile.txt";
		String subDirName = TEST_DIR_NAME + java.io.File.separator + TEST_DIR_NAME + "subDir";
		String subFileName = subDirName + java.io.File.separator + "testFileSubDir.txt";
		
		PhysicalFile.create(fileName);
		PhysicalDirectory.create(subDirName);
		PhysicalFile.create(subFileName);
		
		File testDir = new File(physicalDir);
		
		assertEquals(2l, testDir.getDirectory().getNumberOfFiles());
	}

}
