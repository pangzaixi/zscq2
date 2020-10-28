package com.system.utils;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *根据byte数组，生成文件
 *
 * @author seesun2012@163.com
 *
 */
public class FileUtil {

	/**
     * 根据byte数组，生成文件
     * filePath  文件路径
     * fileName  文件名称（需要带后缀，如*.jpg、*.java、*.xml）
     */
    public static void putFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            System.out.println("文件路径"+filePath);
            System.out.println(!dir.exists());
            System.out.println(dir.isDirectory());
//            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
            if(!dir.exists()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}