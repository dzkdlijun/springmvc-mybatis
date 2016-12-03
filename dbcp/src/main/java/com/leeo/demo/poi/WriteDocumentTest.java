package com.leeo.demo.poi;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by lijun on 2016/12/1.
 */
public class WriteDocumentTest {
    public static void main(String[] args) throws IOException, XmlException {
//        InputStream is = new FileInputStream("E:/powergrid/检测报告/物资/PW037眉山西电蜀能.docx");
//        XWPFDocument doc = new XWPFDocument(is);
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
        XWPFParagraph codePara = new XWPFParagraph(ctp, document);
        XWPFRun r1 = codePara.createRun();
        r1.setText("第");
        setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

        r1 = codePara.createRun();
        CTFldChar fldChar = r1.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.BEGIN);

        r1 = codePara.createRun();
        CTText ctText = r1.getCTR().addNewInstrText();
        ctText.setStringValue("PAGE  \\* MERGEFORMAT");
        ctText.setSpace(SpaceAttribute.Space.PRESERVE);
        setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

        fldChar = r1.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.END);

        r1 = codePara.createRun();
        r1.setText("页 总共");
        setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

        r1 = codePara.createRun();
        fldChar = r1.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.BEGIN);

        r1 = codePara.createRun();
        ctText = r1.getCTR().addNewInstrText();
        ctText.setStringValue("NUMPAGES  \\* MERGEFORMAT ");
        ctText.setSpace(SpaceAttribute.Space.PRESERVE);
        setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

        fldChar = r1.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.END);

        r1 = codePara.createRun();
        r1.setText("页");
        setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

        setParagraphAlignInfo(codePara, ParagraphAlignment.RIGHT,
                TextAlignment.CENTER);
        codePara.setBorderTop(Borders.THICK);
        XWPFParagraph[] newparagraphs = new XWPFParagraph[1];
        newparagraphs[0] = codePara;
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(
                document, sectPr);
        headerFooterPolicy.createFooter(STHdrFtr.DEFAULT, newparagraphs);

        document.write(out);
        out.close();
    }

    /**
     * @Description 设置字体信息
     */
    public static void setParagraphRunFontInfo(XWPFParagraph p, XWPFRun pRun,
                                        String content, String fontFamily, String fontSize) {
        CTRPr pRpr = getRunCTRPr(p, pRun);
        if (content!=null&&!content.equals("")) {
            pRun.setText(content);
        }
        // 设置字体
        CTFonts fonts = pRpr.isSetRFonts() ? pRpr.getRFonts() : pRpr
                .addNewRFonts();
        fonts.setAscii(fontFamily);
        fonts.setEastAsia(fontFamily);
        fonts.setHAnsi(fontFamily);

        // 设置字体大小
        CTHpsMeasure sz = pRpr.isSetSz() ? pRpr.getSz() : pRpr.addNewSz();
        sz.setVal(new BigInteger(fontSize));

        CTHpsMeasure szCs = pRpr.isSetSzCs() ? pRpr.getSzCs() : pRpr
                .addNewSzCs();
        szCs.setVal(new BigInteger(fontSize));
    }

    /**
     * @Description: 得到XWPFRun的CTRPr
     */
    public static CTRPr getRunCTRPr(XWPFParagraph p, XWPFRun pRun) {
        CTRPr pRpr = null;
        if (pRun.getCTR() != null) {
            pRpr = pRun.getCTR().getRPr();
            if (pRpr == null) {
                pRpr = pRun.getCTR().addNewRPr();
            }
        } else {
            pRpr = p.getCTP().addNewR().addNewRPr();
        }
        return pRpr;
    }

    /**
     * @Description: 设置段落对齐
     */
    public static void setParagraphAlignInfo(XWPFParagraph p,
                                      ParagraphAlignment pAlign, TextAlignment valign) {
        if (pAlign != null) {
            p.setAlignment(pAlign);
        }
        if (valign != null) {
            p.setVerticalAlignment(valign);
        }
    }
}
