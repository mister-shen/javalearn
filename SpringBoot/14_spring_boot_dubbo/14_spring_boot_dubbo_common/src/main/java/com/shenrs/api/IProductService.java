package com.shenrs.api;

import com.shenrs.bean.Product;

import java.util.List;

/**
 * @author shenrs
 * @Description 产品服务接口类
 * @create 2019-04-28 15:03
 **/
public interface IProductService {

    Product queryProductById(int id);

    List<Product> queryAllProducts();
}
