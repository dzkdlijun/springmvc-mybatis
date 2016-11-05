package com.leeo.demo.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by lijun on 2016/11/5.
 */
public class ZipOutputStreamDemo {
    public static void main(String[] args) throws IOException {
//        compressFile("D:"+File.separator+"test","d:" + File.separator + "zipFile.zip");
        unCompressFile("D:" + File.separator + "zipFile.zip", "E:"+File.separator);
    }

    /**
     * 压缩文件夹或文件
     *
     * @param srcFilePath 目标文件或文件夹路径
     * @param desFilePath 压缩后压缩文件保存路径
     * @throws IOException
     */
    public static void compressFile(String srcFilePath, String desFilePath) throws IOException {
        File zipFile = new File(desFilePath.endsWith(".zip") ? desFilePath : desFilePath + ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(
                zipFile));
        zipOut.setComment("hello");
        File file = new File(srcFilePath);
        convertToZip(null, file, zipOut);
        zipOut.close();
    }

    /**
     * 压缩文件或文件夹
     *
     * @param fileName 父文件夹名字，如果压缩的文件夹已经是根文件夹，则为空，如果不为空，则会在压缩文件中增加一级目录
     * @param file     要压缩的文件或文件
     * @param zipOut
     * @throws IOException
     */
    public static void convertToZip(String fileName, File file, ZipOutputStream zipOut) throws IOException {
        if (fileName == null) {
            fileName = "";
        }
        if (zipOut == null) {
            File zipFile = new File("d:" + File.separator + "zipFile.zip");
            zipOut = new ZipOutputStream(new FileOutputStream(
                    zipFile));
            zipOut.setComment("hello");
        }
        if (!file.exists()) {//文件夹或文件不存在
            zipOut.close();
            return;
        } else if (file.isDirectory()) {//处理文件夹
            for (File file1 : file.listFiles()) {
                convertToZip(fileName.isEmpty() ? file.getName() : fileName + File.separator + file.getName(), file1, zipOut);
            }
        } else {//处理文件
            InputStream inputStream = new FileInputStream(file);
            zipOut.putNextEntry(new ZipEntry(fileName + File.separator + file.getName()));
            int temp = 0;
            while ((temp = inputStream.read()) != -1) {
                zipOut.write(temp);
            }
            inputStream.close();
        }
    }


    /**
     * 解压文件
     * @param zipFilePath 压缩文件目录
     * @param unZipFilePath 解压后要存放的目录，为空则解压到当前目录
     * @throws IOException
     */
    public static void unCompressFile(String zipFilePath, String unZipFilePath) throws IOException {
        File file = new File(zipFilePath.endsWith(".zip") ? zipFilePath : zipFilePath + ".zip");
        File outFile = null;
        ZipFile zipFile = new ZipFile(file);
        ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry = null;
        InputStream input = null;
        OutputStream output = null;
        String unZipFilePathPrefix = zipFilePath.endsWith(".zip") ? zipFilePath.substring(0, zipFilePath.lastIndexOf(".zip")) : zipFilePath;//默认加压到当前文件夹
        unZipFilePathPrefix += File.separator;
        if (unZipFilePath != null && !unZipFilePath.trim().isEmpty()) {
            unZipFilePathPrefix = unZipFilePath.endsWith(File.separator) ? unZipFilePath : unZipFilePath + File.separator;
        }
        while ((entry = zipInput.getNextEntry()) != null) {
            System.out.println("解压缩" + entry.getName() + "文件");
            outFile = new File(unZipFilePathPrefix + entry.getName());
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            input = zipFile.getInputStream(entry);
            output = new FileOutputStream(outFile);
            int temp = 0;
            while ((temp = input.read()) != -1) {
                output.write(temp);
            }
            input.close();
            output.close();
        }
    }

}

