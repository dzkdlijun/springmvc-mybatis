package com.leeo.demo.poi;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by lijun on 2016/12/5.
 */
public class TestMergeDoc {
    public static void main(String[] args) {

    }

    public static void mergeDoc(String filePath1,String filePath2) throws IOException {
        FileInputStream fileInputStream1 = new FileInputStream(filePath1);
        FileInputStream fileInputStream2 = new FileInputStream(filePath2);

        XWPFDocument document1 = new XWPFDocument(fileInputStream1);
        XWPFDocument document2 = new XWPFDocument(fileInputStream2);
        String mergePath = "D:"+ File.separator+"merge.docx";
        FileInputStream fileInputStream = new FileInputStream(mergePath);
        XWPFDocument document = new XWPFDocument(fileInputStream);



    }
}
