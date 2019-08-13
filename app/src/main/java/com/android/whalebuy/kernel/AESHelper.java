package com.android.whalebuy.kernel;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AESHelper
{
	private static final String AESTYPE ="AES/ECB/PKCS5Padding";
	 
    public static String encrypt(String keyStr, String plainText) throws Exception {
        byte[] encrypt = null; 
        Key key = generateKey(keyStr);
        Cipher cipher = Cipher.getInstance(AESTYPE);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        encrypt = cipher.doFinal(plainText.getBytes()); 
        
        return android.util.Base64.encodeToString(encrypt, android.util.Base64.DEFAULT); 
    } 
 
    public static String decrypt(String keyStr, String encryptData) throws Exception {
        byte[] decrypt = null; 
        Key key = generateKey(keyStr);
        Cipher cipher = Cipher.getInstance(AESTYPE);
        cipher.init(Cipher.DECRYPT_MODE, key);
        decrypt = cipher.doFinal(android.util.Base64.decode(encryptData, android.util.Base64.DEFAULT));
        return new String(decrypt).trim();
    } 
 
    private static Key generateKey(String key)throws Exception {
    	SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        return keySpec;
    } 
}
