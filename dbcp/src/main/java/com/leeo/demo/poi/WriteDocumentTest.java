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
//        InputStream is = new FileInputStream(new File("D:"+File.separator+"poi.docx"));
//        is.close();
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


        XWPFParagraph paragraphPage2 = document.createParagraph();
        paragraphPage2.setPageBreak(true);
        CTSectPr sectPr1 = document.getDocument().getBody().addNewSectPr();
        if(!sectPr1.isSetPgSz()){
            sectPr1.addNewPgSz();
        }
        CTPageSz pageSz = sectPr1.getPgSz();
        pageSz.setH(new BigInteger("5950"));
        pageSz.setW(new BigInteger("8450"));
        pageSz.setOrient(STPageOrientation.Enum.forInt(STPageOrientation.INT_LANDSCAPE));
        XWPFRun runPage2 = paragraphPage2.createRun();
        runPage2.setText("At w3ii.com, we strive hard to \" +\n" +
                "   \"provide quality tutorials for self-learning \" +\n" +
                "   \"purpose in the domains of Academics, Information \" +\n" +
                "   \"Technology, Management and Computer Programming\n" +
                "   Languages.");
        document.write(out);
        out.close();
    }

    /**
     * @Description 设置字体信息
     */
    public static void setParagraphRunFontInfo(XWPFParagraph p, XWPFRun pRun,
                                               String content, String fontFamily, String fontSize) {
        CTRPr pRpr = getRunCTRPr(p, pRun);
        if (content != null && !content.equals("")) {
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


    public static void setDocumentSizeAndDirection(XWPFDocument document, String width, String height, STPageOrientation.Enum orientation){
        CTSectPr sectPr = getDocumentCTSectPr(document);
        CTPageSz pageSz = sectPr.getPgSz();
        if(width!=null&&!width.equals("")){
            pageSz.setW(new BigInteger(width));
        }else {
            pageSz.setW(new BigInteger("21"));
        }
        if(height!=null&&!height.equals("")){
            pageSz.setH(new BigInteger(height));
        }else {
            pageSz.setH(new BigInteger("29.7"));
        }
        pageSz.setOrient(orientation);
    }

    public static CTSectPr getDocumentCTSectPr(XWPFDocument document){
        //start of 分页
//        XWPFParagraph p = document.createParagraph();
//        //给这个段落添加一个分隔符即可。
//        p.setPageBreak(true);
        //end of 分页
        CTSectPr sectPr =document.getDocument().getBody().isSetSectPr()?document.getDocument().getBody().getSectPr():
                document.getDocument().getBody().addNewSectPr();
        return sectPr;
    }
}
