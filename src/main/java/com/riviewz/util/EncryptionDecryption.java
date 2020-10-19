package com.riviewz.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecryption {

	public static byte[] encrypt(String password, String encString) {

		byte[] encrypted = null;

		try {

			//String key = "Bar12345Bar12345"; // 128 bit key

			// Create key and cipher
			Key aesKey = new SecretKeySpec(encString.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			// encrypt the uniqueKeyString
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			encrypted = cipher.doFinal(password.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return encrypted;
	}

	public static String decrypt(byte[] encryptedCode, String encString) {

		String decrypted = null;
		//String key = "Bar12345Bar12345"; // 128 bit key

		try {

			// Create key and cipher
			Key aesKey = new SecretKeySpec(encString.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			// decrypt the uniqueKeyString
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(encryptedCode));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return decrypted;
	}

	public static void main(String[] args) {

		EncryptionDecryption app = new EncryptionDecryption();

		String password = "plastic";
		String encString = "Bar12345Bar12345";

		byte[] encryptedCode = app.encrypt(password, encString);
		System.out.println("EncryptedCode = " + encryptedCode);

		String decryptedString = app.decrypt(encryptedCode, encString);
		System.out.println("decryptedString = " + decryptedString);

	}

}