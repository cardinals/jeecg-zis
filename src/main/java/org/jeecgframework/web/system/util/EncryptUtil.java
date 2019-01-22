package org.jeecgframework.web.system.util;

/**
 * Created by tap-pcng43 on 2017-6-6.
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class EncryptUtil {

    public static String encrypt(String str, String password) throws Exception {
        byte[] encryptResult = encrypt2Byte(str, password);
        String encryptResultStr = parseByteToHexStr(encryptResult);
        return encryptResultStr;
    }

    public static String decrypt(String str, String password) throws Exception {
        byte[] b = parseHexStrToByte(str);
        String r = new String(decrypt2Byte(b, password));
        return r;
    }

    private static byte[] encrypt2Byte(String content, String password) throws Exception {
        byte[] result = null;
        try {
            KeyGenerator kGen = KeyGenerator.getInstance("AES");
            kGen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            result = cipher.doFinal(byteContent);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private static byte[] decrypt2Byte(byte[] content, String password) throws Exception {
        byte[] result = null;
        try {
            KeyGenerator kGen = KeyGenerator.getInstance("AES");
            kGen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(content);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public static String parseByteToHexStr(byte[] buf) {
        StringBuffer stb = new StringBuffer(1024);
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            stb.append(hex.toUpperCase());
        }
        return stb.toString();
    }

    public static byte[] parseHexStrToByte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        int length = hexStr.length() / 2;
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] arge) throws Exception {
        String content = "测试内容测试内容测试内容测试内容测试内容";
        String password = "abc123a";
        String encryptResult = encrypt(content, password);
        System.out.println("encrypt1：[" + encryptResult + "]");
        String decryptResult = decrypt(encryptResult, password);
        System.out.println("decrypt1：[" + decryptResult + "]");
    }
}