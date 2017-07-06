package aero.sita.test.sample;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import aero.sita.src.ProcessMessages;

public class ProcessMessagesTest {
	private ProcessMessages processor;

	/**
	 * SetUp.
	 */
	@Before
	public void setUp() {
		processor = new ProcessMessages();
	}

	/**
	 * tear down resources
	 */
	@After
	public void tearDown() {
		processor = null;
	}

	/**
	 * testBuildValidMessages
	 */
	@Test
	public void testValidMessgaes() {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("file_name", "validfile.txt");
		headers.put("correlation_id", "500hjgd");
		MessageHeaders messageHeaders = new MessageHeaders(headers);
		Message<?> message = MessageBuilder.createMessage("10\n10\n10", messageHeaders);
		Message<?> output = (Message<?>) processor.getMessages(message);
		Assert.assertEquals("30", output.getPayload().toString());
		Assert.assertEquals("validfile.txt", output.getHeaders().get("file_name"));
		Assert.assertEquals("500hjgd", output.getHeaders().get("correlation_id"));
	}
}
