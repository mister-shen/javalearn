package com.shenrs;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.List;

/**
 * @author shenrs
 * @Description dom4j解析xml
 * @create 2018-09-03 11:36
 **/
public class Test002 {

    public static void main(String[] args) throws DocumentException {
        // 1.获取到读取对象
        SAXReader reader = new SAXReader();
        Document document = reader.read("E:\\projects\\javalearn\\basics\\json_demo\\pom.xml");
        // 2.获取根节点
        Element rootElement = document.getRootElement();
        getNode(rootElement);
    }

    /**
     * 根据
     * @param element
     */
    public static void getNode(Element element){

        String name = element.getName();
        System.out.println("节点name：" + name);
        List<Attribute> attributes = element.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性name：" + attribute.getName() + ", 属性value：" + attribute.getValue());
        }
        String nodeValue = element.getTextTrim();
        if(!nodeValue.equals("")){
            System.out.println("节点value：" + nodeValue);
        }

        // 遍历节点之下的节点
        Iterator<Element> elementIterator = element.elementIterator();
        while (elementIterator.hasNext()){
            Element nextElement = elementIterator.next();
            getNode(nextElement);
        }


    }
}
