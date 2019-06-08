package com.megatravel.password;

import java.io.IOException;
import java.security.SecureRandom;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64Utility {
	 /**
	  * Function to encode bytes into a String
	  * @param data - array of bytes, that are encoded before putting into a file
	  * @return - Bytes encoded into a String
	  */
	 public static String encode(byte[] data){
		 BASE64Encoder encoder = new BASE64Encoder();
		 return encoder.encode(data);
	 }
	 
	 /**
	  * Decodes the String into a byte array
	  * @param base64Data - The string that we want to decode
	  * @return - Returns the decoded string
	  * @throws IOException
	  */
	 public static byte[] decode(String base64Data) throws IOException{
		 BASE64Decoder decoder = new BASE64Decoder();
		 return decoder.decodeBuffer(base64Data);
	 }
	 
	 /**
	  * Generate the string of a given len with numbers ranging from 0 to 9
	  * @param len - the length of the string
	  * @return String with the given length (containing the numbers 0 to 9)
	  */
	 public static String generateRandomNumberForLength(int len){
		   SecureRandom sr = new SecureRandom();
		   
		   String result = "";
		   
		   for(int i= 0; i < len; i++) {
			   result += sr.nextInt(10); //0-9
		   }
		   
		   //System.out.println("rezultat " + result);
		   return result;
		}
}
