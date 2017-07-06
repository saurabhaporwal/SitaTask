package aero.sita.test.sample;

import java.util.Collection;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ExecutorSubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;

import aero.sita.src.Router;


public class RouterTest {

	private static Router router;
	
	@BeforeClass
	public static void  setUp() {
		router = new Router();
	}
	
	@AfterClass
	public static  void destroy() {
		router = null;
	}
	
	/**
	 * testValidContent.
	 * Returns two channels(output/processed).
	 */
	@Test
	public void testContentTrue() {
		
		Message<String> msg = MessageBuilder.withPayload("43\n78\n22").build();
		MessageChannel outputChannel = new ExecutorSubscribableChannel();
		MessageChannel processedChannel = new ExecutorSubscribableChannel();
		router.setOutputChannel(outputChannel);
		router.setProcessedChannel(processedChannel);
		Collection<MessageChannel> targetChannels = router.determineTargetChannels(msg);
		Assert.assertEquals("Valid with target channels.", 2, targetChannels.size());
	}
	
	/**
	 * testInvalidContent
	 * This with return only one channel(problem).
	 */
	@Test
	public void testContentFalse() {
		
		Message<String> msg = MessageBuilder.withPayload("hgfdf").build();
		MessageChannel problemChannel = new ExecutorSubscribableChannel();
		router.setProblemChannel(problemChannel);
		Collection<MessageChannel> targetChannels = router.determineTargetChannels(msg);
		Assert.assertEquals("Invalid input with one problem target channels.", 1, targetChannels.size());
	}

}
