package othertest;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tong.luo
 * @description File
 * @date 2021/2/27 22:17
 */
public class FileUse {
    private static final String FILE_AFTER_NAME = ".mp4";

    public static void main(String[] args) {
        String path1 = "G:\\Using\\2021-01-30";
        String path2 = "G:\\Using\\2021-02-27";
        String path3 = "G:\\Using\\2021-03-26";


        List<String> pathList = Arrays.asList(path1, path2, path3);
        String pathJoin = pathList.stream().collect(Collectors.joining(","));
        System.out.println("pathJoin：" + pathJoin);

        getFiles(pathJoin,null,FILE_AFTER_NAME);
//        rennameFileName(path3, null);
    }

    /**
     * @param filepath eg file,file2
     * @return
     */
    public static ArrayList<String> getFiles(String filepath, String filepathSplite, String fileNameReplace) {
        ArrayList<String> files = new ArrayList<String>();
        if (StringUtils.isEmpty(filepath)) {
            return null;
        }
        if (StringUtils.isEmpty(filepathSplite)) {
            filepathSplite = ",";
        }
        if (StringUtils.isEmpty(fileNameReplace)) {
            fileNameReplace = FILE_AFTER_NAME;
        }
        StringBuilder builder = new StringBuilder();
        String[] split = filepath.split(filepathSplite);
        HashMap<String, String> hashMap = new HashMap<>();
        for (String filename : split) {
            File file = new File(filename);
            File[] tempLists = file.listFiles();
            for (int i = 0; i < tempLists.length; i++) {
                if (tempLists[i].isFile()) {
                    String name = tempLists[i].getName();
                    if (!name.contains(fileNameReplace)) {
                        builder.append("path:" + filename + "\t  " + name + FILE_AFTER_NAME +" \n");
                    }
                    if (hashMap.keySet().contains(name)) {
                        System.out.println(name + "=============" + filename);
                        System.out.println(hashMap.get(name) + "======before=======");
                    }
                    hashMap.put(name, filename);

                }
            }
        }
        System.out.println("size : " + hashMap.keySet().size());
        System.out.println(builder);
//        for (int i = 0; i < files.size(); i++) {
//            System.out.println(files.get(i));
//        }
        return files;
    }

    /**
     * 更换文件名称
     *
     * @param path           文件地址
     * @param fileNameSplite 文件分隔符默认为 _
     */
    public static void rennameFileName(String path, String fileNameSplite) {
        if (StringUtils.isEmpty(fileNameSplite)) {
            fileNameSplite = "_";
        }

        String[] split = path.split(",");
        for (String filePath : split) {
            File folder = new File(filePath);
            File[] files = folder.listFiles();
            for (File file : files) {
                String name = file.getName();
                String[] s = name.split(fileNameSplite);
                if(name.contains(FILE_AFTER_NAME)){
                    continue;
                }
                File newfile = new File(path + "/" + s[0] + FILE_AFTER_NAME);
                System.out.println(newfile);
                file.renameTo(newfile);
            }
        }

    }
}
