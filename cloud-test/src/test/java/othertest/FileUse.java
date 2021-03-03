package othertest;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author tong.luo
 * @description File
 * @date 2021/2/27 22:17
 */
public class FileUse {
    public static void main(String[] args) {
        String path = "G:\\Using\\2021-02-27";
        rennameFileName(path);
    }

    /**
     * @param filepath eg file,file2
     * @return
     */
    public static ArrayList<String> getFiles(String filepath) {
        ArrayList<String> files = new ArrayList<String>();
        if (StringUtils.isEmpty(filepath)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        String[] split = filepath.split(",");
        HashMap<String, String> hashMap = new HashMap<>();
        for (String filename : split) {
            File file = new File(filename);
            File[] tempLists = file.listFiles();
            for (int i = 0; i < tempLists.length; i++) {
                if (tempLists[i].isFile()) {
                    String name = tempLists[i].getName();
                    String[] s = name.split("_");
                    String names1 = s[0];
                    builder.append("ren " + name + " " + names1 + ".mp4 \n");
                    if (hashMap.keySet().contains(names1)) {
                        System.out.println(names1 + "=============" + filename);
                        System.out.println(hashMap.get(names1) + "======before=======");
                    }
                    hashMap.put(names1, filename);

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

    public static void rennameFileName(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (File file : files) {
            String name = file.getName();
            String[] s = name.split("_");
            File newfile = new File(path + "/" + s[0] + ".mp4");
            System.out.println(newfile);
            file.renameTo(newfile);
//            break;
        }
    }
}
