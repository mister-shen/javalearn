package com.shenrs.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shenrs
 * @Description
 * @create 2019-04-28 15:04
 **/

/**
 * @Data 生成以下方法
 * Getter
 * Setter
 * RequiredArgsConstructor 会生成一个包含常量，和标识了NotNull的变量的构造方法。生成的构造方法是私有的private
 * ToString
 * EqualsAndHashCode
 */
@Data
@AllArgsConstructor/*会生成一个包含所有变量的构造方法*/
@NoArgsConstructor/*生成一个无参数的构造方法*/
public class Product implements Serializable{
    // 产品序列号
    private int id;

    // 产品名称
    private String name;

    // 是否贵重品
    private Boolean isPrecious;

    //生产日期
    private Date dateInProduced;

    //产品价格
    private float price;
}
