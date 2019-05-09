package com.shenrs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shenrs.api.IProductService;
import com.shenrs.bean.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 产品提供接口实现类
 */
@Service(timeout = 5000)
public class ProductService implements IProductService {

    private static List<Product> productList = new ArrayList<Product>();

    static {
        for (int i = 0; i < 20; i++) {
            productList.add(new Product(i, "产品" + i, i / 2 == 0, new Date(), 66.66f * i));
        }
    }

    public Product queryProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> queryAllProducts() {
        return productList;
    }
}