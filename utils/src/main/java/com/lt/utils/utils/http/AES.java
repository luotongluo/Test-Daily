package com.lt.utils.utils.http;

/**
 * @Author: LT
 * @Date: 2019/10/31 19:24
 * @Description:
 * @Version 1.0
 */

import com.lt.utils.utils.tools.MD5;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public AES() {
    }

    public static String aesEecrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(1, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
        String encryptResultStr = parseByte2HexStr(encrypted);
        return encryptResultStr;
    }

    public static String aesDecrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(2, skeySpec, iv);
            byte[] encrypted1 = parseHexStr2Byte(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, encodingFormat);
            return originalString;
        } catch (Exception var11) {
            var11.printStackTrace();
            throw var11;
        }
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for (int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }

            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        String sKey = MD5.MD5Encode("1234", "UTF-8").substring(0, 16);
        String ivParameter = MD5.MD5Encode("1234", "UTF-8").substring(0, 16);
        String cSrc = "123456";
        System.out.println("加密前的字串是：" + cSrc);
        String enString = aesEecrypt(cSrc, "utf-8", sKey, ivParameter);
        System.out.println("加密后的字串是：" + enString);
        String DeString = aesDecrypt(enString, "utf-8", sKey, ivParameter);
        System.out.println("解密后的字串是：" + DeString);
    }
}
