package com.ek.mobileapp.secret;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DDES {
    public static final String KEY = "12345678";//8

    private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

    public static String eencryptDES(String encryptString, String encryptKey) {
        //      IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = null;
        byte[] encryptedData = (byte[]) null;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            encryptedData = cipher.doFinal(encryptString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BBase64.encode(encryptedData);
    }

    public static String decryptDES(String decryptString, String decryptKey) {
        byte[] byteMi = new BBase64().decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        //      IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");

        Cipher cipher = null;
        byte[] decryptedData = (byte[]) null;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            decryptedData = cipher.doFinal(byteMi);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String(decryptedData);
    }
}