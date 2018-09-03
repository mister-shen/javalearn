package com.shenrs;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射测试实体类
 */
class ReflectEntity {
    private String id;
    private String name;
    public String sex;

    public ReflectEntity() {
    }

    private ReflectEntity(String id) {
        this.id = id;
    }

    public ReflectEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public int print(){
        System.out.println("公共方法反射执行方法---id: " + this.id + ", name: " + this.name);
        return 2018;
    }

    public void privateprint(){
        System.out.println("私有方法反射执行方法---id: " + this.id + ", name: " + this.name);
    }

    @Override
    public String toString() {
        return "ReflectEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

/**
 * @author shenrs
 * @Description 使用反射实例化对象
 * @create 2018-09-03 13:09
 **/
public class Test003 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        // 1.加载Class对象
        // 获取class对象的三种方式
//        Class<?> clazz = ReflectEntity.class; // 方式一
//        Class<?> clazz = new ReflectEntity().getClass(); // 方式二
        Class<?> clazz = Class.forName("com.shenrs.ReflectEntity"); // 方式三

        // 2.实例化对象
        ReflectEntity entity = (ReflectEntity) clazz.newInstance();// 实例化公共空构造方法

        // 操作成员变量
        Field[] declaredFields = clazz.getDeclaredFields(); // 获取所有成员变量（包含私有变量）
        for (Field field : declaredFields) {
            field.setAccessible(true); // 由于存在私有成员变量，因此要加此设置
            field.set(entity, Math.random()*10+"");
        }

        // 操作公共成员变量
        Field sex = clazz.getField("sex");
        sex.set(entity, "女");

        //操作私有成员变量
        Field id = clazz.getDeclaredField("id");
        id.setAccessible(true);
        id.set(entity, "20");

        System.out.println(entity);

        // 操作公共方法
        Method print = clazz.getMethod("print");
        Object returnValue = print.invoke(entity);// 接收返回值
        System.out.println("接收的返回值：" +returnValue);

        // 操作私有方法
        Method privateprint = clazz.getDeclaredMethod("privateprint");
        privateprint.setAccessible(true);
        privateprint.invoke(entity);

        /*Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class); // 实例化私有构造方法
        declaredConstructor.setAccessible(true);//构造方法是private,故必须进行此操作
        ReflectEntity entity = (ReflectEntity) declaredConstructor.newInstance("23");
        System.out.println(entity);*/

        /*Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
        ReflectEntity entity = (ReflectEntity) constructor.newInstance("12", "22");// 实例化公共有参构造方法
        entity.sex = "女";
        System.out.println(entity);*/

       /* Constructor<?>[] constructors = clazz.getConstructors();// 获取所有公有的构造方法
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors(); // 获取所有的构造方法（包括私有方法）
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println(constructor);
        }*/


    }
}
