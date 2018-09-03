package com.shenrs;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author shenrs
 * @Description 使用dom4j与反射实现 spring的getBean功能
 * @create 2018-09-03 16:05
 **/
public class ClassPathXmlApplicationContext {
    public static String PATH;
    public static String ID;
    public static String CLAZZ;
    public static String NAME;
    public static String VLAUE;

    public ClassPathXmlApplicationContext(String path) {
        this.PATH = path;
    }

    public void init (){
        ID = "id";
        CLAZZ = "class";
        NAME = "name";
        VLAUE = "value";
    }

    public Object getBean(String beanid) throws DocumentException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        init();
        // 1.解析xml
        if (StringUtils.isEmpty(beanid)){
            return null;
        }
        SAXReader reader = new SAXReader();
        Document read = reader.read(this.getClass().getClassLoader().getResource(PATH));
        Element rootElement = read.getRootElement();
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            // 2.使用beanid查找对应的xml节点，获取class节点属性。
            String id = element.attributeValue(ID);
            if(!beanid.equals(id)){
                // 结束本次循环
                continue;
            }
            // 3.使用java的反射机制初始化类
            String clazz = element.attributeValue(CLAZZ);
            Class<?> forName = Class.forName(clazz);
            Constructor<?> constructor = forName.getDeclaredConstructor();
            Object newInstance = constructor.newInstance();

            // 4.使用java的反射机制给私有属性赋值
            List<Element> elementsList = element.elements();
            for (Element el : elementsList) {
                // 获取配置文件属性名称
                String attrName = el.attributeValue(NAME);
                String attrValue = el.attributeValue(VLAUE);
                Field declaredField = forName.getDeclaredField(attrName);
                declaredField.setAccessible(true);
                declaredField.set(newInstance, attrValue);
            }
            return newInstance;

        }

        return null;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
        try {
            Object user1 = context.getBean("user2");
            System.out.println(user1);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
