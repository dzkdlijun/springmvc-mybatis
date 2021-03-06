package com.leeo.demo.poi;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;

import java.io.*;

/**
 * Created by lijun on 2016/9/26.
 */
public class HWPFDocumentTest {
    public static void main(String[] args) throws Exception {
        HWPFDocumentTest test = new HWPFDocumentTest();
        test.testReadByDoc("E:/powergrid/检测报告/环化/巴中供电公司0282.doc");
    }
    public void testReadByDoc(String filePath) throws Exception {
        InputStream is = new FileInputStream(filePath);
        HWPFDocument doc = new HWPFDocument(is);
        FileOutputStream os = new FileOutputStream("E:/test.doc");
        closeInStream(is);
        //输出书签信息
        printInfo(doc.getBookmarks());
        //输出文本
        System.out.println(doc.getDocumentText());
        Range range = doc.getRange();
//    this.insertInfo(range);
        printInfo(range);
        //读表格
        readTable(range);
        //读列表
        readList(range);
        //删除range
//        Range r = new Range(2, 5, doc);
//        r.delete();//在内存中进行删除，如果需要保存到文件中需要再把它写回文件
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        doc.write(ostream);
        os.write(ostream.toByteArray());
        closeOutStream(os);
        ostream.close();
    }

    /**
     * 关闭输入流
     * @param is
     */
    private void closeInStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeOutStream(OutputStream os){
        if(os!=null){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输出书签信息
     * @param bookmarks
     */
    private void printInfo(Bookmarks bookmarks) {
        int count = bookmarks.getBookmarksCount();
        System.out.println("书签数量：" + count);
        Bookmark bookmark;
        for (int i=0; i<count; i++) {
            bookmark = bookmarks.getBookmark(i);
            System.out.println("书签" + (i+1) + "的名称是：" + bookmark.getName());
            System.out.println("开始位置：" + bookmark.getStart());
            System.out.println("结束位置：" + bookmark.getEnd());
        }
    }

    /**
     * 读表格
     * 每一个回车符代表一个段落，所以对于表格而言，每一个单元格至少包含一个段落，每行结束都是一个段落。
     * @param range
     */
    private void readTable(Range range) {
        //遍历range范围内的table。
        TableIterator tableIter = new TableIterator(range);
        Table table;
        TableRow row;
        TableCell cell;
        while (tableIter.hasNext()) {
            table = tableIter.next();
            int rowNum = table.numRows();
            for (int j=0; j<rowNum; j++) {
                row = table.getRow(j);
                int cellNum = row.numCells();
                for (int k=0; k<cellNum; k++) {
                    cell = row.getCell(k);
                    //输出单元格的文本
                    System.out.println(cell.text().trim());
                }
            }
        }
    }

    /**
     * 读列表
     * @param range
     */
    private void readList(Range range) {
        int num = range.numParagraphs();
        Paragraph para;
        for (int i=0; i<num; i++) {
            para = range.getParagraph(i);
            if (para.isInList()) {
                System.out.println("list: " + para.text());
            }
        }
    }

    /**
     * 输出Range
     * @param range
     */
    private void printInfo(Range range) {
        //获取段落数
        int paraNum = range.numParagraphs();
        System.out.println(paraNum);
        for (int i=0; i<paraNum; i++) {
//       this.insertInfo(range.getParagraph(i));
            System.out.println("段落" + (i+1) + "：" + range.getParagraph(i).text());
//            if (i == (paraNum-1)) {
//                this.insertInfo(range.getParagraph(i));
//            }
            if(range.getParagraph(i).text().replaceAll(" ","").contains("委托单位:")){
                System.out.println("****检查到委托单位*****：");
                range.getParagraph(i).insertAfter("这是委托单位");
            }
        }
        int secNum = range.numSections();
        System.out.println(secNum);
        org.apache.poi.hwpf.usermodel.Section section;
        for (int i=0; i<secNum; i++) {
            section = range.getSection(i);
            System.out.println(section.getMarginLeft());
            System.out.println(section.getMarginRight());
            System.out.println(section.getMarginTop());
            System.out.println(section.getMarginBottom());
            System.out.println(section.getPageHeight());
            System.out.println(section.text());
        }
    }

    /**
     * 插入内容到Range，这里只会写到内存中
     * @param range
     */
    private void insertInfo(Range range) {
        range.insertAfter("Hello");
    }
}
