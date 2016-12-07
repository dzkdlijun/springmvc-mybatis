package com.leeo.demo.poi;

import com.leeo.demo.poi.domain.BaseDomain;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2016/9/26.
 */
public class XWPFDocumentTest {

    private static final String DOCX_TEMPLATE_PATH = "D:"+File.separator+"template.docx";
    private static final String DOC_TEMPLATE_PATH = "D:"+File.separator+"template.doc";

    public static void main(String[] args) throws Exception {
        XWPFDocumentTest test = new XWPFDocumentTest();
//        test.testReadByDocx("E:/powergrid/检测报告/物资/PW037眉山西电蜀能.docx");
//        test.testReadByDocx("D:"+File.separator+"template.docx");
        test.update("D:"+File.separator+"update.docx",new BaseDomain(0l,"（新都500千繁220千伏间隔扩建工程（删除））"));
    }
    
    public void testReadByDocx(String filePath) throws Exception {
        InputStream is = new FileInputStream(filePath);
        XWPFDocument doc = new XWPFDocument(is);
//        FileOutputStream os = new FileOutputStream(new File("E:/powergrid/检测报告/环化/test.docx"));
//        doc.write(os);
//        os.close();
        List<XWPFParagraph> paras = doc.getParagraphs();
        for (XWPFParagraph para : paras) {
            //当前段落的属性
//       CTPPr pr = para.getCTP().getPPr();
            System.out.println(para.getText());
        }
        //获取文档中所有的表格
        List<XWPFTable> tables = doc.getTables();
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        for (XWPFTable table : tables) {
            //表格属性
//       CTTblPr pr = table.getCTTbl().getTblPr();
            //获取表格对应的行
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                //获取行对应的单元格
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    System.out.println(cell.getText());
                }
            }
        }
//        FileOutputStream os = new FileOutputStream(new File("E:/powergrid/检测报告/物资/test.docx"));
//        doc.write(os);
//        os.close();
        this.close(is);
    }

    public void update(String filePath,BaseDomain domain) throws IOException, IllegalAccessException {
        if(filePath.endsWith("docx")){
            updateDocx(filePath,domain);
        }else {
            updateDoc(filePath,domain);
        }

    }

    public void updateDocx(String filePath,BaseDomain domain) throws IOException, IllegalAccessException {
        OPCPackage pack = POIXMLDocument.openPackage(DOCX_TEMPLATE_PATH);
//        FileInputStream inputStream = new FileInputStream(DOCX_TEMPLATE_PATH);
        XWPFDocument document = new XWPFDocument(pack);
//        inputStream.close();

        List<XWPFParagraph> paragraphs =document.getParagraphs();
        processParagraphs(paragraphs,BaseDomain.getPropertyMap(domain));
        Iterator<XWPFTable> it = document.getTablesIterator();
        while (it.hasNext()){
            XWPFTable table = it.next();
            List<XWPFTableRow> rows = table.getRows();
            for(XWPFTableRow row:rows){
                List<XWPFTableCell> cells = row.getTableCells();
                for(XWPFTableCell cell:cells){
                    List<XWPFParagraph> tableParagraphs = cell.getParagraphs();
                    processParagraphs(tableParagraphs,BaseDomain.getPropertyMap(domain));
                }
            }
        }

        WriteDocumentTest.setDocumentSizeAndDirection(document,null,null, STPageOrientation.Enum.forInt(STPageOrientation.INT_PORTRAIT));
        FileOutputStream fos = new FileOutputStream(filePath);
        document.write(fos);
        fos.flush();
        fos.close();
    }

    public void updateDoc(String filePath,BaseDomain domain) throws IOException, IllegalAccessException {
        FileInputStream ins = new FileInputStream(DOC_TEMPLATE_PATH);
        HWPFDocument document = new HWPFDocument(ins);
        Range bodyRange = document.getRange();
        Map<String,String> map = BaseDomain.getPropertyMap(domain);
        for(Map.Entry<String,String> entry:map.entrySet()){
            bodyRange.replaceText("{%"+entry.getKey()+"%}",entry.getValue());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.write(outputStream);
        OutputStream outputStream1 = new FileOutputStream(filePath);
        outputStream1.write(outputStream.toByteArray());
        outputStream1.close();
        outputStream.close();
    }

    private void processParagraphs(List<XWPFParagraph> paragraphs,Map<String,String> map){
        for(XWPFParagraph paragraph:paragraphs){
            List<XWPFRun> runs = paragraph.getRuns();
            for(XWPFRun run:runs){
                String text = run.getText(0);
                boolean isSetText = false;
                if(text!=null&&!text.trim().equals("")){
                    for(Map.Entry<String,String> entry:map.entrySet()){
                        String key = "{%"+entry.getKey()+"%}";
                        if(text.indexOf(key)!=-1){
                            isSetText = true;
                            text = text.replace(key,entry.getValue());
                        }
                    }
                    if(isSetText){
                        run.setText(text,0);
                    }
                }
            }
        }
    }


    /**
     * 关闭输入流
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
