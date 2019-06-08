package com.megatravel.rating.validation;

import java.util.regex.Pattern;

public class StaticData {
	public static final int lengthValue = 30;
	public static final int lengthDescription = 140;
	public static final int minLength = 0;
	public static final int minLengthEmail = 10;
	public static final int maxLengthEmail = 60;
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
}
