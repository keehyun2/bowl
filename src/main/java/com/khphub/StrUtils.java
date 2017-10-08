package com.khphub;

/**
 * @author khpark
 * A collection of useful functions related to String
 */
public class StrUtils {

	/**
	 * It takes a Korean phone number as a string and adds a dash between the phone numbers.
	 * @param phoneNumber
	 * @return
	 */
	public String phoneFormatter(String phoneNumber){
    	
		if(phoneNumber != null){
			if(phoneNumber.length() == 11){
	        	return phoneNumber.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
	        }else if(phoneNumber.length() ==8){
	        	return phoneNumber.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
	        }else{
	            if(phoneNumber.indexOf("02")==0){
	            	if(phoneNumber.length() == 9){
	            		return phoneNumber.replaceAll("(\\d{2})(\\d{3})(\\d{4})", "$1-$2-$3");
	            	}else{
	            		return phoneNumber.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "$1-$2-$3");
	            	}
	            }else{
	            	return phoneNumber.replaceAll("(\\d{3})(\\d{3})(\\d{4})", "$1-$2-$3");
	            }
	        }
		}else{
			return "";
		}
    }
	
}
