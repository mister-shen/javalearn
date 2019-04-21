package com.shenrs.repository;

import com.shenrs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-29 16:57
 **/
public interface UserRepository extends JpaRepository<User, Integer>{
}
