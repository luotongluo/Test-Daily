package com.lt.demo.test;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: LT
 * @Date: 2019/4/15 11:14
 * @Description: 文件的遍历
 * @Version 1.0
 */
public class FileGet {
    @Test
    public void list() throws IOException {
        Files.list(Paths.get(".")).filter(Files::isDirectory).forEach(System.out::println);
    }

    @Test
    public void walk() throws IOException {
        Files.walk(Paths.get("."), FileVisitOption.FOLLOW_LINKS).forEach(System.out::println);
    }
}
