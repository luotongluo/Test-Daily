package com.lt.utils.utils.http;

/**
 * @Author: LT
 * @Date: 2019/10/31 19:23
 * @Description:
 * @Version 1.0
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class Base64Util {
    private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    public Base64Util() {
    }

    public static String encode(byte[] data) {
        int start = 0;
        int len = data.length;
        StringBuffer buf = new StringBuffer(data.length * 3 / 2);
        int end = len - 3;
        int i = start;
        int n = 0;

        int d;
        while(i <= end) {
            d = (data[i] & 255) << 16 | (data[i + 1] & 255) << 8 | data[i + 2] & 255;
            buf.append(legalChars[d >> 18 & 63]);
            buf.append(legalChars[d >> 12 & 63]);
            buf.append(legalChars[d >> 6 & 63]);
            buf.append(legalChars[d & 63]);
            i += 3;
            if (n++ >= 14) {
                n = 0;
                buf.append(" ");
            }
        }

        if (i == start + len - 2) {
            d = (data[i] & 255) << 16 | (data[i + 1] & 255) << 8;
            buf.append(legalChars[d >> 18 & 63]);
            buf.append(legalChars[d >> 12 & 63]);
            buf.append(legalChars[d >> 6 & 63]);
            buf.append("=");
        } else if (i == start + len - 1) {
            d = (data[i] & 255) << 16;
            buf.append(legalChars[d >> 18 & 63]);
            buf.append(legalChars[d >> 12 & 63]);
            buf.append("==");
        }

        return buf.toString();
    }

    private static int decode(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 65;
        } else if (c >= 'a' && c <= 'z') {
            return c - 97 + 26;
        } else if (c >= '0' && c <= '9') {
            return c - 48 + 26 + 26;
        } else {
            switch(c) {
                case '+':
                    return 62;
                case '/':
                    return 63;
                case '=':
                    return 0;
                default:
                    throw new RuntimeException("unexpected code: " + c);
            }
        }
    }

    public static byte[] decode(String s) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            decode(s, bos);
        } catch (IOException var5) {
            throw new RuntimeException();
        }

        byte[] decodedBytes = bos.toByteArray();

        try {
            bos.close();
            bos = null;
        } catch (IOException var4) {
            System.err.println("Error while decoding BASE64: " + var4.toString());
        }

        return decodedBytes;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "=";
        System.out.println(new String(decode(s), "UTF-8"));
    }

    private static void decode(String s, OutputStream os) throws IOException {
        int i = 0;
        int len = s.length();

        while(true) {
            while(i < len && s.charAt(i) <= ' ') {
                ++i;
            }

            if (i == len) {
                break;
            }

            int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6) + decode(s.charAt(i + 3));
            os.write(tri >> 16 & 255);
            if (s.charAt(i + 2) == '=') {
                break;
            }

            os.write(tri >> 8 & 255);
            if (s.charAt(i + 3) == '=') {
                break;
            }

            os.write(tri & 255);
            i += 4;
        }

    }
}
