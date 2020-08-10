package com.lt.other;

import java.util.UUID;

/**
 * @author tong.luo
 * @description Change
 * @date 2020/7/29 22:59
 */
public class Change {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("copy /b ");
        int loop = 1023;
        String a1 = "Y2hlbmppbmdjb25n";
//                     Y2hlbmppbmdjb25n0
        for (int i = 0; i <= loop; i++) {
            if(i == loop ){
                stringBuffer.append(a1+ i);
            }else {
                stringBuffer.append(a1+ i + "+");

            }
        }
        UUID uuid = UUID.randomUUID();
        String substring = String.valueOf(uuid).substring(0, 10);
        stringBuffer.append(" "+substring + ".avi");
        System.out.print(stringBuffer);
    }
}
