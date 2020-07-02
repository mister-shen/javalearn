package cn.shenrs.com.movielease;

import sun.applet.Main;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shenrs
 * @Description
 * @create 2019-12-10 10:39
 **/
public class Test {


    public static void main(String[] args) {

//        IntStream.range(0,10).forEach(i->{
//            new Thread(()-> {
//                System.out.println("111");
//                System.out.println(Thread.currentThread().getId());
//            }).start();
//        });
//
//        Function function = (k)->String.valueOf(k);
//
//        System.out.println(function.apply(55556) instanceof String);

//        List<MyList> list = new ArrayList<>();
//        IntStream.range(0,10).forEach(i->{
//            MyList myList = new MyList();
//            myList.setId(i+"");
//            myList.setName("xxxx");
//            myList.setAge((int) (Math.random() * 10));
//            list.add(myList);
//        });
//        Map<Integer, List<MyList>> collect = list.stream().collect(Collectors.groupingBy(MyList::getAge));
//        System.out.println(collect);
//        String xx = null;
////        String xxx = xx ?
//        String xxxxxx = Optional.ofNullable(xx).map(x -> {
//            //
//                    return "xxx";
//        }).orElse("xxxxxx");

        //
        String 你好 = String.format("asdasd%s", "你好");
        System.out.println(你好);


    }


}
