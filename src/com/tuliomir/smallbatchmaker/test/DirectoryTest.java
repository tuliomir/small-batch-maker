/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tuliomir.smallbatchmaker.File;

/**
 * @author tuliomiranda
 *
 */
public class DirectoryTest {

	private static final String TEST_DIR_NAME = "testDir";
	private File logicalDir;
	
	@Before
	public void createTestDirectory() {
		this.logicalDir = PhysicalDirectory.create(TEST_DIR_NAME);
	}
	
	@After
	public void destroyTestDirectory() {
		if (logicalDir.getPhysicalFile().exists()) {
			PhysicalDirectory.destroy(logicalDir.getDirectory());
		}
	}
	
	private void reScanTestDirectory() {
		this.logicalDir.getDirectory().reScan();
	}

	@Test
	public void shouldFindDirectory() {
		assertEquals(true, logicalDir.isDirectory());
	}
	
	@Test
	public void shouldFindEmptyDirectory() {
		
		assertEquals(0l, logicalDir.getDirectory().getNumLocalFiles());
	}
	
	@Test
	public void shouldFindDirectoryWithOneFile() {
		String fileName = TEST_DIR_NAME + java.io.File.separator + "testFile.txt";
		PhysicalFile.create(fileName);
		
		this.reScanTestDirectory();
		assertEquals(1l, logicalDir.getDirectory().getNumLocalFiles());
	}
	
	@Test
	public void shouldFindDirectoryWithManyFiles() {
		int amountOfFiles = Util.randomInt(2, 10);
		String fileName;
		
		for (int i=0; i<amountOfFiles; i++) {
			fileName = TEST_DIR_NAME + java.io.File.separator + "testFile" + i + ".txt";
			PhysicalFile.create(fileName);	
		}
		
		this.reScanTestDirectory();
		assertEquals(amountOfFiles, logicalDir.getDirectory().getNumLocalFiles());
	}
	
	@Test
	public void shouldFindSubDirectoryWithin() {
		String fileName = TEST_DIR_NAME + java.io.File.separator + "testFile.txt";
		String subDirName = TEST_DIR_NAME + java.io.File.separator + TEST_DIR_NAME + "subDir";
		
		PhysicalFile.create(fileName);
		PhysicalDirectory.create(subDirName);
		
		this.reScanTestDirectory();
		assertEquals(2l, logicalDir.getDirectory().getNumLocalFiles());
	}
	
	@Test
	public void shouldFindFileWithinSubDirectory() {
		String fileName = TEST_DIR_NAME + java.io.File.separator + "testFile.txt";
		String subDirName = TEST_DIR_NAME + java.io.File.separator + "subDir";
		String subFileName = subDirName + java.io.File.separator + "testFileSubDir.txt";
		
		PhysicalFile.create(fileName);
		PhysicalDirectory.create(subDirName);
		PhysicalFile.create(subFileName);
		
		this.reScanTestDirectory();
		assertEquals(3l, logicalDir.getDirectory().getNumTotalFiles());
	}

}
