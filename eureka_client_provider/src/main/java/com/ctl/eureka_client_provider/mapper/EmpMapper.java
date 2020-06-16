package com.ctl.eureka_client_provider.mapper;

import com.ctl.eureka_client_provider.entity.emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpMapper {
    @Select("select * from emp where id=#{id}")
   public emp getEmpList(Integer id);
}
