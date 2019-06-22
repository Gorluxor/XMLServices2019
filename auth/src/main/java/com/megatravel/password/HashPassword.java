package com.megatravel.password;

import java.security.SecureRandom;

import org.bouncycastle.crypto.generators.BCrypt;

public class HashPassword {
	/**
	 * generise radnom salt duzine 16 bajta
	 * @return vraca izgenerisani salt kao niz bajtova
	 */
	public byte[] generateSalt() {

		SecureRandom secureRandom = new SecureRandom();
		
		int randomNumber = 16;

		
		byte bytes[] = new byte[randomNumber];
		secureRandom.nextBytes(bytes);
        return bytes;
	}
	
	/**
	 * od prosledjenog salt-a i passworda izracunava hash po bcrypt algoritmu
	 * @param password
	 * @param salt
	 * @return vraca hash kao niz bajtova
	 */
	public byte[] hashPassword(String password, byte[] salt) {	
		byte[] passwordInBytes = password.getBytes();
        int iterations = 10; // 2^10=1024   
        byte[] hash = BCrypt.generate(passwordInBytes, salt, iterations);
        return hash;
	}
}
