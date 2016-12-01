package com.leeo.demo.poi;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import java.io.*;

/**
 * Created by lijun on 2016/12/1.
 */
public class WriteDocumentTest {
    public static void main(String[] args) throws IOException, XmlException {
        InputStream is = new FileInputStream("E:/powergrid/检测报告/物资/PW037眉山西电蜀能.docx");
        XWPFDocument doc = new XWPFDocument(is);
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File("D:"+File.separator+"poi.docx"));
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("At w3ii.com, we strive hard to \" +\n" +
                "   \"provide quality tutorials for self-learning \" +\n" +
                "   \"purpose in the domains of Academics, Information \" +\n" +
                "   \"Technology, Management and Computer Programming\n" +
                "   Languages.");
        CTP ctp = CTP.Factory.newInstance();
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(
                document, (CTSectPr) doc.getFooterArray(4).getParagraph(ctp));
        document.write(out);
        out.close();
    }
}
