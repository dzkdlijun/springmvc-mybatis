package com.leeo.demo.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;
import org.dom4j.xpath.DefaultXPath;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by lijun on 2016/12/8.
 */
public class ParseXml {

    public static void main(String[] args) throws IOException, DocumentException {
        //将src下面的xml转换为输入流
        InputStream inputStream = new FileInputStream(new File("D:"+File.separator+"环保天威07.xml"));
        //InputStream inputStream = this.getClass().getResourceAsStream("/module01.xml");//也可以根据类的编译文件相对路径去找xml
        //创建SAXReader读取器，专门用于读取xml
        SAXReader saxReader = new SAXReader();
        //根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
        Document document = saxReader.read(inputStream);
        inputStream.close();
//        Document document = saxReader.read(new File("D:/project/dynamicWeb/src/resource/module01.xml"));//必须指定文件的绝对路径
        System.out.println(document.toString());
        DefaultXPath xPath = new DefaultXPath("pkg:package");
        xPath.setNamespaceURIs(Collections.singletonMap("pkg","http://schemas.microsoft.com/office/2006/xmlPackage"));

        List list = xPath.selectNodes(document);
        System.out.println(list);

        DefaultXPath wPath = new DefaultXPath("//w:t");
        wPath.setNamespaceURIs(Collections.singletonMap("w","http://schemas.openxmlformats.org/wordprocessingml/2006/main"));
        List<DefaultElement> wList = wPath.selectNodes(document);
        List<DefaultText> texts = wList.get(1).content();
        wList.remove(5);
        texts.get(0).setText("DOM4J替换标题");
        System.out.println(wList);

        XMLWriter writer = null;
        /** 格式化输出 */
        //OutputFormat format = OutputFormat.createPrettyPrint();
        //OutputFormat format = OutputFormat.createCompactFormat();
        //OutputFormat format = new OutputFormat();
        //格式化各标签之间的间隔符，这里将其设置为空
        OutputFormat format  = new OutputFormat("");
        //格式化换行符，这里将其设成空
        format.setLineSeparator("");

        /** 指定XML编码，一定要设成UTF-8，否则如果有中文，就会导致文件打不开 */
        format.setEncoding("UTF-8");
        writer= new XMLWriter(new FileOutputStream(new File("D:"+File.separator+"dom4j.doc")),format);
        writer.write(document);
        writer.flush();
        writer.close();
    }
}
