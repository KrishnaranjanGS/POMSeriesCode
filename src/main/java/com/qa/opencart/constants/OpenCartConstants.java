package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

// should contain application specific values

public class OpenCartConstants {
	
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_LONG_TIME_OUT = 20;
	
	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE_VALUE = "My Account";
	public static final String REGISTER_PAGE_TITLE_VALUE = "Register Account";

	public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";
	public static final String ACCOUNT_PAGE_URL_FRACTION_VALUE = "route=account/account";
	public static final String REGISTER_PAGE_URL_FRACTION_VALUE = "route=account/register";
	
	public static final int ACCOUNT_PAGE_HEADERS_COUNT = 4;
	
	public static final List<String> ACCOUNT_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	
	public static final String REGISTER_SHEET_NAME = "register";

}
