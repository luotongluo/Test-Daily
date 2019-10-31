package com.lt.utils.utils.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashSet;


/**
 * csv导入的时候用到的工具类
 *
 * @author LT
 * @date 3.14
 * Update by ZuoYuan 2019/08/05
 */
public class CsvUtils {

    // 解决csv文件导入乱码
    public static HashSet<String> importCsvWithNoMessyCode(MultipartFile picFile) throws Exception {
        HashSet<String> objects = new HashSet<>();
        // 判断是否为空
        if (picFile.isEmpty()) {
            return null;
        }

        InputStreamReader fileReader = new InputStreamReader(picFile.getInputStream(), "gbk");
        BufferedReader br = new BufferedReader(fileReader);

        String line;
        int a = 0;
        while ((line = br.readLine()) != null) {
            if (a == 0) {
                a = a + 1;
            } else {
                objects.add(line.trim());
            }
        }

        return objects;
    }

    public static HashSet<String> importCsv(MultipartFile picFile) throws Exception {
        int a = 0;

        HashSet<String> objects = new HashSet<>();
        // 判断是否为空
        if (picFile.isEmpty()) {
            return null;
        }
//        csv文件默认编码为ANSI，这里出现乱码主要是编码不一致问题 参考博课
//        DataInputStream in = new DataInputStream(new FileInputStream(new File("d:\\*.csv")));
//        BufferedReader br= new BufferedReader(new InputStreamReader(in,"GBK"));

        DataInputStream dataInputStream = new DataInputStream(picFile.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(dataInputStream, "utf-8"));
//        InputStreamReader ins = new InputStreamReader(inputStream);
//        BufferedReader br = new BufferedReader(ins);

        String line = "";
        while ((line = br.readLine()) != null) {
            //将第一行的数据不进行导入

            if (a == 0) {
                a = a + 1;
            } else {
                //转成流文件后，就可以自己处理了
                objects.add(line.trim());
            }

        }
        return objects;

    }

//    /**
//     * 导出CSV 商品组
//     *
//     * @param file
//     * @param
//     * @return
//     */
//    public static boolean exportGroupCsv(File file, List<Group> groups) {
//        boolean isSucess = false;
//        FileOutputStream out = null;
//        OutputStreamWriter osw = null;
//        BufferedWriter bw = null;
//
//        try {
//            out = new FileOutputStream(file);
//            osw = new OutputStreamWriter(out, "utf-8");
//            bw = new BufferedWriter(osw);
//            if (groups != null && !groups.isEmpty()) {
//                bw.write("商品组编号" + "," + "商品组名称" + "," + "商品子组名称" + "," + "子组关系" + "," + "商品组类型" +
//                        "," + "数据编号" + "," + "数据名称");
//                bw.write("\r\n");
////		                for(PrdProdgrpsubForCSV data : prdProdgrpsubdetailAll){
////		                    bw.write(data.getName()+","+data.getProdgrpsubname()+","+data.getIsnecessary()+","+data.getType()+","+data.getTextId()+","+data.getText());
////		                    bw.write("\r\n");
////		                }
//                for (Group group : groups) {
//                    bw.write(group.getProdgrpId() + "," + group);
//                }
//            }
//            isSucess = true;
//        } catch (Exception e) {
//            isSucess = false;
//            e.printStackTrace();
//        } finally {
//            if (bw != null) {
//                try {
//                    bw.close();
//                    bw = null;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (osw != null) {
//                try {
//                    osw.close();
//                    osw = null;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (out != null) {
//                try {
//                    out.close();
//                    out = null;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return isSucess;
//    }


//		    public static void main(String[] args) {
//		    	 List<String> dataList=new ArrayList<String>();
//			        dataList.add("1,张三,男");
//			        dataList.add("2,李四,男");
//			        dataList.add("3,小红,女");
//			        boolean isSuccess=CsvUtils.exportCsv(new File("C:/Users/Administrator/Desktop/11.csv"), dataList);
//			        System.out.println(isSuccess);
//			}
//
//

}


