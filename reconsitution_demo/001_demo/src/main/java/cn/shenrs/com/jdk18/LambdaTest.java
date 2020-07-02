package cn.shenrs.com.jdk18;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author shenrs
 * @Description
 * @create 2019-12-10 13:25
 **/
public class LambdaTest {

    interface Person{
        String eat(String name);
    }

    public static void main(String[] args) {
        //匿名内部类
        /*Comparator<Integer> cpt = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };*/

        Comparator<Integer> cpt2 = (x, y) -> Integer.compare(x, y);
        Set<Integer> set = new TreeSet<>(cpt2);
        System.out.println(cpt2.compare(5, 4));

        Person person = (name) ->{
            String action = name+"在吃饭";
            System.out.println(action);
            return action;
        };
        person.eat("张三");
    }


    interface MyPredicate<T>{
        boolean test(T t);
    }

    public List<Product> filterProductByPredicate(List<Product> list, MyPredicate<Product> mp){
        List<Product> prods = new ArrayList<>();
        for (Product prod : list){
            if (mp.test(prod)){
                prods.add(prod);
            }
        }
        return prods;
    }

    @Test
    public void test4(){
        List<Product> proList =
                Arrays.asList(new Product(),new Product(),new Product(),new Product(),new Product());
        List<Product> products = filterProductByPredicate(proList, (p) -> p.getPrice() < 8000);
        for (Product pro : products){
            System.out.println(pro);
        }
    }



}
