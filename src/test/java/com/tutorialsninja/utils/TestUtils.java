package com.tutorialsninja.utils;

import java.util.Date;

public class TestUtils {

	public static String generateNewEmail() {
		return new Date().toString().replaceAll(" ", "").replaceAll(":", "") + "@gmail.com";
	}
}
