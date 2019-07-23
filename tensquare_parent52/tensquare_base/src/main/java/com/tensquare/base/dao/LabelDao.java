package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author shenrs
 * @Description
 * @create 2019-07-23 15:22
 **/
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label>{
}
