/**
 * 
 */
package com.tuliomir.smallbatchmaker.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tuliomir.smallbatchmaker.File;

/**
 * @author tuliomiranda
 *
 */
public class DirectoryTest {

	private static final String SUB_DIRECTORY_NAME = "subDir";
	private static final String TEST_FILE_TXT = "testFile.txt";
	private static final String ROOT_DIR_NAME = "testDir";
	private File logicalDir;
	
	@Before
	public void createTestDirectory() {
		this.logicalDir = PhysicalDirectory.create(ROOT_DIR_NAME);
	}
	
	@After
	public void destroyTestDirectory() {
		if (logicalDir.getPhysicalFile().exists()) {
			reScanTestDirectory();
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
		PhysicalFile.createFileWithinDirectory(ROOT_DIR_NAME, TEST_FILE_TXT);
		
		this.reScanTestDirectory();
		assertEquals(1l, logicalDir.getDirectory().getNumLocalFiles());
	}
	
	@Test
	public void shouldFindDirectoryWithManyFiles() {
		int amountOfFiles = Util.randomInt(2, 10);
		
		for (int i=0; i<amountOfFiles; i++) {
			PhysicalFile.createFileWithinDirectory(ROOT_DIR_NAME, i + TEST_FILE_TXT);
		}
		
		this.reScanTestDirectory();
		assertEquals(amountOfFiles, logicalDir.getDirectory().getNumLocalFiles());
	}
	
	@Test
	public void shouldFindSubDirectoryWithin() {
		PhysicalFile.createFileWithinDirectory(ROOT_DIR_NAME, TEST_FILE_TXT);
		PhysicalDirectory.createPhysicalSubDirectory(ROOT_DIR_NAME, SUB_DIRECTORY_NAME);
		
		this.reScanTestDirectory();
		assertEquals(2l, logicalDir.getDirectory().getNumLocalFiles());
	}
	
	@Test
	public void shouldFindFileWithinSubDirectory() {
		PhysicalFile.createFileWithinDirectory(ROOT_DIR_NAME, TEST_FILE_TXT);
		java.io.File subDir = PhysicalDirectory.createPhysicalSubDirectory(ROOT_DIR_NAME, SUB_DIRECTORY_NAME);
		PhysicalFile.createFileWithinDirectory(subDir.getAbsolutePath() ,"sub" + TEST_FILE_TXT);
		
		this.reScanTestDirectory();
		assertEquals(3l, logicalDir.getDirectory().getNumTotalFiles());
	}
	
	@Test
	public void shouldHaveSizeOfLocalFiles() {
		PhysicalFile.createFileWithinDirectory(ROOT_DIR_NAME, "10" + TEST_FILE_TXT, 10);
		PhysicalFile.createFileWithinDirectory(ROOT_DIR_NAME, "20" + TEST_FILE_TXT, 20);
		
		this.reScanTestDirectory();
		assertEquals(30l, logicalDir.getDirectory().getLocalSize());
	}
	
	@Test
	public void shouldHaveSizeOfAllFilesRecursively() {
		PhysicalFile.createFileWithinDirectory(ROOT_DIR_NAME, "10" + TEST_FILE_TXT, 10);
		PhysicalFile.createFileWithinDirectory(ROOT_DIR_NAME, "20" + TEST_FILE_TXT, 20);
		java.io.File subDir = PhysicalDirectory.createPhysicalSubDirectory(ROOT_DIR_NAME, SUB_DIRECTORY_NAME);
		PhysicalFile.createFileWithinDirectory(subDir.getAbsolutePath() ,"sub" + TEST_FILE_TXT, 40);
		
		this.reScanTestDirectory();
		assertEquals(70l, logicalDir.getDirectory().getTotalSize());
	}

}
