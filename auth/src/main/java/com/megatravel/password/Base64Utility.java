package com.megatravel.password;

import java.io.IOException;
import java.security.SecureRandom;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64Utility {
	 /**
	  * Pomocna funkcija za enkodovanje bajtova u string
	  * @param data - niz bajtova koje enkodiramo pre sacuvavanja u file
	  * @return - vraca konvertovane bajtove u string
	  */
	 public static String encode(byte[] data){
		 BASE64Encoder encoder = new BASE64Encoder();
		 return encoder.encode(data);
	 }
	 
	 /**
	  * Pomocna funkcija za dekodovanje stringa u bajt niz
	  * @param base64Data - string koji zelimo da dekoiramo u niz bajtova
	  * @return - vraca dekodiran string u niz bajtova
	  * @throws IOException
	  */
	 public static byte[] decode(String base64Data) throws IOException{
		 BASE64Decoder decoder = new BASE64Decoder();
		 return decoder.decodeBuffer(base64Data);
	 }
	 
	 /**
	  * generise string po zadatoj duzini koji sadri cifre od 0-9
	  * @param len - duzina stringa koji ce biri izgenerisan od random brojeva izmedju 0-9
	  * @return vraca generisani string
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
