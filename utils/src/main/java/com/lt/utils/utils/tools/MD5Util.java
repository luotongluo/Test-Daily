package com.lt.utils.utils.tools;

/**
 * @Author: LT
 * @Date: 2019/10/31 19:22
 * @Description:
 * @Version 1.0
 */

import java.security.MessageDigest;

public class MD5Util {
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public MD5Util() {
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = 256 + b;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception var3) {
        }

        return resultString;
    }

    public static String MD5ShiroEncode(String origin, String salt, int hashIterations) {
        String resultString = null;

        try {
            byte[] bytes = origin.getBytes("UTF-8");
            byte[] salts = salt.getBytes("UTF-8");
            MessageDigest digest = MessageDigest.getInstance("MD5");
            if (salt != null) {
                digest.reset();
                digest.update(salts);
            }

            byte[] hashed = digest.digest(bytes);
            int iterations = hashIterations - 1;

            for(int i = 0; i < iterations; ++i) {
                digest.reset();
                hashed = digest.digest(hashed);
            }

            resultString = byteArrayToHexString(hashed);
        } catch (Exception var10) {
        }

        return resultString;
    }

    public static String MD5Encode16Bit(String origin) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes())).substring(8, 24);
        } catch (Exception var3) {
        }

        return resultString;
    }

    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder("");
        String stmp = "";

        for(int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }

        return hs.toString().toUpperCase();
    }

    public static byte[] getCipherStr(String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(input.getBytes("UTF-8"));
            return byte2hex(md5.digest()).getBytes();
        } catch (Exception var2) {
            var2.printStackTrace();
            return input.getBytes();
        }
    }
}
