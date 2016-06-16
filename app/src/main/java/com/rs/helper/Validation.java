package com.rs.helper;

import java.util.regex.Pattern;

import android.widget.EditText;
/**
 * 
 * @author Richa
 * 
 *
 */
public class Validation {

	// Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "^[0-9]{10}$";
    private static final String FIRSTNAME_REGEX="^[aA-zZ\\s.]{1,50}$";
    private static final String LASTNAME_REGEX="^[aA-zZ\\s.]{1,50}$";
 
    // Error Messages
    private static final String REQUIRED_MSG = "Required";
    private static final String EMAIL_MSG = "Invalid Email";
    private static final String PHONE_MSG = "Invalid Phone Number, Accepts Only 10 Digits";
    private static final String FIRSTNAME_MSG="Please Enter Alphabets Only";
    private static final String LASTNAME_MSG="Please Enter Alphabets Only";
    private static final String PHONE_NO="Please Enter Number Not More Then 10 Digits";
    private static final String FIRSTNAME1_MSG="Please Enter Alphabets Not More Then 50 Characters";
 
    // call this method when you need to check firstname validation
    	public static boolean isFirstName(EditText editText, boolean required)
    	{
    	return isValidOnClickFirstName(editText,  FIRSTNAME_REGEX, FIRSTNAME_MSG, required);
    	}
    	// call this method when you need to check Lastname validation	
    	public static boolean isLastName(EditText editText, boolean required)
    	{
    	return isValid(editText,  LASTNAME_REGEX, LASTNAME_MSG, required);
    	}
    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }
 
    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }
    
    public static boolean isEmailAddressOnClick(EditText editText,boolean required){
    	return isValidOnClick(editText,EMAIL_REGEX, EMAIL_MSG, required);
    }
    
    public static boolean isValidFirstNameOnClick(EditText editText,boolean required){
    	return isValidOnClickFirstName(editText,FIRSTNAME_REGEX, FIRSTNAME_MSG, required);
    }
    public static boolean isValidLastNameOnClick(EditText editText,boolean required){
    	return isValidOnClick(editText,LASTNAME_REGEX, LASTNAME_MSG, required);
    }
 
    public static boolean isValidPhoneNoOnClick(EditText editText,boolean required)
    {
    	return isValidOnClickPhone(editText, PHONE_REGEX,PHONE_MSG, required);
    }
    public static boolean isValidPhoneNo(EditText editText,boolean required)
    {
    	return isValidOnClickPhone(editText, PHONE_REGEX,PHONE_MSG, required);
    } 
    // return true if the input field is valid, based on the parameter passed
    @SuppressWarnings("unused")
	public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {
    	 String text = editText.getText().toString().trim();
         // clearing the error, if it was previously set by some other values
         editText.setError(null);
      
        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) 
        	return false;
        return true;
    }
    
    public static boolean isValidOnClick(EditText editText, String regex, String errMsg, boolean required)
    {
    	  String text = editText.getText().toString().trim();
          // clearing the error, if it was previously set by some other values
          editText.setError(null);
    	if(required&& !hasText(editText))
    		return false;
    	// pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }
        return true;
    }
    
    private static boolean isValidOnClickFirstName(EditText editText,String regex,String errMsg, boolean required){
    	String text=editText.getText().toString();
    	editText.setError(null);
    	if(text.length()==0){
    		editText.setError(REQUIRED_MSG);
    		return false;
    	}
    	if(text.length()>50){
    		editText.setError(FIRSTNAME1_MSG);
    		return false;
    	}
    	if(!Pattern.matches(regex, text)){
    		editText.setError(FIRSTNAME_MSG);
    		return false;
    	}
    	return true;
    
    }
 
    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {
    	 
        String text = editText.getText().toString().trim();
        editText.setError(null);
 
        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }
        return true;
    }
    public static boolean checkPhoneNo(EditText editText){
    	
      	 String text = editText.getText().toString().trim();
      	 
           editText.setError(null); 
           try{
           if(Long.parseLong(text)==0||Long.parseLong(text)<10){
          	 editText.setError(PHONE_MSG);
          	 return false;
           }
            if(!Pattern.matches(PHONE_REGEX, text)){
        		editText.setError(PHONE_MSG);
         		return false;
            }
           }
           catch(Exception ex)
           {
        		editText.setError(PHONE_MSG);
        	   ex.printStackTrace();
        	   
           }
      	return true;
    }
    private static boolean isValidOnClickPhone(EditText editText,String regex,String errMsg, boolean required){
    	 String text = editText.getText().toString().trim();
         editText.setError(null); 
         if(text.length()==0){
        	 editText.setError(REQUIRED_MSG);
        	 return false;
         }
         if(text.length()>10){
        	 editText.setError(PHONE_NO);
        	 return false;
         }
         if(text.length()<10){
        	 editText.setError(PHONE_MSG);
        	 return false;
         }
         if(text.length()==10){
        	 if(!Pattern.matches(regex, text)){
          		editText.setError(PHONE_MSG);
          		return false;
         }
      
         }
    	return true;
    }
    
    } 

