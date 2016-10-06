package headfirst.command.undo;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

import org.junit.Test;

public class LightTest {

	@Test
	public void testOn() {
		Light light = new Light("testLocation");
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(byteStream);
		System.setOut(stream);
		light.on();
		try {
			assertEquals(byteStream.toString("UTF-8"),"Light is on");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assertEquals(light.level, 100);
	}

	@Test
	public void testOff() {
		fail("Not yet implemented");
	}

}
