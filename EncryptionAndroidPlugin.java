package com.TechM.QSpace;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class EncryptionAndroidPlugin {

	public static final String ENCRYPT_DATA = "encrypt_data";
	public static final String DECRYPT_DATA = "decrypt_data";

	public static String encryptPlainText(final String t_stringToEncrypt) {
		String encryptedString = "";
		try {
			// Log.d("CALERT","ENTER encryptPlainText  ");
			String key = "IDEA1APPIFYSPACEQM";
			byte[] keyBytes;
			byte[] keyBytes16 = null;
			byte[] plainBytes = null;

			try {
				keyBytes = key.getBytes("UTF-8");
				keyBytes16 = new byte[16];
				System.arraycopy(keyBytes, 0, keyBytes16, 0,
						Math.min(keyBytes.length, 16));
				plainBytes = t_stringToEncrypt.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}

			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes16, "AES");
			Cipher cipher;
			try {
				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				byte[] iv = new byte[16]; // initialization vector with all 0
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(
						iv));
				byte[] encrypted = cipher.doFinal(plainBytes);
				byte[] encodedBytes = Base64.encode(encrypted, Base64.DEFAULT);
				encryptedString = new String(encodedBytes);
				// Log.d("Encryption string final:", encryptedString);

			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			// Log.d("Caught exception in encryptPlainText","");
			ex.printStackTrace();
		}
		return encryptedString;
	}

	public static String encryptPlainText(final String t_stringToEncrypt, String key) {
		String encryptedString = "";
		try {
			// Log.d("CALERT","ENTER encryptPlainText  ");
			byte[] keyBytes;
			byte[] keyBytes16 = null;
			byte[] plainBytes = null;

			try {
				keyBytes = key.getBytes("UTF-8");
				keyBytes16 = new byte[16];
				System.arraycopy(keyBytes, 0, keyBytes16, 0,
						Math.min(keyBytes.length, 16));
				plainBytes = t_stringToEncrypt.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}

			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes16, "AES");
			Cipher cipher;
			try {
				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				byte[] iv = new byte[16]; // initialization vector with all 0
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(
						iv));
				byte[] encrypted = cipher.doFinal(plainBytes);
				byte[] encodedBytes = Base64.encode(encrypted, Base64.DEFAULT);
				encryptedString = new String(encodedBytes);
				// Log.d("Encryption string final:", encryptedString);

			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			// Log.d("Caught exception in encryptPlainText","");
			ex.printStackTrace();
		}
		return encryptedString;
	}

	public static String decryptCipherText(final String t_stringToDecrypt) {
		String decryptedString = "";
		try {
			// Log.d("CALERT","ENTER decryptCipherText  ");

			//String key = "TIMCONFSERVICE2014";
			String key = "IDEA1APPIFYSPACEQM";
			byte[] keyBytes;
			byte[] keyBytes16 = null;
			byte[] plainBytes = null;

			try {
				keyBytes = key.getBytes("UTF-8");
				keyBytes16 = new byte[16];
				System.arraycopy(keyBytes, 0, keyBytes16, 0,
						Math.min(keyBytes.length, 16));

				plainBytes = Base64.decode(t_stringToDecrypt, Base64.DEFAULT); // t_stringToDecrypt.getBytes("UTF-8");

			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}

			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes16, "AES");
			Cipher cipher;
			try {
				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				byte[] iv = new byte[16]; // initialization vector with
											// all 0
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(
						iv));
				byte[] decrypted = cipher.doFinal(plainBytes);
				// byte[] encodedBytes = Base64.encodeBase64(decrypted);
				decryptedString = new String(decrypted);
				// Log.d("DE cryption string final:", decryptedString);

			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			// Log.d("Caught exception in encryptPlainText","");
		}
		return decryptedString;
	}
	public static String decryptCipherText(final String t_stringToDecrypt, String key) {
		String decryptedString = "";
		try {
			// Log.d("CALERT","ENTER decryptCipherText  ");

			byte[] keyBytes;
			byte[] keyBytes16 = null;
			byte[] plainBytes = null;

			try {
				keyBytes = key.getBytes("UTF-8");
				keyBytes16 = new byte[16];
				System.arraycopy(keyBytes, 0, keyBytes16, 0,
						Math.min(keyBytes.length, 16));

				plainBytes = Base64.decode(t_stringToDecrypt, Base64.DEFAULT); // t_stringToDecrypt.getBytes("UTF-8");

			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}

			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes16, "AES");
			Cipher cipher;
			try {
				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				byte[] iv = new byte[16]; // initialization vector with
				// all 0
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(
						iv));
				byte[] decrypted = cipher.doFinal(plainBytes);
				// byte[] encodedBytes = Base64.encodeBase64(decrypted);
				decryptedString = new String(decrypted);
				// Log.d("DE cryption string final:", decryptedString);

			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			// Log.d("Caught exception in encryptPlainText","");
		}
		return decryptedString;
	}
}