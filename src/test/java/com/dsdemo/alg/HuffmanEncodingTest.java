package com.dsdemo.alg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dsdemo.alg.HuffmanEncoding.HuffmanEncodingResult;

class HuffmanEncodingTest {

	private HuffmanEncoding encoder = new HuffmanEncoding();
	private String textToEncode;
	
	@BeforeEach
	void setUp() {
		this.textToEncode = "This is string to test";
	}
	
	@Test
	void testEncoding() {
		HuffmanEncodingResult encodingResult = encoder.encode(textToEncode);
		String encodedString = encodingResult.getEncoded();
		
		String decoded = encoder.decode(encodingResult.getRoot(), encodedString);
		assertEquals(decoded, textToEncode);
		
	}

}
