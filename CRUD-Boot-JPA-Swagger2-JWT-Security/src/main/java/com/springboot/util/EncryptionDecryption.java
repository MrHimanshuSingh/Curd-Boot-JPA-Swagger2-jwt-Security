package com.springboot.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncryptionDecryption {

	private static final String algorithm = "AES/CBC/PKCS5Padding";
	private static final String key = "cV64oxsZ-xRvxwxl_sRiaA58HNVMhEuo";
	private static final String iv = "m-eTAIdH6b9uud-f";

	public static String encrypt(String rawString) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] encrypted = cipher.doFinal(rawString.toString().getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			log.error("encrypt >> {}", e.toString());
		}
		return null;

	}

	public static String decrypt(String encryptedString) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
			return new String(decrypted, StandardCharsets.UTF_8);
		} catch (Exception e) {
			log.error("decrypt >> {}", e.toString());
		}
		return null;
	}

//	public static void main(String[] args) {
//		String name = "Himanshu";
//		String enc = encrypt(name);
//		String dec = decrypt(enc);
//		System.out.println(name);
//		System.out.println(enc);
//		System.out.println(dec);
//
//	}
}
