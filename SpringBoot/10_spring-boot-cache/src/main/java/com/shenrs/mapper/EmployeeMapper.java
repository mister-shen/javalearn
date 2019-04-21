package com.shenrs.mapper;

import com.shenrs.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-29 16:03
 **/
@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Options(useGeneratedKeys = true, keyColumn = "id")
    @Insert("INSERT INTO employee(lastName, email, gender, d_id) VALUES(#{lastName}, #{email}, #{gender}, #{dId})")
    public void insertEmp(Employee employee);

    @Insert("update employee set lastName= #{lastName}, email = #{email}, gender = #{gender}, d_id = #{dId} where id = #{id}")
    public void updateEmp(Employee employee);

    @Delete("delete from employee where id = #{id}")
    public void deleteEmp(Integer id);

    @Select("select * from employee where lastName = #{lastName}")
    public Employee getEmpByLastName(String lastName);

}
