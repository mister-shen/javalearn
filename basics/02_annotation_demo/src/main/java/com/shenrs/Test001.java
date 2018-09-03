package com.shenrs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * 注解是对应表的关联
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table {
    String  value();
}

/**
 * 属性对应的列
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Propety{
    String value();
    int len() default 0;
}

/**
 * 学生实体类
 */
@Table("shenrs_student")
class Student {
    @Propety(value = "student_id", len = 10)
    private String studentId;

    @Propety(value = "student_name")
    private String studentName;

    @Propety(value = "student_age")
    private String studentAge;
}

/**
 * 自定义注解实现CRM
 */
public class Test001 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> forName = Class.forName("com.shenrs.Student");

        // 获取类上面的注解
        Table table = forName.getDeclaredAnnotation(Table.class);

        StringBuffer sb = new StringBuffer();
        sb.append(" select ");
        // 获取所有当前的成员变量
        Field[] declaredFields = forName.getDeclaredFields();
        for (Field field : declaredFields) {
            Propety propety = field.getAnnotation(Propety.class);
            sb.append( propety.value() + ", ");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append( " from " + table.value());
        System.out.println(sb.toString());
    }
}
