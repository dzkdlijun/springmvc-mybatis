package com.leeo.demo.poi;

import com.zhuozhengsoft.pageoffice.ThemeType;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTAltChunk;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lijun on 2016/12/5.
 */
public class TestMergeDoc {
    public static void main(String[] args) throws IOException, Docx4JException {
//        mergeDoc("D:"+File.separator+"template.docx","D:"+File.separator+"template.docx");

        mergeDocx(Arrays.<InputStream>asList(new FileInputStream("D:"+File.separator+"cover.docx"),new FileInputStream("D:"+File.separator+"table.docx"),new FileInputStream("D:"+File.separator+"report.docx")));
    }

    public static void mergeDoc(String filePath1,String filePath2) throws IOException {
        FileInputStream fileInputStream1 = new FileInputStream(filePath1);
        FileInputStream fileInputStream2 = new FileInputStream(filePath2);

        XWPFDocument document1 = new XWPFDocument(fileInputStream1);
        XWPFDocument document2 = new XWPFDocument(fileInputStream2);
        String mergePath = "D:"+ File.separator+"merge.docx";
        FileOutputStream outputStream = new FileOutputStream(mergePath);

        CTP ctp = CTP.Factory.newInstance();
        XWPFParagraph paragraph2 = document2.getParagraphArray(2);
        XWPFWordExtractor docx = new XWPFWordExtractor(document1);
        docx.appendParagraphText(new StringBuffer("新增段落"),document1.getParagraphArray(2));

        document1.write(outputStream);

        outputStream.close();
        fileInputStream1.close();
        fileInputStream2.close();

    }

    public static InputStream mergeDocx(final List<InputStream> streams) throws IOException, Docx4JException {
        WordprocessingMLPackage target = null;
        final File merged = new File("D:"+File.separator+"merge.docx");
        int chunkId = 0;
        Iterator<InputStream> it = streams.iterator();
        while (it.hasNext()){
            InputStream is = it.next();
            if(is!=null){
                if(target==null){
                    OutputStream os = new FileOutputStream(merged);
                    os.write(IOUtils.toByteArray(is));
                    os.close();

                    target = WordprocessingMLPackage.load(merged);
                }else {
                    insertDocx(target.getMainDocumentPart(),IOUtils.toByteArray(is),chunkId++);
                }
            }
        }

        if(target!=null){
            target.save(merged);
            return new FileInputStream(merged);
        }else {
            return null;
        }
    }

    private static void  insertDocx(MainDocumentPart main, byte[] bytes, int chunkId) throws InvalidFormatException {
        AlternativeFormatInputPart alternativeFormatInputPart = new AlternativeFormatInputPart(new PartName("/part"+chunkId+".docx"));
        alternativeFormatInputPart.setContentType(new ContentType(main.getContentType()));
        alternativeFormatInputPart.setBinaryData(bytes);
        Relationship relationship = main.addTargetPart(alternativeFormatInputPart);

        CTAltChunk chunk = Context.getWmlObjectFactory().createCTAltChunk();
        chunk.setId(relationship.getId());

        main.addObject(chunk);
    }
}
