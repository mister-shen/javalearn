package cn.shenrs.patterns.utils;

import javax.xml.parsers.*;
import org.w3c.dom.*;  
import java.io.*;
public class XMLUtil {
    /**
     * 该方法用于从XML配置文件中提取图表类型，并返回类型名
     * @param resourcesPath 配置文件路径
     * @return
     */
    public static String getChartType(String resourcesPath) {
        try {  
            //创建文档对象  
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();  
            DocumentBuilder builder = dFactory.newDocumentBuilder();  
            Document doc;
            doc = builder.parse(new File(XMLUtil.class.getResource("/").getPath()+ File.separator + resourcesPath));
            //获取包含图表类型的文本节点  
            NodeList nl = doc.getElementsByTagName("chartType");  
            Node classNode = nl.item(0).getFirstChild();  
            String chartType = classNode.getNodeValue().trim();  
            return chartType;  
        }     
        catch(Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }


    /**
     * 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
     * @return
     */
    public static Object getBean(String resourcesPath) {
        try {
            //创建DOM文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File(XMLUtil.class.getResource("/").getPath()+ File.separator + resourcesPath));
            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode=nl.item(0).getFirstChild();
            String cName=classNode.getNodeValue();
            //通过类名生成实例对象并将其返回
            Class c=Class.forName(cName);
            Object obj=c.newInstance();
            return obj;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public static void main(String[] args) {
        System.out.println(XMLUtil.getBean("creational/factorymethod/config.xml"));
    }*/
}