package com.tutorialsninja.utils;

public class TestConstants {
	
	//public -- because accessible in all classes
	
	//static Means the variable belongs to the class, not an object.
	//Because of static, you do not need to create an object of Constants.
	
	//Without static:
	//Constants constants = new Constants();
	//constants.FIRST_NAME

	//With static (correct way):
	//Constants.FIRST_NAME
		
	//"final" Means the value cannot be changed after it is assigned.

	//Example:
	//public static final String FIRST_NAME = "Monika";

	//If someone tries:
	//Constants.FIRST_NAME = "Riya";
		
	//Java will give compile-time error.
		
	//This protects your test data from accidental modification.
	
	public static final String FIRST_NAME = "Monika";
	public static final String LAST_NAME = "Gahane";
	public static final String TELEPHONE = "9812345678";
    public static final String PASSWORD = "123456";
    
    public static final String EXISTING_EMAIL = "monika08@gmail.com";   		
    
    //Success page expected messages
  	//final static because they are constants
    public static final String SUCCESS_MSG_1 = "Congratulations! Your new account has been successfully created!";
    public static final String SUCCESS_MSG_2 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
    public static final String SUCCESS_MSG_3 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
    public static final String SUCCESS_MSG_4 = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please";
    public static final String SUCCESS_MSG_5 = "contact us";
    
    //Warning Messages
    public static final String EXPECTED_FIRST_NAME_WARNING = "First Name must be between 1 and 32 characters!";
    public static final String EXPECTED_LAST_NAME_WARNING = "Last Name must be between 1 and 32 characters!";
    public static final String EXPECTED_EMAIL_WARNING = "E-Mail Address does not appear to be valid!";
    public static final String EXPECTED_TELEPHONE_WARNING = "Telephone must be between 3 and 32 characters!";
    public static final String EXPECTED_PASSWORD_WARNING = "Password must be between 4 and 20 characters!";
    public static final String EXPECTED_PRIVACY_POLICY_WARNING = "Warning: You must agree to the Privacy Policy!";

    //Warning message for different passwords into password and confirm password fields
    public static final String EXPECTED_CONFIRM_PASSWORD_WARNING = "Password confirmation does not match password!";
    
    //Warning message for existing email 
    public static final String EXPECTED_EXISTING_EMAIL_WARNING = "Warning: E-Mail Address is already registered!";
        
    //constant array of string (invalid emails) for test data storage
    public static final String[] INVALID_EMAIL = {
    		"monika", "monika@", "12345gmail", "monika@gmail", "monika@gmail."    		
    };
    
    //constant string array of invalid telephone numbers for test data
    public static final String[] INVALID_TELEPHONE_NUMBER = {
    		"0987654321", "123456", "abcdef", "r1", "123abc", "!@#$$", "98765abc"   		
    };
    
    
    
    
}
