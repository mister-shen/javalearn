package com.shenrs.mapper;

import com.shenrs.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @author shenrs
 * @Description 指定这是一个操作数据库的mapper
 * @create 2019-03-29 15:13
 **/
@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id = #{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id = #{id}")
    public int delDeptById(Integer id);

    // 使用自增主键，并把生成的主键返回给Department对象
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO department (department_name) VALUES (#{departmentName})")
    public int insertDept(Department department);

    @Update("update department  set department_name = #{departmentName} where id = #{id}")
    public int updateDept(Department department);
}
