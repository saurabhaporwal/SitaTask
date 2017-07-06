package aero.sita.test.sample;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import aero.sita.src.GenerateFiles;

public class GenerateFilesTest {
	
private GenerateFiles generator;
	
	/**
	 * Setup.
	 */
	@Before
	public void setUp(){
		generator = new GenerateFiles();
	}
	
	/**
	 * tearDown
	 */
	@After
	public void tearDown(){
		generator = null;
	}
	
	/**
	 * testProcessedFile.
	 */
	@Test
	public void testProcessedFile(){
		Message<?> message = MessageBuilder.withPayload("testData").setHeader("file_name", "valid.txt").build();	
		generator.setFileExtension("PROCESSED");
		String generatedFileName = generator.generateFileName(message);
		System.out.println(generatedFileName);
		Assert.assertEquals("File generated", "valid.txt.PROCESSED", generatedFileName);
	}
	
	/**
	 * testOutputFile.
	 */
	@Test
	public void testOutputFile(){
		Message<?> message = MessageBuilder.withPayload("test messages").setHeader("file_name", "valid.txt").build();	
		generator.setFileExtension("OUTPUT");
		String generatedFileName = generator.generateFileName(message);
		Assert.assertEquals("TFile generated ", "valid.txt.OUTPUT", generatedFileName);
	}
}
