package com.hospital.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HospitalApiApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void whenMatchesTenDigitsNumberPrefix_thenCorrect() {
	  Pattern pattern = Pattern.compile("((\\+*)((0[ -]+)*|(91 )*)(\\d{12}+|\\d{10}+))|\\d{5}([- ]*)\\d{6}");
	  Matcher matcher = pattern.matcher("+91 9165415598");
	  
	  assertTrue(matcher.matches());
	}

}
