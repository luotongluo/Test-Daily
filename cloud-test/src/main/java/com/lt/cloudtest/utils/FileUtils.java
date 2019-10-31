package com.lt.cloudtest.utils;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by tong.luo on 2019/8/11 10:58
 */
public class FileUtils {
    /**
     * 文件的读取
     *
     * @param path
     * @param filename
     */
    public static void readFile(String path, String filename) {
        try {
            int length = 0;
            String str = "";
            byte buffer[] = new byte[10];
            FileInputStream fis = new FileInputStream(new File(path, filename));
            while ((length = fis.read(buffer, 0, buffer.length)) != -1) {
                str += new String(buffer, 0, length);
            }
            System.out.println(str);
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件夹
     *
     * @param path
     */
    public static void createFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            System.out.println("文件夹已存在!");
        } else {
            folder.mkdir();
        }
    }

    /**
     * 创建文件
     *
     * @param path
     * @param filename
     */
    public static void createFile(String path, String filename) {
        File file = new File(path, filename);
        if (file.exists()) {
            System.out.println("文件已存在!");
            System.out.println(file.length());
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("文件创建失败");
            }
        }
    }

    /**
     * 写出文件
     *
     * @param path
     * @param filename
     * @param content
     */
    public static void writeFile(@NotNull String path, @NotNull String filename, String content) {
        try {
            String str = null;
            if (content == null || content.isEmpty()) {
                str = "123456789";
            } else {
                str = content;
            }

            byte b[] = str.getBytes();
            FileOutputStream fos = new FileOutputStream(new File(path, filename));
            fos.write(b);
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            System.out.println("写文件失败");
        }
    }
//    public static void main(String[] args){
//        FileUtils.createFolder("c:/text");
//        FileUtils.createFile("c:/text","1.txt");
//    }
}
