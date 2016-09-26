package com.leeo.demo.poi;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lijun on 2016/9/26.
 */
public class PoiWordTest {
    public static void main(String[] args) throws IOException, OpenXML4JException, XmlException {
//        FileInputStream in = new FileInputStream("E:/powergrid/检测报告/环化/保变天威.doc");
//        WordExtractor ex = new WordExtractor(in);
//        String text2003 = ex.getText();
//        String [] textParagraph = ex.getParagraphText();
//        System.out.println(text2003);
//        System.out.println(textParagraph);

        read_07("E:/powergrid/检测报告/环化/环保天威07.docx");
    }

    public static void read_07(String filePath) throws IOException, OpenXML4JException, XmlException {
        InputStream is = new FileInputStream(new File(filePath));
        WordExtractor ex = new WordExtractor(is);
        String text2003 = ex.getText();
        System.out.println(text2003);

        //word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
        OPCPackage opcPackage = POIXMLDocument.openPackage("c://files//2007.docx");
        POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
        String text2007 = extractor.getText();
        System.out.println(text2007);
    }
}
