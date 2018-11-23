package com.anup.tutorials.sampleserviceaopsecurity.security.handler;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class DataSecurityHandler {
	
	private static final String DATA_ENC_KEY_ALG = "AES";
	private static final String DATA_ENC_PROVIDER = "SunJCE";
	private static final String DATA_SECURITY_ALG = "AES/CBC/PKCS5Padding";
	private static final String VALUE_SEPARATOR = ":";
	private static final String KEY = "ENC_KEY_PARAM";
	private static final String DATA = "ENC_DATA_PARAM";
	private final byte[] iv = {30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45};
	
	private SecretKey generateKey() {
		try {
			final KeyGenerator keyGen = KeyGenerator.getInstance(DATA_ENC_KEY_ALG);
			keyGen.init(256); 
			return keyGen.generateKey();
		} catch(NoSuchAlgorithmException algorithmException ) {
			throw new SecurityException("Error while performing security operation.", algorithmException);
		}
	}
	
	public String encrypt(final String originalData) {
		final SecretKey key = generateKey();
		try {
			final Cipher cipher = Cipher.getInstance(DATA_SECURITY_ALG, DATA_ENC_PROVIDER);
			final IvParameterSpec ivParams = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
			byte[] encryptedData = cipher.doFinal(originalData.getBytes());
			final String encodedEncryptedData = Base64.getEncoder().encodeToString(encryptedData);
			return buildFinalEncryptedData(encodedEncryptedData, key);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			throw new SecurityException("Error while performing security operation.", e);
		}
	}

	private String buildFinalEncryptedData(String encryotedEncodedData, Key secretKey) {
		final StringBuffer dataBuffer = new StringBuffer(); 
		dataBuffer.append(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
		dataBuffer.append(VALUE_SEPARATOR);
		dataBuffer.append(encryotedEncodedData);
		return dataBuffer.toString();
	}
	
	private Map<String, Object> processFinalEncryptedData(String encryotedEncodedData) {
		final Map<String, Object> processedDataMap = new HashMap<>();
		final String[] encryptedDataWithKey = encryotedEncodedData.split(VALUE_SEPARATOR);
		byte[] keyBytes = Base64.getDecoder().decode(encryptedDataWithKey[0]);
		processedDataMap.put(KEY, new SecretKeySpec(keyBytes, DATA_ENC_KEY_ALG));
		processedDataMap.put(DATA, Base64.getDecoder().decode(encryptedDataWithKey[1]));
		return processedDataMap;
	}
	
	
	
	public String decrypt(final String encryptedData) {
		try {
			final Map<String, Object> processedData = processFinalEncryptedData(encryptedData);
			final Cipher cipher = Cipher.getInstance(DATA_SECURITY_ALG, DATA_ENC_PROVIDER);
			final IvParameterSpec ivParams = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, (Key)processedData.get(KEY), ivParams);
			byte[] decryptedBytes = cipher.doFinal((byte[]) processedData.get(DATA));
			return new String(decryptedBytes);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			throw new SecurityException("Error while performing security operation.", e);
		}
	}
}
