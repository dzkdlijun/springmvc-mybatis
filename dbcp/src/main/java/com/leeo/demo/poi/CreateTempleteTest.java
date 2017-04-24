package com.leeo.demo.poi;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author lijun
 * @since 2017/4/24 15:37
 */
public class CreateTempleteTest {

    public static void main(String[] args) throws IOException {
        String filePath = "E:/powergrid/检测报告/环化/环保天威07.docx";
        String filePath2 = "E:/powergrid/检测报告/环化/环保天威07-1.docx";
        InputStream is = new FileInputStream(filePath);
        XWPFDocument doc = new XWPFDocument(is);
        is.close();
//        FileOutputStream os = new FileOutputStream(new File("E:/powergrid/检测报告/环化/test.docx"));
//        doc.write(os);
//        os.close();
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        readParagraphTemplete(paragraphs);
        is = new FileInputStream(filePath2);
        XWPFDocument doc2 = new XWPFDocument(is);
        is.close();
        System.out.println("--------------------去掉委托单位后--------------");
        List<XWPFParagraph> paragraphs2 = doc2.getParagraphs();
        readParagraphTemplete(paragraphs2);
        readTableTemplete(doc.getTablesIterator());
        //改写文档
        createParagraphTemplete(paragraphs2, "${{company}}");
        createTableTemplete(doc2.getTablesIterator());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        doc2.write(outputStream);
        String outFilePath = "E:/templete.docx";
        OutputStream outputStream1 = new FileOutputStream(outFilePath);
        outputStream1.write(outputStream.toByteArray());
        outputStream.close();
        outputStream1.close();
    }

    public static void readParagraphTemplete(List<XWPFParagraph> paragraphs) {
        for (XWPFParagraph paragraph : paragraphs) {
            System.out.println("****" + paragraph.getText() + "*****");
        }
    }

    public static void createParagraphTemplete(List<XWPFParagraph> paragraphs, String newCompany) {
        for (XWPFParagraph paragraph : paragraphs) {
            String oldText = paragraph.getText();
            if (oldText.replaceAll(" ", "").equals("委托单位:")) {
                String newText = oldText + newCompany;
                List<XWPFRun> runs = paragraph.getRuns();
                runs.get(0).setText(newText, 0);
            }
        }
    }

    public static void readTableTemplete(Iterator<XWPFTable> it) {
        while (it.hasNext()) {
            XWPFTable table = it.next();
            List<XWPFTableRow> rows = table.getRows();
            for (int i = 0; i < rows.size(); i++) {
                XWPFTableRow row = rows.get(i);
                System.out.println("######表格第" + i + "行开始#####");
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    System.out.println(cell.getText());
                }
                System.out.println("！！！！！！！表格第" + i + "行结束！！！！！！！");
            }
        }
    }

    public static void createTableTemplete(Iterator<XWPFTable> it) {
        while (it.hasNext()) {
            XWPFTable table = it.next();
            List<XWPFTableRow> rows = table.getRows();
            boolean nextReplace = false;
            boolean hasReplaced = false;
            if (!hasReplaced) {
                for (int i = 0; i < rows.size(); i++) {
                    XWPFTableRow row = rows.get(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (int j = 0; j < cells.size(); j++) {
                        XWPFTableCell cell = cells.get(j);
                        if (!nextReplace) {
                            if(cell.getText().trim().equals("名称Name")){
                                nextReplace = true;
                                break;
                            }
                        }else{
                            cell.setText("${{replaceCell"+j+"}}");
                        }
                    }

                }
            } else {
                break;
            }

        }
    }
}
