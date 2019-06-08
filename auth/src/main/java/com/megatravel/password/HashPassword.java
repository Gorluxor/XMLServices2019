package com.megatravel.password;

import org.springframework.security.crypto.bcrypt.BCrypt;


import java.security.SecureRandom;



public class HashPassword {
	/**
	 * Generate a random salt of 16 bytes
	 * @return The generated salt of 16 bytes
	 */
	public byte[] generateSalt() {

	/*
		 java.securiti.SecureRandom class: Ova klasa obezbeđuje kriptografski jak generator slučajnih brojeva (RNG). 
		 Kriptografski jak slučajni broj je minimalno u skladu sa statističkim testovima generatora slučajnih brojeva
		 navedenim u FIPS 140-2. Pored toga, SecureRandom 
		 mora proizvesti ne-deterministički izlaz. Prema tome, svaki seed koji je prosleđen objektu 
		 SecureRandom mora biti nepredvidljiv.

		java.util.Random class: Klase definisane u Random-u nisu kriptografski jake, a izabrani brojevi nisu potpuno slučajni, 
		jer se za njihov odabir koristi definitivni matematički algoritam (zasnovan na Donald E. Knuth-ovom algoritmu generatora
		 slučajnih brojeva). Zbog toga nije bezbedno koristiti ovu klasu za zadatke koji zahtevaju visok nivo bezbednosti,
		  kao što je pravljenje nasumične lozinke itd.
		  
		  Poredjenja: 
		  1. Veličina: Random klasa ima samo 48 bita gde SecureRandom može imati do 128 bita. 
		  Tako su šanse za ponavljanje u SecureRandomu manje.
		  
		  2. Generacija semena: Random koristi sistemski sat kao seed / ili da generiše seed. 
		  Sto znaci da se moze lako reprodukovati ako napadač zna vreme u kojem je seed generisan. 
		  SecureRandom preuzima slučajne podatke iz vašeg operativnog sistema (oni mogu biti interval 
		  između pritisaka na tastere itd. - većina OS skuplja ove podatke i čuva ih u fajlovima, pa 
		  se moguy koristiti kao seed.
		  
		  3.  Vreme koje je potrebno isprobati kombinacije koristeci random je 2^48 vs vreme koje je potrebno isprobati
		  kombinacije od 2^128 u SecureRandom.
		  
		  https://www.geeksforgeeks.org/random-vs-secure-random-numbers-java/
	 */
		SecureRandom secureRandom = new SecureRandom();
		
		int randomNumber = 16; //velicina nam je 16 posto bcrypt ne podrzava vecu velicinu salta inace bi trebala da bude 
		//random velicina koja se menja stalno za svakog korisnika
		//System.out.println(randomNumber);
		
		byte bytes[] = new byte[randomNumber];
		secureRandom.nextBytes(bytes);
        return bytes;
	}
	
	/* Algoritmi za hashovanje passworda koji su dobri i mogu se koristiti su PBKDF2, bcrypt, scrypt
	 
	 Scrypt bi trebao biti "bolji" nego bcrypt, ali je i mnogo noviji, i to je loše (jer "noviji" podrazumeva da je "dobio manje
	 pažnje").

	Sve ove sheme heširanja lozinki pokušavaju da obrade jednu lozinku skuplje za napadača, a 
	da to ne čini previše skupim za nas server. Pošto je nas server u osnovi PC, a napadač može 
	izabrati najefikasniji hardver za svoj zadatak, šeme heširanja lozinki pokušavaju da budu takve 
	da je najbolja platforma za njih PC. PBKDF2 se može temeljno optimizovati pomoću GPU-a, dok su
	bcript i script znatno manje GPU-friendly. Bcrypt i scrypt zahtevaju brzu RAM memoriju,
	što je oskudan resurs u GPU-u (GPU može imati mnogo RAM-a, ali neće moći istovremeno pristupiti svim jezgrima).
	 
	Posto moderni FPGA uredjaji ugrađuju mnoge male RAM blokove koji su vrlo korisni za optimizaciju paralelnog 
	napada rečnika sa bcrypt algoritmom: to znači da će napadač  pokusati koriscenjem FPGA da napadne nase sifre a ne
	koriscenjem PC-a.Posto to zelimo da izbegnemo.  Zbog toga je bolje koristiti scrypt, koji zahteva mnogo više RAM-a; ne previše za 
	PC (govorimo samo o nekoliko megabajta ovde), ali dovoljno da FPGA ne moze da ga razbije.
	
	Dakle, teoretski, scrypt bi trebao biti bolja od bcrypt-a. Međutim, to je sve podložno tome da li šifriranje 
	ispunjava svoje kriptografske obećanja. Ovakva garancija robusnosti može se postići samo kroz vreme: shema 
	će se smatrati sigurnom ako preživi godinama nemilosrdnih napada kriptografa. Zbog toga je potrebno sacekati js neko vreme za 
	scrypt. 
	 
	 https://security.blogoverflow.com/2013/09/about-secure-password-hashing/
	 https://security.stackexchange.com/questions/26245/is-bcrypt-better-than-scrypt
	 */
	/**
	 * Uses password in plain text with salt with BCrypt algorithm to one way hash it
	 * @param password the password in plain text
	 * @param salt that will be added to the hash
	 * @return hashed password
	 */
	public byte[] hashPassword(String password, byte[] salt) {
		String saltString = salt.toString();
		//byte[] passwordInBytes = password.getBytes();
		//int iterations = 10; // 2^10=1024
		//BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String hash = BCrypt.hashpw(password, saltString);
		//byte[] hash = org.springframework.security.crypto.bcrypt.BCrypt.generate(passwordInBytes, salt, iterations);
		return hash.getBytes();
	}
}
