package com.lt.alidemo.IoTest;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyFolderDemo {
    private static String filePath = "F:\\Using\\2020-07-26";

    public static void main(String[] args) throws IOException {
//        String filePath = "F:\\Using\\2020-07-26";
// 封装数据源File
        File srcFile = new File(filePath);    //这里填源文件夹路径

        copyFolder(srcFile);
//copyFile(srcFile);
    }

    private static void copyFolder(File srcFile)
            throws IOException {
// 判断该File是文件夹还是文件
        File[] ff = srcFile.listFiles();
        for (File f : ff) {

//如果是文件夹，则递归调用
            if (f.isDirectory()) {
                copyFolder(f);
            } else {               ///11111

//如果是文件则复制
                copyFile(f);
//System.out.println(f.getName());


            }
        }
    }

    //用字节缓冲流实现文件复制
    private static void copyFile(File srcFile) throws IOException {

        File destFile = new File(filePath, srcFile.getName());   //这里写目的文件夹路径
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destFile));

        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }

        bos.close();
        bis.close();
    }
}